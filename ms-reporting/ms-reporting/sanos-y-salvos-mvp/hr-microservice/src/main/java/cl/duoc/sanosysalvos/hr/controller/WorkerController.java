package cl.duoc.sanosysalvos.hr.controller;

import cl.duoc.sanosysalvos.hr.entity.Worker;
import cl.duoc.sanosysalvos.hr.repository.WorkerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workers")
public class WorkerController {

    private final WorkerRepository repository;

    public WorkerController(WorkerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> getWorker(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
