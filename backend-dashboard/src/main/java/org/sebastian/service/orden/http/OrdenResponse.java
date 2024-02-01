package org.sebastian.service.orden.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class OrdenResponse {
    int ventasDiarias;
    Double montoDiario;
    int ventasMensual;
    Double montoMensual;
    int ventasSemanal;
    Double montoSemanal;
}


