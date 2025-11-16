package com.planilla_DAWI.cibertec.Config;

import com.planilla_DAWI.cibertec.Service.Impl.UserDetailsServiceImpl;
import com.planilla_DAWI.cibertec.Utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            // Debug: imprimir información de la request
            logger.debug("Request URL: {}", request.getRequestURL());
            logger.debug("Request Method: {}", request.getMethod());

            String jwt = parseJwt(request);
            logger.debug("JWT encontrado: {}", jwt != null ? "SÍ" : "NO");

            if (jwt == null) {
                logHeaders(request); // Mostrar todos los headers
            }


            if (jwt != null) {
                // Extraer username del token
                String username = jwtUtil.extractUsername(jwt);

                // Cargar UserDetails desde la base de datos
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Validar token CON UserDetails (como solicitaste)
                if (jwtUtil.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    logger.debug("JWT válido para usuario: {}", username);
                } else {
                    logger.warn("JWT inválido o expirado para usuario: {}", username);
                }
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        // 1. Intentar con el header estándar
        String headerAuth = request.getHeader("Authorization");

        // 2. Si no funciona, intentar con headers en minúsculas
        if (headerAuth == null) {
            headerAuth = request.getHeader("authorization");
        }

        // 3. Intentar con otros casos comunes
        if (headerAuth == null) {
            headerAuth = request.getHeader("AUTHORIZATION");
        }

        // 4. Buscar en parámetros de query (útil para testing)
        if (headerAuth == null) {
            headerAuth = request.getParameter("token");
        }

        // 5. Buscar en atributos de request (para WebSockets, etc.)
        if (headerAuth == null) {
            headerAuth = (String) request.getAttribute("Authorization");
        }

        // 6. Debug: mostrar todos los headers si es necesario
        if (headerAuth == null && logger.isDebugEnabled()) {
            logHeaders(request);
        }

        // Extraer el token si se encuentra
        if (StringUtils.hasText(headerAuth)) {
            // Manejar diferentes formatos
            if (headerAuth.startsWith("Bearer ")) {
                return headerAuth.substring(7);
            } else if (headerAuth.startsWith("bearer ")) {
                return headerAuth.substring(7);
            } else if (headerAuth.startsWith("Token ")) {
                return headerAuth.substring(6);
            } else if (headerAuth.startsWith("token ")) {
                return headerAuth.substring(6);
            } else {
                // Asumir que es solo el token
                return headerAuth;
            }
        }

        return null;
    }

    private void logHeaders(HttpServletRequest request) {
        logger.debug("=== REQUEST HEADERS ===");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            logger.debug("{}: {}", headerName, headerValue);
        }
        logger.debug("=======================");

        logger.debug("=== REQUEST PARAMETERS ===");
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String paramValue = request.getParameter(paramName);
            logger.debug("{}: {}", paramName, paramValue);
        }
        logger.debug("=========================");
    }}
