package com.planilla_DAWI.cibertec.Service.Impl;

import com.planilla_DAWI.cibertec.Dto.PlanillaMensualDTO;
import com.planilla_DAWI.cibertec.Dto.PlanillaMensualResponse;
import com.planilla_DAWI.cibertec.Dto.PlanillaPorDocumentoDTO;
import com.planilla_DAWI.cibertec.Entity.*;
import com.planilla_DAWI.cibertec.Mappers.PlanillaMapper;
import com.planilla_DAWI.cibertec.Mappers.PlanillaMapperManual;
import com.planilla_DAWI.cibertec.Repository.*;
import com.planilla_DAWI.cibertec.Service.PlanillaMensualService;
//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanillaMensualServiceImpl implements PlanillaMensualService {

    private final PlanillaRepository planillaRepository;
    private final ParametroRepository parametroRepository;
    private final TrabajadorRepository trabajadorRepository;
    private final IngresoTrabajadorRepository ingresoTrabajadorRepository;
    private final AsistenciaTrabajadorRepository asistenciaTrabajadorRepository;
    private final SistemaPensionRepository sistemaPensionRepository;

    private final CargoRepository cargoRepository;
    private final EstadoCivilRepository estadoCivilRepository;
    private final SituacionTrabajadorRepository situacionTrabajadorRepository;
    /*
     @Autowired
     private ModelMapper modelMapper;
 */
    @Autowired
    private PlanillaMapperManual planillaMapper;

    public PlanillaMensualServiceImpl(PlanillaRepository planillaRepository, ParametroRepository parametroRepository,
                                      TrabajadorRepository trabajadorRepository, IngresoTrabajadorRepository ingresoTrabajadorRepository,
                                      AsistenciaTrabajadorRepository asistenciaTrabajadorRepository, SistemaPensionRepository sistemaPensionRepository,
                                      CargoRepository cargoRepository, EstadoCivilRepository estadoCivilRepository,
                                      SituacionTrabajadorRepository situacionTrabajadorRepository) {
      
        this.planillaRepository = planillaRepository;
        this.parametroRepository = parametroRepository;
        this.trabajadorRepository = trabajadorRepository;
        this.ingresoTrabajadorRepository = ingresoTrabajadorRepository;
        this.asistenciaTrabajadorRepository = asistenciaTrabajadorRepository;
        this.sistemaPensionRepository = sistemaPensionRepository;
        this.cargoRepository = cargoRepository;
        this.estadoCivilRepository = estadoCivilRepository;
        this.situacionTrabajadorRepository = situacionTrabajadorRepository;

    }

    @Override
    public List<PlanillaMensualDTO> listarPlanillas(Integer anio, Integer mes) {
        List<PlanillaMensual> planillaMensual = planillaRepository.findByAnioAndMes(anio, mes);
        return planillaMapper.planillaListToDTO(planillaMensual);
        //return PlanillaMapper.INSTANCE  .PlanillaListToDTO(planillaMensual);
       /* return planillaMensual.stream()
                .map(planilla -> modelMapper.map(planilla, PlanillaMensualDTO.class))
                .collect(Collectors.toList());

        */

    }

    @Override
    public PlanillaPorDocumentoDTO ObtenerPlanillaPorDocumento(String documento, Integer anio, Integer mes) {

        PlanillaMensual planillaMensual = planillaRepository.findPlanillaActivaPorDocumento(anio, mes, documento);
        return planillaMapper.documentoToDTO(planillaMensual);
        //return PlanillaMapper.INSTANCE.DocumentoToDTO(planillaMensual);
        /*return modelMapper.map(planillaMensual, PlanillaPorDocumentoDTO.class);

         */

    }

    @Override
    public List<PlanillaMensualResponse> calcularPlanilla(Integer anio, Integer mes) {

        Parametro parametro = parametroRepository.findParametro();
        List<Trabajador> trabajadores = trabajadorRepository.listarTrabajadoresActivos();

        List<IngresoTrabajador> ingresosActivos = ingresoTrabajadorRepository.ListarIngresosActivos();
        List<AsistenciaTrabajador> listarAsistencia = asistenciaTrabajadorRepository.findByPeriodoEntidad(anio, mes);
        List<SistemaPension> sistemaPensiones = sistemaPensionRepository.findAll();

        int diasMes = YearMonth.of(anio, mes).lengthOfMonth();
        List<PlanillaMensual> planillas = new ArrayList<>();

        for (Trabajador t : trabajadores) {

            PlanillaMensual pla = new PlanillaMensual();

            AsistenciaTrabajador itemAsistencia = listarAsistencia.stream()
                    .filter(a -> a.getTrabajador().getIdTrabajador().equals(t.getIdTrabajador()))
                    .findFirst()
                    .orElse(null);

            IngresoTrabajador itemIngreso = ingresosActivos.stream()
                    .filter(i -> i.getTrabajador().getIdTrabajador().equals(t.getIdTrabajador()))
                    .findFirst()
                    .orElse(null);

            int diasCalculo = (itemAsistencia != null ? itemAsistencia.getDiasLaborales() : 0)
                    + (itemAsistencia != null ? itemAsistencia.getDiasDescanso() : 0);

            pla.setAnio(anio);
            pla.setMes(mes);
            pla.setTrabajador(t);
            pla.setSituacion(situacionTrabajadorRepository.findById(t.getIdSituacion()).orElse(new SituacionTrabajador()));
            pla.setCargo(cargoRepository.findById(t.getIdCargo()).orElse(new Cargo()));
            pla.setApellido(t.getApellidoPaterno() + " " + t.getApellidoMaterno());
            pla.setNombre(t.getNombres());
            pla.setSistemaPension(sistemaPensionRepository.findById(t.getIdSistemaPension()).orElse(new SistemaPension()));
            pla.setEstadoCivil(estadoCivilRepository.findById(t.getIdEstadoCivil()).orElse(new EstadoCivil()));
            pla.setHijos(t.getHijos().shortValue());

            pla.setFechaIngreso(t.getFecIngreso());

            pla.setSueldoBasico(itemIngreso != null ? itemIngreso.getRemuneracion() : BigDecimal.ZERO);

            pla.setPorcHoraExtra1(parametro.getPorcExtra1());
            pla.setPorcHoraExtra2(parametro.getPorcExtra2());
            pla.setPorcDescansoTrab(BigDecimal.valueOf(itemAsistencia.getDiasDescanso()));
            pla.setPorcFeriadoTrab(BigDecimal.valueOf(itemAsistencia.getDiasFeriados()));
            pla.setPorcAsigFamiliar(parametro.getPorcAsigancionFamiliar());

            pla.setNHorasNormal(itemAsistencia != null
                    ? BigDecimal.valueOf(itemAsistencia.getDiasLaborales() * 8)
                    : BigDecimal.ZERO);


            pla.setNHorasExtra1(itemAsistencia.getHorasExtra25());
            pla.setNHorasExtra2(itemAsistencia != null ? itemAsistencia.getHorasExtra35() : BigDecimal.ZERO);
            pla.setNDiasTrab((short) (itemAsistencia != null ? itemAsistencia.getDiasLaborales() : 0));
            pla.setNDiasDescansos((short) (itemAsistencia != null ? itemAsistencia.getDiasDescanso() : 0));
            pla.setNFeriadosTrab((short) (itemAsistencia != null ? itemAsistencia.getDiasFeriados() : 0));
            pla.setNDescansosTrab((short) 0);
            pla.setNDiasInasistencias((short) (itemAsistencia != null ? itemAsistencia.getDiasInasistencia() : 0));

            BigDecimal sueldoBasico = itemIngreso != null ? itemIngreso.getRemuneracion() : BigDecimal.ZERO;
            BigDecimal vale = itemIngreso != null ? itemIngreso.getVale() : BigDecimal.ZERO;

            BigDecimal haberBasico = sueldoBasico
                    .divide(BigDecimal.valueOf(diasMes), 2, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(diasCalculo));

            BigDecimal valesEmpleado = vale
                    .divide(BigDecimal.valueOf(diasMes), 2, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(diasCalculo));

            BigDecimal valorHora = sueldoBasico
                    .divide(BigDecimal.valueOf(30), 2, RoundingMode.HALF_UP)
                    .divide(BigDecimal.valueOf(8), 2, RoundingMode.HALF_UP);

            BigDecimal vHorasExtra1 = valorHora.multiply(
                            BigDecimal.ONE.add(parametro.getPorcExtra1()))
                    .multiply(pla.getNHorasExtra1());

            BigDecimal vHorasExtra2 = valorHora.multiply(
                            BigDecimal.ONE.add(parametro.getPorcExtra2()))
                    .multiply(pla.getNHorasExtra2());

            BigDecimal vAsigFamiliar = t.getHijos() > 0
                    ? sueldoBasico.multiply(parametro.getPorcAsigancionFamiliar())
                    : BigDecimal.ZERO;

            BigDecimal vFeriadoTrab = valorHora.multiply(BigDecimal.valueOf(8))
                    .multiply(BigDecimal.valueOf(2));

            pla.setHaberBasico(haberBasico);
            pla.setValesEmpleado(valesEmpleado);
            pla.setVHorasExtra1(vHorasExtra1);
            pla.setVHorasExtra2(vHorasExtra2);
            pla.setVAsigFamiliar(vAsigFamiliar);
            pla.setVFeriadoTrab(vFeriadoTrab);

            pla.setTotalIngreso(pla.getHaberBasico().add(pla.getValesEmpleado()).add(pla.getVHorasExtra2()).add(pla.getVHorasExtra1())
                    .add(pla.getVAsigFamiliar()).add(pla.getVFeriadoTrab()));


            SistemaPension sp = sistemaPensionRepository.findById(t.getIdSistemaPension()).orElse(new SistemaPension());

            pla.setPorcAporte(sp.getAporte());
            pla.setAporte(pla.getTotalIngreso().multiply(sp.getAporte().divide(BigDecimal.valueOf(100))));

            pla.setPorcComision(sp.getComision());
            pla.setComision(pla.getTotalIngreso().multiply(sp.getComision().divide(BigDecimal.valueOf(100))));

            pla.setPorcPrima(sp.getPrima());
            pla.setPrima(pla.getTotalIngreso().multiply(sp.getPrima().divide(BigDecimal.valueOf(100))));
            planillas.add(pla);
        }


        return planillaMapper.entityListToResponse(planillas);
        // return PlanillaMapper.INSTANCE.toDtoList(planillas);
       /* return planillas.stream()
                .map(planilla -> modelMapper.map(planilla, PlanillaMensualResponse.class))
                .collect(Collectors.toList());

        */

    }

    @Override
    public boolean insertarListaCalculo(List<PlanillaMensualResponse> planResponse) {
        if (planResponse == null || planResponse.isEmpty()) {
            return false;
        }
        int rows = planillaRepository.eliminarPlanillas(planResponse.get(0).getAnio(), planResponse.get(0).getMes());
        System.out.println("Registros eliminados: " + rows);
        List<PlanillaMensual> entidades = planillaMapper.responseListToEntity(planResponse);

        // List<PlanillaMensual> entidades =PlanillaMapper.INSTANCE.toEntityList(planResponse);
       /* List<PlanillaMensual> entidades = planResponse.stream()
                .map(response -> modelMapper.map(response, PlanillaMensual.class))
                .collect(Collectors.toList());

        */
        planillaRepository.saveAll(entidades);
        return true;
    }

}
