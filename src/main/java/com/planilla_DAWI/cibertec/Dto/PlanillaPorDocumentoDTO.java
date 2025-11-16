package com.planilla_DAWI.cibertec.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


import java.math.BigDecimal;
@Data
public class PlanillaPorDocumentoDTO {
    private Integer anio;
    private Integer mes;
    private Short nDiasTrab;
    private BigDecimal nHorasNormal;
    private BigDecimal haberBasico;
    private BigDecimal vAsigFamiliar;
    private BigDecimal vHorasExtra1;
    private BigDecimal vHorasExtra2;
    private BigDecimal vFeriadoTrab;
    private BigDecimal valesEmpleado;
    private BigDecimal bonificacionCargo;
    private BigDecimal totalIngreso;
    private BigDecimal aporte;
    private BigDecimal comision;
    private BigDecimal prima;
    private BigDecimal totalDescuento;
    private BigDecimal esSalud;
    private BigDecimal seguroVidaLey;
    private BigDecimal totalNetoBoleta;
    private String TotalNetoBoletaCad;
}
