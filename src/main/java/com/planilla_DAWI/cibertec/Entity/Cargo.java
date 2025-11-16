package com.planilla_DAWI.cibertec.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "Cargos")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCargo;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false)
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
    public Cargo(){}
}

