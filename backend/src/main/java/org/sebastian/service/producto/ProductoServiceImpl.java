package org.sebastian.service.producto;

import jakarta.persistence.EntityNotFoundException;
import org.sebastian.dao.ProductoDAO;
import org.sebastian.domain.Producto;

import org.sebastian.excepciones.ProductoExistente;
import org.sebastian.service.producto.http.request.EditarRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoDAO productoDAO;


    @Override
    public List<Producto> listarProductos() {
        return (List<Producto>) productoDAO.findAll();
    }

    @Override
    public String guardar(Producto producto) throws ProductoExistente {
        String mensaje;
        if (this.existeProducto(producto.getNombre())) {
            throw new ProductoExistente();
        } else {
            productoDAO.save(producto);
            mensaje = "Guardado correcto";
        }

        return mensaje;

    }


    private boolean existeProducto(String nombre) {
        return productoDAO.encontrarPorNombre(nombre).isPresent();
    }

    @Override
    public void eliminar(String id_producto) {
        Optional<Producto> productoOptional = productoDAO.findById(Long.valueOf(id_producto));

        Producto producto = productoOptional.orElse(null); // or provide a default value

        if (producto != null) {
            productoDAO.delete(producto);
        } else {
            // Si el producto no se encuentra, podrías lanzar una excepción específica o simplemente no hacer nada
            throw new EntityNotFoundException("No se encontró el producto con ID: " + id_producto);
        }
    }

    public ResponseEntity<String> editarProducto(EditarRequest request) {
        try {
            Long idProducto = Long.valueOf(request.getId_producto());
            // Validar el ID del producto
            if (idProducto <= 0) {
                return ResponseEntity.badRequest().body("Error: El ID del producto no es válido.");
            }

            // Recuperar la entidad existente
            Producto producto = productoDAO.findById(idProducto)
                    .orElseThrow(() -> new EntityNotFoundException("No se encontró el producto con ID: " + idProducto));

            // Actualizar los campos
            producto.setCategoria(request.getCategoria());
            producto.setDescripcion(request.getDescripcion());
            producto.setPrecio(Integer.parseInt(request.getPrecio()));

            // Guardar la entidad actualizada
            productoDAO.save(producto);

            return ResponseEntity.ok("Producto editado correctamente.");
        } catch (NumberFormatException e) {
            // Manejar el error de formato de número
            return ResponseEntity.badRequest().body("Error: El precio del producto no es un número válido.");
        } catch (EntityNotFoundException e) {
            // Manejar el error de entidad no encontrada
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Manejar otros errores internos
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado al editar el producto. Detalles: " + e.getMessage());
        }
    }


    @Override
    public Producto encontrarProducto(Long id_producto) {
        return productoDAO.findById(id_producto).orElse(null);
    }
}
