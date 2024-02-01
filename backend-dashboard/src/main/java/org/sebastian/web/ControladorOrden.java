package org.sebastian.web;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sebastian.domain.Orden;
import org.sebastian.service.orden.OrdenService;
import org.sebastian.service.orden.http.OrdenResponse;
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


}
