package farmacia.repository.interfaces;

import java.util.List;

import farmacia.domain.Producto;

public interface ProductoRepository {
	public List<Producto> findAll();
	public void save(Producto nuevoProducto);
	public void delete(Producto nuevoProducto);
}
