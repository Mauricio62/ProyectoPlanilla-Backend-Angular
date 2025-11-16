package com.planilla_DAWI.cibertec.Mappers;

import com.planilla_DAWI.cibertec.Dto.PlanillaMensualDTO;
import com.planilla_DAWI.cibertec.Dto.PlanillaMensualResponse;
import com.planilla_DAWI.cibertec.Dto.PlanillaPorDocumentoDTO;
import com.planilla_DAWI.cibertec.Entity.PlanillaMensual;
/*
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
*/
import java.util.List;
/*
@Mapper

 */


public interface PlanillaMapper {
/*
    PlanillaMapper INSTANCE = Mappers.getMapper(PlanillaMapper.class);

 */

    PlanillaMensualDTO PlanillatoDTO(PlanillaMensual planillaMensual);

    PlanillaMensual DTOtoEntity(PlanillaMensualDTO planillaMensualDTO);

    List<PlanillaMensualDTO> PlanillaListToDTO (List<PlanillaMensual> planillasList);
    List<PlanillaMensual> PlanillaListToEntity (List<PlanillaMensualDTO> planillaDTOList);

    PlanillaPorDocumentoDTO  DocumentoToDTO (PlanillaMensual planillaMensual);

    List<PlanillaMensual> toEntityList(List<PlanillaMensualResponse> dtos);

    List<PlanillaMensualResponse> toDtoList(List<PlanillaMensual> entities);

}
