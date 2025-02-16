package farmacia.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import farmacia.domain.enums.Orden;
import farmacia.domain.Producto;
import farmacia.repository.interfaces.ProductoRepository;
import org.springframework.stereotype.Service;

@Service
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

		precioValido(nuevoProducto.getPrecio());
		nombreProductoValido(nuevoProducto.getNombre());

		Producto productoEncontrado = buscarPorProducto(nuevoProducto);

		if (productoEncontrado != null) {
			productoEncontrado.sumarStock(nuevoProducto.getStock());

		} else {
			this.productoRepository.save(nuevoProducto);
		}

	}

	public void venderProducto(Producto nuevoProducto) throws Exception {

		precioValido(nuevoProducto.getPrecio());
		nombreProductoValido(nuevoProducto.getNombre());
		Producto productoEncontrado = buscarPorProducto(nuevoProducto);

		if (productoEncontrado == null) {
			throw new Exception("El producto a vender no existe");
		}

		if (existeStock(nuevoProducto, productoEncontrado)) {
			productoEncontrado.quitarStock(nuevoProducto.getStock());
			return;
		}

		if(eliminarStock(nuevoProducto, productoEncontrado)) {
			this.productoRepository.delete(nuevoProducto);
			
		} else {
			throw new Exception("No hay stock");
		}
	}

	private boolean eliminarStock(Producto nuevoProducto, Producto productoEncontrado) {
		return productoEncontrado.getStock() - nuevoProducto.getStock() == 0;
	}

	private boolean existeStock(Producto nuevoProducto, Producto productoEncontrado) {
		return productoEncontrado.getStock() - nuevoProducto.getStock() >= 1;
	};

	private Producto buscarPorProducto(Producto nuevoProducto) {
		List<Producto> productosFiltrados = this.productoRepository.findAll().stream()
				.filter(producto -> producto.equals(nuevoProducto)).collect(Collectors.toList());

		if (productosFiltrados.size() == 0) {
			return null;
		}

		return productosFiltrados.get(0);
	}

	private void precioValido(BigDecimal precio){
		BigDecimal precioCero = new BigDecimal(0);

		boolean esPrecioMenorIgualCero = precio.compareTo(precioCero) == -1 || precio.compareTo(precioCero) == 0;

		if (esPrecioMenorIgualCero) {
			throw new IllegalArgumentException("El precio del producto es menor o igual a 0, precio invalido");
		}
	}

	private void nombreProductoValido(String nombreProducto) throws Exception {
		if (nombreProducto.length() == 0) {
			throw new Exception("El nombre ingresado es no tiene caracteres");
		}
	}

}
