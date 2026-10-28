package pe.upeu.ecorecicla.web.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthLoginRequest {

    @NotBlank(message = "El usuario es obligatorio")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}