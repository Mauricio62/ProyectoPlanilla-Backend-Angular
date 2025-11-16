
package com.planilla_DAWI.cibertec.Mappers;

import com.planilla_DAWI.cibertec.Dto.TrabajadorDTO;
import com.planilla_DAWI.cibertec.Entity.Trabajador;
import com.planilla_DAWI.cibertec.Utils.Helps.Convert;

public class TrabajadorMapper {

    public static Trabajador toEntity(TrabajadorDTO request) {
        Trabajador entity = new Trabajador();
        entity.setIdTipoDocumento(request.getIdTipoDocumento());
        entity.setDocumento(request.getDocumento());
        entity.setNombres(request.getNombres());
        entity.setApellidoPaterno(request.getApellidoPaterno());
        entity.setApellidoMaterno(request.getApellidoMaterno());
        entity.setIdGenero(request.getIdGenero());
        entity.setIdEstadoCivil(request.getIdEstadoCivil());
        entity.setDireccion(request.getDireccion());
        entity.setEmail(request.getEmail());
        entity.setHijos(request.getHijos());
        entity.setIdCargo(request.getIdCargo());
        entity.setFecNacimiento(Convert.convertToLocalDateTime( request.getFecNacimiento()));
        entity.setFecIngreso(Convert.convertToLocalDateTime( request.getFecIngreso()));
        entity.setIdSituacion(request.getIdSituacion());
        entity.setIdSistemaPension(request.getIdSistemaPension());
        entity.setFoto(request.getFoto());
        return entity;
    }

    public static TrabajadorDTO toDTO(Trabajador entity) {
        TrabajadorDTO response = new TrabajadorDTO();
        response.setIdTrabajador(entity.getIdTrabajador());
        response.setIdTipoDocumento(entity.getIdTipoDocumento());
        response.setDocumento(entity.getDocumento());
        response.setNombres(entity.getNombres());
        response.setApellidoPaterno(entity.getApellidoPaterno());
        response.setApellidoMaterno(entity.getApellidoMaterno());
        response.setIdGenero(entity.getIdGenero());
        response.setIdEstadoCivil(entity.getIdEstadoCivil());
        response.setDireccion(entity.getDireccion());
        response.setEmail(entity.getEmail());
        response.setHijos(entity.getHijos());
        response.setIdCargo(entity.getIdCargo());
        response.setFecNacimiento(Convert.convertToDate( entity.getFecNacimiento()));
        response.setFecIngreso(Convert.convertToDate( entity.getFecIngreso()));
        response.setIdSituacion(entity.getIdSituacion());
        response.setIdSistemaPension(entity.getIdSistemaPension());
        response.setFoto(entity.getFoto());
        response.setActivo(entity.getActivo());
        response.setFecCreacion(Convert.convertToDate( entity.getFecCreacion()));
        response.setFecUltimaModificacion(Convert.convertToDate( entity.getFecUltimaModificacion()));
        return response;
    }
}
