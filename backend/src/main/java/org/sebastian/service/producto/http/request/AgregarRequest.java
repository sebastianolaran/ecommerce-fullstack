package org.sebastian.service.producto.http.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgregarRequest {
    String nombre;
    String precio;
    String categoria;
    String descripcion;
}
