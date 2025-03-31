package farmacia.service.impl;

import farmacia.domain.Usuario;
import farmacia.domain.UsuarioEjecucion;
import farmacia.repository.interfaces.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UsuarioService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService( UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username + " Vacio");

        Usuario usuarioBD = usuarioRepository.findFirstBynombre(username);

        Assert.isTrue(!username.isEmpty(),"El nombre de usuario es invalido");
        Assert.notNull(usuarioBD,"El usuario ingresado es inexistente");

        return new UsuarioEjecucion(usuarioBD);
    }
}
