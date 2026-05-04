# Arquitectura del Sistema "Sanos y Salvos" - Duoc UC

Este documento detalla la base técnica del MVP enfocado en la Gestión de Capacidad Preventiva (SSO).

## 1. Estructura de Microservicios (cl.duoc.sanosysalvos)

```text
/sanos-y-salvos-mvp
   ├── /bff-service (BFF - Orquestador Reactivo)
   ├── /hr-microservice (Micro A - RRHH y Salud)
   ├── /risk-project-microservice (Micro B - Proyectos y Riesgos)
   └── /ms-reporting (Micro C - Reportes e Incidentes - NoSQL)
```

## 2. Lógica de "Capacidad Preventiva"

El sistema implementa una lógica de protección al trabajador:
- **Disponibilidad:** Un trabajador se marca como `No Disponible` para proyectos de `ALTO RIESGO` si:
    - Su estado de salud no es `APTO`.
    - Presenta `Riesgo de Fatiga` (detectado por medicina ocupacional).
    - Ha superado sus `Horas Hombre Máximas` semanales permitidas.

## 3. Estrategia de Git Flow

Para garantizar un flujo colaborativo sin conflictos, el equipo utiliza:

- **`main`**: Rama productiva. Contiene el código estable del MVP.
- **`develop`**: Rama de integración. Aquí se fusionan las funcionalidades terminadas.
- **`feature/`**: Ramas de desarrollo por tarea.
    - `feature/hr-entities`: Definición de modelos de salud.
    - `feature/risk-calculation`: Lógica de índices de accidentabilidad.
    - `feature/bff-adapter`: Implementación del patrón Adapter en el orquestador.
- **`hotfix/`**: Correcciones urgentes sobre `main`.

## 4. Patrones de Diseño Aplicados

- **BFF (Backend For Frontend):** Unifica la visión para el prevencionista de riesgos, abstrayendo la complejidad de múltiples microservicios.
- **DTO (Data Transfer Object):** Desacopla las entidades de base de datos de los contratos de la API.
- **Adapter:** El BFF adapta los datos crudos de RRHH y Riesgos para generar el reporte consolidado de Capacidad Preventiva.

## 5. Optimización y Persistencia

- **Stored Procedures:** Se utiliza el procedimiento `calculate_accident_index` para procesar grandes volúmenes de datos directamente en el motor relacional.
- **Persistencia NoSQL:** El microservicio `ms-reporting` incorpora MongoDB para el almacenamiento de hallazgos preventivos complejos, permitiendo una evolución flexible de los datos de seguridad.

## 6. Stack Tecnológico de Vanguardia

- **Java 21:** Aprovechamos las últimas mejoras del lenguaje para un rendimiento superior.
- **Spring Boot 3.2.5:** Versión estable que garantiza compatibilidad con el ecosistema moderno.
- **Maven Wrapper:** Asegura que cualquier desarrollador pueda compilar el proyecto sin instalar Maven localmente.
- **MongoDB:** Proporciona la flexibilidad necesaria para reportes de incidentes que varían según el tipo de industria.

## 7. Guía de Integración para el Equipo

Para que el grupo trabaje de forma colaborativa:
1. **Clonar Repositorio:** Asegurarse de clonar el monorepo completo.
2. **Maven Wrapper:** Usar `./mvnw` para compilar. No es necesario instalar Maven localmente.
3. **Base de Datos:**
    - Micro A y B: Requieren PostgreSQL.
    - Micro C (Reporting): Requiere MongoDB.
4. **Git Flow:** Crear ramas `feature/nombre-tarea` desde `develop` y realizar Pull Requests.
