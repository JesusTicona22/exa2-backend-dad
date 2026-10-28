package pe.upeu.ecorecicla.dominio;

import jakarta.persistence.*;

@Entity
@Table(name = "puntos_acopio")
public class PuntoAcopio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String razonSocial;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String zona;

    @Column(nullable = false)
    private String materialHabitual;

    @Column(nullable = false)
    private Integer secuencia;

    @Column(nullable = false)
    private Boolean atendido = false;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRazonSocial() { return razonSocial; }
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getZona() { return zona; }
    public void setZona(String zona) { this.zona = zona; }
    public String getMaterialHabitual() { return materialHabitual; }
    public void setMaterialHabitual(String materialHabitual) { this.materialHabitual = materialHabitual; }
    public Integer getSecuencia() { return secuencia; }
    public void setSecuencia(Integer secuencia) { this.secuencia = secuencia; }
    public Boolean getAtendido() { return atendido; }
    public void setAtendido(Boolean atendido) { this.atendido = atendido; }
}