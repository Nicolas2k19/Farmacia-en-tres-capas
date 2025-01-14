package farmacia.service;

import java.util.Collections;
import java.util.List;

import farmacia.domain.Producto;
import farmacia.enums.Orden;
import farmacia.interfaces.ProductoRepository;

public class ProductoService {

	private ProductoRepository productoRepository;

	public ProductoService(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}

	public List<Producto> obtenerProductos(Orden ordenAlfabetico) {

		if (ordenAlfabetico == null) {
			throw new IllegalArgumentException("Se ingreso un orden nulo.");
		}

		List<Producto> productos = productoRepository.findAll();
		productos.sort(null);

		if (ordenAlfabetico == Orden.DECRECIENTE) {
			Collections.reverse(productos);
		}

		return productos;
	}
}
