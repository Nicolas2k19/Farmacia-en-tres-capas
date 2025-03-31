package farmacia.repository.interfaces;

import farmacia.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    public Usuario findFirstBynombre(String username);
}
