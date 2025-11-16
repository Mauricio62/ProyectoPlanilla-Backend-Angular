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
@Table(name = "IngresosTrabajadores")
public class IngresoTrabajador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIngresoTrabajador;

    @ManyToOne
    @JoinColumn(name = "idTrabajador")
    private Trabajador trabajador;

    private BigDecimal remuneracion;
    private BigDecimal vale;
    private BigDecimal bonifCargo;

    @Column(nullable = false)
    private Boolean activo = true;

    @Column(nullable = false)
    private LocalDateTime fecCreacion = LocalDateTime.now();

    private LocalDateTime fecUltimaModificacion;
}
