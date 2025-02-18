package farmacia.controller;

import org.springframework.ui.Model;
import farmacia.domain.enums.Orden;
import farmacia.service.impl.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductoController {

    private ProductoService productoService;

    public ProductoController(ProductoService productoService){
        this.productoService = productoService;

    }

    @GetMapping("/productos")
    public String getVistaProducto(Model model){
        model.addAttribute("productos", productoService.obtenerProductos(Orden.CRECIENTE));
        return "producto";
    }
}
