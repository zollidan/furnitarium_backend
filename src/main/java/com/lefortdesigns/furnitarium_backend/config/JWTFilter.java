package com.lefortdesigns.furnitarium_backend.config;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.lefortdesigns.furnitarium_backend.security.JwtUtil;
import com.lefortdesigns.furnitarium_backend.services.PersonDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private final PersonDetailsService personDetailsService;

    @Autowired
    public JWTFilter(JwtUtil jwtUtil, PersonDetailsService personDetailsService) {
        this.jwtUtil = jwtUtil;
        this.personDetailsService = personDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.isBlank() && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);

            if (jwt.isBlank()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "invalid token in Bearerrequest");
            } else {
                try {
                    String username = jwtUtil.validateTokenAndGetClaim(jwt);
                    UserDetails userDetails = personDetailsService.loadUserByUsername(username);

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, userDetails.getPassword(),
                            userDetails.getAuthorities());

                    if (SecurityContextHolder.getContext().getAuthentication() == null) {
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                } catch (JWTVerificationException exception) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                            "invalid JWT token you are aboba and i guess token expired");
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
