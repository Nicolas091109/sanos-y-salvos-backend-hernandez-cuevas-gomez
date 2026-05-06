package cl.duoc.sanosysalvos.hr.repository;

import cl.duoc.sanosysalvos.hr.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
