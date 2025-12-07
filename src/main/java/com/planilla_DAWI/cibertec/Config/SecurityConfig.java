package com.planilla_DAWI.cibertec.Config;

import com.planilla_DAWI.cibertec.Service.Impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService,
                          JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authz -> authz
                        // Endpoints públicos (sin autenticación)
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/enums/**").permitAll()
                        .requestMatchers("/api/chat/**").hasAnyRole("USUARIO", "ADMINISTRADOR")
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/configuration/ui",
                                "/configuration/security"
                        ).permitAll()
                        
                        // Gestión de Cargos
                        .requestMatchers("/api/cargos/listar").hasAnyRole("USUARIO", "ADMINISTRADOR")
                        .requestMatchers("/api/cargos/obtenerById/**").hasAnyRole("USUARIO", "ADMINISTRADOR")
                        .requestMatchers("/api/cargos/insertar").hasAnyRole("ADMINISTRADOR")
                        .requestMatchers("/api/cargos/actualizar/**").hasAnyRole("ADMINISTRADOR")
                        .requestMatchers("/api/cargos/cambiarEstado/**").hasAnyRole("ADMINISTRADOR")
                        
                        // Gestión de Asistencias
                        .requestMatchers("/api/asistencias").hasAnyRole("USUARIO", "ADMINISTRADOR")
                        .requestMatchers("/api/asistencias/buscar").hasAnyRole("USUARIO", "ADMINISTRADOR")
                        .requestMatchers("/api/asistencias/descargar-excel").hasAnyRole("USUARIO", "ADMINISTRADOR")
                        .requestMatchers("/api/asistencias/cargar-excel").hasAnyRole("ADMINISTRADOR")
                        .requestMatchers("/api/asistencias/guardar").hasAnyRole("ADMINISTRADOR")
                        
                        // Gestión de Estados Civiles
                        .requestMatchers("/api/estados-civiles/listar").hasAnyRole("USUARIO", "ADMINISTRADOR")
                        .requestMatchers("/api/estados-civiles/obtenerById/**").hasAnyRole("USUARIO", "ADMINISTRADOR")
                        .requestMatchers("/api/estados-civiles/insertar").hasAnyRole("ADMINISTRADOR")
                        .requestMatchers("/api/estados-civiles/actualizar/**").hasAnyRole("ADMINISTRADOR")
                        .requestMatchers("/api/estados-civiles/cambiarEstado/**").hasAnyRole("ADMINISTRADOR")
                        .requestMatchers("/api/estados-civiles/**").hasAnyRole("ADMINISTRADOR")
                        
                        // Gestión de Géneros
                        .requestMatchers("/api/generos/listar").hasAnyRole("USUARIO", "ADMINISTRADOR")
                        .requestMatchers("/api/generos/obtenerById/**").hasAnyRole("USUARIO", "ADMINISTRADOR")
                        .requestMatchers("/api/generos/insertar").hasAnyRole("ADMINISTRADOR")
                        .requestMatchers("/api/generos/actualizar/**").hasAnyRole("ADMINISTRADOR")
                        .requestMatchers("/api/generos/cambiarEstado/**").hasAnyRole("ADMINISTRADOR")
                        .requestMatchers("/api/generos/**").hasAnyRole("ADMINISTRADOR")

                        // Gestión de Sistemas de Pensión
                        .requestMatchers("/api/sistemas-pension/listar").hasAnyRole("USUARIO", "ADMINISTRADOR")
                        .requestMatchers("/api/sistemas-pension/obtenerById/**").hasAnyRole("USUARIO", "ADMINISTRADOR")
                        .requestMatchers("/api/sistemas-pension/insertar").hasAnyRole("ADMINISTRADOR")
                        .requestMatchers("/api/sistemas-pension/actualizar/**").hasAnyRole("ADMINISTRADOR")
                        .requestMatchers("/api/sistemas-pension/cambiarEstado/**").hasAnyRole("ADMINISTRADOR")
                        .requestMatchers("/api/sistemas-pension/**").hasAnyRole("ADMINISTRADOR")
                        // Gestión de Situaciones de Trabajador
                        .requestMatchers("/api/situaciones-trabajador/listar").hasAnyRole("USUARIO", "ADMINISTRADOR")
                        .requestMatchers("/api/situaciones-trabajador/obtenerById/**").hasAnyRole("USUARIO", "ADMINISTRADOR")
                        .requestMatchers("/api/situaciones-trabajador/insertar").hasAnyRole("ADMINISTRADOR")
                        .requestMatchers("/api/situaciones-trabajador/actualizar/**").hasAnyRole("ADMINISTRADOR")
                        .requestMatchers("/api/situaciones-trabajador/cambiarEstado/**").hasAnyRole("ADMINISTRADOR")
                        .requestMatchers("/api/situaciones-trabajador/**").hasAnyRole("ADMINISTRADOR")

                        // Gestión de Tipos de Documento
                        .requestMatchers("/api/tipos-documento/listar").hasAnyRole("USUARIO", "ADMINISTRADOR")
                        .requestMatchers("/api/tipos-documento/obtenerById/**").hasAnyRole("USUARIO", "ADMINISTRADOR")
                        .requestMatchers("/api/tipos-documento/insertar").hasAnyRole("ADMINISTRADOR")
                        .requestMatchers("/api/tipos-documento/actualizar/**").hasAnyRole("ADMINISTRADOR")
                        .requestMatchers("/api/tipos-documento/cambiarEstado/**").hasAnyRole("ADMINISTRADOR")

                        // Gestión de Trabajador
                        .requestMatchers("/api/trabajador/listar").hasAnyRole("USUARIO", "ADMINISTRADOR")
                        .requestMatchers("/api/trabajador/obtenerById/**").hasAnyRole("USUARIO", "ADMINISTRADOR")
                        .requestMatchers("/api/trabajador/insertar").hasAnyRole("ADMINISTRADOR")
                        .requestMatchers("/api/trabajador/actualizar/**").hasAnyRole("ADMINISTRADOR")
                        .requestMatchers("/api/trabajador/cambiarEstado/**").hasAnyRole("ADMINISTRADOR")
                        .requestMatchers("/api/trabajador/**").hasAnyRole("ADMINISTRADOR")
                        // Gestión de Planilla-Mensual
                        .requestMatchers("/api/planilla-mensual/listarPlanilla").hasAnyRole("USUARIO", "ADMINISTRADOR")
                        .requestMatchers("/api/planilla-mensual/buscarBoleta").hasAnyRole("USUARIO", "ADMINISTRADOR")
                        .requestMatchers("/api/planilla-mensual/calcularPlanilla").hasAnyRole("ADMINISTRADOR")
                        .requestMatchers("/api/planilla-mensual/guardarPlanilla").hasAnyRole("ADMINISTRADOR")
                        // Cualquier otra solicitud requiere autenticación
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept", "X-Requested-With"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}