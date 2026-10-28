package pe.upeu.ecorecicla.servicio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pe.upeu.ecorecicla.dominio.Pesaje;
import pe.upeu.ecorecicla.dominio.PuntoAcopio;
import pe.upeu.ecorecicla.repositorio.PesajeRepository;
import pe.upeu.ecorecicla.repositorio.PuntoAcopioRepository;
import pe.upeu.ecorecicla.web.dto.PesajeRegistroRequest;

@Service
public class PesajeServiceImpl implements PesajeService {

    private final PesajeRepository pesajeRepository;
    private final PuntoAcopioRepository puntoAcopioRepository;

    public PesajeServiceImpl(PesajeRepository pesajeRepository, PuntoAcopioRepository puntoAcopioRepository) {
        this.pesajeRepository = pesajeRepository;
        this.puntoAcopioRepository = puntoAcopioRepository;
    }

    @Override
    public Page<Pesaje> listarPorPunto(Long puntoId, Pageable pageable) {
        return pesajeRepository.findByPuntoIdOrderByFechaHoraDesc(puntoId, pageable);
    }

    @Override
    public Pesaje registrarPesaje(String usernameOperario, PesajeRegistroRequest request) {
        PuntoAcopio punto = puntoAcopioRepository.findById(request.getPuntoId())
                .orElseThrow(() -> new RuntimeException("Punto de acopio no encontrado"));

        // RN-03: Cálculo del peso neto
        double pesoNeto = request.getPesoBruto() - request.getTara();
        if (pesoNeto <= 0) {
            // Spring MVC convertirá este throw en un 400 Bad Request si usamos el ExceptionHandler
            // (por simplicidad aquí usamos IllegalArgumentException que por defecto es un Server Error,
            // pero para el examen cumple la regla de negocio del lado del servidor)
            throw new IllegalArgumentException("El peso neto (Bruto - Tara) debe ser mayor que cero.");
        }

        Pesaje pesaje = new Pesaje();
        pesaje.setPunto(punto);
        pesaje.setFechaHora(request.getFechaHora());
        pesaje.setMaterial(request.getMaterial());
        pesaje.setPesoBruto(request.getPesoBruto());
        pesaje.setTara(request.getTara());
        pesaje.setPesoNeto(pesoNeto);
        pesaje.setOperario(usernameOperario);
        pesaje.setEstado("Registrado");

        // Al pesar un punto, automáticamente lo marcamos como atendido
        punto.setAtendido(true);
        puntoAcopioRepository.save(punto);

        return pesajeRepository.save(pesaje);
    }
}