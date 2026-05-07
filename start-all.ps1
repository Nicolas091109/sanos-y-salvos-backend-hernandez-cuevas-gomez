$ErrorActionPreference = "Stop"

$projectRoot = Split-Path -Parent $MyInvocation.MyCommand.Path
$identityPath = Join-Path $projectRoot "ms-identity"
$reportingPath = Join-Path $projectRoot "ms-reporting"

# --- CONFIGURACIÓN MANUAL FORZADA ---
$env:IDENTITY_DB_URL = "jdbc:postgresql://localhost:5432/sanos_y_salvos_identity"
$env:IDENTITY_DB_USERNAME = "postgres"
$env:IDENTITY_DB_PASSWORD = "root"  # <--- Cambiado de 'postgres' a 'root'
$env:REPORTING_MONGODB_URI = "mongodb://localhost:27017/ms-reporting_db"
# ------------------------------------

$identityCommand = @"
`$env:IDENTITY_DB_URL = '$($env:IDENTITY_DB_URL)'
`$env:IDENTITY_DB_USERNAME = '$($env:IDENTITY_DB_USERNAME)'
`$env:IDENTITY_DB_PASSWORD = '$($env:IDENTITY_DB_PASSWORD)'
Set-Location '$identityPath'
.\mvnw.cmd spring-boot:run
"@

$reportingCommand = @"
`$env:REPORTING_MONGODB_URI = '$($env:REPORTING_MONGODB_URI)'
Set-Location '$reportingPath'
.\mvnw.cmd spring-boot:run
"@

# Lanzar procesos
Start-Process powershell -WorkingDirectory $identityPath -ArgumentList "-NoExit", "-ExecutionPolicy", "Bypass", "-Command", $identityCommand
Start-Process powershell -WorkingDirectory $reportingPath -ArgumentList "-NoExit", "-ExecutionPolicy", "Bypass", "-Command", $reportingCommand

Write-Host "✅ Variables configuradas con contraseña ROOT." -ForegroundColor Green
Write-Host "🚀 Microservicios lanzados."