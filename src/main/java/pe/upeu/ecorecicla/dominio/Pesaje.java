package pe.upeu.ecorecicla.dominio;

import jakarta.persistence.*;

@Entity
@Table(name = "pesajes")
public class Pesaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "punto_id", nullable = false)
    private PuntoAcopio punto;

    @Column(nullable = false)
    private String fechaHora;

    @Column(nullable = false)
    private String material;

    @Column(nullable = false)
    private Double pesoBruto;

    @Column(nullable = false)
    private Double tara;

    @Column(nullable = false)
    private Double pesoNeto;

    @Column(nullable = false)
    private String operario;

    @Column(nullable = false)
    private String estado; // "Registrado" o "Anulado"

    @Column
    private String motivoAnulacion;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public PuntoAcopio getPunto() { return punto; }
    public void setPunto(PuntoAcopio punto) { this.punto = punto; }
    public String getFechaHora() { return fechaHora; }
    public void setFechaHora(String fechaHora) { this.fechaHora = fechaHora; }
    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
    public Double getPesoBruto() { return pesoBruto; }
    public void setPesoBruto(Double pesoBruto) { this.pesoBruto = pesoBruto; }
    public Double getTara() { return tara; }
    public void setTara(Double tara) { this.tara = tara; }
    public Double getPesoNeto() { return pesoNeto; }
    public void setPesoNeto(Double pesoNeto) { this.pesoNeto = pesoNeto; }
    public String getOperario() { return operario; }
    public void setOperario(String operario) { this.operario = operario; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getMotivoAnulacion() { return motivoAnulacion; }
    public void setMotivoAnulacion(String motivoAnulacion) { this.motivoAnulacion = motivoAnulacion; }
}