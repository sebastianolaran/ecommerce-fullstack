package org.sebastian.domain;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "orden_detalle")
public class DetalleOrden {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle;

    private Long id_orden;

    private Long producto;


    private int cantidad;

    private int precio_unitario;


    public DetalleOrden(Long id_orden, Long producto, int cantidad, int precio_unitario) {
        this.id_orden = id_orden;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
    }
}
