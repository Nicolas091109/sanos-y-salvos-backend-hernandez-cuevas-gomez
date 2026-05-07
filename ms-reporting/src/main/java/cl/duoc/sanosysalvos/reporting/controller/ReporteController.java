package cl.duoc.sanosysalvos.reporting.controller;

import cl.duoc.sanosysalvos.reporting.model.ReporteMascota;
import cl.duoc.sanosysalvos.reporting.service.ReporteService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteService reporteService;

    @PostMapping
    public ResponseEntity<ReporteMascota> crearReporte(@Valid @RequestBody ReporteMascota reporteMascota) {
        ReporteMascota reporteCreado = reporteService.crearReporte(reporteMascota);
        return ResponseEntity.status(HttpStatus.CREATED).body(reporteCreado);
    }

    @GetMapping
    public ResponseEntity<List<ReporteMascota>> listarReportes() {
        return ResponseEntity.ok(reporteService.listarReportes());
    }
}
