package org.sebastian.service.orden;

import org.sebastian.dao.OrdenDAO;
import org.sebastian.domain.Orden;
import org.sebastian.service.orden.http.OrdenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrdenServiceImpl implements OrdenService {

    private final OrdenDAO ordenDAO;

    @Autowired
    public OrdenServiceImpl(OrdenDAO ordenDAO) {
        this.ordenDAO = ordenDAO;
    }

    @Override
    public List<Orden> obtenerOrdenes() {
        return ordenDAO.findAll();
    }

    @Override
    public void guardar(Orden orden) {
        // Asigna un valor para id_orden si aún no lo tiene
        if (orden.getId_orden() == null) {
            orden.setId_orden(2222L); // Asigna un ID único
        }

        try {
            // Guarda la orden en la base de datos
            ordenDAO.save(orden);
        } catch (Exception e) {
            // Maneja cualquier excepción que pueda ocurrir al guardar la orden
            e.printStackTrace(); // O maneja la excepción de acuerdo a tus necesidades
        }
    }


    @Override
    public void eliminar(String id_orden) {
        Optional<Orden> ordenOptional = ordenDAO.findById(Long.valueOf(id_orden));

        Orden orden = ordenOptional.orElse(null); // or provide a default value

        if (orden != null) {
            ordenDAO.delete(orden);
        } else {
            ///
        }
    }

    @Override
    public OrdenResponse obtenerOrdenesEnFechas(Date date) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormateada = formato.format(date);

        try {
            Date fechaFormateadaDate = formato.parse(fechaFormateada);

            // Llamar al método del repositorio para obtener la información
            List<Object[]> resultadosDiarios = ordenDAO.obtenerMontoYCantidadVentasDiarias(fechaFormateadaDate);
            List<Object[]> resultadosSemanales = ordenDAO.obtenerMontoYCantidadVentasSemanales(fechaFormateadaDate);
            List<Object[]> resultadosMensuales = ordenDAO.obtenerMontoYCantidadVentasMensuales(fechaFormateadaDate);

            // Procesar los resultados
            if (!resultadosDiarios.isEmpty()) {
                Object[] resultadosDiariosList = resultadosDiarios.get(0);
                Object[] resultadosSemanalesList = resultadosSemanales.get(0);
                Object[] resultadosMensualesList = resultadosMensuales.get(0);

                OrdenResponse ordenResponse = new OrdenResponse();
                asignarValoresOrdenResponse(resultadosDiariosList, ordenResponse, "Diario");
                asignarValoresOrdenResponse(resultadosSemanalesList, ordenResponse, "Semanal");
                asignarValoresOrdenResponse(resultadosMensualesList, ordenResponse, "Mensual");

                return ordenResponse;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String generarIdUnico() {
        try {
            // Obtener instancia de MessageDigest con algoritmo MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Generar un hash único a partir de una cadena aleatoria (por ejemplo, el timestamp actual)
            String randomString = String.valueOf(System.currentTimeMillis());
            byte[] hashBytes = md.digest(randomString.getBytes());

            // Convertir los bytes del hash a una representación legible (Base64 en este caso)
            String hashString = Base64.getEncoder().encodeToString(hashBytes);

            // Devolver el hash como una cadena
            return hashString;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; // Manejo de errores
        }
    }

    private void asignarValoresOrdenResponse(Object[] resultadosList, OrdenResponse ordenResponse, String tipo) {
        Double monto = (Double) resultadosList[0];
        Long cantidad = (Long) resultadosList[1];

        if ("Diario".equals(tipo)) {
            ordenResponse.setMontoDiario(monto);
            ordenResponse.setVentasDiarias(Math.toIntExact(cantidad));
        } else if ("Semanal".equals(tipo)) {
            ordenResponse.setMontoSemanal(monto);
            ordenResponse.setVentasSemanal(Math.toIntExact(cantidad));
        } else if ("Mensual".equals(tipo)) {
            ordenResponse.setMontoMensual(monto);
            ordenResponse.setVentasMensual(Math.toIntExact(cantidad));
        }
    }
}
