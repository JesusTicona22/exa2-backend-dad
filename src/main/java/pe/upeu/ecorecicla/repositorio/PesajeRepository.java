package pe.upeu.ecorecicla.repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.upeu.ecorecicla.dominio.Pesaje;

public interface PesajeRepository extends JpaRepository<Pesaje, Long> {

    // RF-A04: Listado paginado de pesajes de un punto, del más reciente al más antiguo
    Page<Pesaje> findByPuntoIdOrderByFechaHoraDesc(Long puntoId, Pageable pageable);
}