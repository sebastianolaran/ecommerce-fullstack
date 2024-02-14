package org.sebastian.web;

import lombok.extern.slf4j.Slf4j;
import org.sebastian.domain.Producto;
import org.sebastian.service.producto.ProductoService;
import org.sebastian.service.producto.http.request.AgregarRequest;
import org.sebastian.service.producto.http.request.DeleteRequest;
import org.sebastian.service.producto.http.request.EditarRequest;
import org.sebastian.service.producto.http.response.ProductoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Integer.parseInt;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/productos")
public class ControladorProductos {

    @Autowired
    private ProductoService productoService;

    // Obtener la lista de productos
    @GetMapping("/")
    public ResponseEntity<List<Producto>> obtenerProductos() {
        List<Producto> productos = productoService.listarProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    // Guardar un nuevo producto
    @PostMapping("/agregar")
    public ProductoResponse guardar(@RequestBody AgregarRequest request, Errors errors) {
        Producto producto = new Producto(request.getNombre(), parseInt(request.getPrecio()), request.getCategoria(), request.getDescripcion());
        if (errors.hasErrors()) {
            // Si hay errores de validación, retorna un código de estado BAD_REQUEST

            return ProductoResponse.builder()
                    .mensaje("ERROR").build();
        } else {
            // Guarda el producto y retorna un código de estado CREATED
            productoService.guardar(producto);
            return ProductoResponse.builder()
                    .mensaje("Agregado con exito").build();
        }
    }

    // Obtener un producto para editar por su ID
    @GetMapping("/buscar/{id_producto}")
    public ResponseEntity<Producto> encontrarProducto(@PathVariable Long id_producto) {
        Producto producto = productoService.encontrarProducto(id_producto);
        if (producto != null) {
            // Si encuentra el producto, retorna el producto y un código de estado OK
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } else {
            // Si no encuentra el producto, retorna un código de estado NOT_FOUND
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }


    @PostMapping("/editar")
    public ProductoResponse editarProducto(@RequestBody EditarRequest request) {
        return (ProductoResponse.builder().mensaje(productoService.editarProducto(request)).build());
    }


    // Eliminar un producto por su ID
    @PostMapping("/eliminar")
    public ResponseEntity<Producto> eliminar(@RequestBody DeleteRequest request) {
        Long producto_id = request.getId_producto();
        if (producto_id != null) {
            // Si encuentra el producto, lo elimina y retorna un código de estado OK
            productoService.eliminar(String.valueOf(producto_id));
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            // Si no encuentra el producto, retorna un código de estado NOT_FOUND
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
