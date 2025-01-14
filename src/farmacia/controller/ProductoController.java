package farmacia.controller;

import farmacia.enums.Orden;
import farmacia.service.ProductoService;

public class ProductoController {
	
	private ProductoService productoService;
	
	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}

	public void obtenerProductos(Orden ordenAlfabetico ) {
		this.productoService.obtenerProductos(ordenAlfabetico).stream().forEach(producto -> System.out.println(producto));
	};

}
