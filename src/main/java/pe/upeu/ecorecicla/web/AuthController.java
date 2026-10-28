package pe.upeu.ecorecicla.web;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import pe.upeu.ecorecicla.seguridad.Privilegio;
import pe.upeu.ecorecicla.seguridad.Usuario;
import pe.upeu.ecorecicla.seguridad.config.JwtService;
import pe.upeu.ecorecicla.web.dto.AuthLoginRequest;
import pe.upeu.ecorecicla.web.dto.AuthLoginResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthLoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            Usuario usuario = (Usuario) authentication.getPrincipal();

            // Extraer la lista de nombres de privilegios en base a la matriz asignada
            List<String> privilegiosStr = usuario.getRol().getPrivilegios().stream()
                    .map(Privilegio::name)
                    .collect(Collectors.toList());

            // Generar el token inyectando el username (nombre) y los privilegios en los claims
            String token = jwtService.generateToken(usuario, usuario.getUsername(), privilegiosStr);

            return ResponseEntity.ok(new AuthLoginResponse(token, usuario.getUsername(), privilegiosStr));

        } catch (AuthenticationException e) {
            // El ManejadorDeErrores o Spring Security capturará este error y devolverá un 401 Unauthorized
            throw new RuntimeException("Credenciales incorrectas");
        }
    }
}