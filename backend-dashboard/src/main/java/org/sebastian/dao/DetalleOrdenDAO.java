package org.sebastian.dao;

import org.sebastian.domain.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetalleOrdenDAO extends JpaRepository<Orden,Long> {

    @Query("SELECT p.id_producto " +
            "FROM Producto p " +
            "JOIN DetalleOrden op ON p.id_producto = op.producto " +
            "WHERE op.id_orden = :idOrden")
    List<Long> obtenerIdsProductosDeOrden(@Param("idOrden") Long idOrden);


    @Query("SELECT do.cantidad " +
            "FROM DetalleOrden do " +
            "WHERE do.id_orden = :idOrden AND do.producto = :id")
    int obtenerCantidadDeProductoConId(Long idOrden, Long id);
}
