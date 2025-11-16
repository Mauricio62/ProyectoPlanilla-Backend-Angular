package com.planilla_DAWI.cibertec.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "SistemaPensiones")
public class SistemaPension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSistemaPension;

    @Column(nullable = false, length = 50)
    private String nombre;

    private BigDecimal aporte;
    private BigDecimal comision;
    private BigDecimal prima;

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
    public  SistemaPension(){}
}
