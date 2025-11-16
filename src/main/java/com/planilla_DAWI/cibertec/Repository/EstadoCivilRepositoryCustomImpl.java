package com.planilla_DAWI.cibertec.Repository;

import com.planilla_DAWI.cibertec.Entity.EstadoCivil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public class EstadoCivilRepositoryCustomImpl implements EstadoCivilRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EstadoCivil insertarUsingSP(String nombre) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_ESTADO_CIVIL_INSERTAR", EstadoCivil.class);
        query.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
        query.setParameter("p_nombre", nombre);
        
        query.execute();
        return (EstadoCivil) query.getSingleResult();
    }

    @Override
    public EstadoCivil actualizarUsingSP(Integer id, String nombre, Boolean activo) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_ESTADO_CIVIL_ACTUALIZAR", EstadoCivil.class);
        query.registerStoredProcedureParameter("p_id_estado_civil", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_activo", Boolean.class, ParameterMode.IN);
        
        query.setParameter("p_id_estado_civil", id);
        query.setParameter("p_nombre", nombre);
        query.setParameter("p_activo", activo);
        
        query.execute();
        return (EstadoCivil) query.getSingleResult();
    }

    @Override
    public int cambiarEstadoUsingSP(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_ESTADO_CIVIL_CAMBIAR_ESTADO");
        query.registerStoredProcedureParameter("p_id_estado_civil", Integer.class, ParameterMode.IN);
        query.setParameter("p_id_estado_civil", id);
        
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
    public EstadoCivil obtenerPorIdUsingSP(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_ESTADO_CIVIL_OBTENER_POR_ID", EstadoCivil.class);
        query.registerStoredProcedureParameter("p_id_estado_civil", Integer.class, ParameterMode.IN);
        query.setParameter("p_id_estado_civil", id);
        
        query.execute();
        return query.getResultList().isEmpty() ? null : (EstadoCivil) query.getSingleResult();
    }
}

