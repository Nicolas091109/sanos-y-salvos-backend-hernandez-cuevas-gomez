package cl.duoc.sanosysalvos.ms_reporting.service;

import cl.duoc.sanosysalvos.ms_reporting.model.IncidentReport;
import cl.duoc.sanosysalvos.ms_reporting.repository.IncidentReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IncidentReportService {

    @Autowired(required = false)
    private IncidentReportRepository repository;

    public List<IncidentReport> getAllReports() {
        if (repository == null) return new ArrayList<>();
        return repository.findAll();
    }

    public Optional<IncidentReport> getReportById(String id) {
        if (repository == null) return Optional.empty();
        return repository.findById(id);
    }

    public IncidentReport createReport(IncidentReport report) {
        report.setCreatedAt(LocalDateTime.now());
        report.setUpdatedAt(LocalDateTime.now());
        if (report.getStatus() == null) {
            report.setStatus("ABIERTO");
        }
        if (repository == null) return report;
        return repository.save(report);
    }

    public List<IncidentReport> getReportsByWorker(String workerId) {
        if (repository == null) return new ArrayList<>();
        return repository.findByWorkerId(workerId);
    }
}
