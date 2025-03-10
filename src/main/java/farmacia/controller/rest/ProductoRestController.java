package farmacia.controller.rest;

import farmacia.domain.enums.Orden;
import farmacia.domain.Producto;
import farmacia.service.impl.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoRestController {

	private ProductoService productoService;

	public ProductoRestController(ProductoService productoService) {
		this.productoService = productoService;
	}

	@GetMapping("api/getProductos/")
	public List<Producto> obtenerProductos(Orden ordenAlfabetico) {
		return this.productoService.obtenerProductos(ordenAlfabetico);
	};

	@PostMapping("api/getProductos/ingresarProducto")
	public void ingresarNuevoProducto(@RequestBody Producto nuevoProducto) throws Exception {
		productoService.ingresar(nuevoProducto);
	}

	@DeleteMapping("api/getProductos/venderProducto")
	public void venderProducto(@RequestBody Producto productoAVender) throws Exception {
		productoService.vender(productoAVender);
	}
}
