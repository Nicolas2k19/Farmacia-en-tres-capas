package farmacia.controller;

import farmacia.service.impl.FarmaciaService;
import org.springframework.ui.Model;
import farmacia.domain.enums.Orden;
import farmacia.service.impl.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductoController {

    private ProductoService productoService;
    private FarmaciaService farmaciaService;

    public ProductoController(ProductoService productoService,FarmaciaService farmaciaService){
        this.productoService = productoService;
        this.farmaciaService = farmaciaService;
    }

    @GetMapping("/productos")
    public String getVistaProducto(Model model){
        model.addAttribute("productos", productoService.obtenerProductos(Orden.CRECIENTE));
        model.addAttribute("farmacias", farmaciaService.obtenerFarmacias(Orden.CRECIENTE));
        return "producto";
    }
}
