package cl.duoc.sanosysalvos.bff.controller;

import cl.duoc.sanosysalvos.bff.dto.PreventiveCapacityDTO;
import cl.duoc.sanosysalvos.bff.service.PreventiveCapacityService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/preventive-capacity")
public class PreventiveCapacityController {

    private final PreventiveCapacityService capacityService;

    public PreventiveCapacityController(PreventiveCapacityService capacityService) {
        this.capacityService = capacityService;
    }

    @GetMapping("/worker/{id}")
    public Mono<PreventiveCapacityDTO> getWorkerPreventiveStatus(@PathVariable Long id) {
        return capacityService.calculatePreventiveCapacity(id);
    }
}
