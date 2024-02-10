package org.sebastian.service.orden.http;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AgregarProductoConOrdenRequest {

    Long id_orden;
    Long id_producto;
    int cantidad;
    int precio_unitario;
}
