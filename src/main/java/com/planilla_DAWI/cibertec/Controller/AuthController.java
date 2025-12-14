package com.planilla_DAWI.cibertec.Controller;

import com.planilla_DAWI.cibertec.Dto.JwtResponseDto;
import com.planilla_DAWI.cibertec.Dto.LoginRequestDto;
import com.planilla_DAWI.cibertec.Dto.RegisterRequestDto;
import com.planilla_DAWI.cibertec.Dto.RegisterResponseDto;
import com.planilla_DAWI.cibertec.Dto.RoleDTO;
import com.planilla_DAWI.cibertec.Service.AuthService;
import com.planilla_DAWI.cibertec.Utils.Enums.RolUsuarioEnum;
import com.planilla_DAWI.cibertec.Utils.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(summary = "Autenticar usuario", description = "Endpoint para iniciar sesión y obtener JWT token", security = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticación exitosa"),
            @ApiResponse(responseCode = "400", description = "Credenciales inválidas")
    })
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDto loginRequest) {
        try {
            JwtResponseDto response = authService.authenticateUser(loginRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error en autenticación: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    @Operation(summary = "Registrar usuario", description = "Endpoint para crear un nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error en el registro")
    })
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDto registerRequest) {
        try {
            RegisterResponseDto result = authService.registerUser(registerRequest);
            System.out.println("RegisterResponseDto creado: " + result);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            RegisterResponseDto errorResponse = new RegisterResponseDto(
                    false,
                    "Error en registro: " + e.getMessage(),
                    null,
                    null
            );
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
    @GetMapping("/roles")
    @Operation(summary = "Obtener roles disponibles", description = "Retorna la lista de roles disponibles para registro")
    public List<RoleDTO> getAvailableRoles() {
        return Arrays.stream(RolUsuarioEnum.values())
                .map(role -> new RoleDTO(role.name(), role.getDescription()))
                .collect(Collectors.toList());
    }


}