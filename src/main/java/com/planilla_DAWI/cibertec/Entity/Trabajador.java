package com.planilla_DAWI.cibertec.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trabajadores")

public class Trabajador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdTrabajador")
    private Integer idTrabajador;

    @Column(name = "IdTipoDocumento", nullable = false)
    private Integer idTipoDocumento;

    @Column(name = "Documento", nullable = false, length = 11)
    private String documento;

    @Column(name = "Nombres", nullable = false, length = 50)
    private String nombres;

    @Column(name = "ApellidoPaterno", nullable = false, length = 50)
    private String apellidoPaterno;

    @Column(name = "ApellidoMaterno", nullable = false, length = 50)
    private String apellidoMaterno;

    @Column(name = "IdGenero", nullable = false)
    private Integer idGenero;

    @Column(name = "IdEstadoCivil", nullable = false)
    private Integer idEstadoCivil;

    @Column(name = "Direccion", nullable = false, length = 120)
    private String direccion;

    @Column(name = "Email", length = 120)
    private String email;

    @Column(name = "Hijos", nullable = false)
    private Integer hijos ;

    @Column(name = "IdCargo", nullable = false)
    private Integer idCargo;

    @Column(name = "FecNacimiento", nullable = false)
    private LocalDateTime fecNacimiento;

    @Column(name = "FecIngreso", nullable = false)
    private LocalDateTime fecIngreso;

    @Column(name = "IdSituacion", nullable = false)
    private Integer idSituacion;

    @Column(name = "IdSistemaPension", nullable = false)
    private Integer idSistemaPension;

    @Lob
    @Column(name = "Foto")
    private byte[] foto;

    @Column(name = "Activo", nullable = false)
    private Boolean activo = true;

    @Column(name = "FecCreacion", nullable = false, updatable = false)
    private LocalDateTime fecCreacion;

    @Column(name = "FecUltimaModificacion")
    private LocalDateTime fecUltimaModificacion;

    @PrePersist
    protected void onCreate() {
        if (fecCreacion == null) {
            fecCreacion = LocalDateTime.now();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        fecUltimaModificacion = LocalDateTime.now();
    }
}
