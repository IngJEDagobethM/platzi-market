package com.platzi.market.web.security.filter;

import com.platzi.market.domain.service.PlatziUserDetailsService;
import com.platzi.market.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilterRequest extends OncePerRequestFilter { // Para que se ejecute en cada petición
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private PlatziUserDetailsService platziUserDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization"); // Se obtiene el valor del header Authorization.

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")){ // Se valida que sea un token de jwt válido.
            // String jwt = authorizationHeader.substring(7); // Se extrae el cuerpo del token.
            String jwt = authorizationHeader.split(" ")[1].trim(); // Se extrae el cuerpo del token.
            String username = jwtUtil.extractUsername(jwt); // Se extrae el usuario

            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                // Se valida que exista usuario y no exista una autenticación anterior.
                UserDetails userDetails = platziUserDetailsService.loadUserByUsername(username); // Encuentra el usuario a través de su nombre.

                if(jwtUtil.validateToken(jwt, userDetails)){ // Si el token es válido
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // Se obtiene la información detallada del cliente web.
                    SecurityContextHolder.getContext().setAuthentication(authToken); // Se asigna la autenticación.
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
