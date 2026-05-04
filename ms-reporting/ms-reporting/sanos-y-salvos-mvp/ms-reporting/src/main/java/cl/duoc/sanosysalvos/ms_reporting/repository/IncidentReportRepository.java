package cl.duoc.sanosysalvos.ms_reporting.repository;

import cl.duoc.sanosysalvos.ms_reporting.model.IncidentReport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentReportRepository extends MongoRepository<IncidentReport, String> {
    List<IncidentReport> findByWorkerId(String workerId);
    List<IncidentReport> findByStatus(String status);
}
