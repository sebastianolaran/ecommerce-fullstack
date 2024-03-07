package org.sebastian.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private int precio;

    @NotEmpty

    private String categoria;


    private String descripcion;


    public Producto() {

    }

    public Producto(String nombre, int precio, String categoria, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }
}
