package org.sebastian.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sebastian.domain.JwtTokenData;
import org.sebastian.excepciones.JwtTokenException;
import org.sebastian.service.jwt.JwtTokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthorizationFilter extends OncePerRequestFilter {


    @Autowired
    JwtTokenServiceImpl jwtUtilityService;

    public JwtAuthorizationFilter(JwtTokenServiceImpl jwtUtilityService) {
        this.jwtUtilityService = jwtUtilityService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            logger.info("Solicitud no contiene token JWT. Permitiendo acceso.");
            filterChain.doFilter(request, response);
            return;

        }

        String token = header.substring(7); // Extract the token excluding "Bearer "

        try {
            JwtTokenData tokenData = jwtUtilityService.parseToken(token);

            // Verificar el rol del usuario
            if ("ADMIN".equals(tokenData.getRol())) {
                // Si el usuario tiene el rol "ADMIN", autenticarlo
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(tokenData.getEmail(), null, null);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                logger.info("Usuario autenticado como ADMIN.");
            }
        } catch (JwtTokenException e) {
            logger.warn("Usuario no tiene el rol ADMIN. Acceso denegado.");
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Acceso denegado");
            return;
        }

        filterChain.doFilter(request, response);
    }
}



