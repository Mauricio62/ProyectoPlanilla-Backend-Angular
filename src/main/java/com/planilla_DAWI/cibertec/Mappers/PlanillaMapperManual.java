package com.planilla_DAWI.cibertec.Mappers;

import com.planilla_DAWI.cibertec.Dto.PlanillaMensualDTO;
import com.planilla_DAWI.cibertec.Dto.PlanillaMensualResponse;
import com.planilla_DAWI.cibertec.Dto.PlanillaPorDocumentoDTO;
import com.planilla_DAWI.cibertec.Entity.PlanillaMensual;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlanillaMapperManual {

    public PlanillaMensualDTO planillaToDTO(PlanillaMensual planillaMensual) {
        if (planillaMensual == null) {
            return null;
        }

        PlanillaMensualDTO dto = new PlanillaMensualDTO();
        dto.setIdPlanillaMensual(planillaMensual.getIdPlanillaMensual());
        dto.setAnio(planillaMensual.getAnio());
        dto.setMes(planillaMensual.getMes());
        dto.setTrabajador(planillaMensual.getTrabajador());
        dto.setSituacion(planillaMensual.getSituacion());
        dto.setCargo(planillaMensual.getCargo());
        dto.setApellido(planillaMensual.getApellido());
        dto.setNombre(planillaMensual.getNombre());
        dto.setSistemaPension(planillaMensual.getSistemaPension());
        dto.setEstadoCivil(planillaMensual.getEstadoCivil());
        dto.setHijos(planillaMensual.getHijos());
        dto.setFechaIngreso(planillaMensual.getFechaIngreso());
        dto.setSueldoBasico(planillaMensual.getSueldoBasico());
        dto.setPorcHoraExtra1(planillaMensual.getPorcHoraExtra1());
        dto.setPorcHoraExtra2(planillaMensual.getPorcHoraExtra2());
        dto.setPorcDescansoTrab(planillaMensual.getPorcDescansoTrab());
        dto.setPorcFeriadoTrab(planillaMensual.getPorcFeriadoTrab());
        dto.setPorcAsigFamiliar(planillaMensual.getPorcAsigFamiliar());
        dto.setNHorasNormal(planillaMensual.getNHorasNormal());
        dto.setNHorasExtra1(planillaMensual.getNHorasExtra1());
        dto.setNHorasExtra2(planillaMensual.getNHorasExtra2());
        dto.setNDiasTrab(planillaMensual.getNDiasTrab());
        dto.setNDiasDescansos(planillaMensual.getNDiasDescansos());
        dto.setNFeriadosTrab(planillaMensual.getNFeriadosTrab());
        dto.setNDescansosTrab(planillaMensual.getNDescansosTrab());
        dto.setNDiasInasistencias(planillaMensual.getNDiasInasistencias());
        dto.setHaberBasico(planillaMensual.getHaberBasico());
        dto.setValesEmpleado(planillaMensual.getValesEmpleado());
        dto.setVHorasExtra1(planillaMensual.getVHorasExtra1());
        dto.setVHorasExtra2(planillaMensual.getVHorasExtra2());
        dto.setVAsigFamiliar(planillaMensual.getVAsigFamiliar());
        dto.setVDescansoTrab(planillaMensual.getVDescansoTrab());
        dto.setVFeriadoTrab(planillaMensual.getVFeriadoTrab());
        dto.setBonificacionCargo(planillaMensual.getBonificacionCargo());
        dto.setBonificacionMovilidad(planillaMensual.getBonificacionMovilidad());
        dto.setCanastaNavidad(planillaMensual.getCanastaNavidad());
        dto.setEscolaridad(planillaMensual.getEscolaridad());
        dto.setDiaTrabajador(planillaMensual.getDiaTrabajador());
        dto.setTotalIngreso(planillaMensual.getTotalIngreso());
        dto.setRenta5ta(planillaMensual.getRenta5ta());
        dto.setDescuentoJud1(planillaMensual.getDescuentoJud1());
        dto.setDescuentoJud2(planillaMensual.getDescuentoJud2());
        dto.setDescuentoJud3(planillaMensual.getDescuentoJud3());
        dto.setOtrosAdelantos(planillaMensual.getOtrosAdelantos());
        dto.setAdelantoCaja(planillaMensual.getAdelantoCaja());
        dto.setAdelantoQuincena(planillaMensual.getAdelantoQuincena());
        dto.setAdelantoVac(planillaMensual.getAdelantoVac());
        dto.setAdelantoGratificacion(planillaMensual.getAdelantoGratificacion());
        dto.setAdelantoLiquidacion(planillaMensual.getAdelantoLiquidacion());
        dto.setAdelantoCTS(planillaMensual.getAdelantoCTS());
        dto.setPorcAporte(planillaMensual.getPorcAporte());
        dto.setAporte(planillaMensual.getAporte());
        dto.setPorcComision(planillaMensual.getPorcComision());
        dto.setComision(planillaMensual.getComision());
        dto.setPorcPrima(planillaMensual.getPorcPrima());
        dto.setPrima(planillaMensual.getPrima());
        dto.setOtdSeg(planillaMensual.getOtdSeg());
        dto.setOtdPacifico(planillaMensual.getOtdPacifico());
        dto.setIdBanco1(planillaMensual.getIdBanco1());
        dto.setPrestamo1(planillaMensual.getPrestamo1());
        dto.setTardanza(planillaMensual.getTardanza());
        dto.setTotalDescuento(planillaMensual.getTotalDescuento());
        dto.setPorcEsSalud(planillaMensual.getPorcEsSalud());
        dto.setEsSalud(planillaMensual.getEsSalud());
        dto.setAccidenteTrab(planillaMensual.getAccidenteTrab());
        dto.setSenati(planillaMensual.getSenati());
        dto.setSeguroVidaLey(planillaMensual.getSeguroVidaLey());
        dto.setTotalNeto(planillaMensual.getTotalNeto());
        dto.setTotalNetoCad(planillaMensual.getTotalNetoCad());
        dto.setTotalNetoBoleta(planillaMensual.getTotalNetoBoleta());
        dto.setTotalNetoBoletaCad(planillaMensual.getTotalNetoBoletaCad());
        dto.setActivo(planillaMensual.isActivo());
        dto.setFecCreacion(planillaMensual.getFecCreacion());
        dto.setFecUltimaModificacion(planillaMensual.getFecUltimaModificacion());

        return dto;
    }

    public PlanillaMensual dtoToEntity(PlanillaMensualDTO dto) {
        if (dto == null) {
            return null;
        }

        PlanillaMensual entity = new PlanillaMensual();
        entity.setIdPlanillaMensual(dto.getIdPlanillaMensual());
        entity.setAnio(dto.getAnio());
        entity.setMes(dto.getMes());
        entity.setTrabajador(dto.getTrabajador());
        entity.setSituacion(dto.getSituacion());
        entity.setCargo(dto.getCargo());
        entity.setApellido(dto.getApellido());
        entity.setNombre(dto.getNombre());
        entity.setSistemaPension(dto.getSistemaPension());
        entity.setEstadoCivil(dto.getEstadoCivil());
        entity.setHijos(dto.getHijos());
        entity.setFechaIngreso(dto.getFechaIngreso());
        entity.setSueldoBasico(dto.getSueldoBasico());
        entity.setPorcHoraExtra1(dto.getPorcHoraExtra1());
        entity.setPorcHoraExtra2(dto.getPorcHoraExtra2());
        entity.setPorcDescansoTrab(dto.getPorcDescansoTrab());
        entity.setPorcFeriadoTrab(dto.getPorcFeriadoTrab());
        entity.setPorcAsigFamiliar(dto.getPorcAsigFamiliar());
        entity.setNHorasNormal(dto.getNHorasNormal());
        entity.setNHorasExtra1(dto.getNHorasExtra1());
        entity.setNHorasExtra2(dto.getNHorasExtra2());
        entity.setNDiasTrab(dto.getNDiasTrab());
        entity.setNDiasDescansos(dto.getNDiasDescansos());
        entity.setNFeriadosTrab(dto.getNFeriadosTrab());
        entity.setNDescansosTrab(dto.getNDescansosTrab());
        entity.setNDiasInasistencias(dto.getNDiasInasistencias());
        entity.setHaberBasico(dto.getHaberBasico());
        entity.setValesEmpleado(dto.getValesEmpleado());
        entity.setVHorasExtra1(dto.getVHorasExtra1());
        entity.setVHorasExtra2(dto.getVHorasExtra2());
        entity.setVAsigFamiliar(dto.getVAsigFamiliar());
        entity.setVDescansoTrab(dto.getVDescansoTrab());
        entity.setVFeriadoTrab(dto.getVFeriadoTrab());
        entity.setBonificacionCargo(dto.getBonificacionCargo());
        entity.setBonificacionMovilidad(dto.getBonificacionMovilidad());
        entity.setCanastaNavidad(dto.getCanastaNavidad());
        entity.setEscolaridad(dto.getEscolaridad());
        entity.setDiaTrabajador(dto.getDiaTrabajador());
        entity.setTotalIngreso(dto.getTotalIngreso());
        entity.setRenta5ta(dto.getRenta5ta());
        entity.setDescuentoJud1(dto.getDescuentoJud1());
        entity.setDescuentoJud2(dto.getDescuentoJud2());
        entity.setDescuentoJud3(dto.getDescuentoJud3());
        entity.setOtrosAdelantos(dto.getOtrosAdelantos());
        entity.setAdelantoCaja(dto.getAdelantoCaja());
        entity.setAdelantoQuincena(dto.getAdelantoQuincena());
        entity.setAdelantoVac(dto.getAdelantoVac());
        entity.setAdelantoGratificacion(dto.getAdelantoGratificacion());
        entity.setAdelantoLiquidacion(dto.getAdelantoLiquidacion());
        entity.setAdelantoCTS(dto.getAdelantoCTS());
        entity.setPorcAporte(dto.getPorcAporte());
        entity.setAporte(dto.getAporte());
        entity.setPorcComision(dto.getPorcComision());
        entity.setComision(dto.getComision());
        entity.setPorcPrima(dto.getPorcPrima());
        entity.setPrima(dto.getPrima());
        entity.setOtdSeg(dto.getOtdSeg());
        entity.setOtdPacifico(dto.getOtdPacifico());
        entity.setIdBanco1(dto.getIdBanco1());
        entity.setPrestamo1(dto.getPrestamo1());
        entity.setTardanza(dto.getTardanza());
        entity.setTotalDescuento(dto.getTotalDescuento());
        entity.setPorcEsSalud(dto.getPorcEsSalud());
        entity.setEsSalud(dto.getEsSalud());
        entity.setAccidenteTrab(dto.getAccidenteTrab());
        entity.setSenati(dto.getSenati());
        entity.setSeguroVidaLey(dto.getSeguroVidaLey());
        entity.setTotalNeto(dto.getTotalNeto());
        entity.setTotalNetoCad(dto.getTotalNetoCad());
        entity.setTotalNetoBoleta(dto.getTotalNetoBoleta());
        entity.setTotalNetoBoletaCad(dto.getTotalNetoBoletaCad());
        entity.setActivo(dto.isActivo());
        entity.setFecCreacion(dto.getFecCreacion());
        entity.setFecUltimaModificacion(dto.getFecUltimaModificacion());

        return entity;
    }

    public List<PlanillaMensualDTO> planillaListToDTO(List<PlanillaMensual> planillas) {
        if (planillas == null) {
            return null;
        }
        return planillas.stream()
                .map(this::planillaToDTO)
                .collect(Collectors.toList());
    }

    public List<PlanillaMensual> planillaListToEntity(List<PlanillaMensualDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }

    public PlanillaPorDocumentoDTO documentoToDTO(PlanillaMensual planillaMensual) {
        if (planillaMensual == null) {
            return null;
        }

        PlanillaPorDocumentoDTO dto = new PlanillaPorDocumentoDTO();
        dto.setAnio(planillaMensual.getAnio());
        dto.setMes(planillaMensual.getMes());
        dto.setNDiasTrab(planillaMensual.getNDiasTrab());
        dto.setNHorasNormal(planillaMensual.getNHorasNormal());
        dto.setHaberBasico(planillaMensual.getHaberBasico());
        dto.setVAsigFamiliar(planillaMensual.getVAsigFamiliar());
        dto.setVHorasExtra1(planillaMensual.getVHorasExtra1());
        dto.setVHorasExtra2(planillaMensual.getVHorasExtra2());
        dto.setVFeriadoTrab(planillaMensual.getVFeriadoTrab());
        dto.setValesEmpleado(planillaMensual.getValesEmpleado());
        dto.setBonificacionCargo(planillaMensual.getBonificacionCargo());
        dto.setTotalIngreso(planillaMensual.getTotalIngreso());
        dto.setAporte(planillaMensual.getAporte());
        dto.setComision(planillaMensual.getComision());
        dto.setPrima(planillaMensual.getPrima());
        dto.setTotalDescuento(planillaMensual.getTotalDescuento());
        dto.setEsSalud(planillaMensual.getEsSalud());
        dto.setSeguroVidaLey(planillaMensual.getSeguroVidaLey());
        dto.setTotalNetoBoleta(planillaMensual.getTotalNetoBoleta());
        dto.setTotalNetoBoletaCad(planillaMensual.getTotalNetoBoletaCad());

        return dto;
    }

    public List<PlanillaMensual> responseListToEntity(List<PlanillaMensualResponse> responses) {
        if (responses == null) {
            return null;
        }
        return responses.stream()
                .map(this::responseToEntity)
                .collect(Collectors.toList());
    }

    public List<PlanillaMensualResponse> entityListToResponse(List<PlanillaMensual> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::entityToResponse)
                .collect(Collectors.toList());
    }

    public PlanillaMensualResponse entityToResponse(PlanillaMensual entity) {
        if (entity == null) {
            return null;
        }

        PlanillaMensualResponse response = new PlanillaMensualResponse();
        response.setIdPlanillaMensual(entity.getIdPlanillaMensual());
        response.setAnio(entity.getAnio());
        response.setMes(entity.getMes());
        response.setTrabajador(entity.getTrabajador());
        response.setSituacion(entity.getSituacion());
        response.setCargo(entity.getCargo());
        response.setApellido(entity.getApellido());
        response.setNombre(entity.getNombre());
        response.setSistemaPension(entity.getSistemaPension());
        response.setEstadoCivil(entity.getEstadoCivil());
        response.setHijos(entity.getHijos());
        response.setFechaIngreso(entity.getFechaIngreso());
        response.setSueldoBasico(entity.getSueldoBasico());
        response.setPorcHoraExtra1(entity.getPorcHoraExtra1());
        response.setPorcHoraExtra2(entity.getPorcHoraExtra2());
        response.setPorcDescansoTrab(entity.getPorcDescansoTrab());
        response.setPorcFeriadoTrab(entity.getPorcFeriadoTrab());
        response.setPorcAsigFamiliar(entity.getPorcAsigFamiliar());
        response.setNHorasNormal(entity.getNHorasNormal());
        response.setNHorasExtra1(entity.getNHorasExtra1());
        response.setNHorasExtra2(entity.getNHorasExtra2());
        response.setNDiasTrab(entity.getNDiasTrab());
        response.setNDiasDescansos(entity.getNDiasDescansos());
        response.setNFeriadosTrab(entity.getNFeriadosTrab());
        response.setNDescansosTrab(entity.getNDescansosTrab());
        response.setNDiasInasistencias(entity.getNDiasInasistencias());
        response.setHaberBasico(entity.getHaberBasico());
        response.setValesEmpleado(entity.getValesEmpleado());
        response.setVHorasExtra1(entity.getVHorasExtra1());
        response.setVHorasExtra2(entity.getVHorasExtra2());
        response.setVAsigFamiliar(entity.getVAsigFamiliar());
        response.setVDescansoTrab(entity.getVDescansoTrab());
        response.setVFeriadoTrab(entity.getVFeriadoTrab());
        response.setBonificacionCargo(entity.getBonificacionCargo());
        response.setBonificacionMovilidad(entity.getBonificacionMovilidad());
        response.setCanastaNavidad(entity.getCanastaNavidad());
        response.setEscolaridad(entity.getEscolaridad());
        response.setDiaTrabajador(entity.getDiaTrabajador());
        response.setTotalIngreso(entity.getTotalIngreso());
        response.setRenta5ta(entity.getRenta5ta());
        response.setDescuentoJud1(entity.getDescuentoJud1());
        response.setDescuentoJud2(entity.getDescuentoJud2());
        response.setDescuentoJud3(entity.getDescuentoJud3());
        response.setOtrosAdelantos(entity.getOtrosAdelantos());
        response.setAdelantoCaja(entity.getAdelantoCaja());
        response.setAdelantoQuincena(entity.getAdelantoQuincena());
        response.setAdelantoVac(entity.getAdelantoVac());
        response.setAdelantoGratificacion(entity.getAdelantoGratificacion());
        response.setAdelantoLiquidacion(entity.getAdelantoLiquidacion());
        response.setAdelantoCTS(entity.getAdelantoCTS());
        response.setPorcAporte(entity.getPorcAporte());
        response.setAporte(entity.getAporte());
        response.setPorcComision(entity.getPorcComision());
        response.setComision(entity.getComision());
        response.setPorcPrima(entity.getPorcPrima());
        response.setPrima(entity.getPrima());
        response.setOtdSeg(entity.getOtdSeg());
        response.setOtdPacifico(entity.getOtdPacifico());
        response.setIdBanco1(entity.getIdBanco1());
        response.setPrestamo1(entity.getPrestamo1());
        response.setTardanza(entity.getTardanza());
        response.setTotalDescuento(entity.getTotalDescuento());
        response.setPorcEsSalud(entity.getPorcEsSalud());
        response.setEsSalud(entity.getEsSalud());
        response.setAccidenteTrab(entity.getAccidenteTrab());
        response.setSenati(entity.getSenati());
        response.setSeguroVidaLey(entity.getSeguroVidaLey());
        response.setTotalNeto(entity.getTotalNeto());
        response.setTotalNetoCad(entity.getTotalNetoCad());
        response.setTotalNetoBoleta(entity.getTotalNetoBoleta());

        return response;
    }

    public PlanillaMensual responseToEntity(PlanillaMensualResponse response) {
        if (response == null) {
            return null;
        }

        PlanillaMensual entity = new PlanillaMensual();
        entity.setIdPlanillaMensual(response.getIdPlanillaMensual());
        entity.setAnio(response.getAnio());
        entity.setMes(response.getMes());
        entity.setTrabajador(response.getTrabajador());
        entity.setSituacion(response.getSituacion());
        entity.setCargo(response.getCargo());
        entity.setApellido(response.getApellido());
        entity.setNombre(response.getNombre());
        entity.setSistemaPension(response.getSistemaPension());
        entity.setEstadoCivil(response.getEstadoCivil());
        entity.setHijos(response.getHijos());
        entity.setFechaIngreso(response.getFechaIngreso());
        entity.setSueldoBasico(response.getSueldoBasico());
        entity.setPorcHoraExtra1(response.getPorcHoraExtra1());
        entity.setPorcHoraExtra2(response.getPorcHoraExtra2());
        entity.setPorcDescansoTrab(response.getPorcDescansoTrab());
        entity.setPorcFeriadoTrab(response.getPorcFeriadoTrab());
        entity.setPorcAsigFamiliar(response.getPorcAsigFamiliar());
        entity.setNHorasNormal(response.getNHorasNormal());
        entity.setNHorasExtra1(response.getNHorasExtra1());
        entity.setNHorasExtra2(response.getNHorasExtra2());
        entity.setNDiasTrab(response.getNDiasTrab());
        entity.setNDiasDescansos(response.getNDiasDescansos());
        entity.setNFeriadosTrab(response.getNFeriadosTrab());
        entity.setNDescansosTrab(response.getNDescansosTrab());
        entity.setNDiasInasistencias(response.getNDiasInasistencias());
        entity.setHaberBasico(response.getHaberBasico());
        entity.setValesEmpleado(response.getValesEmpleado());
        entity.setVHorasExtra1(response.getVHorasExtra1());
        entity.setVHorasExtra2(response.getVHorasExtra2());
        entity.setVAsigFamiliar(response.getVAsigFamiliar());
        entity.setVDescansoTrab(response.getVDescansoTrab());
        entity.setVFeriadoTrab(response.getVFeriadoTrab());
        entity.setBonificacionCargo(response.getBonificacionCargo());
        entity.setBonificacionMovilidad(response.getBonificacionMovilidad());
        entity.setCanastaNavidad(response.getCanastaNavidad());
        entity.setEscolaridad(response.getEscolaridad());
        entity.setDiaTrabajador(response.getDiaTrabajador());
        entity.setTotalIngreso(response.getTotalIngreso());
        entity.setRenta5ta(response.getRenta5ta());
        entity.setDescuentoJud1(response.getDescuentoJud1());
        entity.setDescuentoJud2(response.getDescuentoJud2());
        entity.setDescuentoJud3(response.getDescuentoJud3());
        entity.setOtrosAdelantos(response.getOtrosAdelantos());
        entity.setAdelantoCaja(response.getAdelantoCaja());
        entity.setAdelantoQuincena(response.getAdelantoQuincena());
        entity.setAdelantoVac(response.getAdelantoVac());
        entity.setAdelantoGratificacion(response.getAdelantoGratificacion());
        entity.setAdelantoLiquidacion(response.getAdelantoLiquidacion());
        entity.setAdelantoCTS(response.getAdelantoCTS());
        entity.setPorcAporte(response.getPorcAporte());
        entity.setAporte(response.getAporte());
        entity.setPorcComision(response.getPorcComision());
        entity.setComision(response.getComision());
        entity.setPorcPrima(response.getPorcPrima());
        entity.setPrima(response.getPrima());
        entity.setOtdSeg(response.getOtdSeg());
        entity.setOtdPacifico(response.getOtdPacifico());
        entity.setIdBanco1(response.getIdBanco1());
        entity.setPrestamo1(response.getPrestamo1());
        entity.setTardanza(response.getTardanza());
        entity.setTotalDescuento(response.getTotalDescuento());
        entity.setPorcEsSalud(response.getPorcEsSalud());
        entity.setEsSalud(response.getEsSalud());
        entity.setAccidenteTrab(response.getAccidenteTrab());
        entity.setSenati(response.getSenati());
        entity.setSeguroVidaLey(response.getSeguroVidaLey());
        entity.setTotalNeto(response.getTotalNeto());
        entity.setTotalNetoCad(response.getTotalNetoCad());
        entity.setTotalNetoBoleta(response.getTotalNetoBoleta());
        // Nota: PlanillaMensualResponse no tiene TotalNetoBoletaCad, pero PlanillaMensual sí
        // Si necesitas este campo, tendrás que manejarlo por separado

        return entity;
    }
}