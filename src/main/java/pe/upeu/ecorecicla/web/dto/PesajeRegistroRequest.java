package pe.upeu.ecorecicla.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class PesajeRegistroRequest {

    @NotNull(message = "El ID del punto es obligatorio")
    private Long puntoId;

    @NotBlank(message = "La fecha y hora son obligatorias")
    private String fechaHora;

    @NotBlank(message = "El material es obligatorio")
    private String material;

    @NotNull(message = "El peso bruto es obligatorio")
    @PositiveOrZero(message = "El peso bruto no puede ser negativo")
    private Double pesoBruto;

    @NotNull(message = "La tara es obligatoria")
    @PositiveOrZero(message = "La tara no puede ser negativa")
    private Double tara;

    private String observacion;

    // Getters y Setters obligatorios para que Spring pueda leer el JSON
    public Long getPuntoId() { return puntoId; }
    public void setPuntoId(Long puntoId) { this.puntoId = puntoId; }

    public String getFechaHora() { return fechaHora; }
    public void setFechaHora(String fechaHora) { this.fechaHora = fechaHora; }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    public Double getPesoBruto() { return pesoBruto; }
    public void setPesoBruto(Double pesoBruto) { this.pesoBruto = pesoBruto; }

    public Double getTara() { return tara; }
    public void setTara(Double tara) { this.tara = tara; }

    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
}