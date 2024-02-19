package org.sebastian.domain;

import lombok.Data;

import java.util.Date;

@Data
public class JwtTokenData {

    String email;

    String contrasenia;

    String rol;

    Date expiracion;


    public JwtTokenData(String email, String contrasenia, String rol,Date expiracion) {
        this.email = email;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.expiracion= expiracion;
    }
}
