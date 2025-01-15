package farmacia.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import farmacia.domain.Producto;
import farmacia.domain.enums.Orden;
import farmacia.domain.interfaces.ProductoRepository;

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

	public void ingresarNuevoProducto(Producto nuevoProducto) throws Exception {

		precioValido(nuevoProducto);
		nombreProductoValido(nuevoProducto);

		List<Producto> productosFiltrados = filtrarPorProducto(nuevoProducto);

		if (productosFiltrados != null) {
			productosFiltrados.get(0).sumarStock(nuevoProducto.getStock());

		} else {
			this.productoRepository.save(nuevoProducto);
		}

	}

	public void venderProducto(Producto nuevoProducto) throws Exception {
		
		precioValido(nuevoProducto);
		nombreProductoValido(nuevoProducto);
		List<Producto> productosFiltrados = filtrarPorProducto(nuevoProducto);
		
		if (productosFiltrados == null) {
			throw new Exception("El producto a vender no existe");
		}
		
		Producto productoEncontrado = productosFiltrados.get(0);
		
		if(existeStock(nuevoProducto, productoEncontrado)) {
			productoEncontrado.quitarStock(nuevoProducto.getStock());
			return;
		}
		
		if(eliminarStock(nuevoProducto,productoEncontrado)) {
			this.productoRepository.delete(nuevoProducto);
		}
	}
	
	private boolean eliminarStock(Producto nuevoProducto, Producto productoEncontrado) {
		return productoEncontrado.getStock()-nuevoProducto.getStock()==0;
	}

	private boolean existeStock(Producto nuevoProducto, Producto productoEncontrado) {
		return productoEncontrado.getStock()-nuevoProducto.getStock()>=1;
	};
	
	
	private List<Producto> filtrarPorProducto(Producto nuevoProducto) {
		List<Producto> productosFiltrados = productoRepository.findAll().stream()
				.filter(producto -> producto.equals(nuevoProducto)).collect(Collectors.toList());

		if (productosFiltrados.size() == 0) {
			return null;
		}

		return productosFiltrados;
	}

	private void precioValido(Producto nuevoProducto) throws Exception {
		BigDecimal precio = nuevoProducto.getPrecio();

		boolean precioMenorIgualCero = precio.compareTo(new BigDecimal(0)) == -1
				|| precio.compareTo(new BigDecimal(0)) == 0;

		if (precioMenorIgualCero) {
			throw new Exception("El precio del producto es menor o igual a 0, precio invalido");
		}
	}

	private void nombreProductoValido(Producto nuevoProducto) throws Exception {
		if (nuevoProducto.getNombre().length() == 0) {
			throw new Exception("El nombre ingresado es no tiene caracteres");
		}
	}

}
