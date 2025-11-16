package com.planilla_DAWI.cibertec.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AsistenciasTrabajadores")
public class AsistenciaTrabajador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAsistencia;

    @ManyToOne
    @JoinColumn(name = "idTrabajador")
    private Trabajador trabajador;

    private Integer año;
    private Integer mes;
    private Integer diasLaborales;
    private Integer diasDescanso;
    private Integer diasInasistencia;
    private Integer diasFeriados;

    private BigDecimal horasExtra25;
    private BigDecimal horasExtra35;

    @Column(nullable = false)
    private boolean activo =  true;

    @Column(nullable = false)
    private LocalDateTime fecCreacion = LocalDateTime.now();

    private LocalDateTime fecUltimaModificacion;
}

