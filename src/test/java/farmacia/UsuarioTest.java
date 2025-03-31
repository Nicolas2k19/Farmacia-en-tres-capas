package farmacia;

import farmacia.service.impl.UsuarioService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class UsuarioTest {

    @Autowired
    private UsuarioService usuarioService;

    @Test
    public void loadUserByUsername_usuarioExistente_retornaUsuario(){
        UserDetails usuarioEncontrado = this.usuarioService.loadUserByUsername("Juan Pérez");

        assertThat(usuarioEncontrado).isNotNull();
        assertThat(usuarioEncontrado).isInstanceOf(UserDetails.class);
    }

    @Test
    public void loadUserByUsername_usuarioInexistente_lanzaIllegalArgument(){
        String msjError = "El usuario ingresado es inexistente";

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> this.usuarioService.loadUserByUsername(msjError));
        assertThat(exception.getMessage()).isEqualTo(msjError);
    }

    @Test
    public void loadUserByUsername_usernameSinCaracteres_lanzaIllegalArgument(){
        String msjError = "El nombre de usuario es invalido";
        String nombreDeUsuarioConCeroCaracteres = "";

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> this.usuarioService.loadUserByUsername(nombreDeUsuarioConCeroCaracteres));
        assertThat(exception.getMessage()).isEqualTo(msjError);
    }
}
