package com.planilla_DAWI.cibertec.Repository;

import com.planilla_DAWI.cibertec.Entity.PlanillaMensual;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PlanillaRepository extends JpaRepository<PlanillaMensual, Integer> {

    public List<PlanillaMensual> findByAnioAndMes(Integer anio, Integer mes);

    @Query(
            value = "SELECT pm.* " +
                    "FROM PlanillaMensual pm " +
                    "INNER JOIN Trabajadores t ON pm.IdTrabajador = t.IdTrabajador " +
                    "INNER JOIN SituacionTrabajador st ON t.IdSituacion = st.IdSituacion " +
                    "WHERE pm.Año = :anio " +
                    "  AND pm.Mes = :mes " +
                    "  AND t.Documento = :documento " +
                    "  AND st.Activo = 1",
            nativeQuery = true)
    public PlanillaMensual findPlanillaActivaPorDocumento(
            @Param("anio") int anio,
            @Param("mes") int mes,
            @Param("documento") String documento);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM PlanillaMensual WHERE Año = :Año AND Mes = :Mes", nativeQuery = true)
    public int eliminarPlanillas (@Param(value = "Año")Integer año,@Param(value = "mes") Integer mes);

}
