package org.sebastian.domain;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
@Entity
@Table(name = "producto")

public class Producto {



    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;

    @NotEmpty
    private String nombre;
    @NotEmpty
    private String precio;

    @NotEmpty

    private String categoria;


    private String descripcion;
}
