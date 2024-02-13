package org.sebastian.service.orden.http;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AgregarProductoConOrdenRequest {

    String id_orden;

    Long id_producto;

    int cantidad;

    int precio_unitario;
}
