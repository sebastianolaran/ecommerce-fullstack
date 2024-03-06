package org.sebastian.service.producto;

import jakarta.persistence.EntityNotFoundException;
import org.sebastian.dao.ProductoDAO;
import org.sebastian.domain.Producto;

import org.sebastian.excepciones.ProductoExistente;
import org.sebastian.service.producto.http.request.EditarRequest;

import org.springframework.beans.factory.annotation.Autowired;

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
        if( this.existeProducto(producto.getNombre())){
            throw new ProductoExistente();
        }
        else {
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
            ///
        }

    }

    public String editarProducto(EditarRequest request) {
        String mensaje;
        try {
            // Recuperar la entidad existente
            Producto producto = productoDAO.findById(Long.valueOf(request.getId_producto())).orElseThrow(() -> new EntityNotFoundException("No se encontró el producto con ID: " + request.getId_producto()));

            // Actualizar los campos
            producto.setCategoria(request.getCategoria());
            producto.setDescripcion(request.getDescripcion());
            producto.setPrecio(Integer.parseInt(request.getPrecio()));

            // Guardar la entidad actualizada
            productoDAO.save(producto);

            mensaje= "Producto editado correctamente.";
        } catch (NumberFormatException e) {
            mensaje= "Error: El ID del producto no es válido.";
        } catch (EntityNotFoundException e) {
            mensaje= "Error: No se encontró el producto con el ID proporcionado.";
        } catch (Exception e) {
            mensaje ="Error inesperado al editar el producto. Detalles: " + e.getMessage();
        }
        return mensaje;
    }




    @Override
    public Producto encontrarProducto(Long id_producto) {
        return productoDAO.findById(id_producto).orElse(null);
    }
}
