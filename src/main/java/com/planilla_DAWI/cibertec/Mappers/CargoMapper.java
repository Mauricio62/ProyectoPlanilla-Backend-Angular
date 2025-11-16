package com.planilla_DAWI.cibertec.Mappers;

import com.planilla_DAWI.cibertec.Dto.CargoDTO;
import com.planilla_DAWI.cibertec.Entity.Cargo;
import com.planilla_DAWI.cibertec.Utils.Helps.Convert;

public class CargoMapper {
    public static CargoDTO toDTO(Cargo entity) {
        CargoDTO dto = new CargoDTO();
        dto.setIdCargo(entity.getIdCargo());
        dto.setNombre(entity.getNombre());
        dto.setActivo(entity.getActivo());
        dto.setFecCreacion(Convert.convertToDate(  entity.getFecCreacion()));
        dto.setFecUltimaModificacion(Convert.convertToDate(entity.getFecUltimaModificacion()));
        return dto;
    }

    public static Cargo toEntity(CargoDTO dto) {
        Cargo entity = new Cargo();
        entity.setIdCargo(dto.getIdCargo());
        entity.setNombre(dto.getNombre());
        entity.setActivo(dto.getActivo());
        entity.setFecCreacion(Convert.convertToLocalDateTime( dto.getFecCreacion()));
        entity.setFecUltimaModificacion(Convert.convertToLocalDateTime(dto.getFecUltimaModificacion()));
        return entity;
    }
}
