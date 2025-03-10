package farmacia;

import farmacia.domain.Farmacia;
import farmacia.domain.Higiene;
import farmacia.domain.Producto;
import farmacia.domain.Vacuna;
import farmacia.domain.enums.Aroma;
import farmacia.domain.enums.Orden;
import farmacia.repository.interfaces.FarmaciaRepository;
import farmacia.repository.interfaces.ProductoRepository;
import farmacia.service.impl.ProductoService;
import static java.util.Arrays.asList;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import java.math.BigDecimal;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Sql(scripts = "../schemas.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Transactional
public class ProductoTest {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private FarmaciaRepository farmaciaRepository;
    @Autowired
    private ProductoRepository productoRepository;

    @Test
    public void ingresar_conProducto_retornaVoid() throws Exception {
        Vacuna vacuna = crearVacuna();
        this.productoService.ingresar(vacuna);
        boolean contieneProducto = this.productoService.obtenerProductos(Orden.CRECIENTE).contains(vacuna);
        assertThat(contieneProducto).isTrue();
    }

    @Test
    public void ingresar_con2Productos_retornaVoid() throws Exception {
        Vacuna vacuna = crearVacuna();
        Vacuna vacuna2 = crearVacuna();
        this.productoService.ingresar(vacuna);
        this.productoService.ingresar(vacuna2);
        int stock = this.productoService.stockPorProducto(vacuna.getNombre());
        assertThat(stock).isEqualTo(2);
    }

    @Test
    public void vender_unProducto_retornaVoid() throws Exception {
        Vacuna vacuna = crearVacuna();
        this.productoRepository.save(vacuna);
        this.productoService.vender(vacuna);
        int stock = this.productoService.stockPorProducto(vacuna.getNombre());
        assertThat(stock).isEqualTo(0);
    }

    @Test
    public void ingresar_unProductoNombreSinCaracteres_lanzaIllegaArgumentException(){
        Vacuna vacuna = crearVacuna();
        vacuna.setNombre("");
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> productoService.ingresar(vacuna));
        assertThat(exception.getMessage()).isEqualTo("El nombre de producto ingresado no tiene caracteres");
    }

    @Test
    public void vender_unProductoNombreSinCaracteres_lanzaIllegaArgumentException(){
        Vacuna vacuna = crearVacuna();
        vacuna.setNombre("");
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> productoService.vender(vacuna));
        assertThat(exception.getMessage()).isEqualTo("El nombre de producto ingresado no tiene caracteres");
    }

    @Test
    public void ingresar_unProductoConPrecioMenorACero_lanzaIllegaArgumentException(){
        Vacuna vacuna = crearVacuna();
        vacuna.setPrecio(new BigDecimal("-0.01"));
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> productoService.ingresar(vacuna));
        assertThat(exception.getMessage()).isEqualTo("El precio del producto es menor o igual a 0, precio invalido");
    }

    @Test
    public void vender_unProductoConPrecioMenorACero_lanzaIllegaArgumentException(){
        Vacuna vacuna = crearVacuna();
        vacuna.setPrecio(BigDecimal.valueOf(0));
        productoRepository.save(vacuna);
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> productoService.vender(vacuna));
        assertThat(exception.getMessage()).isEqualTo("El precio del producto es menor o igual a 0, precio invalido");
    }

    @Test
    public void obtenerProductos_conOrdenAscdente_retornaListaOrdenadaAsc() throws Exception {
        Vacuna vacuna = crearVacuna();
        Higiene higiene = crearProductoHigiene();
        productoRepository.save(vacuna);
        productoRepository.save(higiene);
        List<Producto> productos = productoService.obtenerProductos(Orden.CRECIENTE);
        List<Producto> ordenEsperado =asList(higiene, vacuna);
        assertThat(productos).isEqualTo(ordenEsperado);
    }

    @Test
    public void obtenerProductos_conOrdenDesc_retornaListaOrdenadaDesc() throws Exception {
        Vacuna vacuna = crearVacuna();
        Higiene higiene = crearProductoHigiene();
        productoRepository.save(vacuna);
        productoRepository.save(higiene);
        List<Producto> productos = productoService.obtenerProductos(Orden.DECRECIENTE);
        List<Producto> ordenEsperado =asList(vacuna,higiene);
        assertThat(productos).isEqualTo(ordenEsperado);
    }

    private Farmacia crearFarmacia(){
        Farmacia nuevaFarmacia = new Farmacia();
        nuevaFarmacia.setNombre("NUEVA FARMACIA");
        nuevaFarmacia.setLocalidad("San miguel");
        nuevaFarmacia.setCuil("2042370424");
        nuevaFarmacia.setCalle("Zuviria");
        nuevaFarmacia.setNroCalle(2168);
        farmaciaRepository.save(nuevaFarmacia);
        return nuevaFarmacia;
    }

    private Higiene crearProductoHigiene(){
        Farmacia nuevaFarmacia = crearFarmacia();
        BigDecimal precio = new BigDecimal("1.00");
        Higiene productoHigiene = new Higiene();
        productoHigiene.setNombre("Shampo");
        productoHigiene.setPrecio(precio);
        productoHigiene.setAroma(Aroma.COCO);
        productoHigiene.setHipoalergenico(true);
        productoHigiene.setFarmacia(nuevaFarmacia);
        return productoHigiene;
    }

    private Vacuna crearVacuna() {
        Farmacia nuevaFarmacia = crearFarmacia();
        BigDecimal precio = new BigDecimal("0.1");
        Vacuna vacuna = new Vacuna();
        vacuna.setNombre("Vacuna 1");
        vacuna.setPrecio(precio);
        vacuna.setCantidadDeAplicacionesRequeridas(1);
        vacuna.setFarmacia(nuevaFarmacia);
        return vacuna;
    }
}
