package pe.upeu.ecorecicla.servicio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.upeu.ecorecicla.dominio.Pesaje;
import pe.upeu.ecorecicla.web.dto.PesajeRegistroRequest;

public interface PesajeService {
    // Listar
    Page<Pesaje> listarPorPunto(Long puntoId, Pageable pageable);
    // Registrar (Calculando el Neto y aplicando RN-03)
    Pesaje registrarPesaje(String usernameOperario, PesajeRegistroRequest request);
}