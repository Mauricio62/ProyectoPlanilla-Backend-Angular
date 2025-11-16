package com.planilla_DAWI.cibertec.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // Definir el esquema de seguridad
        SecurityScheme securityScheme = new SecurityScheme()
                .name("bearerAuth")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .description("Ingrese el token JWT en el formato: Bearer <token>");

        // Crear el requerimiento de seguridad
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("bearerAuth");

        return new OpenAPI()
                .info(createInfo())
                .servers(createServers())
                .addSecurityItem(securityRequirement)
                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme));
    }

    private Info createInfo() {
        return new Info()
                .title("Sistema de Planilla DAWL-Cibertec API")
                .version("1.0.0")
                .description("API REST para el sistema de planilla de DAWL-Cibertec")
                .contact(new Contact()
                        .name("Equipo de Desarrollo")
                        .email("desarrollo@cibertec.edu.pe"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("https://www.apache.org/licenses/LICENSE-2.0.html"));
    }

    private List<Server> createServers() {
        return List.of(
                new Server()
                        .url("http://localhost:8080")
                        .description("Servidor local de desarrollo"),
                new Server()
                        .url("https://api.tudominio.com")
                        .description("Servidor de producción")
        );
    }
}