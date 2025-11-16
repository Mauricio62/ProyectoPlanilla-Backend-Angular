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
@Table(name = "Parametros")
public class Parametro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idParametro;

    private BigDecimal remBasico;
    private BigDecimal porcAsigancionFamiliar;
    private BigDecimal porcExtra1;
    private BigDecimal porcExtra2;

    @Column(nullable = false)
    private boolean activo = true;

    @Column(nullable = false)
    private LocalDateTime fecCreacion = LocalDateTime.now();

    private LocalDateTime fecUltimaModificacion;
}
