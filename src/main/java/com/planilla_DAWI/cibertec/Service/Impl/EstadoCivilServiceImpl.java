package com.planilla_DAWI.cibertec.Service.Impl;

import com.planilla_DAWI.cibertec.Dto.EstadoCivilDTO;
import com.planilla_DAWI.cibertec.Entity.EstadoCivil;
import com.planilla_DAWI.cibertec.Mappers.EstadoCivilMapper;
import com.planilla_DAWI.cibertec.Repository.EstadoCivilRepository;
import com.planilla_DAWI.cibertec.Service.EstadoCivilService;
import com.planilla_DAWI.cibertec.Utils.Enums.EstadoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class EstadoCivilServiceImpl implements EstadoCivilService {

    private final EstadoCivilRepository repository;

    @Autowired
    public EstadoCivilServiceImpl(EstadoCivilRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<EstadoCivilDTO> listarEstadosCiviles(Pageable page, EstadoEnum estado, String texto) {
        return repository.findByEstado(estado.getValor(), texto, page)
                .map(EstadoCivilMapper::toDTO);
    }

    @Override
    public EstadoCivilDTO obtenerPorId(Integer id) {
        EstadoCivil entity = repository.obtenerPorIdUsingSP(id);
        return entity != null ? EstadoCivilMapper.toDTO(entity) : null;
    }

    @Transactional
    @Override
    public EstadoCivilDTO crear(EstadoCivilDTO dto) {
        EstadoCivil entity = repository.insertarUsingSP(dto.getNombre());
        return EstadoCivilMapper.toDTO(entity);
    }

    @Transactional
    @Override
    public EstadoCivilDTO actualizar(EstadoCivilDTO dto) {
        repository.findById(dto.getIdEstadoCivil())
                .orElseThrow(() -> new RuntimeException("Estado Civil no encontrado"));
        EstadoCivil entity = repository.actualizarUsingSP(dto.getIdEstadoCivil(), dto.getNombre(), dto.getActivo());
        return EstadoCivilMapper.toDTO(entity);
    }

    @Transactional
    @Override
    public void cambiarEstado(Integer id) {
        repository.cambiarEstadoUsingSP(id);
    }



}