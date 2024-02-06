package org.sebastian.service.detalle_orden;

import org.sebastian.dao.DetalleOrdenDAO;
import org.sebastian.dao.ProductoDAO;
import org.sebastian.domain.Producto;
import org.sebastian.interfaces.ProductoConCantidad;
import org.sebastian.service.producto.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DetalleOrdenServiceImpl implements DetalleOrdenService{


    @Autowired
    private DetalleOrdenDAO detalleOrdenDAO;

    @Autowired
    private ProductoService productoService;
    @Override
    public List<ProductoConCantidad> obtenerProductos(Long idOrden) {
        List<Long> id_productos = this.detalleOrdenDAO.obtenerIdsProductosDeOrden(idOrden);
        List<ProductoConCantidad> productos = new ArrayList<>();
        for(Long id : id_productos){
            Producto producto = productoService.encontrarProducto(id);
            int cantidadProducto = detalleOrdenDAO.obtenerCantidadDeProductoConId(idOrden,id);
            ProductoConCantidad productoConCantidad = new ProductoConCantidad(cantidadProducto,producto);
            productos.add(productoConCantidad);

        }
        return productos;
    }
}