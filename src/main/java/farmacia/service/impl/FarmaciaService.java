package farmacia.service.impl;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import farmacia.domain.Farmacia;
import farmacia.domain.enums.Orden;
import farmacia.repository.interfaces.FarmaciaRepository;


@Service
public class FarmaciaService {
	
	private FarmaciaRepository farmaciaRepository;

	public FarmaciaService(FarmaciaRepository farmaciaRepository) {
		this.farmaciaRepository = farmaciaRepository;
	}

	public List<Farmacia> obtenerFarmacias(Orden ordenAlfabetico) {

		Assert.isTrue(ordenAlfabetico!=null,"Se ingreso un orden nulo");

		if (ordenAlfabetico == Orden.DECRECIENTE) {
			return farmaciaRepository.findAllByOrderByNombreDesc();
		}

		return farmaciaRepository.findAllByOrderByNombreAsc();
	}

	public void ingresarFarmacia(Farmacia nuevaFarmacia) {
		verificarFarmacia(nuevaFarmacia);
		this.farmaciaRepository.save(nuevaFarmacia);
		
	}

	
	public void modificarFarmacia(Farmacia nuevaFarmacia) {
		verificarFarmacia(nuevaFarmacia);
		this.farmaciaRepository.save(nuevaFarmacia);
		
	}
	
	private void verificarFarmacia(Farmacia nuevaFarmacia) {
		Assert.notNull(nuevaFarmacia.getNombre(), "El nombre de la farmacia es vacio.");
		Assert.notNull(nuevaFarmacia.getLocalidad(), "La localidad ingresada es vacia.");
		Assert.notNull(nuevaFarmacia.getCuil(), "El cuil ingresado es vacio.");
		Assert.notNull(nuevaFarmacia.getCalle(), "La calle ingresada es vacia.");
		Assert.notNull(nuevaFarmacia.getNroCalle(), "La altura de la farmacia es vacia.");
	}

	public void eliminarFarmacia(Long id) {
		Assert.notNull(id, "El id ingresado es vacio.");
		Optional<Farmacia> contieneElId  = this.farmaciaRepository.findById(id);		
		Assert.isTrue(!contieneElId.isEmpty(), "El id ingresado es invalido.");
		this.farmaciaRepository.deleteById(id);
		
	}

}
