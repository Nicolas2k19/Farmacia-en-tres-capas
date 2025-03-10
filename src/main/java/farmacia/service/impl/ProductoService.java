package farmacia.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import farmacia.domain.enums.Orden;
import farmacia.domain.Producto;
import farmacia.repository.interfaces.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class ProductoService {


	private ProductoRepository productoRepository;

	public ProductoService(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}

	public List<Producto> obtenerProductos(Orden ordenAlfabetico) {
		Assert.isTrue(ordenAlfabetico!=null,"Se ingreso un orden nulo.");

		if(ordenAlfabetico == Orden.CRECIENTE){
			return productoRepository.findAllByOrderByNombreAsc();
		}

		return productoRepository.findAllByOrderByNombreDesc();
	}

	public void ingresar(Producto nuevoProducto) throws Exception {
		precioValido(nuevoProducto.getPrecio());
		nombreProductoValido(nuevoProducto.getNombre());
		this.productoRepository.save(nuevoProducto);
	}

	public void vender(Producto nuevoProducto) throws Exception {
		precioValido(nuevoProducto.getPrecio());
		nombreProductoValido(nuevoProducto.getNombre());
		int stock = stockPorProducto(nuevoProducto.getNombre());
		Assert.isTrue(stock > 0,"No hay stock");
		this.productoRepository.delete(nuevoProducto);
	}

	private void precioValido(BigDecimal precio){
		BigDecimal precioCero = new BigDecimal(0);
		boolean esPrecioMenorIgualCero = precio.compareTo(precioCero) == -1 || precio.compareTo(precioCero) == 0;
		Assert.isTrue(!esPrecioMenorIgualCero , "El precio del producto es menor o igual a 0, precio invalido");
	}

	private void nombreProductoValido(String nombreProducto){
		Assert.isTrue(!nombreProducto.isEmpty(),"El nombre de producto ingresado no tiene caracteres");
	}

	public int stockPorProducto(String nombre){
		return productoRepository.countByNombre(nombre);
	}



}
