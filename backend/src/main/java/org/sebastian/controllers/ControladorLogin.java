package org.sebastian.controllers;

import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sebastian.domain.Usuario;
import org.sebastian.service.auth.AuthResponse;
import org.sebastian.service.auth.AuthService;
import org.sebastian.service.auth.LoginRequest;
import org.sebastian.service.auth.RegisterRequest;
import org.sebastian.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/usuarios")
public class ControladorLogin {


    private final AuthService authService;

    @Autowired
    UsuarioService usuarioService;


    @GetMapping("/")
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioService.obtenerUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // Guardar un nuevo producto
    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@Valid @RequestBody Usuario usuario) {
        try {
            // Guarda el usuario
            usuarioService.guardar(usuario);
            // Devuelve el usuario guardado junto con el código de estado CREATED
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            // Maneja cualquier excepción que pueda ocurrir durante el proceso de guardado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el usuario: " + e.getMessage());
        }
    }


    @GetMapping("/editar/{id_usuario}")
    public ResponseEntity<?> editar(@PathVariable("id_usuario") Long idUsuario) {
        try {
            // Buscar el usuario por su ID
            Usuario usuarioEditado = usuarioService.encontrarUsuario(idUsuario);
            // Devolver el usuario encontrado con el código de estado OK
            return ResponseEntity.ok(usuarioEditado);
        } catch (NotFoundException e) {
            // Manejar el caso donde el usuario no es encontrado
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Manejar cualquier otra excepción que pueda ocurrir
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar el usuario: " + e.getMessage());
        }
    }



    // Eliminar un producto por su ID
    @DeleteMapping("/eliminar/{id_usuario}")
    public ResponseEntity<Usuario> eliminar(Usuario usuario) {
        if (usuario != null) {
            // Si encuentra el producto, lo elimina y retorna un código de estado OK
            usuarioService.eliminar(usuario);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            // Si no encuentra el producto, retorna un código de estado NOT_FOUND
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        try {
            // Asegúrate de que authService esté inicializado antes de llamar a los métodos de login
            return ResponseEntity.ok(authService.login(request));
        } catch (Exception e) {
            log.error("Error durante el inicio de sesión", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        try {
            // Asegúrate de que authService esté inicializado antes de llamar a los métodos de registro
            return ResponseEntity.ok(authService.register(request));
        } catch (Exception e) {
            log.error("Error durante el registro", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}

