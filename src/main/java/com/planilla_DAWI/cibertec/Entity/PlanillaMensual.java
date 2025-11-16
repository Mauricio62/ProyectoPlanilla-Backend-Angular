package com.planilla_DAWI.cibertec.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PlanillaMensual")
public class PlanillaMensual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPlanillaMensual")
    private Long idPlanillaMensual;

    @Column(name="Año")
    private Integer anio;
    private Integer mes;

    @ManyToOne
    @JoinColumn(name = "idTrabajador")
    private Trabajador trabajador;

    @ManyToOne
    @JoinColumn(name = "idSituacion")
    private SituacionTrabajador situacion;

    @ManyToOne
    @JoinColumn(name = "idCargo")
    private Cargo cargo;

    @Column(length = 40)
    private String apellido;

    @Column(length = 40)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "idSistemaPension")
    private SistemaPension sistemaPension;

    @ManyToOne
    @JoinColumn(name = "idEstadoCivil")
    private EstadoCivil estadoCivil;

    private Short hijos;
    private LocalDateTime fechaIngreso;
    private BigDecimal sueldoBasico;
    private BigDecimal porcHoraExtra1;
    private BigDecimal porcHoraExtra2;
    private BigDecimal porcDescansoTrab;
    private BigDecimal porcFeriadoTrab;
    private BigDecimal PorcAsigFamiliar;
    private BigDecimal nHorasNormal;
    private BigDecimal nHorasExtra1;
    private BigDecimal nHorasExtra2;
    private Short nDiasTrab;
    private Short nDiasDescansos;
    private Short nFeriadosTrab;
    private Short nDescansosTrab;
    private Short nDiasInasistencias;
    private BigDecimal haberBasico;
    private BigDecimal valesEmpleado;
    private BigDecimal vHorasExtra1;
    private BigDecimal vHorasExtra2;
    private BigDecimal vAsigFamiliar;
    private BigDecimal vDescansoTrab;
    private BigDecimal vFeriadoTrab;
    private BigDecimal bonificacionCargo;
    private BigDecimal bonificacionMovilidad;
    private BigDecimal canastaNavidad;
    private BigDecimal escolaridad;
    private BigDecimal diaTrabajador;
    private BigDecimal totalIngreso;
    private BigDecimal renta5ta;
    private BigDecimal descuentoJud1;
    private BigDecimal descuentoJud2;
    private BigDecimal descuentoJud3;
    private BigDecimal otrosAdelantos;
    private BigDecimal adelantoCaja;
    private BigDecimal adelantoQuincena;
    private BigDecimal adelantoVac;
    private BigDecimal adelantoGratificacion;
    private BigDecimal adelantoLiquidacion;
    private BigDecimal adelantoCTS;
    private BigDecimal porcAporte;
    private BigDecimal aporte;
    private BigDecimal porcComision;
    private BigDecimal comision;
    private BigDecimal porcPrima;
    private BigDecimal prima;
    private BigDecimal otdSeg;
    private BigDecimal otdPacifico;
    private Long idBanco1;
    private BigDecimal prestamo1;
    private BigDecimal tardanza;
    private BigDecimal totalDescuento;
    private BigDecimal porcEsSalud;
    private BigDecimal esSalud;
    private BigDecimal accidenteTrab;
    private BigDecimal senati;
    private BigDecimal seguroVidaLey;


    private BigDecimal totalNeto;

    @Column(length = 180)
    private String totalNetoCad;

    private BigDecimal totalNetoBoleta;
    private String TotalNetoBoletaCad;

    @Column(nullable = false)
    private boolean activo = true;

    @Column(nullable = false)
    private LocalDateTime fecCreacion = LocalDateTime.now();

    private LocalDateTime fecUltimaModificacion;
}
