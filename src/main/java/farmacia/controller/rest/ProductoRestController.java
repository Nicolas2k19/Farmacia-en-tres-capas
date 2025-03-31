package farmacia.controller.rest;

import farmacia.domain.Higiene;
import farmacia.domain.Medicamento;
import farmacia.domain.Vacuna;
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

	@PostMapping("api/ingresarMedicamento")
	public void ingresarMedicina(@ModelAttribute Medicamento medicamento) throws Exception {
		productoService.ingresar(medicamento);
	}

	@PostMapping("api/ingresarVacuna")
	public void ingresarVacuna(@ModelAttribute Vacuna vacuna) throws Exception {
		productoService.ingresar(vacuna);
	}

	@PostMapping("api/ingresarHigiene")
	public void ingresarHigiene(@ModelAttribute Higiene higiene) throws Exception {
		productoService.ingresar(higiene);
	}

	@DeleteMapping("api/venderProducto")
	public void venderProducto(@RequestBody Producto productoAVender) throws Exception {
		productoService.vender(productoAVender);
	}
}
