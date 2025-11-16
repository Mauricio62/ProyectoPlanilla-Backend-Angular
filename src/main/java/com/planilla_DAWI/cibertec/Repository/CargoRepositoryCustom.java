package com.planilla_DAWI.cibertec.Repository;

import com.planilla_DAWI.cibertec.Entity.Cargo;

public interface CargoRepositoryCustom {
    Cargo insertarUsingSP(String nombre);
    Cargo actualizarUsingSP(Integer id, String nombre, Boolean activo);
    int cambiarEstadoUsingSP(Integer id);
    Cargo obtenerPorIdUsingSP(Integer id);
}

