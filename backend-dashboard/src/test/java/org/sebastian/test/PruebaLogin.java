package org.sebastian.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.sebastian.auth.AuthResponse;
import org.sebastian.auth.AuthService;
import org.sebastian.auth.LoginRequest;
import org.sebastian.domain.Usuario;
import org.sebastian.jwt.JwtService;
import org.sebastian.service.usuario.UsuarioServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PruebaLogin {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UsuarioServiceImpl userRepository;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthService tuClaseConMetodoDeLogin;

    @Test
    public void pruebaLogin() {
        // Configura el comportamiento esperado del UserRepository y JwtService según tus necesidades
        when(userRepository.encontrarUsuarioPorUsername(anyString())).thenReturn(Optional.of(new Usuario()));
        when(jwtService.getToken(any(Usuario.class))).thenReturn("TOKEN_GENERADO");

        // Ejecuta el método de login con un LoginRequest (puedes crear uno para la prueba)
        AuthResponse authResponse = tuClaseConMetodoDeLogin.login(new LoginRequest("nombreUsuario", "contraseña"));

        // Verifica que el método authenticate del AuthenticationManager fue llamado
        verify(authenticationManager).authenticate(any());

        // Verifica que el token devuelto es el esperado
        assertEquals("TOKEN_GENERADO", authResponse.getToken());
    }
}
