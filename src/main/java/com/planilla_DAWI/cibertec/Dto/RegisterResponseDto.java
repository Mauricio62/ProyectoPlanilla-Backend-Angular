package com.planilla_DAWI.cibertec.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDto {
    private boolean success;
    private String message;
    private String username;
    private String role;
}

