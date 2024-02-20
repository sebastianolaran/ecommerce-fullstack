package org.sebastian.service.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.sebastian.domain.JwtTokenData;
import org.sebastian.excepciones.JwtTokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    @Value("${jwt.expiration}")
    private int jwtExpirationMs;

    // Clave secreta para validar el token
    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    @Override
    public String generateToken(String email, String contrasenia) {
        Date expirationDate = new Date(System.currentTimeMillis() + jwtExpirationMs);

        // Generar una nueva clave segura para HS512
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);



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
                .signWith(secretKey)
                .compact();
    }

    @Override
    public JwtTokenData parseToken(String token) throws JwtTokenException {
        try {
            // Analizar el token para obtener sus claims
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);

            // Extraer los claims del token
            Claims claims = claimsJws.getBody();

            // Obtener los datos relevantes del token
            String email = claims.getSubject();
            String contrasenia = (String) claims.get("contrasenia");
            String rol = (String) claims.get("rol");
            Date expirationDate = claims.getExpiration();

            // Devolver los datos en un objeto JwtTokenData
            return new JwtTokenData(email, contrasenia, rol, expirationDate);
        } catch (Exception e) {
            // Manejar cualquier excepción que ocurra al analizar el token
            throw new JwtTokenException("Error al analizar el token: " + e.getMessage());
        }
    }

}
