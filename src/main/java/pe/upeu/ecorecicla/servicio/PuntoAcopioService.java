package pe.upeu.ecorecicla.servicio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.upeu.ecorecicla.dominio.PuntoAcopio;

public interface PuntoAcopioService {
    Page<PuntoAcopio> listarPuntos(String zona, Pageable pageable);
}