package org.sebastian.service.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.sebastian.domain.JwtTokenData;
import org.sebastian.excepciones.JwtTokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component
@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    @Value("${jwt.expiration}")
    private int jwtExpirationMs;

    @Value("${jwt.secretKey}")
    private String secretKey;


    @Override
    public String generateToken(String email, String contrasenia) {
        Date expirationDate = new Date(System.currentTimeMillis() + jwtExpirationMs);


        // Agregar los datos adicionales que deseas incluir en el token
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("contrasenia", contrasenia);
        claims.put("rol", "ADMIN");

        // Construir el token JWT con los datos adicionales y la nueva clave
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }


    @Override
    public JwtTokenData parseToken(String token) throws JwtTokenException {
        try {
            // Convertir la clave secreta de String a bytes

            // Analizar el token para obtener sus claims
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);

            // Extraer los claims del token
            Claims claims = claimsJws.getBody();

            // Obtener los datos relevantes del token
            String email = claims.getSubject();
            String rol = (String) claims.get("rol");
            Date expirationDate = claims.getExpiration();

            // Crear un logger
            Logger logger = Logger.getLogger("JwtTokenParser");

            // Registrar los datos obtenidos
            logger.info("Email: " + email);
            logger.info("Rol: " + rol);
            logger.info("Fecha de expiración: " + expirationDate);

            // Devolver los datos en un objeto JwtTokenData
            return new JwtTokenData(email, rol, expirationDate);
        } catch (Exception e) {
            // Manejar cualquier excepción que ocurra al analizar el token
            throw new JwtTokenException("Error al analizar el token: " + e.getMessage());
        }
    }

}
