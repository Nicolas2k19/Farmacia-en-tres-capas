package farmacia.interfaces;

import java.util.List;

import farmacia.domain.Producto;

public interface ProductoRepository {
	public List<Producto> findAll();
}
