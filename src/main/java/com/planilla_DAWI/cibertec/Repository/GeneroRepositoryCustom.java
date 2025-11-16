package com.planilla_DAWI.cibertec.Repository;

import com.planilla_DAWI.cibertec.Entity.Genero;

public interface GeneroRepositoryCustom {
    Genero insertarUsingSP(String nombre);
    Genero actualizarUsingSP(Integer id, String nombre, Boolean activo);
    int cambiarEstadoUsingSP(Integer id);
    Genero obtenerPorIdUsingSP(Integer id);
}

