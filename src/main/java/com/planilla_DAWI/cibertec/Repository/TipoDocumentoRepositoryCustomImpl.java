package com.planilla_DAWI.cibertec.Repository;

import com.planilla_DAWI.cibertec.Entity.TipoDocumento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public class TipoDocumentoRepositoryCustomImpl implements TipoDocumentoRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public TipoDocumento insertarUsingSP(String nombre) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_TIPO_DOCUMENTO_INSERTAR", TipoDocumento.class);
        query.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
        query.setParameter("p_nombre", nombre);
        
        query.execute();
        return (TipoDocumento) query.getSingleResult();
    }

    @Override
    public TipoDocumento actualizarUsingSP(Integer id, String nombre, Boolean activo) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_TIPO_DOCUMENTO_ACTUALIZAR", TipoDocumento.class);
        query.registerStoredProcedureParameter("p_id_tipo_documento", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_activo", Boolean.class, ParameterMode.IN);
        
        query.setParameter("p_id_tipo_documento", id);
        query.setParameter("p_nombre", nombre);
        query.setParameter("p_activo", activo);
        
        query.execute();
        return (TipoDocumento) query.getSingleResult();
    }

    @Override
    public int cambiarEstadoUsingSP(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_TIPO_DOCUMENTO_CAMBIAR_ESTADO");
        query.registerStoredProcedureParameter("p_id_tipo_documento", Integer.class, ParameterMode.IN);
        query.setParameter("p_id_tipo_documento", id);
        
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
    public TipoDocumento obtenerPorIdUsingSP(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_TIPO_DOCUMENTO_OBTENER_POR_ID", TipoDocumento.class);
        query.registerStoredProcedureParameter("p_id_tipo_documento", Integer.class, ParameterMode.IN);
        query.setParameter("p_id_tipo_documento", id);
        
        query.execute();
        return query.getResultList().isEmpty() ? null : (TipoDocumento) query.getSingleResult();
    }
}

