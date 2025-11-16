package com.planilla_DAWI.cibertec.Service.Impl;

import com.planilla_DAWI.cibertec.Dto.SistemaPensionDTO;
import com.planilla_DAWI.cibertec.Entity.SistemaPension;
import com.planilla_DAWI.cibertec.Mappers.SistemaPensionMapper;
import com.planilla_DAWI.cibertec.Repository.SistemaPensionRepository;
import com.planilla_DAWI.cibertec.Service.SistemaPensionService;
import com.planilla_DAWI.cibertec.Utils.Enums.EstadoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class SistemaPensionServiceImpl implements SistemaPensionService {

    @Autowired
    private SistemaPensionRepository repository;

    @Override
    public Page<SistemaPensionDTO> buscarPorEstado(EstadoEnum estado, String texto, Pageable pageable) {
        return repository.findByEstado(estado.getValor(), texto, pageable).map(SistemaPensionMapper::toDTO);
    }

    @Override
    @Transactional
    public SistemaPensionDTO insertar(SistemaPensionDTO dto) {
        SistemaPension entity =SistemaPensionMapper.toEntity(dto);
        entity.setActivo(true);
        entity.setFecCreacion(LocalDateTime.now());
        return SistemaPensionMapper.toDTO( repository.save(entity));
    }

    @Override
    @Transactional
    public SistemaPensionDTO actualizar(SistemaPensionDTO dto) {
         repository.findById(dto.getIdSistemaPension())
                .orElseThrow(() -> new RuntimeException("Sistema de pensión no encontrado"));
        SistemaPension entity =SistemaPensionMapper.toEntity(dto);

        entity.setFecUltimaModificacion(LocalDateTime.now());
        return SistemaPensionMapper.toDTO(repository.save(entity));
    }

    @Override
    @Transactional
    public int cambiarEstado(Integer id) {
        return repository.cambiarEstado(id, LocalDateTime.now());
    }

    @Override
    public SistemaPensionDTO obtenerPorId(Integer id) {
        return repository.findById(id).map(SistemaPensionMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Sistema de pensión no encontrado"));
    }
}
