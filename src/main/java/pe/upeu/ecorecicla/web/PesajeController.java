package pe.upeu.ecorecicla.web;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pe.upeu.ecorecicla.dominio.Pesaje;
import pe.upeu.ecorecicla.servicio.PesajeService;
import pe.upeu.ecorecicla.web.dto.PesajeRegistroRequest;
import pe.upeu.ecorecicla.web.dto.RespuestaPaginada;

@RestController
@RequestMapping("/api/v1/pesajes")
public class PesajeController {

    private final PesajeService pesajeService;

    public PesajeController(PesajeService pesajeService) {
        this.pesajeService = pesajeService;
    }

    // Exige privilegio de LECTURA
    @GetMapping("/punto/{puntoId}")
    @PreAuthorize("hasAuthority('PESAJE_LEER')")
    public ResponseEntity<RespuestaPaginada<Pesaje>> listarPorPunto(
            @PathVariable Long puntoId,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "20") int tamanio) {

        Pageable pageable = PageRequest.of(pagina, tamanio);
        Page<Pesaje> paginaPesajes = pesajeService.listarPorPunto(puntoId, pageable);

        RespuestaPaginada<Pesaje> respuesta = new RespuestaPaginada<>(
                paginaPesajes.getContent(),
                paginaPesajes.getNumber(),
                paginaPesajes.getSize(),
                paginaPesajes.getTotalElements(),
                paginaPesajes.getTotalPages(),
                paginaPesajes.isLast()
        );

        return ResponseEntity.ok(respuesta);
    }

    // Exige privilegio de ESCRITURA
    @PostMapping
    @PreAuthorize("hasAuthority('PESAJE_CREAR')")
    public ResponseEntity<?> registrarPesaje(@Valid @RequestBody PesajeRegistroRequest request, Authentication authentication) {
        try {
            // Pasamos el username extraído del token JWT (el Authentication ya lo resolvió)
            String usernameOperario = authentication.getName();
            Pesaje nuevoPesaje = pesajeService.registrarPesaje(usernameOperario, request);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPesaje);
        } catch (IllegalArgumentException e) {
            // Captura la RN-03 y devuelve HTTP 400 Bad Request
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}