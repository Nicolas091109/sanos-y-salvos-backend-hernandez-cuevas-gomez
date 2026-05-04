package cl.duoc.sanosysalvos.hr.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "workers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String position;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "health_status_id", referencedColumnName = "id")
    private HealthStatus healthStatus;

    @Column(nullable = false)
    private Integer maxWeeklyHours; // Horas hombre permitidas según contrato/ley

    @Column(nullable = false)
    private Integer currentWeeklyHours;
}
