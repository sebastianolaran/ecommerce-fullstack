package org.sebastian.service.detalle_orden;

import org.sebastian.domain.Producto;
import org.sebastian.interfaces.ProductoConCantidad;

import java.util.List;

public interface DetalleOrdenService {


    List<ProductoConCantidad> obtenerProductos(Long idOrden);
}
