package com.planilla_DAWI.cibertec.Dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
public class AsistenciaTrabajadorDTO {
    private Integer idAsistencia;
    private Integer idTrabajador;
    private String documento;
    private String nombre;
    private Integer año;
    private Integer mes;
    private Integer diasLaborales;
    private Integer diasDescanso;
    private Integer diasInasistencia;
    private Integer diasFeriados;
    private BigDecimal horasExtra25;
    private BigDecimal horasExtra35;
    private Date fecCreacion;
    private Boolean activo;
}
