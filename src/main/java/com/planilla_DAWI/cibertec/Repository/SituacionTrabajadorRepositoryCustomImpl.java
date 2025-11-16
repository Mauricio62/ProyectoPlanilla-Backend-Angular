package com.planilla_DAWI.cibertec.Repository;

import com.planilla_DAWI.cibertec.Entity.SituacionTrabajador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public class SituacionTrabajadorRepositoryCustomImpl implements SituacionTrabajadorRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public SituacionTrabajador insertarUsingSP(String nombre) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_SITUACION_TRABAJADOR_INSERTAR", SituacionTrabajador.class);
        query.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
        query.setParameter("p_nombre", nombre);
        
        query.execute();
        return (SituacionTrabajador) query.getSingleResult();
    }

    @Override
    public SituacionTrabajador actualizarUsingSP(Integer id, String nombre, Boolean activo) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_SITUACION_TRABAJADOR_ACTUALIZAR", SituacionTrabajador.class);
        query.registerStoredProcedureParameter("p_id_situacion", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_activo", Boolean.class, ParameterMode.IN);
        
        query.setParameter("p_id_situacion", id);
        query.setParameter("p_nombre", nombre);
        query.setParameter("p_activo", activo);
        
        query.execute();
        return (SituacionTrabajador) query.getSingleResult();
    }

    @Override
    public int cambiarEstadoUsingSP(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_SITUACION_TRABAJADOR_CAMBIAR_ESTADO");
        query.registerStoredProcedureParameter("p_id_situacion", Integer.class, ParameterMode.IN);
        query.setParameter("p_id_situacion", id);
        
        query.execute();
        Object result = query.getSingleResult();
        
        if (result instanceof BigInteger) {
            return ((BigInteger) result).intValue();
        } else if (result instanceof Long) {
            return ((Long) result).intValue();
        } else if (result instanceof Integer) {
            return (Integer) result;
        }
        return 0;
    }

    @Override
    public SituacionTrabajador obtenerPorIdUsingSP(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_SITUACION_TRABAJADOR_OBTENER_POR_ID", SituacionTrabajador.class);
        query.registerStoredProcedureParameter("p_id_situacion", Integer.class, ParameterMode.IN);
        query.setParameter("p_id_situacion", id);
        
        query.execute();
        return query.getResultList().isEmpty() ? null : (SituacionTrabajador) query.getSingleResult();
    }
}

