package org.sebastian.service.jwt;

import org.sebastian.domain.JwtTokenData;
import org.sebastian.excepciones.JwtTokenException;

public interface JwtTokenService {
    String generateToken(String email, String contrasenia);

    JwtTokenData parseToken(String token) throws JwtTokenException;
}
