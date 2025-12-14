package com.planilla_DAWI.cibertec.Integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.planilla_DAWI.cibertec.Dto.LoginRequestDto;
import com.planilla_DAWI.cibertec.Dto.RegisterRequestDto;
import com.planilla_DAWI.cibertec.Utils.Enums.RolUsuarioEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Pruebas de Integración para el módulo de Autenticación
 * Estas pruebas requieren que la base de datos esté disponible
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Pruebas de Integración - Autenticación")
@Transactional
class AuthIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Caso 1: Login exitoso con credenciales válidas")
    void testLoginExitoso() throws Exception {
        // Precondición: Usuario debe existir en la base de datos
        // Pasos: Enviar POST a /api/auth/login con credenciales válidas
        
        LoginRequestDto loginRequest = new LoginRequestDto("admin", "password123");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.type").value("Bearer"));
    }

    @Test
    @DisplayName("Caso 2: Registro de nuevo usuario exitoso")
    void testRegistroExitoso() throws Exception {
        // Precondición: El usuario no debe existir en la base de datos
        // Pasos: Enviar POST a /api/auth/register con datos válidos
        
        RegisterRequestDto registerRequest = new RegisterRequestDto();
        registerRequest.setUsername("test_user_" + System.currentTimeMillis());
        registerRequest.setPassword("password123");
        registerRequest.setEmail("test_" + System.currentTimeMillis() + "@example.com");
        registerRequest.setRole(RolUsuarioEnum.USUARIO);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.username").exists());
    }

    @Test
    @DisplayName("Caso 3: Obtener lista de roles disponibles")
    void testObtenerRoles() throws Exception {
        // Precondición: Ninguna
        // Pasos: Enviar GET a /api/auth/roles
        
        mockMvc.perform(get("/api/auth/roles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").exists())
                .andExpect(jsonPath("$[0].description").exists());
    }
}
