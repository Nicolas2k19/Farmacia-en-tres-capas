package farmacia.main;

import java.math.BigDecimal;
import java.util.ArrayList;

import farmacia.controller.ProductoController;
import farmacia.domain.Higiene;
import farmacia.domain.Medicamento;
import farmacia.domain.Producto;
import farmacia.domain.Vacuna;
import farmacia.domain.enums.Aroma;
import farmacia.domain.enums.Orden;
import farmacia.domain.enums.TipoMedicamento;
import farmacia.repository.ProductoEnRunTime;
import farmacia.repository.ProductoMock;
import farmacia.service.ProductoService;

public class main {

	public static void main(String[] args) throws Exception {
		
		System.out.println("Primera implementacion\n-------------------------------------");
		ProductoController controllerProducto1 = new ProductoController(new ProductoService(new ProductoMock()));
		System.out.println("Orden alfabetico creciente\n-----------------------------------");
		controllerProducto1.obtenerProductos(Orden.CRECIENTE);
		System.out.println("\nOrden alfabetico decreciente\n-----------------------------------");
		controllerProducto1.obtenerProductos(Orden.DECRECIENTE);

		System.out.println("\nSegunda implementacion\n-----------------------------------------");
		ProductoController controllerProducto2 = new ProductoController(
				new ProductoService(new ProductoEnRunTime(new ArrayList<Producto>())));
		controllerProducto2.ingresarNuevoProducto(new Vacuna(1l, "Vacuna tetanos", new BigDecimal(300.00), 1, 2));
		controllerProducto2.ingresarNuevoProducto(new Vacuna(1l, "Vacuna tetanos", new BigDecimal(300.00), 1, 2));
		controllerProducto2.ingresarNuevoProducto(new Vacuna(1l, "Vacuna tetanos", new BigDecimal(300.00), 1, 2));
		
		controllerProducto2.ingresarNuevoProducto(new Medicamento(2l, "Ibuprofeno", new BigDecimal(100.00), 1, TipoMedicamento.ANTIINFLAMATORIOS, 600));
		controllerProducto2.ingresarNuevoProducto(new Medicamento(2l, "Ibuprofeno", new BigDecimal(100.00), 1, TipoMedicamento.ANTIINFLAMATORIOS, 600));
		controllerProducto2.ingresarNuevoProducto(new Medicamento(2l, "Ibuprofeno", new BigDecimal(100.00), 1, TipoMedicamento.ANTIINFLAMATORIOS, 600));
		
		controllerProducto2.ingresarNuevoProducto(new Higiene(3l, "Sedal Shampo", new BigDecimal(50.00), 1, Aroma.COCO, true));
		controllerProducto2.ingresarNuevoProducto(new Higiene(3l, "Sedal Shampo", new BigDecimal(50.00), 1, Aroma.COCO, true));
		controllerProducto2.ingresarNuevoProducto(new Higiene(3l, "Sedal Shampo", new BigDecimal(50.00), 1, Aroma.COCO, true));
		
		
		controllerProducto2.venderProducto(new Higiene(3l, "Sedal Shampo", new BigDecimal(50.00), 1, Aroma.COCO, true));
		controllerProducto2.venderProducto(new Higiene(3l, "Sedal Shampo", new BigDecimal(50.00), 1, Aroma.COCO, true));
		controllerProducto2.venderProducto(new Higiene(3l, "Sedal Shampo", new BigDecimal(50.00), 1, Aroma.COCO, true));
		
		controllerProducto2.venderProducto(new Medicamento(2l, "Ibuprofeno", new BigDecimal(100.00), 1, TipoMedicamento.ANTIINFLAMATORIOS, 600));
		controllerProducto2.venderProducto(new Medicamento(2l, "Ibuprofeno", new BigDecimal(100.00), 1, TipoMedicamento.ANTIINFLAMATORIOS, 600));
		
		System.out.println("Orden alfabetico creciente\n-----------------------------------");
		controllerProducto2.obtenerProductos(Orden.CRECIENTE);
		System.out.println("\nOrden alfabetico decreciente\n-----------------------------------");
		controllerProducto2.obtenerProductos(Orden.DECRECIENTE);
	}

}
