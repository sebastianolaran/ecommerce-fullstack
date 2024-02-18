package org.sebastian.web;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sebastian.domain.DetalleOrden;
import org.sebastian.domain.Orden;
import org.sebastian.domain.ProductoConCantidad;
import org.sebastian.service.detalle_orden.DetalleOrdenService;
import org.sebastian.service.orden.OrdenService;
import org.sebastian.service.orden.http.request.AgregarOrdenRequest;
import org.sebastian.service.orden.http.request.AgregarProductoConOrdenRequest;
import org.sebastian.service.orden.http.response.AgregarProductoConOrdenResponse;
import org.sebastian.service.orden.http.response.IdResponse;
import org.sebastian.service.orden.http.response.OrdenResponse;
import org.sebastian.service.producto.http.request.DeleteRequest;
import org.sebastian.service.producto.http.request.ProductoOrdenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/ordenes")
public class ControladorOrden {


    @Autowired
    OrdenService ordenService;


    @Autowired
    DetalleOrdenService detalleOrdenService;


    @GetMapping("/")
    public ResponseEntity<List<Orden>> obtenerOrdenes() {
        List<Orden> ordenes = ordenService.obtenerOrdenes();
        return new ResponseEntity<>(ordenes, HttpStatus.OK);
    }


    @PostMapping("/guardar")
    public ResponseEntity<String> guardar(@RequestBody @Valid AgregarOrdenRequest request, Errors errors) {
        if (errors.hasErrors()) {
            // Si hay errores de validación, retorna un código de estado BAD_REQUEST
            return ResponseEntity.badRequest().body("Datos inválidos");
        } else {
            // Crea un objeto Orden a partir de los datos de la solicitud
            Orden orden = new Orden(request.getIdOrden(), (double) request.getValorTotal(), new Date(), request.getEstado());

            // Guarda la orden
            ordenService.guardar(orden);

            // Retorna un código de estado OK junto con un mensaje
            return ResponseEntity.ok("Orden guardada correctamente");
        }
    }




    @GetMapping("/info")
    public OrdenResponse obtenerOrdenesEnFecha() throws ParseException {
        Date fechaActual = new Date();

        return ordenService.obtenerOrdenesEnFechas(fechaActual);

    }

    @PostMapping("/eliminar")
    public ResponseEntity<Orden> eliminar(@RequestBody DeleteRequest request) {
        Long orden_id = request.getId_producto();
        if (orden_id != null) {
            ordenService.eliminar(String.valueOf(orden_id));
            return new ResponseEntity<>(HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/productos")
    public ResponseEntity<List<ProductoConCantidad>> obtenerProductos(@RequestBody ProductoOrdenRequest request) {
        Long orden_id = request.getId_orden();
        List<ProductoConCantidad> productos = detalleOrdenService.obtenerProductos(orden_id);
        return new ResponseEntity<>(productos, HttpStatus.OK);

    }


    @GetMapping("/id")
    public ResponseEntity<IdResponse> obtenerId() {
        String idUnico = ordenService.generarIdUnico();

        // Crear un objeto IdResponse y establecer el ID
        IdResponse idResponse = new IdResponse();
        idResponse.setId_orden(idUnico);

        // Devolver el objeto IdResponse como respuesta con el código de estado OK (200)
        return ResponseEntity.ok(idResponse);
    }



    @PostMapping("/producto/agregar")
    public ResponseEntity<AgregarProductoConOrdenResponse> agregarProductoDeOrden(@RequestBody AgregarProductoConOrdenRequest request) {
        DetalleOrden orden = new DetalleOrden(request.getId_orden(), request.getId_producto(), request.getCantidad(), request.getPrecio_unitario());
        String mensaje = detalleOrdenService.guardar(orden);
        AgregarProductoConOrdenResponse agregarProductoConOrdenResponse = new AgregarProductoConOrdenResponse();
        agregarProductoConOrdenResponse.setMensaje(mensaje);
        // Agregar cualquier otra lógica de respuesta si es necesario

        return new ResponseEntity<>(agregarProductoConOrdenResponse, HttpStatus.OK);
    }


}
