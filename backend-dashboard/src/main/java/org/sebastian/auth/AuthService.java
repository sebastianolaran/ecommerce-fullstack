package org.sebastian.auth;

import org.sebastian.dao.UsuarioDAO;
import org.sebastian.domain.Role;
import org.sebastian.domain.Usuario;
import org.sebastian.jwt.JwtService;
import org.sebastian.service.usuario.UsuarioService;
import org.sebastian.service.usuario.UsuarioServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        Usuario user = userRepository.encontrarUsuarioPorUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();

    }

    public AuthResponse register(RegisterRequest request) {
        Usuario user = Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .rol(Role.USER)
                .build();

        userRepository.guardar(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }

}