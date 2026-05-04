package cl.duoc.sanosysalvos.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PreventiveCapacityDTO {
    private Long workerId;
    private String workerName;
    private String position;
    private String healthStatus;
    private Boolean availableForHighRisk;
    private String preventionNote;
    private List<RiskAssignmentDTO> currentAssignments;

    @Data
    @Builder
    public static class RiskAssignmentDTO {
        private String projectName;
        private String riskLevel;
        private Integer hours;
    }
}
