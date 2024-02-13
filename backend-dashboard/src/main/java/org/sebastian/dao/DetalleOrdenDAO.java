package org.sebastian.dao;

import org.sebastian.domain.DetalleOrden;
import org.sebastian.domain.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetalleOrdenDAO extends JpaRepository<DetalleOrden, Long> {

    /**
     * Obtiene una lista de IDs de productos asociados a una orden específica.
     * @param idOrden El ID de la orden para la cual se desean obtener los IDs de productos.
     * @return Una lista de IDs de productos asociados a la orden especificada.
     */


    @Query("SELECT p.id_producto " +
            "FROM Producto p " +
            "JOIN DetalleOrden op ON p.id_producto = op.producto " +
            "WHERE op.id_orden = :idOrden")
    List<Long> obtenerIdsProductosDeOrden(@Param("idOrden") Long idOrden);


    /**
     * Obtiene la cantidad de un producto asociado a una orden específica.
     * @param idOrden El ID de la orden a la cual está asociado el producto.
     * @param id El ID del producto del cual se desea obtener la cantidad.
     * @return La cantidad del producto asociado a la orden especificada.
     */


    @Query("SELECT do.cantidad " +
            "FROM DetalleOrden do " +
            "WHERE do.id_orden = :idOrden AND do.producto = :id")
    int obtenerCantidadDeProductoConId(Long idOrden, Long id);
}