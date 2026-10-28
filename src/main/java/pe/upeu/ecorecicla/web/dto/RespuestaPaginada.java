package pe.upeu.ecorecicla.web.dto;

import java.util.List;

public class RespuestaPaginada<T> {
    private List<T> contenido;
    private int pagina;
    private int tamanio;
    private long totalElementos;
    private int totalPaginas;
    private boolean ultima;

    public RespuestaPaginada(List<T> contenido, int pagina, int tamanio, long totalElementos, int totalPaginas, boolean ultima) {
        this.contenido = contenido;
        this.pagina = pagina;
        this.tamanio = tamanio;
        this.totalElementos = totalElementos;
        this.totalPaginas = totalPaginas;
        this.ultima = ultima;
    }

    public List<T> getContenido() { return contenido; }
    public int getPagina() { return pagina; }
    public int getTamanio() { return tamanio; }
    public long getTotalElementos() { return totalElementos; }
    public int getTotalPaginas() { return totalPaginas; }
    public boolean isUltima() { return ultima; }
}