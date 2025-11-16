package com.planilla_DAWI.cibertec.Dto;

import lombok.Data;

@Data
public class ApiErrorResponse {

    private String mensaje;
    private int codigo;
}
