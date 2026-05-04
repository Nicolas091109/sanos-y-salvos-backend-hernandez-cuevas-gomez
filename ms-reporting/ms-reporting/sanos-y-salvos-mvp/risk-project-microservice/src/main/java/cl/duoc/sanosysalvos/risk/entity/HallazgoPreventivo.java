package cl.duoc.sanosysalvos.risk.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "hallazgos_preventivos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HallazgoPreventivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String severity; // LEVE, MODERADO, GRAVE

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    private Long reportedByWorkerId;

    @Column(nullable = false)
    private LocalDateTime foundAt;

    @Column(nullable = false)
    private String status; // ABIERTO, EN_REVISION, CERRADO
}
