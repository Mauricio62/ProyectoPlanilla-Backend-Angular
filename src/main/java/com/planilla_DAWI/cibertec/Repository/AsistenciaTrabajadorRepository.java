package com.planilla_DAWI.cibertec.Repository;

import com.planilla_DAWI.cibertec.Dto.AsistenciaTrabajadorResponse;
import com.planilla_DAWI.cibertec.Entity.AsistenciaTrabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AsistenciaTrabajadorRepository extends JpaRepository<AsistenciaTrabajador, Integer> {

    @Query("SELECT new com.planilla_DAWI.cibertec.Dto.AsistenciaTrabajadorResponse(" +
            "t.idTrabajador, t.documento, CONCAT(t.apellidoPaterno, ' ', t.apellidoMaterno, ' ', t.nombres), " +
            "a.diasLaborales, a.diasDescanso, a.diasInasistencia, a.diasFeriados, " +
            "a.horasExtra25, a.horasExtra35, a.idAsistencia) " +
            "FROM Trabajador t LEFT JOIN AsistenciaTrabajador a " +
            "ON a.trabajador.idTrabajador = t.idTrabajador AND a.año = :año AND a.mes = :mes " +
            "WHERE t.activo = true")
    List<AsistenciaTrabajadorResponse> findByPeriodo(@Param("año") Integer año, @Param("mes") Integer mes);
    @Query("SELECT a " +
            "FROM AsistenciaTrabajador a " +
            "JOIN a.trabajador t " +
            "WHERE t.activo = true " +
            "AND a.año = :año " +
            "AND a.mes = :mes")
    List<AsistenciaTrabajador> findByPeriodoEntidad(@Param("año") Integer año, @Param("mes") Integer mes);

    @Transactional
    @Modifying
    @Query("DELETE FROM AsistenciaTrabajador a WHERE a.año = :año AND a.mes = :mes")
    void deleteByPeriodo(@Param("año") Integer año, @Param("mes") Integer mes);

    List<AsistenciaTrabajador> findByAñoAndMes(Integer año, Integer mes);
}