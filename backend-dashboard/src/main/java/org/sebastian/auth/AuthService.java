package org.sebastian.auth;


import lombok.RequiredArgsConstructor;
import org.sebastian.domain.Usuario;
import org.sebastian.service.usuario.UsuarioService;
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
     * @return Un objeto AuthResponse que contiene el mensaje que indica como resulto el login
     */
    public AuthResponse login(LoginRequest request) {

        return AuthResponse.builder()
                .mensaje(userRepository.verificarLogin(request.getEmail(), request.getPassword()))
                .build();

    }


    //Esta funcion se encarga de la distribucion de llamados al momento de hacer el login

    public AuthResponse register(RegisterRequest request) {

            //Creacion de usuario con los datos obtenidos en el llamado a la API
            Usuario user = Usuario.builder()
                    .username(request.getUsername())
                    .password(request.getPassword())
                    .email(request.getEmail())
                    .build();


            return AuthResponse.builder()
                    .mensaje(userRepository.guardar(user))
                    .build();

    }


}