package cl.duoc.sanosysalvos.ms_reporting.controller;

import cl.duoc.sanosysalvos.ms_reporting.model.IncidentReport;
import cl.duoc.sanosysalvos.ms_reporting.service.IncidentReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
public class IncidentReportController {

    private final IncidentReportService service;

    public IncidentReportController(IncidentReportService service) {
        this.service = service;
    }

    @GetMapping
    public List<IncidentReport> getAllReports() {
        return service.getAllReports();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncidentReport> getReportById(@PathVariable String id) {
        return service.getReportById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<IncidentReport> createReport(@RequestBody IncidentReport report) {
        IncidentReport created = service.createReport(report);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/worker/{workerId}")
    public List<IncidentReport> getReportsByWorker(@PathVariable String workerId) {
        return service.getReportsByWorker(workerId);
    }
}
