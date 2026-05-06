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
public class CapacityDTO {
    private SummaryDTO summary;
    private List<ResourceDTO> resources;

    @Data
    @Builder
    public static class SummaryDTO {
        private Integer totalResources;
        private Integer totalCapacityHours;
        private Integer allocatedHours;
        private Double overallUtilization;
    }

    @Data
    @Builder
    public static class ResourceDTO {
        private Long id;
        private String fullName;
        private List<String> skills;
        private Integer totalCapacity;
        private Integer allocatedHours;
        private Double utilization;
    }
}
