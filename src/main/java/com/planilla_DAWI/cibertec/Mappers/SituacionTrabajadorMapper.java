package com.planilla_DAWI.cibertec.Mappers;

import com.planilla_DAWI.cibertec.Dto.SituacionTrabajadorDTO;
import com.planilla_DAWI.cibertec.Entity.SituacionTrabajador;
import com.planilla_DAWI.cibertec.Utils.Helps.Convert;

public class SituacionTrabajadorMapper {

    public static SituacionTrabajadorDTO toDTO(SituacionTrabajador entity) {
        if (entity == null) {
            return null;
        }

        SituacionTrabajadorDTO dto = new SituacionTrabajadorDTO();
        dto.setIdSituacion(entity.getIdSituacion());
        dto.setNombre(entity.getNombre());
        dto.setActivo(entity.getActivo());
        dto.setFecCreacion(Convert.convertToDate( entity.getFecCreacion()));
        dto.setFecUltimaModificacion(Convert.convertToDate( entity.getFecUltimaModificacion()));

        return dto;
    }

    public static SituacionTrabajador toEntity(SituacionTrabajadorDTO dto) {
        if (dto == null) {
            return null;
        }

        SituacionTrabajador entity = new SituacionTrabajador();
        entity.setIdSituacion(dto.getIdSituacion());
        entity.setNombre(dto.getNombre());
        entity.setActivo(dto.getActivo());
        entity.setFecCreacion(Convert.convertToLocalDateTime( dto.getFecCreacion()));
        entity.setFecUltimaModificacion(Convert.convertToLocalDateTime(dto.getFecUltimaModificacion()));

        return entity;
    }
}
