package com.planilla_DAWI.cibertec.Dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AsistenciaTrabajadorResponse {
    private Integer idTrabajador;
    private String documento;
    private String nombre;
    private Integer diasLaborales;
    private Integer diasDescanso;
    private Integer diasInasistencia;
    private Integer diasFeriados;
    private BigDecimal horasExtra25;
    private BigDecimal horasExtra35;
    private Integer idAsistencia;
}
