package org.sebastian.service.producto;

import org.sebastian.domain.Producto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public interface ProductoService {

    public List<Producto> listarProductos();

    public void guardar(Producto producto);

    public void eliminar(String id_producto);


    public Producto encontrarProducto(Long id_producto);
}
