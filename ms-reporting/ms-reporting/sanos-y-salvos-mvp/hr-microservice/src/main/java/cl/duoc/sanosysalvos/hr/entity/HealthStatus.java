package cl.duoc.sanosysalvos.hr.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "health_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String status; // e.g., APTO, APTO CON RESTRICCIONES, NO APTO

    private String medicalRestrictions; // Detalle de restricciones

    private LocalDate lastCheckupDate;

    @Column(nullable = false)
    private Boolean fatigueRisk; // Riesgo de fatiga detectado
}
