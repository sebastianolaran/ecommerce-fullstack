package org.sebastian.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/usuarios")
public class ControladorLogin {


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



}

