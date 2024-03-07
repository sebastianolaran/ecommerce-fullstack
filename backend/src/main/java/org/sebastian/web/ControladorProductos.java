package org.sebastian.web;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.sebastian.domain.Producto;
import org.sebastian.excepciones.ContraseñaIncorrectaExcepcion;
import org.sebastian.excepciones.EmailNoEncontrado;
import org.sebastian.excepciones.ProductoExistente;
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
    public ProductoResponse guardar(@RequestBody AgregarRequest request) throws ProductoExistente {
        ProductoResponse productoResponse = new ProductoResponse();
        Producto producto = new Producto(request.getNombre(), parseInt(request.getPrecio()), request.getCategoria(), request.getDescripcion());
        try {
            String mensaje = productoService.guardar(producto);
            productoResponse.setMensaje(mensaje);
        } catch (ProductoExistente e) {
            productoResponse.setError(e.getMessage());
        }
        return productoResponse;
    }


    // Obtener un producto para editar por su ID
    @GetMapping("/buscar/{idProducto}")
    public ResponseEntity<Producto> encontrarProductoPorId(@PathVariable Long idProducto) {
        // Buscar el producto por su ID
        Producto producto = productoService.encontrarProducto(idProducto);

        // Verificar si se encontró el producto
        if (producto != null) {
            // Si se encontró el producto, retorna el producto y un código de estado OK
            return ResponseEntity.ok(producto);
        } else {
            // Si no se encontró el producto, retorna un código de estado NOT_FOUND
            return ResponseEntity.notFound().build();
        }
    }




    @PostMapping("/editar")
    public ResponseEntity<String> editarProducto(@RequestBody EditarRequest request) {
        return productoService.editarProducto(request);
    }


    @PostMapping("/eliminar")
    public ResponseEntity<Void> eliminar(@RequestBody DeleteRequest request) {
        Long productoId = request.getId_producto();

        if (productoId != null) {
            try {
                productoService.eliminar(String.valueOf(productoId));
                return ResponseEntity.ok().build(); // Producto eliminado exitosamente
            } catch (EntityNotFoundException e) {
                return ResponseEntity.notFound().build(); // Producto no encontrado
            }
        } else {
            return ResponseEntity.badRequest().build(); // ID de producto no válido
        }
    }

}
