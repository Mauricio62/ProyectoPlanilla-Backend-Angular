
package com.planilla_DAWI.cibertec.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class TrabajadorDTO {
    private Integer idTrabajador;
    private Integer idTipoDocumento;
    private String documento;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Integer idGenero;
    private Integer idEstadoCivil;
    private String direccion;
    private String email;
    private Integer hijos;
    private Integer idCargo;
    private Date fecNacimiento;
    private Date fecIngreso;
    private Integer idSituacion;
    private Integer idSistemaPension;
    private byte[] foto;
    private Boolean activo;
    private Date fecCreacion;
    private Date fecUltimaModificacion;

}


