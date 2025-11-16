package com.planilla_DAWI.cibertec.Service.Impl;

import com.planilla_DAWI.cibertec.Dto.GeneroDTO;
import com.planilla_DAWI.cibertec.Entity.Genero;
import com.planilla_DAWI.cibertec.Mappers.GeneroMapper;
import com.planilla_DAWI.cibertec.Repository.GeneroRepository;
import com.planilla_DAWI.cibertec.Service.GeneroService;
import com.planilla_DAWI.cibertec.Utils.Enums.EstadoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private GeneroRepository repository;

    @Override
    public Page<GeneroDTO> buscarPorEstado(EstadoEnum estado, String texto, Pageable pageable) {
        return repository.findByEstado(estado.getValor(), texto, pageable).map(GeneroMapper::toDTO);
    }

    @Override
    @Transactional
    public GeneroDTO insertar(GeneroDTO dto) {
        Genero entity = repository.insertarUsingSP(dto.getNombre());
        return GeneroMapper.toDTO(entity);
    }

    @Override
    @Transactional
    public GeneroDTO actualizar(GeneroDTO dto) {
        repository.findById(dto.getIdGenero())
                .orElseThrow(() -> new RuntimeException("Género no encontrado"));
        Genero entity = repository.actualizarUsingSP(dto.getIdGenero(), dto.getNombre(), dto.getActivo());
        return GeneroMapper.toDTO(entity);
    }

    @Override
    @Transactional
    public int cambiarEstado(Integer id) {
        return repository.cambiarEstadoUsingSP(id);
    }

    @Override
    public GeneroDTO obtenerPorId(Integer id) {
        Genero entity = repository.obtenerPorIdUsingSP(id);
        if (entity == null) {
            throw new RuntimeException("Género no encontrado");
        }
        return GeneroMapper.toDTO(entity);
    }
}