package com.planilla_DAWI.cibertec.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "estadosciviles")
public class EstadoCivil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEstadoCivil")
    private Integer idEstadoCivil;

    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "Activo", nullable = false)
    private Boolean activo = true;

    @Column(name = "FecCreacion", nullable = false)
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
    public EstadoCivil(){}
}