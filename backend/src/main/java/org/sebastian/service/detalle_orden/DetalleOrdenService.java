package org.sebastian.service.detalle_orden;

import org.sebastian.domain.DetalleOrden;
import org.sebastian.domain.ProductoConCantidad;

import java.util.List;

public interface DetalleOrdenService {

    /**
     * Obtiene una lista de productos con su cantidad asociado al id
     *
     * @param idOrden El ID de la orden para la cual se desean obtener los productos.
     * @return Una lista de  productos asociados a la orden especificada.
     */
    List<ProductoConCantidad> obtenerProductos(String idOrden);


    /**
     * Guardar el detalle de orden en la base de datos
     *
     * @param detalleOrden El detalle de la orden correspondinete
     * @return Un string que indica el estado del guardado del detalle de la orden.
     */
    String guardar(DetalleOrden detalleOrden);
}
