package com.planilla_DAWI.cibertec.Mappers;

import com.planilla_DAWI.cibertec.Dto.EstadoCivilDTO;
import com.planilla_DAWI.cibertec.Entity.EstadoCivil;
import com.planilla_DAWI.cibertec.Utils.Helps.Convert;


public class EstadoCivilMapper {

    public static EstadoCivilDTO toDTO(EstadoCivil entity) {
        EstadoCivilDTO dto = new EstadoCivilDTO();
        dto.setIdEstadoCivil(entity.getIdEstadoCivil());
        dto.setNombre(entity.getNombre());
        dto.setActivo(entity.getActivo());
        dto.setFecCreacion(Convert.convertToDate( entity.getFecCreacion()));
        dto.setFecUltimaModificacion(Convert.convertToDate( entity.getFecUltimaModificacion()));
        return dto;
    }

    public static EstadoCivil toEntity(EstadoCivilDTO dto) {
        EstadoCivil entity = new EstadoCivil();
        entity.setIdEstadoCivil(dto.getIdEstadoCivil());
        entity.setNombre(dto.getNombre());
        entity.setActivo(dto.getActivo());
        entity.setFecCreacion( Convert.convertToLocalDateTime(dto.getFecCreacion()));
        entity.setFecUltimaModificacion(Convert.convertToLocalDateTime(dto.getFecUltimaModificacion()));
        return entity;
    }
}
