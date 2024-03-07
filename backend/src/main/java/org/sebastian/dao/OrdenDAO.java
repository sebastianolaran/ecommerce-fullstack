package org.sebastian.dao;


import org.sebastian.domain.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrdenDAO extends JpaRepository<Orden, Long> {

    /**
     * Obtiene el monto total y la cantidad total de ventas realizadas en el dia de la fecha.
     *
     * @param fechaActual La fecha para la cual se desean obtener las estadísticas de ventas diarias.
     * @return Una lista de objetos donde el primer elemento es el monto total y el segundo elemento es la cantidad total de ventas diarias.
     */


    @Query("SELECT SUM(o.monto) AS montoTotal, COUNT(o) AS cantidadTotal " +
            "FROM Orden o " +
            "WHERE DATE(o.fecha) = :fechaActual")
    List<Object[]> obtenerMontoYCantidadVentasDiarias(@Param("fechaActual") Date fechaActual);

    /**
     * Obtiene el monto total y la cantidad total de ventas realizadas en la semana correspondiente de la fecha actual.
     *
     * @param fechaActual La fecha para la cual se desean obtener las estadísticas de ventas semanales.
     * @return Una lista de objetos donde el primer elemento es el monto total y el segundo elemento es la cantidad total de ventas semanales.
     */


    @Query("SELECT SUM(o.monto) AS montoTotal, COUNT(o) AS cantidadTotal " +
            "FROM Orden o " +
            "WHERE YEAR(o.fecha) = YEAR(:fechaActual) AND WEEK(o.fecha) = WEEK(:fechaActual)")
    List<Object[]> obtenerMontoYCantidadVentasSemanales(@Param("fechaActual") Date fechaActual);


    /**
     * Obtiene el monto total y la cantidad total de ventas realizadas en el mes correspondiente a la fecha actual.
     *
     * @param fechaActual La fecha para la cual se desean obtener las estadísticas de ventas mensuales.
     * @return Una lista de objetos donde el primer elemento es el monto total y el segundo elemento es la cantidad total de ventas mensuales.
     */


    @Query("SELECT SUM(o.monto) AS montoTotal, COUNT(o) AS cantidadTotal " +
            "FROM Orden o " +
            "WHERE YEAR(o.fecha) = YEAR(:fechaActual) AND MONTH(o.fecha) = MONTH(:fechaActual)")
    List<Object[]> obtenerMontoYCantidadVentasMensuales(@Param("fechaActual") Date fechaActual);

}
