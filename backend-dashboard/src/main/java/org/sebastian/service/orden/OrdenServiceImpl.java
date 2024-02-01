package org.sebastian.service.orden;

import org.sebastian.dao.OrdenDAO;

import org.sebastian.domain.Orden;

import org.sebastian.service.orden.http.OrdenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service

public class OrdenServiceImpl implements OrdenService {

    @Autowired
    private OrdenDAO ordenDAO;


    @Override
    public List<Orden> obtenerOrdenes() {
        return ordenDAO.findAll();
    }

    @Override
    public void guardar(Orden orden) {
        ordenDAO.save(orden);
    }

    @Override
    public void eliminar(Orden orden) {
        ordenDAO.delete(orden);

    }

    @Override
    public OrdenResponse obtenerOrdenesEnFechas(Date date) throws ParseException {
        // Formato de la fecha y hora
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");


        // Convertir la fecha y hora a una cadena con el formato deseado
        String fechaFormateada = formato.format(date);

        Date fechaFormateadaDate = formato.parse(fechaFormateada);

        // Llamar al método del repositorio para obtener la información
        List<Object[]> resultadosDiarios = ordenDAO.obtenerMontoYCantidadVentasDiarias(fechaFormateadaDate);

        List<Object[]> resultadosSemanales = ordenDAO.obtenerMontoYCantidadVentasSemanales(fechaFormateadaDate);

        List<Object[]> resultadosMensuales = ordenDAO.obtenerMontoYCantidadVentasMensuales(fechaFormateadaDate);

        // Verificar si hay resultados
        OrdenResponse ordenResponse = null;
        if (!resultadosDiarios.isEmpty()) {
            Object[] resultadosDiariosList = resultadosDiarios.get(0);
            Object[] resultadosSemanalesList = resultadosSemanales.get(0);
            Object[] resultadosMensualesList = resultadosMensuales.get(0);

            // Acceder a los valores del resultado
            Double montoDiario = (Double) resultadosDiariosList[0];
            Long cantidadDiaria = (Long) resultadosDiariosList[1];

            Double montoSemanal = (Double) resultadosSemanalesList[0];
            Long cantidadSemanal = (Long) resultadosSemanalesList[1];

            Double montoMensual= (Double) resultadosMensualesList[0];
            Long cantidadMensual = (Long) resultadosMensualesList[1];

            // Puedes utilizar estos valores para construir tu respuesta
            // Por ejemplo, puedes construir un objeto OrdenResponse
            ordenResponse = new OrdenResponse();
            ordenResponse.setMontoDiario(montoDiario);
            ordenResponse.setVentasDiarias(Math.toIntExact(cantidadDiaria));
            ordenResponse.setVentasSemanal(Math.toIntExact(cantidadSemanal));
            ordenResponse.setMontoSemanal(montoSemanal);
            ordenResponse.setMontoMensual(montoMensual);
            ordenResponse.setVentasMensual(Math.toIntExact(cantidadMensual));


        }
        return ordenResponse;
    }


}