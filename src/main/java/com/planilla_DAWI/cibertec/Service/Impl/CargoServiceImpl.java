package com.planilla_DAWI.cibertec.Service.Impl;

import com.planilla_DAWI.cibertec.Dto.CargoDTO;
import com.planilla_DAWI.cibertec.Entity.Cargo;
import com.planilla_DAWI.cibertec.Mappers.CargoMapper;
import com.planilla_DAWI.cibertec.Repository.CargoRepository;
import com.planilla_DAWI.cibertec.Service.CargoService;
import com.planilla_DAWI.cibertec.Utils.Enums.EstadoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class CargoServiceImpl implements CargoService {

    @Autowired
    private CargoRepository repository;

    @Override
    public Page<CargoDTO> buscarPorEstado(EstadoEnum estado,String texto ,Pageable pageable) {
        return repository.findByEstado(estado.getValor(),texto, pageable).map(CargoMapper::toDTO);
    }

    @Override
    @Transactional
    public CargoDTO insertar(CargoDTO dto) {
        Cargo entity = repository.insertarUsingSP(dto.getNombre());
        return CargoMapper.toDTO(entity);
    }

    @Override
    @Transactional
    public CargoDTO actualizar(CargoDTO dto) {
        repository.findById(dto.getIdCargo())
                .orElseThrow(() -> new RuntimeException("Cargo no encontrado"));
        Cargo entity = repository.actualizarUsingSP(dto.getIdCargo(), dto.getNombre(), dto.getActivo());
        return CargoMapper.toDTO(entity);
    }

    @Override
    @Transactional
    public int cambiarEstado(Integer id) {
        return repository.cambiarEstadoUsingSP(id);
    }

    @Override
    public CargoDTO obtenerPorId(Integer id) {
        Cargo entity = repository.obtenerPorIdUsingSP(id);
        if (entity == null) {
            throw new RuntimeException("Cargo no encontrado");
        }
        return CargoMapper.toDTO(entity);
    }
}