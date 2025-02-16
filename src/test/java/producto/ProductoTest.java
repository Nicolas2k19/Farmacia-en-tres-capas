package producto;

import farmacia.domain.Vacuna;
import farmacia.domain.enums.Orden;
import farmacia.service.impl.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = {ProductoTest.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestPropertySource(properties={"spring.jpa.hibernate.ddl-auto=create"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ProductoTest {

    @Autowired
    private ProductoService productoService;


    @Test
    public void ingresarProducto_conProducto_retornaVoid() throws Exception {
        BigDecimal precio = new BigDecimal(0.1);
        Vacuna vacuna = new Vacuna("Vacuna 1",precio,1,1);
        this.productoService.ingresarNuevoProducto(vacuna);
        boolean contieneProducto = this.productoService.obtenerProductos(Orden.CRECIENTE).contains(vacuna);
        assertThat(contieneProducto).isTrue();
    }
}
