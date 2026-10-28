package pe.upeu.ecorecicla.servicio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pe.upeu.ecorecicla.dominio.PuntoAcopio;
import pe.upeu.ecorecicla.repositorio.PuntoAcopioRepository;

@Service
public class PuntoAcopioServiceImpl implements PuntoAcopioService {

    private final PuntoAcopioRepository puntoAcopioRepository;

    public PuntoAcopioServiceImpl(PuntoAcopioRepository puntoAcopioRepository) {
        this.puntoAcopioRepository = puntoAcopioRepository;
    }

    @Override
    public Page<PuntoAcopio> listarPuntos(String zona, Pageable pageable) {
        return puntoAcopioRepository.findByZonaOptional(zona, pageable);
    }
}