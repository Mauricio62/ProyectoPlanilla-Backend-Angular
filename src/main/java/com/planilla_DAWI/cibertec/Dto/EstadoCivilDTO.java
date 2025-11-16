package com.planilla_DAWI.cibertec.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class EstadoCivilDTO {
    private Integer id;
    private String nombre;
    private Boolean activo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date fecCreacion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date fecUltimaModificacion;

    // Getters and Setters
    public Integer getIdEstadoCivil() {
        return id;
    }

    public void setIdEstadoCivil(Integer idEstadoCivil) {
        this.id = idEstadoCivil;
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
