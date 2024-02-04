package org.sebastian.service.producto;

import org.sebastian.dao.ProductoDAO;
import org.sebastian.domain.Producto;

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
    public void guardar(Producto producto) {
        productoDAO.save(producto);
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

    @Override
    public Producto encontrarProducto(Long id_producto) {
        return productoDAO.findById(id_producto).orElse(null);
    }
}
