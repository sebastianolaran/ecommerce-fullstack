package org.sebastian.web;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sebastian.domain.Orden;
import org.sebastian.domain.Producto;
import org.sebastian.interfaces.ProductoConCantidad;
import org.sebastian.service.detalle_orden.DetalleOrdenService;
import org.sebastian.service.orden.OrdenService;
import org.sebastian.service.orden.http.OrdenResponse;
import org.sebastian.service.producto.http.DeleteRequest;
import org.sebastian.service.producto.http.ProductoOrdenRequest;
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
    public ResponseEntity<?> guardar(@Valid @RequestBody Orden orden, Errors errors) {
        if (errors.hasErrors()) {
            // Si hay errores de validación, retorna un código de estado BAD_REQUEST
            return new ResponseEntity<>("Datos inválidos", HttpStatus.BAD_REQUEST);
        } else {
            // Guarda el producto y retorna un código de estado CREATED
            ordenService.guardar(orden);
            return new ResponseEntity<>(HttpStatus.CREATED);
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


}
