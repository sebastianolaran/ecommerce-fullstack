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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_orden;


    private Double monto;


    private Date fecha;



    private String estado;

}
