package org.sebastian.auth;


import lombok.RequiredArgsConstructor;
import org.sebastian.domain.Role;
import org.sebastian.domain.Usuario;
import org.sebastian.service.usuario.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioService userRepository;

    /**
     * Autentica al usuario utilizando las credenciales proporcionadas en el objeto LoginRequest.
     * Si la autenticación tiene éxito, se genera un token JWT para el usuario autenticado.
     *
     * @param request El objeto LoginRequest que contiene las credenciales del usuario.
     * @return Un objeto AuthResponse que contiene el token JWT en caso de éxito, o un mensaje de error en caso de fallo.
     */
    public AuthResponse login(LoginRequest request) {
        try {
            // Recuperar el usuario por su correo electrónico
            Optional<Usuario> optionalUser = userRepository.encontrarUsuarioPorEmail(request.getEmail());

            // Verificar si el usuario existe y si la contraseña coincide
            if (optionalUser.isPresent()) {
                Usuario user = optionalUser.get();

                if (request.getPassword().equals(user.getPassword())) {
                    return AuthResponse.builder()
                            .mensaje("Login exitoso")
                            .build();
                } else {
                    return AuthResponse.builder()
                            .mensaje("Contraseña incorrecta")
                            .build();
                }
            } else {
                return AuthResponse.builder()
                        .mensaje("Usuario no encontrado")
                        .build();
            }
        } catch (Exception e) {
            // Manejar la excepción de autenticación fallida
            return AuthResponse.builder()
                    .mensaje("Error durante la autenticación: " + e.getMessage())
                    .build();
        }
    }


    //Esta funcion se encarga de la distribucion de llamados al momento de hacer el login

    public AuthResponse register(RegisterRequest request) {
        try {
            //Creacion de usuario con los datos obtenidos en el llamado a la API
            Usuario user = Usuario.builder()
                    .username(request.getUsername())
                    .password(request.getPassword())
                    .email(request.getEmail())
                    .rol(Role.USER)
                    .build();

            userRepository.guardar(user);


            return AuthResponse.builder()
                    .mensaje("Registro Exitoso")
                    .build();
        } catch (Exception e) {
            // Manejar la excepción de registro fallido
            return AuthResponse.builder()
                    .mensaje("Error en el Registro: " + e.getMessage())
                    .build();
        }
    }





}