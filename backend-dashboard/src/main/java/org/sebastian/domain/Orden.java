package org.sebastian.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;


@Data
@Entity
@Table(name = "orden")
public class Orden {

    private static final long serialVersionUID = 1L;

    @Id

    @Column(name = "id_orden") // Nombre de la columna en la base de datos
    private String id_orden;


    private Double monto;


    private Date fecha;


    private String estado;


    public Orden() {
    }

    public Orden(String id_orden, Double monto, Date fecha, String estado) {
        this.id_orden = id_orden;
        this.monto = monto;
        this.fecha = fecha;
        this.estado = estado;
    }
}
