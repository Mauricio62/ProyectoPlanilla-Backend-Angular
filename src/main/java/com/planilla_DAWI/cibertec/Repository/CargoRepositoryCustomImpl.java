package com.planilla_DAWI.cibertec.Repository;

import com.planilla_DAWI.cibertec.Entity.Cargo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public class CargoRepositoryCustomImpl implements CargoRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Cargo insertarUsingSP(String nombre) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_CARGO_INSERTAR", Cargo.class);
        query.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
        query.setParameter("p_nombre", nombre);
        
        query.execute();
        return (Cargo) query.getSingleResult();
    }

    @Override
    public Cargo actualizarUsingSP(Integer id, String nombre, Boolean activo) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_CARGO_ACTUALIZAR", Cargo.class);
        query.registerStoredProcedureParameter("p_id_cargo", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_activo", Boolean.class, ParameterMode.IN);
        
        query.setParameter("p_id_cargo", id);
        query.setParameter("p_nombre", nombre);
        query.setParameter("p_activo", activo);
        
        query.execute();
        return (Cargo) query.getSingleResult();
    }

    @Override
    public int cambiarEstadoUsingSP(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_CARGO_CAMBIAR_ESTADO");
        query.registerStoredProcedureParameter("p_id_cargo", Integer.class, ParameterMode.IN);
        query.setParameter("p_id_cargo", id);
        
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
    public Cargo obtenerPorIdUsingSP(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_CARGO_OBTENER_POR_ID", Cargo.class);
        query.registerStoredProcedureParameter("p_id_cargo", Integer.class, ParameterMode.IN);
        query.setParameter("p_id_cargo", id);
        
        query.execute();
        return query.getResultList().isEmpty() ? null : (Cargo) query.getSingleResult();
    }
}

