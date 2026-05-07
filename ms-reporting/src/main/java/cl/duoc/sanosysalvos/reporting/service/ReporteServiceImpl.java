package cl.duoc.sanosysalvos.reporting.service;

import cl.duoc.sanosysalvos.reporting.model.ReporteMascota;
import cl.duoc.sanosysalvos.reporting.repository.ReporteRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReporteServiceImpl implements ReporteService {

    private final ReporteRepository reporteRepository;

    @Override
    public ReporteMascota crearReporte(ReporteMascota reporteMascota) {
        if (reporteMascota.getEstado() == null || reporteMascota.getEstado().isBlank()) {
            reporteMascota.setEstado("ACTIVO");
        }
        return reporteRepository.save(reporteMascota);
    }

    @Override
    public List<ReporteMascota> listarReportes() {
        return reporteRepository.findAll();
    }
}
