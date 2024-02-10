package org.sebastian.dao;


import org.sebastian.domain.Orden;
import org.sebastian.service.orden.http.OrdenResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrdenDAO extends JpaRepository<Orden,Long> {

    @Query("SELECT SUM(o.monto) AS montoTotal, COUNT(o) AS cantidadTotal " +
            "FROM Orden o " +
            "WHERE DATE(o.fecha) = :fechaActual")
    List<Object[]> obtenerMontoYCantidadVentasDiarias(@Param("fechaActual") Date fechaActual);

    @Query("SELECT SUM(o.monto) AS montoTotal, COUNT(o) AS cantidadTotal " +
            "FROM Orden o " +
            "WHERE YEAR(o.fecha) = YEAR(:fechaActual) AND WEEK(o.fecha) = WEEK(:fechaActual)")
    List<Object[]> obtenerMontoYCantidadVentasSemanales(@Param("fechaActual") Date fechaActual);


    @Query("SELECT SUM(o.monto) AS montoTotal, COUNT(o) AS cantidadTotal " +
            "FROM Orden o " +
            "WHERE YEAR(o.fecha) = YEAR(:fechaActual) AND MONTH(o.fecha) = MONTH(:fechaActual)")
    List<Object[]> obtenerMontoYCantidadVentasMensuales(@Param("fechaActual") Date fechaActual);




}
