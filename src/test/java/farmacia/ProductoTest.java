package farmacia;

import farmacia.domain.Farmacia;
import farmacia.domain.Higiene;
import farmacia.domain.Producto;
import farmacia.domain.Vacuna;
import farmacia.domain.enums.Aroma;
import farmacia.domain.enums.Orden;
import farmacia.service.impl.FarmaciaService;
import farmacia.service.impl.ProductoService;

import static java.util.Arrays.asList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestPropertySource(properties={"spring.jpa.hibernate.ddl-auto=create"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ProductoTest {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private FarmaciaService farmaciaService;


    @Test
    public void ingresarProducto_conProducto_retornaVoid() throws Exception {
        Farmacia nuevaFarmacia = new Farmacia( "NUEVA FARMACIA", "San miguel", "2042370424", "Zuviria", 2168);
        farmaciaService.ingresarFarmacia(nuevaFarmacia);
        BigDecimal precio = new BigDecimal("0.1");
        Vacuna vacuna = new Vacuna("Vacuna 1",precio,1,nuevaFarmacia);
        this.productoService.ingresarNuevoProducto(vacuna);
        boolean contieneProducto = this.productoService.obtenerProductos(Orden.CRECIENTE).contains(vacuna);
        assertThat(contieneProducto).isTrue();
    }

    @Test
    public void ingresarProducto_con2Productos_retornaVoid() throws Exception {
        Farmacia nuevaFarmacia = new Farmacia( "NUEVA FARMACIA", "San miguel", "2042370424", "Zuviria", 2168);
        farmaciaService.ingresarFarmacia(nuevaFarmacia);
        System.out.println(farmaciaService.obtenerFarmacias(Orden.CRECIENTE));
        BigDecimal precio = new BigDecimal("0.1");
        Vacuna vacuna = new Vacuna("Vacuna 1",precio,1,nuevaFarmacia);
        Vacuna vacuna2 = new Vacuna("Vacuna 1",precio,1,nuevaFarmacia);
        this.productoService.ingresarNuevoProducto(vacuna);
        this.productoService.ingresarNuevoProducto(vacuna2);
        int stock = this.productoService.stockPorProducto(vacuna.getNombre());
        assertThat(stock).isEqualTo(2);
    }

    @Test
    public void venderProducto_unProducto_retornaVoid() throws Exception {
        Farmacia nuevaFarmacia = new Farmacia( "NUEVA FARMACIA", "San miguel", "2042370424", "Zuviria", 2168);
        farmaciaService.ingresarFarmacia(nuevaFarmacia);
        BigDecimal precio = new BigDecimal("0.1");
        Vacuna vacuna = new Vacuna("Vacuna 1",precio,1,nuevaFarmacia);
        this.productoService.ingresarNuevoProducto(vacuna);
        this.productoService.venderProducto(vacuna);
        int stock = this.productoService.stockPorProducto(vacuna.getNombre());
        assertThat(stock).isEqualTo(0);

    }

    @Test
    public void ingresarNuevoProducto_unProductoNombreSinCaracteres_lanzaIllegaArgumentException(){
        Farmacia nuevaFarmacia = new Farmacia( "NUEVA FARMACIA", "San miguel", "2042370424", "Zuviria", 2168);
        farmaciaService.ingresarFarmacia(nuevaFarmacia);
        BigDecimal precio = new BigDecimal("0.1");
        Vacuna vacuna = new Vacuna("",precio,1,nuevaFarmacia);
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> productoService.ingresarNuevoProducto(vacuna));
        assertThat(exception.getMessage()).isEqualTo("El nombre de producto ingresado no tiene caracteres");
    }

    @Test
    public void venderProducto_unProductoNombreSinCaracteres_lanzaIllegaArgumentException(){
        Farmacia nuevaFarmacia = new Farmacia( "NUEVA FARMACIA", "San miguel", "2042370424", "Zuviria", 2168);
        farmaciaService.ingresarFarmacia(nuevaFarmacia);
        BigDecimal precio = new BigDecimal("0.1");
        Vacuna vacuna = new Vacuna("",precio,1,nuevaFarmacia);
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> productoService.venderProducto(vacuna));
        assertThat(exception.getMessage()).isEqualTo("El nombre de producto ingresado no tiene caracteres");
    }

    @Test
    public void ingresarNuevoProducto_unProductoConPrecioMenorACero_lanzaIllegaArgumentException(){
        Farmacia nuevaFarmacia = new Farmacia( "NUEVA FARMACIA", "San miguel", "2042370424", "Zuviria", 2168);
        farmaciaService.ingresarFarmacia(nuevaFarmacia);
        BigDecimal precio = new BigDecimal("0");
        Vacuna vacuna = new Vacuna("Vacuna 1",precio,1,nuevaFarmacia);
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> productoService.ingresarNuevoProducto(vacuna));
        assertThat(exception.getMessage()).isEqualTo("El precio del producto es menor o igual a 0, precio invalido");

    }

    @Test
    public void venderProducto_unProductoConPrecioMenorACero_lanzaIllegaArgumentException(){
        Farmacia nuevaFarmacia = new Farmacia( "NUEVA FARMACIA", "San miguel", "2042370424", "Zuviria", 2168);
        farmaciaService.ingresarFarmacia(nuevaFarmacia);
        BigDecimal precio = new BigDecimal("0");
        Vacuna vacuna = new Vacuna("Vacuna 1",precio,1,nuevaFarmacia);
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> productoService.venderProducto(vacuna));
        assertThat(exception.getMessage()).isEqualTo("El precio del producto es menor o igual a 0, precio invalido");

    }

    @Test
    public void obtenerProductos_conOrdenAscdente_retornaListaOrdenadaAsc() throws Exception {
        Farmacia nuevaFarmacia = new Farmacia( "NUEVA FARMACIA", "San miguel", "2042370424", "Zuviria", 2168);
        farmaciaService.ingresarFarmacia(nuevaFarmacia);
        BigDecimal precio = new BigDecimal("1.00");
        Vacuna vacuna = new Vacuna("Vacuna 1",precio,1,nuevaFarmacia);
        Higiene higiene = new Higiene("Shampo",precio, Aroma.COCO,true,nuevaFarmacia);
        productoService.ingresarNuevoProducto(vacuna);
        productoService.ingresarNuevoProducto(higiene);
        List<Producto> productos = productoService.obtenerProductos(Orden.CRECIENTE);
        List<Producto> ordenEsperado =asList(higiene, vacuna);
        assertThat(productos).isEqualTo(ordenEsperado);
    }


    @Test
    public void obtenerProductos_conOrdenDesc_retornaListaOrdenadaDesc() throws Exception {
        Farmacia nuevaFarmacia = new Farmacia( "NUEVA FARMACIA", "San miguel", "2042370424", "Zuviria", 2168);
        farmaciaService.ingresarFarmacia(nuevaFarmacia);
        BigDecimal precio = new BigDecimal("1.00");
        Vacuna vacuna = new Vacuna("Vacuna 1",precio,1,nuevaFarmacia);
        Higiene higiene = new Higiene("Shampo",precio, Aroma.COCO,true,nuevaFarmacia);
        productoService.ingresarNuevoProducto(vacuna);
        productoService.ingresarNuevoProducto(higiene);
        List<Producto> productos = productoService.obtenerProductos(Orden.DECRECIENTE);
        List<Producto> ordenEsperado =asList(vacuna,higiene);
        assertThat(productos).isEqualTo(ordenEsperado);
    }
}
