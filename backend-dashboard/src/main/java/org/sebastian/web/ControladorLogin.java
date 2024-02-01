package org.sebastian.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sebastian.auth.AuthResponse;
import org.sebastian.auth.AuthService;
import org.sebastian.auth.LoginRequest;
import org.sebastian.auth.RegisterRequest;
import org.sebastian.domain.Usuario;
import org.sebastian.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
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
    public ResponseEntity<?> guardar(@Valid @RequestBody Usuario usuario, Errors errors) {
        if (errors.hasErrors()) {
            // Si hay errores de validación, retorna un código de estado BAD_REQUEST
            return new ResponseEntity<>("Datos inválidos", HttpStatus.BAD_REQUEST);
        } else {
            // Guarda el producto y retorna un código de estado CREATED
            usuarioService.guardar(usuario);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    // Obtener un producto para editar por su ID
    @GetMapping("/editar/{id_usuario}")
    public ResponseEntity<Usuario> editar(Usuario usuario) {
        Usuario usuarioEditado = usuarioService.encontrarUsuario(usuario);
        if (usuario != null) {
            // Si encuentra el producto, retorna el producto y un código de estado OK
            return new ResponseEntity<>(usuarioEditado, HttpStatus.OK);
        } else {
            // Si no encuentra el producto, retorna un código de estado NOT_FOUND
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

