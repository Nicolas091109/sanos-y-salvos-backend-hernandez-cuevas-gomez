package cl.duoc.sanosysalvos.ms_reporting.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "incident_reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncidentReport {
    @Id
    private String id;
    private String workerId;
    private String workerName;
    private String description;
    private String severity; // LEVE, MODERADO, GRAVE
    private String status; // ABIERTO, EN_PROCESO, CERRADO
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
