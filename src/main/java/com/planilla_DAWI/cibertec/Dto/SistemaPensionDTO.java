package com.planilla_DAWI.cibertec.Dto;

import java.math.BigDecimal;
import java.util.Date;

public class SistemaPensionDTO {
    private Integer id;
    private String nombre;
    private BigDecimal aporte;
    private BigDecimal comision;
    private BigDecimal prima;
    private Boolean activo;
    private Date fecCreacion;
    private Date fecUltimaModificacion;

    // Getters and Setters
    public Integer getIdSistemaPension() {
        return id;
    }

    public void setIdSistemaPension(Integer idSistemaPension) {
        this.id = idSistemaPension;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getAporte() {
        return aporte;
    }

    public void setAporte(BigDecimal aporte) {
        this.aporte = aporte;
    }

    public BigDecimal getComision() {
        return comision;
    }

    public void setComision(BigDecimal comision) {
        this.comision = comision;
    }

    public BigDecimal getPrima() {
        return prima;
    }

    public void setPrima(BigDecimal prima) {
        this.prima = prima;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Date getFecCreacion() {
        return fecCreacion;
    }

    public void setFecCreacion(Date fecCreacion) {
        this.fecCreacion = fecCreacion;
    }

    public Date getFecUltimaModificacion() {
        return fecUltimaModificacion;
    }

    public void setFecUltimaModificacion(Date fecUltimaModificacion) {
        this.fecUltimaModificacion = fecUltimaModificacion;
    }
}
