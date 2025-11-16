package com.planilla_DAWI.cibertec.Dto;

import java.util.Date;

public class SituacionTrabajadorDTO {
    private Integer id;
    private String nombre;
    private Boolean activo;
    private Date fecCreacion;
    private Date fecUltimaModificacion;

    // Getters and Setters
    public Integer getIdSituacion() {
        return id;
    }

    public void setIdSituacion(Integer idSituacion) {
        this.id = idSituacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
