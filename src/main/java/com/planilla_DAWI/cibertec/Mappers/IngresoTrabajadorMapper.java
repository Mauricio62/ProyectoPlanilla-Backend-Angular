
package com.planilla_DAWI.cibertec.Mappers;

import com.planilla_DAWI.cibertec.Dto.IngresoTrabajadorDTO;
import com.planilla_DAWI.cibertec.Entity.IngresoTrabajador;

public class IngresoTrabajadorMapper {
    public static IngresoTrabajadorDTO toDTO(IngresoTrabajador entity) {
        if (entity == null) {
            return null;
        }
        IngresoTrabajadorDTO dto = new IngresoTrabajadorDTO();
        dto.setIdIngresoTrabajador(entity.getIdIngresoTrabajador());
        dto.setIdTrabajador(entity.getTrabajador().getIdTrabajador());
        dto.setNombres(entity.getTrabajador().getNombres());
        dto.setApellidoPaterno(entity.getTrabajador().getApellidoPaterno());
        dto.setApellidoMaterno(entity.getTrabajador().getApellidoMaterno());
        dto.setDocumento(entity.getTrabajador().getDocumento());
        dto.setRemuneracion(entity.getRemuneracion());
        dto.setVale(entity.getRemuneracion());
        dto.setBonifCargo(entity.getBonifCargo());
        dto.setActivo(entity.getActivo());
        return dto;
    }
}

