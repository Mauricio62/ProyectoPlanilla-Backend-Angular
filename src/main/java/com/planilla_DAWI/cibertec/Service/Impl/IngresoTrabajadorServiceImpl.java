package com.planilla_DAWI.cibertec.Service.Impl;

import com.planilla_DAWI.cibertec.Dto.IngresoTrabajadorDTO;
import com.planilla_DAWI.cibertec.Entity.IngresoTrabajador;
import com.planilla_DAWI.cibertec.Repository.IngresoTrabajadorRepository;
import com.planilla_DAWI.cibertec.Service.IngresoTrabajadorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class IngresoTrabajadorServiceImpl implements IngresoTrabajadorService {

    private final IngresoTrabajadorRepository ingresoTrabajadorRepository;

    public IngresoTrabajadorServiceImpl(IngresoTrabajadorRepository ingresoTrabajadorRepository) {
        this.ingresoTrabajadorRepository = ingresoTrabajadorRepository;
    }

    @Override
    public IngresoTrabajador BuscarPorTrabajador(Integer id) {
        return ingresoTrabajadorRepository.IngresoPorTrabajador(id);
    }

    @Override
    public List<IngresoTrabajador> ListarIngresosActivos() {
        return ingresoTrabajadorRepository.ListarIngresosActivos();
    }

    @Override
    public Integer insertar(IngresoTrabajador IngresoTrabajador) {
        return ingresoTrabajadorRepository.save(IngresoTrabajador).getIdIngresoTrabajador();
    }

    @Override
    @Transactional
    public Integer cambiarEstado(Integer id) {
        return ingresoTrabajadorRepository.cambiarEstado(id, new Date());
    }
}
