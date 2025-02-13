package farmacia.controller.rest;

import farmacia.domain.enums.Orden;
import farmacia.domain.Producto;
import farmacia.service.impl.ProductoService;

public class ProductoController {

	private ProductoService productoService;

	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}

	public void obtenerProductos(Orden ordenAlfabetico) {
		this.productoService.obtenerProductos(ordenAlfabetico).stream()
				.forEach(producto -> System.out.println(producto));
	};

	public void ingresarNuevoProducto(Producto nuevoProducto) throws Exception {
		productoService.ingresarNuevoProducto(nuevoProducto);
	}

	public void venderProducto(Producto productoAVender) throws Exception {
		productoService.venderProducto(productoAVender);
	}
}
