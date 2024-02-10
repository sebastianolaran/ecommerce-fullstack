package org.sebastian.web;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sebastian.domain.DetalleOrden;
import org.sebastian.domain.Orden;
import org.sebastian.interfaces.ProductoConCantidad;
import org.sebastian.service.detalle_orden.DetalleOrdenService;
import org.sebastian.service.orden.OrdenService;
import org.sebastian.service.orden.http.AgregarOrdenRequest;
import org.sebastian.service.orden.http.AgregarProductoConOrdenRequest;
import org.sebastian.service.orden.http.OrdenResponse;
import org.sebastian.service.producto.http.DeleteRequest;
import org.sebastian.service.producto.http.ProductoOrdenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public ResponseEntity<?> guardar(@RequestBody @Valid AgregarOrdenRequest request, Errors errors) {
        if (errors.hasErrors()) {
            // Si hay errores de validación, retorna un código de estado BAD_REQUEST
            return ResponseEntity.badRequest().body("Datos inválidos");
        } else {
            // Crea un objeto Orden a partir de los datos de la solicitud
            Orden orden = new Orden(request.getIdOrden(), (double) request.getValorTotal(), new Date(), request.getEstado());

            // Guarda la orden
            ordenService.guardar(orden);

            // Retorna un código de estado CREATED
            return ResponseEntity.status(HttpStatus.CREATED).build();
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
            // Si encuentra el producto, lo elimina y retorna un código de estado OK
            ordenService.eliminar(String.valueOf(orden_id));
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            // Si no encuentra el producto, retorna un código de estado NOT_FOUND
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
    public String obtenerId(){
        return ordenService.generarIdUnico();
    }


    @PostMapping("/productos/agregar")
    public ResponseEntity<DetalleOrden> agregarProductoDeOrden(@RequestBody AgregarProductoConOrdenRequest request) {
        DetalleOrden orden = new DetalleOrden(request.getId_orden(),request.getId_producto(), request.getCantidad(), request.getPrecio_unitario());
        detalleOrdenService.guardar(orden);
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
