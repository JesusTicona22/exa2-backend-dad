package pe.upeu.ecorecicla.repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.upeu.ecorecicla.dominio.PuntoAcopio;

public interface PuntoAcopioRepository extends JpaRepository<PuntoAcopio, Long> {

    // RF-B03: Búsqueda paginada con filtro opcional por zona
    @Query("SELECT p FROM PuntoAcopio p WHERE :zona IS NULL OR p.zona = :zona ORDER BY p.secuencia ASC")
    Page<PuntoAcopio> findByZonaOptional(@Param("zona") String zona, Pageable pageable);
}