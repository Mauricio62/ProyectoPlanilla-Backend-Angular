package com.planilla_DAWI.cibertec.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "SituacionTrabajador")
public class SituacionTrabajador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSituacion;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false)
    private Boolean activo = true;

    @Column(nullable = false)
    private LocalDateTime fecCreacion = LocalDateTime.now();

    private LocalDateTime fecUltimaModificacion;
    public  SituacionTrabajador(){}
}
