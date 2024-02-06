package org.sebastian.interfaces;

import lombok.Data;
import org.sebastian.domain.Producto;


@Data
public class ProductoConCantidad {


    private Long id_producto;


    private String nombre;

    private int precio;


    private String categoria;


    private String descripcion;

    private int cantidad;


    public ProductoConCantidad(int cantidad, Producto producto) {
        this.id_producto = producto.getId_producto();
        this.categoria= producto.getCategoria();
        this.descripcion= producto.getDescripcion();
        this.precio= producto.getPrecio();
        this.nombre = producto.getNombre();
        this.cantidad = cantidad;
    }
}
