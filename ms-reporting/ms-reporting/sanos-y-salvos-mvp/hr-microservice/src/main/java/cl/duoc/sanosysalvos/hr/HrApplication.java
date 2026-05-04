package cl.duoc.sanosysalvos.hr;

import cl.duoc.sanosysalvos.hr.entity.HealthStatus;
import cl.duoc.sanosysalvos.hr.entity.Worker;
import cl.duoc.sanosysalvos.hr.repository.WorkerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HrApplication {
    public static void main(String[] args) {
        SpringApplication.run(HrApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(WorkerRepository repository) {
        return args -> {
            repository.save(Worker.builder()
                    .fullName("Juan Perez")
                    .position("Operador de Grua")
                    .currentWeeklyHours(45)
                    .maxWeeklyHours(40)
                    .healthStatus(HealthStatus.builder()
                            .status("APTO")
                            .fatigueRisk(true)
                            .build())
                    .build());
            
            repository.save(Worker.builder()
                    .fullName("Maria Lopez")
                    .position("Prevencionista")
                    .currentWeeklyHours(20)
                    .maxWeeklyHours(40)
                    .healthStatus(HealthStatus.builder()
                            .status("APTO")
                            .fatigueRisk(false)
                            .build())
                    .build());
        };
    }
}
