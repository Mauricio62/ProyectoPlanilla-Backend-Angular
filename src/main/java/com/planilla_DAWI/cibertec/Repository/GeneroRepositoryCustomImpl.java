package com.planilla_DAWI.cibertec.Repository;

import com.planilla_DAWI.cibertec.Entity.Genero;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public class GeneroRepositoryCustomImpl implements GeneroRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Genero insertarUsingSP(String nombre) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_GENERO_INSERTAR", Genero.class);
        query.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
        query.setParameter("p_nombre", nombre);
        
        query.execute();
        return (Genero) query.getSingleResult();
    }

    @Override
    public Genero actualizarUsingSP(Integer id, String nombre, Boolean activo) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_GENERO_ACTUALIZAR", Genero.class);
        query.registerStoredProcedureParameter("p_id_genero", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_activo", Boolean.class, ParameterMode.IN);
        
        query.setParameter("p_id_genero", id);
        query.setParameter("p_nombre", nombre);
        query.setParameter("p_activo", activo);
        
        query.execute();
        return (Genero) query.getSingleResult();
    }

    @Override
    public int cambiarEstadoUsingSP(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_GENERO_CAMBIAR_ESTADO");
        query.registerStoredProcedureParameter("p_id_genero", Integer.class, ParameterMode.IN);
        query.setParameter("p_id_genero", id);
        
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
    public Genero obtenerPorIdUsingSP(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_GENERO_OBTENER_POR_ID", Genero.class);
        query.registerStoredProcedureParameter("p_id_genero", Integer.class, ParameterMode.IN);
        query.setParameter("p_id_genero", id);
        
        query.execute();
        return query.getResultList().isEmpty() ? null : (Genero) query.getSingleResult();
    }
}

