package pe.upeu.ecorecicla.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.upeu.ecorecicla.dominio.PuntoAcopio;git add .
import pe.upeu.ecorecicla.servicio.PuntoAcopioService;
import pe.upeu.ecorecicla.web.dto.RespuestaPaginada;

@RestController
@RequestMapping("/api/v1/puntos")
public class PuntoAcopioController {

    private final PuntoAcopioService puntoAcopioService;

    public PuntoAcopioController(PuntoAcopioService puntoAcopioService) {
        this.puntoAcopioService = puntoAcopioService;
    }

    // Exige el privilegio PUNTO_LEER de la matriz
    @GetMapping
    @PreAuthorize("hasAuthority('PUNTO_LEER')")
    public ResponseEntity<RespuestaPaginada<PuntoAcopio>> listarPuntos(
            @RequestParam(required = false) String zona,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "20") int tamanio) {

        Pageable pageable = PageRequest.of(pagina, tamanio);
        Page<PuntoAcopio> paginaPuntos = puntoAcopioService.listarPuntos(zona, pageable);

        RespuestaPaginada<PuntoAcopio> respuesta = new RespuestaPaginada<>(
                paginaPuntos.getContent(),
                paginaPuntos.getNumber(),
                paginaPuntos.getSize(),
                paginaPuntos.getTotalElements(),
                paginaPuntos.getTotalPages(),
                paginaPuntos.isLast()
        );

        return ResponseEntity.ok(respuesta);
    }
}