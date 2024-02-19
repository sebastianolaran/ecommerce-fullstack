package org.sebastian.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sebastian.domain.JwtTokenData;
import org.sebastian.excepciones.JwtTokenException;
import org.sebastian.service.jwt.JwtTokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;

    public JwtTokenFilter(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // Obtener el token del encabezado de la solicitud
            String token = extractToken(request);

            // Analizar el token y obtener los datos
            JwtTokenData tokenData = jwtTokenService.parseToken(token);

            // Verificar si el rol es "ADMIN"
            if ("ADMIN".equals(tokenData.getRol())) {
                // Crear una autenticación con el token
                Authentication authentication = new UsernamePasswordAuthenticationToken(tokenData.getEmail(), null, null);

                // Establecer la autenticación en el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (JwtTokenException e) {
            // Manejar cualquier excepción relacionada con el token JWT
            // Puedes elegir cómo manejar este caso, por ejemplo, devolver un error de autenticación
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token JWT inválido");
            return;
        }

        // Continuar con la cadena de filtros
        filterChain.doFilter(request, response);
    }


    private String extractToken(HttpServletRequest request) {
        // Implementa la lógica para extraer el token del encabezado de la solicitud
        // Esto dependerá de cómo se envíe el token en las solicitudes (por ejemplo, en el encabezado "Authorization")
        // Aquí tienes un ejemplo básico:
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7); // Ignora "Bearer " y devuelve solo el token
        }
        return null;
    }
}

