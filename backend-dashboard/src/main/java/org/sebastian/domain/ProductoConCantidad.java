package org.sebastian.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class ProductoConCantidad extends Producto {


    private int cantidad;


    public ProductoConCantidad(int cantidad, Producto producto) {
        super(producto.getNombre(), producto.getPrecio(), producto.getCategoria(), producto.getDescripcion());
        this.setId_producto(producto.getId_producto());
        this.cantidad = cantidad;
    }
}
