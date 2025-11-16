package com.planilla_DAWI.cibertec.Repository;

import com.planilla_DAWI.cibertec.Entity.SituacionTrabajador;

public interface SituacionTrabajadorRepositoryCustom {
    SituacionTrabajador insertarUsingSP(String nombre);
    SituacionTrabajador actualizarUsingSP(Integer id, String nombre, Boolean activo);
    int cambiarEstadoUsingSP(Integer id);
    SituacionTrabajador obtenerPorIdUsingSP(Integer id);
}

