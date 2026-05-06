package cl.duoc.sanosysalvos.bff.service;

import cl.duoc.sanosysalvos.bff.dto.PreventiveCapacityDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class PreventiveCapacityService {

    private final WebClient webClient;

    @Value("${services.hr.url}")
    private String hrUrl;

    @Value("${services.risk.url}")
    private String riskUrl;

    public PreventiveCapacityService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<PreventiveCapacityDTO> calculatePreventiveCapacity(Long workerId) {
        // Real calls to microservices
        
        Mono<Map> workerData = webClient.get()
                .uri(hrUrl + "/api/workers/" + workerId)
                .retrieve()
                .bodyToMono(Map.class);

        Mono<List> assignmentsData = webClient.get()
                .uri(riskUrl + "/api/assignments/worker/" + workerId)
                .retrieve()
                .bodyToMono(List.class);

        return Mono.zip(workerData, assignmentsData)
                .map(tuple -> {
                    Map worker = tuple.getT1();
                    List<Map> assignments = tuple.getT2();
                    
                    Map health = (Map) worker.get("healthStatus");
                    Integer currentHours = (Integer) worker.get("currentWeeklyHours");
                    Integer maxHours = (Integer) worker.get("maxWeeklyHours");
                    String healthStatus = (String) health.get("status");
                    Boolean hasFatigueRisk = (Boolean) health.get("fatigueRisk");

                    // LOGICA DE NEGOCIO: Capacidad Preventiva
                    boolean isAvailableForHighRisk = true;
                    String note = "Trabajador apto para tareas asignadas.";

                    if (!"APTO".equalsIgnoreCase(healthStatus)) {
                        isAvailableForHighRisk = false;
                        note = "RESTRICCIÓN: El estado de salud no es óptimo para alto riesgo.";
                    } else if (currentHours >= maxHours || hasFatigueRisk) {
                        isAvailableForHighRisk = false;
                        note = "ALERTA PREVENTIVA: Riesgo de fatiga por exceso de horas o diagnóstico médico.";
                    }

                    return PreventiveCapacityDTO.builder()
                            .workerId(workerId)
                            .workerName((String) worker.get("fullName"))
                            .position((String) worker.get("position"))
                            .healthStatus(healthStatus)
                            .availableForHighRisk(isAvailableForHighRisk)
                            .preventionNote(note)
                            .build();
                });
    }
}
