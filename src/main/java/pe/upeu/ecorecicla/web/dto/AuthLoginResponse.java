package pe.upeu.ecorecicla.web.dto;

import java.util.List;

public class AuthLoginResponse {
    private String token;
    private String nombre;
    private List<String> privilegios;

    public AuthLoginResponse(String token, String nombre, List<String> privilegios) {
        this.token = token;
        this.nombre = nombre;
        this.privilegios = privilegios;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public List<String> getPrivilegios() { return privilegios; }
    public void setPrivilegios(List<String> privilegios) { this.privilegios = privilegios; }
}