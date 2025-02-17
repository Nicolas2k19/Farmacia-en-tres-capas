package farmacia.repository.interfaces;

import java.util.List;

import farmacia.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {

    public int countByNombre(String nombre);

    public List<Producto> findAllByOrderByNombreAsc();

    public List<Producto> findAllByOrderByNombreDesc();
}
