package farmacia.main;

import farmacia.controller.ProductoController;
import farmacia.enums.Orden;
import farmacia.repository.ProductoMock;
import farmacia.service.ProductoService;

public class main {

	public static void main(String[] args) {
		ProductoController controllerProducto = new ProductoController(new ProductoService(new ProductoMock()));
		System.out.println("Orden alfabetico creciente\n-----------------------------------");
		controllerProducto.obtenerProductos(Orden.CRECIENTE);
		System.out.println("\nOrden alfabetico decreciente\n-----------------------------------");
		controllerProducto.obtenerProductos(Orden.DECRECIENTE);
	}

}
