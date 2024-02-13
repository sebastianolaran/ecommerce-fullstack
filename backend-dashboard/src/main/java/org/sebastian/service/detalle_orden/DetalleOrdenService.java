package org.sebastian.service.detalle_orden;

import org.sebastian.domain.DetalleOrden;
import org.sebastian.interfaces.ProductoConCantidad;

import java.util.List;

public interface DetalleOrdenService {


    List<ProductoConCantidad> obtenerProductos(Long idOrden);

    String guardar(DetalleOrden detalleOrden);
}
