package org.sebastian.service.orden;

import org.sebastian.domain.Orden;
import org.sebastian.service.orden.http.response.OrdenResponse;


import java.text.ParseException;
import java.util.Date;
import java.util.List;


public interface OrdenService {


    /**
     * @return Una lista de ordenes recuperadas de la base de datos.
     */
    List<Orden> obtenerOrdenes();


    /**
     * Agrega la orden a la base de datos
     * @param orden La orden a agregar a la base de datos
     */
    void guardar(Orden orden);

    /**
     * Elimina la orden con ese mismo id
     * @param id_orden El ID de la orden para eliminar de la base de datos

     */
    void eliminar(String id_orden);

    /**
     * Obtiene las ordenes realizadas en la fecha actual,una semana y un mes, junto con su monto total.
     * @param date Referencia a la fecha actual.
     * @return Un objeto con las ordenes y valores asociados
     */
    OrdenResponse obtenerOrdenesEnFechas(Date date) throws ParseException;

    /**
     * Genera un id_unico para cada orden

     */
    String generarIdUnico();
}
