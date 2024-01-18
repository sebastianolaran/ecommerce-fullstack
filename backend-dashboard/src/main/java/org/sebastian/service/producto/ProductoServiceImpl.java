package org.sebastian.service.producto;

import org.sebastian.dao.ProductoDAO;
import org.sebastian.domain.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
    public void eliminar(Producto producto) {
        productoDAO.delete(producto);
    }

    @Override
    public Producto encontrarProducto(Producto producto) {
        return productoDAO.findById(producto.getId_producto()).orElse(null);
    }
}
