package org.sebastian.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.sebastian.domain.Producto;
import org.sebastian.service.producto.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/productos")
public class ControladorInicio {

    @Autowired
    private ProductoService productoService;

    // Obtener la lista de productos
    @GetMapping("/")
    public ResponseEntity<List<Producto>> obtenerProductos() {
        List<Producto> productos = productoService.listarProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    // Guardar un nuevo producto
    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@Valid @RequestBody Producto producto, Errors errors) {
        if (errors.hasErrors()) {
            // Si hay errores de validación, retorna un código de estado BAD_REQUEST
            return new ResponseEntity<>("Datos inválidos", HttpStatus.BAD_REQUEST);
        } else {
            // Guarda el producto y retorna un código de estado CREATED
            productoService.guardar(producto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    // Obtener un producto para editar por su ID
    @GetMapping("/editar/{id_producto}")
    public ResponseEntity<Producto> editar(Producto producto) {
        Producto productoEditado = productoService.encontrarProducto(producto);
        if (producto != null) {
            // Si encuentra el producto, retorna el producto y un código de estado OK
            return new ResponseEntity<>(productoEditado, HttpStatus.OK);
        } else {
            // Si no encuentra el producto, retorna un código de estado NOT_FOUND
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    // Eliminar un producto por su ID
    @DeleteMapping("/eliminar/{id_producto}")
    public ResponseEntity<Producto> eliminar(Producto producto) {
        if (producto != null) {
            // Si encuentra el producto, lo elimina y retorna un código de estado OK
            productoService.eliminar(producto);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            // Si no encuentra el producto, retorna un código de estado NOT_FOUND
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
