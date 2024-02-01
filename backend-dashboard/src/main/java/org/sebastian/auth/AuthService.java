package org.sebastian.auth;


import org.sebastian.domain.Role;
import org.sebastian.domain.Usuario;
import org.sebastian.jwt.JwtService;
import org.sebastian.service.usuario.UsuarioService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioService userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            Usuario user = userRepository.encontrarUsuarioPorEmail(request.getEmail()).orElseThrow();

            String token = jwtService.getToken(user);
            return AuthResponse.builder()
                    .token(token)
                    .mensaje("Login Correcto")
                    .rol(String.valueOf(user.getRol()))
                    .build();
        } catch (AuthenticationException e) {
            // Manejar la excepción de autenticación fallida
            return AuthResponse.builder()
                    .mensaje("Login Incorrecto")
                    .build();
        }
    }


    public AuthResponse register(RegisterRequest request) {
        try {
            Usuario user = Usuario.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .email(request.getEmail())
                    .rol(Role.USER)
                    .build();

            userRepository.guardar(user);

            String token = jwtService.getToken(user);
            return AuthResponse.builder()
                    .token(token)
                    .mensaje("Registro Exitoso")
                    .rol(String.valueOf(user.getRol()))
                    .build();
        } catch (Exception e) {
            // Manejar la excepción de registro fallido
            return AuthResponse.builder()
                    .mensaje("Error en el Registro: " + e.getMessage())
                    .build();
        }
    }

}