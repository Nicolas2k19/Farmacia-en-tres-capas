package farmacia;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.TestPropertySource;
import farmacia.domain.Farmacia;
import farmacia.domain.enums.Orden;
import farmacia.service.impl.FarmaciaService;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@TestPropertySource(properties={"spring.jpa.hibernate.ddl-auto=create"})
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class FarmaciaTest {
	@Autowired
	private FarmaciaService farmaciaService;

	@Test
	public void obtenerFarmacias_ConOrdenCreciente_retornaListaFarmacias() {
		List<Farmacia> farmacias = this.farmaciaService.obtenerFarmacias(Orden.CRECIENTE);
		farmacias.stream().forEach(farmacia -> assertTrue(farmacia instanceof Farmacia,
				"La clase contiene un elemento que no es del tipo farmacia"));
	}

	@Test
	public void ingresarFarmacia_conObjetoFarmacia_retornaVoid() {
		Farmacia nuevaFarmacia = new Farmacia( "NUEVA FARMACIA", "San miguel", "2042370424", "Zuviria", 2168);
		farmaciaService.ingresarFarmacia(nuevaFarmacia);
		List<Farmacia> farmacias = farmaciaService.obtenerFarmacias(Orden.CRECIENTE);

		assertThat(farmacias.contains(nuevaFarmacia)).isTrue();
	}

	@Test
	public void modificarFarmacia_conFarmaciaModificada_retornaVoid() {
		Farmacia farmaciaAModificar = new Farmacia( "NUEVA FARMACIA 1", "San miguel", "2042370424", "Zuviria", 2168);
		Farmacia copiaFarmacia = new Farmacia( "NUEVA FARMACIA 1", "San miguel", "2042370424", "Zuviria", 2168);
		farmaciaService.ingresarFarmacia(farmaciaAModificar);
		List<Farmacia> farmacias = farmaciaService.obtenerFarmacias(Orden.CRECIENTE);

		assertThat(farmacias.contains(copiaFarmacia)).isTrue();

		farmaciaAModificar.setNombre("Nombre modificado");
		farmaciaService.modificarFarmacia(farmaciaAModificar);

		farmacias = farmaciaService.obtenerFarmacias(Orden.CRECIENTE);

		assertThat(farmacias.contains(copiaFarmacia)).isFalse();
		assertThat(farmacias.contains(farmaciaAModificar)).isTrue();

	}

	@Test
	public void ingresarFarmacia_conFarmaciaNombreNull_lanzaIllegalArgumentException() {
		Farmacia farmaciaAModificar = new Farmacia( null, "San miguel", "2042370424", "Zuviria", 2168);

		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> farmaciaService.ingresarFarmacia(farmaciaAModificar));

		assertThat(exception.getMessage()).isEqualTo("El nombre de la farmacia es vacio.");

	}

	@Test
	public void ingresarFarmacia_conFarmaciaLocalidadNull_lanzaIllegalArgumentException() {
		Farmacia farmaciaAModificar = new Farmacia( "Farmacia", null, "2042370424", "Zuviria", 2168);

		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> farmaciaService.ingresarFarmacia(farmaciaAModificar));

		assertThat(exception.getMessage()).isEqualTo("La localidad ingresada es vacia.");

	}

	@Test
	public void ingresarFarmacia_conFarmaciaCuilNull_lanzaIllegalArgumentException() {
		Farmacia farmaciaAModificar = new Farmacia( "Farmacia", "San miguel", null, "Zuviria", 2168);
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> farmaciaService.ingresarFarmacia(farmaciaAModificar));

		assertThat(exception.getMessage()).isEqualTo("El cuil ingresado es vacio.");

	}

	@Test
	public void ingresarFarmacia_conFarmaciaCalleNull_lanzaIllegalArgumentException() {
		Farmacia farmaciaAModificar = new Farmacia( "Farmacia", "San miguel", "2042370424", null, 2168);
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> farmaciaService.ingresarFarmacia(farmaciaAModificar));

		assertThat(exception.getMessage()).isEqualTo("La calle ingresada es vacia.");

	}

	@Test
	public void ingresarFarmacia_conFarmaciaNroCalleNull_lanzaIllegalArgumentException() {
		Farmacia farmaciaAModificar = new Farmacia( "Farmacia", "San miguel", "2042370424", "Zuviria", null);
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> farmaciaService.ingresarFarmacia(farmaciaAModificar));

		assertThat(exception.getMessage()).isEqualTo("La altura de la farmacia es vacia.");

	}

	@Test
	public void modificarFarmacia_conFarmaciaNombreNull_lanzaIllegalArgumentException() {
		Farmacia farmaciaAModificar = new Farmacia( null, "San miguel", "2042370424", "Zuviria", 2168);

		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> farmaciaService.modificarFarmacia(farmaciaAModificar));

		assertThat(exception.getMessage()).isEqualTo("El nombre de la farmacia es vacio.");

	}

	@Test
	public void modificarFarmacia_conFarmaciaLocalidadNull_lanzaIllegalArgumentException() {
		Farmacia farmaciaAModificar = new Farmacia("Farmacia", null, "2042370424", "Zuviria", 2168);

		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> farmaciaService.modificarFarmacia(farmaciaAModificar));

		assertThat(exception.getMessage()).isEqualTo("La localidad ingresada es vacia.");

	}

	@Test
	public void modificarFarmacia_conFarmaciaCuilNull_lanzaIllegalArgumentException() {
		Farmacia farmaciaAModificar = new Farmacia( "Farmacia", "San miguel", null, "Zuviria", 2168);
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> farmaciaService.modificarFarmacia(farmaciaAModificar));

		assertThat(exception.getMessage()).isEqualTo("El cuil ingresado es vacio.");

	}

	@Test
	public void modificarFarmacia_conFarmaciaCalleNull_lanzaIllegalArgumentException() {
		Farmacia farmaciaAModificar = new Farmacia("Farmacia", "San miguel", "2042370424", null, 2168);
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> farmaciaService.modificarFarmacia(farmaciaAModificar));

		assertThat(exception.getMessage()).isEqualTo("La calle ingresada es vacia.");

	}

	@Test
	public void modificarFarmacia_conFarmaciaNroCalleNull_lanzaIllegalArgumentException() {
		Farmacia farmaciaAModificar = new Farmacia("Farmacia", "San miguel", "2042370424", "Zuviria", null);
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> farmaciaService.modificarFarmacia(farmaciaAModificar));

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
		Long id = 1l;
		Farmacia nuevaFarmacia = new Farmacia("NUEVA FARMACIA", "San miguel", "2042370424", "Zuviria", 2168);
		farmaciaService.ingresarFarmacia(nuevaFarmacia);
		farmaciaService.eliminarFarmacia(id);
				
		farmaciaService.obtenerFarmacias(Orden.CRECIENTE).stream()
				.forEach(farmacia -> assertNotEquals(farmacia.getId(), id));
	}

	@Test
	public void eliminarFarmacia_conIdFarmaciaInexistente_lanzaIllegalArgumentException() {
		Long id = -1l;
		Exception exception = assertThrows(IllegalArgumentException.class, () -> farmaciaService.eliminarFarmacia(id));
		assertThat(exception.getMessage()).isEqualTo("El id ingresado es invalido.");
	}

	@Test
	public void obtenerFarmacias_conOrdenAsc_retornaListaOrdenadaAsc(){
		Farmacia nuevaFarmacia = new Farmacia("B FARMACIA", "San miguel", "2042370424", "Zuviria", 2168);
		Farmacia nuevaFarmacia2 = new Farmacia("A FARMACIA", "San miguel", "2042370424", "Zuviria", 2168);
		farmaciaService.ingresarFarmacia(nuevaFarmacia);
		farmaciaService.ingresarFarmacia(nuevaFarmacia2);
		List<Farmacia> farmacias = farmaciaService.obtenerFarmacias(Orden.CRECIENTE);
		List<Farmacia> farmaciasComparacion = asList(nuevaFarmacia2,nuevaFarmacia);
		assertThat(farmacias).isEqualTo(farmaciasComparacion);

	}

	@Test
	public void obtenerFarmacias_conOrdenAsc_retornaListaOrdenadaDesc(){
		Farmacia nuevaFarmacia = new Farmacia("B FARMACIA", "San miguel", "2042370424", "Zuviria", 2168);
		Farmacia nuevaFarmacia2 = new Farmacia("A FARMACIA", "San miguel", "2042370424", "Zuviria", 2168);
		farmaciaService.ingresarFarmacia(nuevaFarmacia);
		farmaciaService.ingresarFarmacia(nuevaFarmacia2);
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

}
