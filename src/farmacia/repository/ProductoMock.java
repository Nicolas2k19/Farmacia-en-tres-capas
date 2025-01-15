package farmacia.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import farmacia.domain.Higiene;
import farmacia.domain.Medicamento;
import farmacia.domain.Producto;
import farmacia.domain.Vacuna;
import farmacia.domain.enums.Aroma;
import farmacia.domain.enums.TipoMedicamento;
import farmacia.domain.interfaces.ProductoRepository;

public class ProductoMock implements ProductoRepository {

	@Override
	public List<Producto> findAll() {
		ArrayList<Producto> productos=  new ArrayList<Producto>();
		productos.add(new Vacuna(1l, "Vacuna tetanos", new BigDecimal(300.00), 2, 2));
		productos.add(new Medicamento(2l, "Ibuprofeno", new BigDecimal(100.00), 20, TipoMedicamento.ANTIINFLAMATORIOS, 600));
		productos.add(new Higiene(3l, "Sedal Shampo", new BigDecimal(50.00), 10, Aroma.COCO, true));
		return productos;
	}

	@Override
	public void save(Producto nuevoProducto) {
	}

	@Override
	public void delete(Producto nuevoProducto) {
		// TODO Auto-generated method stub
		
	}

}
