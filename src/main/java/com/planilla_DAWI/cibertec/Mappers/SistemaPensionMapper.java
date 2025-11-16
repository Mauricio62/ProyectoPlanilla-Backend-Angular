package com.planilla_DAWI.cibertec.Mappers;

import com.planilla_DAWI.cibertec.Dto.SistemaPensionDTO;
import com.planilla_DAWI.cibertec.Entity.SistemaPension;
import com.planilla_DAWI.cibertec.Utils.Helps.Convert;

import java.math.BigDecimal;

public class SistemaPensionMapper {
    public static SistemaPensionDTO toDTO(SistemaPension entity) {
        if (entity == null) {
            return null;
        }

        SistemaPensionDTO dto = new SistemaPensionDTO();
        dto.setIdSistemaPension(entity.getIdSistemaPension());
        dto.setNombre(entity.getNombre());
        dto.setAporte(entity.getAporte());
        dto.setComision(entity.getComision());
        dto.setPrima(entity.getPrima());
        dto.setActivo(entity.getActivo());
        dto.setFecCreacion(Convert.convertToDate( entity.getFecCreacion()));
        dto.setFecUltimaModificacion(Convert.convertToDate( entity.getFecUltimaModificacion()));

        return dto;
    }

    public static SistemaPension toEntity(SistemaPensionDTO dto) {
        if (dto == null) {
            return null;
        }

        SistemaPension entity = new SistemaPension();
        entity.setIdSistemaPension(dto.getIdSistemaPension());
        entity.setNombre(dto.getNombre());
        entity.setAporte(dto.getAporte() != null ? dto.getAporte() : BigDecimal.ZERO);
        entity.setComision(dto.getComision() != null ? dto.getComision() : BigDecimal.ZERO);
        entity.setPrima(dto.getPrima() != null ? dto.getPrima() : BigDecimal.ZERO);
        entity.setActivo(dto.getActivo());
        entity.setFecCreacion(Convert.convertToLocalDateTime(dto.getFecCreacion()));
        entity.setFecUltimaModificacion(Convert.convertToLocalDateTime(dto.getFecUltimaModificacion()));

        return entity;
    }
}
