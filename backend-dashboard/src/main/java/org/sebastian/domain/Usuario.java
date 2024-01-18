package org.sebastian.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    @NotEmpty
    private String rol;


}
