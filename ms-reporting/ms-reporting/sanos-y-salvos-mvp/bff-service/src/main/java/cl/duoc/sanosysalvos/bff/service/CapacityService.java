package cl.duoc.sanosysalvos.bff.service;

import cl.duoc.sanosysalvos.bff.dto.CapacityDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CapacityService {

    private final WebClient webClient;

    public CapacityService(WebClient.Builder webClientBuilder) {
        // En un escenario real, estas URLs vendrían de un Service Discovery o Config Server
        this.webClient = webClientBuilder.build();
    }

    public Mono<CapacityDTO> getDashboardData() {
        // Llamadas concurrentes a los microservicios
        Mono<List<Object>> resourcesMono = webClient.get()
                .uri("http://resource-microservice/api/resources")
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList();

        Mono<List<Object>> assignmentsMono = webClient.get()
                .uri("http://project-microservice/api/assignments")
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList();

        // Orquestación: esperar a ambos resultados y transformar
        return Mono.zip(resourcesMono, assignmentsMono)
                .map(tuple -> {
                    List<Object> resources = tuple.getT1();
                    List<Object> assignments = tuple.getT2();
                    
                    // Aquí iría la lógica de transformación y consolidación (DTO Pattern)
                    return CapacityDTO.builder()
                            .summary(CapacityDTO.SummaryDTO.builder()
                                    .totalResources(resources.size())
                                    .overallUtilization(75.0) // Mock
                                    .build())
                            .build();
                });
    }
}
