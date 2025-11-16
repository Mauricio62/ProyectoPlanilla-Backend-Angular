package com.planilla_DAWI.cibertec.Service;

import com.planilla_DAWI.cibertec.Dto.SistemaPensionDTO;
import com.planilla_DAWI.cibertec.Entity.SistemaPension;
import com.planilla_DAWI.cibertec.Utils.Enums.EstadoEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SistemaPensionService {
    Page<SistemaPensionDTO> buscarPorEstado(EstadoEnum estado, String texto, Pageable pageable);
    SistemaPensionDTO insertar(SistemaPensionDTO dto);
    SistemaPensionDTO actualizar(SistemaPensionDTO dto);
    int cambiarEstado(Integer id);
    SistemaPensionDTO obtenerPorId(Integer id);
}
