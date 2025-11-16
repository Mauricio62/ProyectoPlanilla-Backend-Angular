package com.planilla_DAWI.cibertec.Dto;

import com.planilla_DAWI.cibertec.Utils.Enums.RolUsuarioEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegisterRequestDto {
    @NotBlank(message = "Username es obligatorio")
    private String username;

    @NotBlank(message = "Password es obligatorio")
    private String password;

    @NotBlank(message = "Email es obligatorio")
    private String email;

    @NotNull(message = "Rol es obligatorio")
    private RolUsuarioEnum role;

    // Getters y Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public RolUsuarioEnum getRole() { return role; }
    public void setRole(RolUsuarioEnum role) { this.role = role; }
}
