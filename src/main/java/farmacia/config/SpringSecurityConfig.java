package farmacia.config;

import ch.qos.logback.core.net.LoginAuthenticator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private UserDetailsService detallesUsuarioService;

    public SpringSecurityConfig(UserDetailsService detallesUsuarioService){
        this.detallesUsuarioService = detallesUsuarioService;
    }

    @Bean
    public SecurityFilterChain filtro(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request -> {
                    request
                            .anyRequest()
                            .authenticated();
                })
                .formLogin(login ->
                        login
                                .loginPage("/login-page")
                                .defaultSuccessUrl("/", true)
                        .failureUrl("/login-page?error=true")
                        .permitAll()
                )
                .build();
    }

    @Bean
    public AuthenticationProvider provedorAuth(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(detallesUsuarioService);
        return provider;
    }

}
