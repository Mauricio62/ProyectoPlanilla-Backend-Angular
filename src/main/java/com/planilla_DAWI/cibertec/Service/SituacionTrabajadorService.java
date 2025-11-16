package com.planilla_DAWI.cibertec.Service;

import com.planilla_DAWI.cibertec.Dto.SituacionTrabajadorDTO;
import com.planilla_DAWI.cibertec.Utils.Enums.EstadoEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SituacionTrabajadorService {
    Page<SituacionTrabajadorDTO> buscarPorEstado(EstadoEnum estado, String texto, Pageable pageable);
    SituacionTrabajadorDTO insertar(SituacionTrabajadorDTO dto);
    SituacionTrabajadorDTO actualizar(SituacionTrabajadorDTO dto);
    int cambiarEstado(Integer id);
    SituacionTrabajadorDTO obtenerPorId(Integer id);
}
