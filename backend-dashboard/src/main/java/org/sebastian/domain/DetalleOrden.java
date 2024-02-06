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

    private int id_orden;


    private int producto;


    private int cantidad;

    private int precio_unitario;
}
