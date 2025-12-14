package com.planilla_DAWI.cibertec.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.planilla_DAWI.cibertec.Dto.JwtResponseDto;
import com.planilla_DAWI.cibertec.Dto.LoginRequestDto;
import com.planilla_DAWI.cibertec.Dto.RegisterRequestDto;
import com.planilla_DAWI.cibertec.Dto.RegisterResponseDto;
import com.planilla_DAWI.cibertec.Service.AuthService;
import com.planilla_DAWI.cibertec.Utils.Enums.RolUsuarioEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas Unitarias - AuthController")
class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Caso 1: Login exitoso con credenciales válidas")
    void testLoginExitoso() throws Exception {
        // Arrange
        LoginRequestDto loginRequest = new LoginRequestDto("admin", "password123");
        JwtResponseDto jwtResponse = new JwtResponseDto("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...");

        when(authService.authenticateUser(any(LoginRequestDto.class))).thenReturn(jwtResponse);

        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.type").value("Bearer"));
    }

    @Test
    @DisplayName("Caso 2: Login fallido con credenciales inválidas")
    void testLoginFallido() throws Exception {
        // Arrange
        LoginRequestDto loginRequest = new LoginRequestDto("usuario_inexistente", "password_incorrecto");

        when(authService.authenticateUser(any(LoginRequestDto.class)))
                .thenThrow(new RuntimeException("Credenciales inválidas"));

        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Error en autenticación")));
    }

    @Test
    @DisplayName("Caso 3: Registro exitoso de nuevo usuario")
    void testRegistroExitoso() throws Exception {
        // Arrange
        RegisterRequestDto registerRequest = new RegisterRequestDto();
        registerRequest.setUsername("nuevo_usuario");
        registerRequest.setPassword("password123");
        registerRequest.setEmail("nuevo@example.com");
        registerRequest.setRole(RolUsuarioEnum.USUARIO);

        RegisterResponseDto response = new RegisterResponseDto(
                true,
                "Usuario registrado exitosamente",
                "nuevo_usuario",
                "USUARIO"
        );

        when(authService.registerUser(any(RegisterRequestDto.class))).thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Usuario registrado exitosamente"))
                .andExpect(jsonPath("$.username").value("nuevo_usuario"));
    }

    @Test
    @DisplayName("Caso 4: Registro fallido - usuario ya existe")
    void testRegistroFallidoUsuarioExistente() throws Exception {
        // Arrange
        RegisterRequestDto registerRequest = new RegisterRequestDto();
        registerRequest.setUsername("usuario_existente");
        registerRequest.setPassword("password123");
        registerRequest.setEmail("existente@example.com");
        registerRequest.setRole(RolUsuarioEnum.USUARIO);

        when(authService.registerUser(any(RegisterRequestDto.class)))
                .thenThrow(new RuntimeException("El usuario ya existe"));

        // Act & Assert
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value(org.hamcrest.Matchers.containsString("Error en registro")));
    }

    @Test
    @DisplayName("Caso 5: Obtener lista de roles disponibles")
    void testObtenerRoles() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/auth/roles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].value").exists())
                .andExpect(jsonPath("$[0].description").exists());
    }
}
