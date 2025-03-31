package farmacia;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import farmacia.repository.interfaces.FarmaciaRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import farmacia.domain.Farmacia;
import farmacia.domain.enums.Orden;
import farmacia.service.impl.FarmaciaService;

@SpringBootTest
@Transactional
public class FarmaciaTest {
	@Autowired
	private FarmaciaService farmaciaService;
	@Autowired
	private FarmaciaRepository farmaciaRepository;

	@Test
	public void obtenerPorId_existeFarmacia_retornaFarmacia(){
		Farmacia farmaciaObtenida = this.farmaciaService.obtenerPorId(1l);
		assertThat(farmaciaObtenida).isInstanceOf(Farmacia.class);
	}

	@Test
	public void obtenerPorId_noExisteFarmacia_lanzaIllegalArgument(){
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> this.farmaciaService.obtenerPorId(-1l));
		assertThat(exception.getMessage()).isEqualTo("La farmacia es inexistente");
	}

	@Test
	public void obtenerPorId_idNull_lanzaIllegalArgument(){
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> this.farmaciaService.obtenerPorId(null));
		assertThat(exception.getMessage()).isEqualTo("El id es nulo");
	}

	@Test
	public void obtenerFarmacias_ConOrdenCreciente_retornaListaFarmacias() {
		List<Farmacia> farmacias = this.farmaciaService.obtenerFarmacias(Orden.CRECIENTE);
		farmacias.stream().forEach(farmacia -> assertTrue(farmacia instanceof Farmacia,
				"La clase contiene un elemento que no es del tipo farmacia"));
	}

	@Test
	public void ingresar_conObjetoFarmacia_retornaVoid() {
		Farmacia nuevaFarmacia = crearFarmacia();
		farmaciaService.ingresar(nuevaFarmacia);
		List<Farmacia> farmacias = farmaciaService.obtenerFarmacias(Orden.CRECIENTE);
		assertThat(farmacias.contains(nuevaFarmacia)).isTrue();
	}

	@Test
	public void modificarFarmacia_conFarmaciaModificada_retornaVoid() {
		Farmacia farmaciaAModificar = crearFarmacia();
		Farmacia copiaFarmacia = crearFarmacia();
		farmaciaRepository.save(farmaciaAModificar);
		List<Farmacia> farmacias = farmaciaService.obtenerFarmacias(Orden.CRECIENTE);

		assertThat(farmacias.contains(copiaFarmacia)).isTrue();

		farmaciaAModificar.setNombre("Nombre modificado");
		farmaciaService.modificar(farmaciaAModificar);

		farmacias = farmaciaRepository.findAll();
		assertThat(farmacias.contains(copiaFarmacia)).isFalse();
		assertThat(farmacias.contains(farmaciaAModificar)).isTrue();
	}

	@Test
	public void ingresar_conFarmaciaNombreNull_lanzaIllegalArgumentException() {
		Farmacia farmaciaAModificar = crearFarmacia();
		farmaciaAModificar.setNombre(null);
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> farmaciaService.ingresar(farmaciaAModificar));

		assertThat(exception.getMessage()).isEqualTo("El nombre de la farmacia es vacio.");
	}

	@Test
	public void ingresar_conFarmaciaLocalidadNull_lanzaIllegalArgumentException() {
		Farmacia farmaciaAModificar = crearFarmacia();
		farmaciaAModificar.setLocalidad(null);
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> farmaciaService.ingresar(farmaciaAModificar));

		assertThat(exception.getMessage()).isEqualTo("La localidad ingresada es vacia.");
	}

	@Test
	public void ingresar_conFarmaciaCuilNull_lanzaIllegalArgumentException() {
		Farmacia farmaciaAModificar = crearFarmacia();
		farmaciaAModificar.setCuil(null);
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> farmaciaService.ingresar(farmaciaAModificar));

		assertThat(exception.getMessage()).isEqualTo("El cuil ingresado es vacio.");
	}

	@Test
	public void ingresar_conFarmaciaCalleNull_lanzaIllegalArgumentException() {
		Farmacia farmaciaAModificar = crearFarmacia();
		farmaciaAModificar.setCalle(null);
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> farmaciaService.ingresar(farmaciaAModificar));

		assertThat(exception.getMessage()).isEqualTo("La calle ingresada es vacia.");
	}

	@Test
	public void ingresar_conFarmaciaNroCalleNull_lanzaIllegalArgumentException() {
		Farmacia farmaciaAModificar = crearFarmacia();
		farmaciaAModificar.setNroCalle(null);
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> farmaciaService.ingresar(farmaciaAModificar));

		assertThat(exception.getMessage()).isEqualTo("La altura de la farmacia es vacia.");
	}

	@Test
	public void modificar_conFarmaciaNombreNull_lanzaIllegalArgumentException() {
		Farmacia farmaciaAModificar = crearFarmacia();
		farmaciaAModificar.setNombre(null);

		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> farmaciaService.modificar(farmaciaAModificar));

		assertThat(exception.getMessage()).isEqualTo("El nombre de la farmacia es vacio.");
	}

	@Test
	public void modificar_conFarmaciaLocalidadNull_lanzaIllegalArgumentException() {
		Farmacia farmaciaAModificar = crearFarmacia();
		farmaciaAModificar.setLocalidad(null);

		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> farmaciaService.modificar(farmaciaAModificar));

		assertThat(exception.getMessage()).isEqualTo("La localidad ingresada es vacia.");
	}

	@Test
	public void modificar_conFarmaciaCuilNull_lanzaIllegalArgumentException() {
		Farmacia farmaciaAModificar = crearFarmacia();
		farmaciaAModificar.setCuil(null);
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> farmaciaService.modificar(farmaciaAModificar));

		assertThat(exception.getMessage()).isEqualTo("El cuil ingresado es vacio.");
	}

	@Test
	public void modificar_conFarmaciaCalleNull_lanzaIllegalArgumentException() {
		Farmacia farmaciaAModificar = crearFarmacia();
		farmaciaAModificar.setCalle(null);
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> farmaciaService.modificar(farmaciaAModificar));

		assertThat(exception.getMessage()).isEqualTo("La calle ingresada es vacia.");
	}

	@Test
	public void modificar_conFarmaciaNroCalleNull_lanzaIllegalArgumentException() {
		Farmacia farmaciaAModificar = crearFarmacia();
		farmaciaAModificar.setNroCalle(null);
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> farmaciaService.modificar(farmaciaAModificar));

		assertThat(exception.getMessage()).isEqualTo("La altura de la farmacia es vacia.");
	}

	@Test
	public void eliminarFarmacia_conIdFarmaciaNull_lanzaIllegalArgumentException() {
		Long id = null;
		Exception exception = assertThrows(IllegalArgumentException.class, () -> farmaciaService.eliminarFarmacia(id));
		assertThat(exception.getMessage()).isEqualTo("El id ingresado es vacio.");
	}

	@Test
	public void eliminarFarmacia_conIdFarmaciaExistente_retornaVoid() {
		Farmacia farmaciaAEliminar = farmaciaRepository.findAll().get(0);
		farmaciaService.eliminarFarmacia(farmaciaAEliminar.getId());
		farmaciaRepository
				.findAll()
				.stream()
				.forEach(farmacia -> assertNotEquals(farmacia.getId(), farmaciaAEliminar.getId()));
	}

	@Test
	public void eliminarFarmacia_conIdFarmaciaInexistente_lanzaIllegalArgumentException() {
		Long id = -1l;
		Exception exception = assertThrows(IllegalArgumentException.class, () -> farmaciaService.eliminarFarmacia(id));
		assertThat(exception.getMessage()).isEqualTo("El id ingresado es invalido.");
	}

	@Test
	public void obtenerFarmacias_conOrdenAsc_retornaListaOrdenadaAsc(){
		farmaciaRepository.deleteAll();
		Farmacia nuevaFarmacia = crearFarmaciaTestListaOrdenadaConLetraB();
		Farmacia nuevaFarmacia2 = crearFarmaciaTestListaOrdenadaConLetraA();
		farmaciaRepository.save(nuevaFarmacia);
		farmaciaRepository.save(nuevaFarmacia2);
		List<Farmacia> farmacias = farmaciaService.obtenerFarmacias(Orden.CRECIENTE);
		List<Farmacia> farmaciasComparacion = asList(nuevaFarmacia2,nuevaFarmacia);
		assertThat(farmacias).isEqualTo(farmaciasComparacion);
	}

	@Test
	public void obtenerFarmacias_conOrdenAsc_retornaListaOrdenadaDesc(){
		farmaciaRepository.deleteAll();
		Farmacia nuevaFarmacia = crearFarmaciaTestListaOrdenadaConLetraB();
		Farmacia nuevaFarmacia2 = crearFarmaciaTestListaOrdenadaConLetraA();
		farmaciaRepository.save(nuevaFarmacia);
		farmaciaRepository.save(nuevaFarmacia2);
		List<Farmacia> farmacias = farmaciaService.obtenerFarmacias(Orden.DECRECIENTE);
		List<Farmacia> farmaciasComparacion = asList(nuevaFarmacia,nuevaFarmacia2);
		assertThat(farmacias).isEqualTo(farmaciasComparacion);
	}

	@Test
	public void obtenerFarmacias_conOrdenNull_lanzaIllegalArgumenException(){

		Exception exception = assertThrows(IllegalArgumentException.class,
				() ->farmaciaService.obtenerFarmacias(null));

		assertThat(exception.getMessage()).isEqualTo("Se ingreso un orden nulo");
	}

	private Farmacia crearFarmacia(){
		Farmacia nuevaFarmacia = new Farmacia();
		nuevaFarmacia.setNombre("NUEVA FARMACIA");
		nuevaFarmacia.setLocalidad("San miguel");
		nuevaFarmacia.setCuil("2042370424");
		nuevaFarmacia.setCalle("Zuviria");
		nuevaFarmacia.setNroCalle(2168);
		return nuevaFarmacia;
	}

	private Farmacia crearFarmaciaTestListaOrdenadaConLetraA(){
		Farmacia nuevaFarmacia = new Farmacia();
		nuevaFarmacia.setNombre("A FARMACIA");
		nuevaFarmacia.setLocalidad("San miguel");
		nuevaFarmacia.setCuil("2042370424");
		nuevaFarmacia.setCalle("Zuviria");
		nuevaFarmacia.setNroCalle(2168);
		return nuevaFarmacia;
	}

	private Farmacia crearFarmaciaTestListaOrdenadaConLetraB(){
		Farmacia nuevaFarmacia = new Farmacia();
		nuevaFarmacia.setNombre("B FARMACIA");
		nuevaFarmacia.setLocalidad("San miguel");
		nuevaFarmacia.setCuil("2042370424");
		nuevaFarmacia.setCalle("Zuviria");
		nuevaFarmacia.setNroCalle(2168);
		return nuevaFarmacia;
	}

}
