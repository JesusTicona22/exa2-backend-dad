package pe.upeu.ecorecicla.seguridad.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import pe.upeu.ecorecicla.web.dto.ErrorRespuesta;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class ManejadorDeErrores implements AuthenticationEntryPoint, AccessDeniedHandler {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        enviarRespuesta(response, 401, "Unauthorized", "Token ausente, alterado o vencido", request.getRequestURI());
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        enviarRespuesta(response, 403, "Forbidden", "No tiene el privilegio necesario para esta accion", request.getRequestURI());
    }

    private void enviarRespuesta(HttpServletResponse response, int status, String error, String message, String path) throws IOException {
        response.setContentType("application/json");
        response.setStatus(status);
        ErrorRespuesta errorRespuesta = new ErrorRespuesta(
                LocalDateTime.now().toString(), status, error, message, path, null
        );
        new ObjectMapper().writeValue(response.getOutputStream(), errorRespuesta);
    }
}