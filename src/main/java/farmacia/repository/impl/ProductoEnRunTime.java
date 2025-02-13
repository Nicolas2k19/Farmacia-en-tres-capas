package farmacia.repository.impl;

import java.util.List;

import farmacia.domain.Producto;
import farmacia.repository.interfaces.ProductoRepository;

public class ProductoEnRunTime implements ProductoRepository {

	private List<Producto> listaDeProducto;

	public ProductoEnRunTime(List<Producto> listaDeProducto) {
		this.listaDeProducto = listaDeProducto;
	}

	@Override
	public List<Producto> findAll() {
		// TODO Auto-generated method stub
		return listaDeProducto;
	}

	@Override
	public void save(Producto nuevoProducto) {
		listaDeProducto.add(nuevoProducto);
	}

	@Override
	public void delete(Producto nuevoProducto) {
		listaDeProducto.remove(nuevoProducto);
	}
}
