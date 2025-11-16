package com.planilla_DAWI.cibertec.Mappers;

import com.planilla_DAWI.cibertec.Dto.GeneroDTO;
import com.planilla_DAWI.cibertec.Entity.Genero;
import com.planilla_DAWI.cibertec.Utils.Helps.Convert;

public class GeneroMapper {
    public static GeneroDTO toDTO(Genero entity) {
        if (entity == null) {
            return null;
        }

        GeneroDTO dto = new GeneroDTO();
        dto.setIdGenero(entity.getIdGenero());
        dto.setNombre(entity.getNombre());
        dto.setActivo(entity.getActivo());
        dto.setFecCreacion(Convert.convertToDate( entity.getFecCreacion()));
        dto.setFecUltimaModificacion(Convert.convertToDate( entity.getFecUltimaModificacion()));

        return dto;
    }

    public static Genero toEntity(GeneroDTO dto) {
        if (dto == null) {
            return null;
        }

        Genero entity = new Genero();
        entity.setIdGenero(dto.getIdGenero());
        entity.setNombre(dto.getNombre());
        entity.setActivo(dto.getActivo());
        entity.setFecCreacion(Convert.convertToLocalDateTime(dto.getFecCreacion()));
        entity.setFecUltimaModificacion(Convert.convertToLocalDateTime(dto.getFecUltimaModificacion()));

        return entity;
    }
}
