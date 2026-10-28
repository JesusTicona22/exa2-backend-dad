package pe.upeu.ecorecicla.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.upeu.ecorecicla.seguridad.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}