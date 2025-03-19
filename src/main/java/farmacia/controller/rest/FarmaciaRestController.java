package farmacia.controller.rest;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import farmacia.domain.Farmacia;
import farmacia.domain.enums.Orden;
import farmacia.service.impl.FarmaciaService;

@RestController
@Controller
public class FarmaciaRestController {
	
	private FarmaciaService farmaciaService;
		
	public FarmaciaRestController(FarmaciaService farmaciaService) {
		this.farmaciaService = farmaciaService;
	}
	
	@GetMapping("api/obtenerFarmacias")
	public List<Farmacia> obtenerFarmacias(Orden ordenAlfabetico) {
		return this.farmaciaService.obtenerFarmacias(ordenAlfabetico).stream().toList();
	}

	@PostMapping("api/ingresarFarmacia")
	public void obtenerFarmacias(@ModelAttribute Farmacia farmacia) {
		this.farmaciaService.ingresar(farmacia);
	}

	@DeleteMapping("api/eliminarFarmacia/{id}")
	public void eliminarFarmacia(@PathVariable Long id){
		this.farmaciaService.eliminarFarmacia(id);
	}
}
