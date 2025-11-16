
package com.planilla_DAWI.cibertec.Dto;

import java.math.BigDecimal;

public class IngresoTrabajadorDTO {
    private Integer idIngresoTrabajador;
    private Integer idTrabajador;
    private String documento;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private BigDecimal remuneracion;
    private BigDecimal vale;
    private BigDecimal bonifCargo;

    private boolean activo = true;


    public Integer getIdIngresoTrabajador() {
        return this.idIngresoTrabajador;
    }

    public Integer getIdTrabajador() {
        return this.idTrabajador;
    }

    public String getDocumento() {
        return this.documento;
    }

    public String getNombres() {
        return this.nombres;
    }

    public String getApellidoPaterno() {
        return this.apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return this.apellidoMaterno;
    }

    public BigDecimal getRemuneracion() {
        return this.remuneracion;
    }

    public BigDecimal getVale() {
        return this.vale;
    }

    public BigDecimal getBonifCargo() {
        return this.bonifCargo;
    }

    public boolean isActivo() {
        return this.activo;
    }

    public void setIdIngresoTrabajador(Integer idIngresoTrabajador) {
        this.idIngresoTrabajador = idIngresoTrabajador;
    }

    public void setIdTrabajador(Integer idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setRemuneracion(BigDecimal remuneracion) {
        this.remuneracion = remuneracion;
    }

    public void setVale(BigDecimal vale) {
        this.vale = vale;
    }

    public void setBonifCargo(BigDecimal bonifCargo) {
        this.bonifCargo = bonifCargo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
