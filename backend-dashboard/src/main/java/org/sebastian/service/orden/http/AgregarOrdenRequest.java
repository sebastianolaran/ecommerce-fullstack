package org.sebastian.service.orden.http;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AgregarOrdenRequest {


    Long idOrden;

    int valorTotal;

    String estado;



}
