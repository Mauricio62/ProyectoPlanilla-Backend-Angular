package com.planilla_DAWI.cibertec.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "TipoDocumentos")
public class TipoDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoDocumento;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false)
    private Boolean activo = true;

    @Column(nullable = false)
    private LocalDateTime fecCreacion = LocalDateTime.now();

    private LocalDateTime fecUltimaModificacion;
}
