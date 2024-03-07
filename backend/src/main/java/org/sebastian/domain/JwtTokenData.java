package org.sebastian.domain;

import lombok.Data;

import java.util.Date;

@Data
public class JwtTokenData {

    String email;

    String rol;

    Date expiracion;


    public JwtTokenData(String email, String rol, Date expiracion) {
        this.email = email;
        this.rol = rol;
        this.expiracion = expiracion;
    }
}
