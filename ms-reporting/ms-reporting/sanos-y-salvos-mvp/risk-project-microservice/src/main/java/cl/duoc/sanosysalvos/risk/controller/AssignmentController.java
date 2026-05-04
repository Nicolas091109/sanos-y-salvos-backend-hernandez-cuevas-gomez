package cl.duoc.sanosysalvos.risk.controller;

import cl.duoc.sanosysalvos.risk.entity.Assignment;
import cl.duoc.sanosysalvos.risk.repository.AssignmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentRepository repository;

    public AssignmentController(AssignmentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/worker/{workerId}")
    public List<Assignment> getAssignmentsByWorker(@PathVariable Long workerId) {
        return repository.findByWorkerId(workerId);
    }
}
