package com.planilla_DAWI.cibertec.Repository;

import com.planilla_DAWI.cibertec.Entity.EstadoCivil;

public interface EstadoCivilRepositoryCustom {
    EstadoCivil insertarUsingSP(String nombre);
    EstadoCivil actualizarUsingSP(Integer id, String nombre, Boolean activo);
    int cambiarEstadoUsingSP(Integer id);
    EstadoCivil obtenerPorIdUsingSP(Integer id);
}

