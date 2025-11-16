package com.planilla_DAWI.cibertec.Mappers;

import com.planilla_DAWI.cibertec.Dto.TipoDocumentoDTO;
import com.planilla_DAWI.cibertec.Entity.TipoDocumento;
import com.planilla_DAWI.cibertec.Utils.Helps.Convert;

public class TipoDocumentoMapper {

    public static TipoDocumentoDTO toDTO(TipoDocumento entity) {
        if (entity == null) {
            return null;
        }

        TipoDocumentoDTO dto = new TipoDocumentoDTO();
        dto.setIdTipoDocumento(entity.getIdTipoDocumento());
        dto.setNombre(entity.getNombre());
        dto.setActivo(entity.getActivo());
        dto.setFecCreacion(Convert.convertToDate( entity.getFecCreacion()));
        dto.setFecUltimaModificacion(Convert.convertToDate(entity.getFecUltimaModificacion()));

        return dto;
    }

    public static TipoDocumento toEntity(TipoDocumentoDTO dto) {
        if (dto == null) {
            return null;
        }

        TipoDocumento entity = new TipoDocumento();
        entity.setIdTipoDocumento(dto.getIdTipoDocumento());
        entity.setNombre(dto.getNombre());
        entity.setActivo(dto.getActivo());
        entity.setFecCreacion(Convert.convertToLocalDateTime(dto.getFecCreacion()));
        entity.setFecUltimaModificacion(Convert.convertToLocalDateTime(dto.getFecUltimaModificacion()));

        return entity;
    }
}
