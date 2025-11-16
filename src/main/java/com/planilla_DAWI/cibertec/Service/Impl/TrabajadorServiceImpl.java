
package com.planilla_DAWI.cibertec.Service.Impl;

import com.planilla_DAWI.cibertec.Dto.TrabajadorDTO;
import com.planilla_DAWI.cibertec.Entity.IngresoTrabajador;
import com.planilla_DAWI.cibertec.Entity.Parametro;
import com.planilla_DAWI.cibertec.Entity.Trabajador;
import com.planilla_DAWI.cibertec.Mappers.TrabajadorMapper;
import com.planilla_DAWI.cibertec.Repository.IngresoTrabajadorRepository;
import com.planilla_DAWI.cibertec.Repository.ParametroRepository;
import com.planilla_DAWI.cibertec.Repository.TrabajadorRepository;
import com.planilla_DAWI.cibertec.Service.TrabajadorService;
import com.planilla_DAWI.cibertec.Utils.Enums.EstadoEnum;
import com.planilla_DAWI.cibertec.Utils.Helps.Convert;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TrabajadorServiceImpl implements TrabajadorService {
    @Autowired
    private TrabajadorRepository trabajadorRepository;
    @Autowired
    private ParametroRepository parametroRepository;
    @Autowired
    private IngresoTrabajadorRepository ingresoTrabajadorRepository;

    @Override
    @Transactional
    public TrabajadorDTO insert(TrabajadorDTO request) {
        Trabajador entity = TrabajadorMapper.toEntity(request);
        entity.setActivo((Boolean) true);
        entity.setFecCreacion(LocalDateTime.now());
        TrabajadorDTO trabajadorDTO = TrabajadorMapper.toDTO(trabajadorRepository.save(entity));
        if (entity.getIdTrabajador() > 0) {
            Parametro parametro = parametroRepository.findParametro();
            IngresoTrabajador ingresoTrabajador = new IngresoTrabajador();
            ingresoTrabajador.setTrabajador(entity);
            ingresoTrabajador.setRemuneracion(parametro.getRemBasico());
            ingresoTrabajadorRepository.save(ingresoTrabajador);
        }
        return trabajadorDTO;
    }

    @Override
    @Transactional
    public TrabajadorDTO update(Integer id, TrabajadorDTO request) {
        Trabajador existingEntity = trabajadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trabajador no encontrado"));

        // Actualizar campos
        existingEntity.setIdTipoDocumento(request.getIdTipoDocumento());
        existingEntity.setDocumento(request.getDocumento());
        existingEntity.setNombres(request.getNombres());
        existingEntity.setApellidoPaterno(request.getApellidoPaterno());
        existingEntity.setApellidoMaterno(request.getApellidoMaterno());
        existingEntity.setIdGenero(request.getIdGenero());
        existingEntity.setIdEstadoCivil(request.getIdEstadoCivil());
        existingEntity.setDireccion(request.getDireccion());
        existingEntity.setEmail(request.getEmail());
        existingEntity.setHijos(request.getHijos());
        existingEntity.setIdCargo(request.getIdCargo());
        existingEntity.setFecNacimiento(Convert.convertToLocalDateTime(request.getFecNacimiento()));
        existingEntity.setFecIngreso(Convert.convertToLocalDateTime(request.getFecIngreso()));
        existingEntity.setIdSituacion(request.getIdSituacion());
        existingEntity.setIdSistemaPension(request.getIdSistemaPension());
        existingEntity.setFoto(request.getFoto());

        existingEntity.setFecUltimaModificacion(LocalDateTime.now());

        Trabajador updatedEntity = trabajadorRepository.save(existingEntity);
        if (updatedEntity.getIdTrabajador() > 0) {
            IngresoTrabajador ingresoTrabajador=ingresoTrabajadorRepository.IngresoPorTrabajador(updatedEntity.getIdTrabajador());
            if(ingresoTrabajador==null  ){
                Parametro parametro = parametroRepository.findParametro();
                ingresoTrabajador.setTrabajador(updatedEntity);
                ingresoTrabajador.setRemuneracion(parametro.getRemBasico());
                ingresoTrabajadorRepository.save(ingresoTrabajador);
            }
        }

        return TrabajadorMapper.toDTO(updatedEntity);
    }

    @Override
    @Transactional
    public int cambiarEstado(Integer id) {
        int result = trabajadorRepository.cambiarEstado(id);
        if (result == 0) {
            throw new RuntimeException("No se pudo cambiar el estado del trabajador");
        }
        return result;
    }

    @Override
    public Page<TrabajadorDTO> busqueda(EstadoEnum estado, String texto, Pageable pageable) {
        Page<Trabajador> trabajador = trabajadorRepository.buscarTrabajadores(texto, estado.getValor(), pageable);
        return trabajador.map(TrabajadorMapper::toDTO);
    }

    @Override
    public TrabajadorDTO getById(Integer id) {
        return trabajadorRepository.findById(id).map(TrabajadorMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Trabajador no encontrado"));

    }
}
