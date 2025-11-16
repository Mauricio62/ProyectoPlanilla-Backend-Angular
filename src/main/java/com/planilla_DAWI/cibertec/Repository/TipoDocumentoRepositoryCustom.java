package com.planilla_DAWI.cibertec.Repository;

import com.planilla_DAWI.cibertec.Entity.TipoDocumento;

public interface TipoDocumentoRepositoryCustom {
    TipoDocumento insertarUsingSP(String nombre);
    TipoDocumento actualizarUsingSP(Integer id, String nombre, Boolean activo);
    int cambiarEstadoUsingSP(Integer id);
    TipoDocumento obtenerPorIdUsingSP(Integer id);
}

