package org.sebastian.service.orden;

import org.sebastian.domain.Orden;
import org.sebastian.service.orden.http.OrdenResponse;


import java.text.ParseException;
import java.util.Date;
import java.util.List;


public interface OrdenService {


    List<Orden> obtenerOrdenes();

    void guardar(Orden orden);

    void eliminar(String orden);

    OrdenResponse obtenerOrdenesEnFechas(Date date) throws ParseException;

    String generarIdUnico();
}
