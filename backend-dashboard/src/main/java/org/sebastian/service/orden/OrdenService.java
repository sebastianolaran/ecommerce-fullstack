package org.sebastian.service.orden;

import org.sebastian.domain.Orden;
import org.sebastian.service.orden.http.OrdenResponse;


import java.text.ParseException;
import java.util.Date;
import java.util.List;


public interface OrdenService {


    public List<Orden> obtenerOrdenes();

    public void guardar(Orden orden);

    public void eliminar(Orden orden);

    OrdenResponse obtenerOrdenesEnFechas(Date date) throws ParseException;
}
