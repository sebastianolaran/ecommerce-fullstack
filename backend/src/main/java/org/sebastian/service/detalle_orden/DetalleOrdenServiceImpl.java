package org.sebastian.service.detalle_orden;

import org.sebastian.dao.DetalleOrdenDAO;
import org.sebastian.domain.DetalleOrden;
import org.sebastian.domain.Producto;
import org.sebastian.domain.ProductoConCantidad;
import org.sebastian.service.producto.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DetalleOrdenServiceImpl implements DetalleOrdenService {


    @Autowired
    private DetalleOrdenDAO detalleOrdenDAO;

    @Autowired
    private ProductoService productoService;

    @Override
    public List<ProductoConCantidad> obtenerProductos(String idOrden) {

        List<Long> id_productos = this.detalleOrdenDAO.obtenerIdsProductosDeOrden(idOrden);
        List<ProductoConCantidad> productos = new ArrayList<>();
        for (Long id : id_productos) {
            Producto producto = productoService.encontrarProducto(id);
            int cantidadProducto = detalleOrdenDAO.obtenerCantidadDeProductoConId(idOrden, id);
            ProductoConCantidad productoConCantidad = new ProductoConCantidad(cantidadProducto, producto);
            productos.add(productoConCantidad);

        }
        return productos;
    }

    @Override
    public String guardar(DetalleOrden detalleOrden) {
        try {
            detalleOrdenDAO.save(detalleOrden);
            return "Ok"; // Operación exitosa
        } catch (Exception e) {
            return "Error: " + e.getMessage(); // Error durante la operación de guardado
        }
    }
}
