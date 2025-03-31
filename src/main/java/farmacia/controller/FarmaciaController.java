package farmacia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import farmacia.domain.enums.Orden;
import farmacia.service.impl.FarmaciaService;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FarmaciaController {
	
	@Autowired
	private FarmaciaService farmaciaService;

	@GetMapping("/farmacias")
	public String getVistaFarmacia(Model model){
		model.addAttribute("farmacias", farmaciaService.obtenerFarmacias(Orden.CRECIENTE));
		return "farmacia";
	}

	@GetMapping("/infoFarmacia/{id}")
	public String getVistaInformacionFarmacia(@PathVariable Long id, Model model){
		model.addAttribute("farmaciaUnica", farmaciaService.obtenerPorId(id));
		return "informacionFarmacia";
	}

	
}