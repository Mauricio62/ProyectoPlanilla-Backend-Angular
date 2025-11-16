package com.planilla_DAWI.cibertec.Service;

import com.planilla_DAWI.cibertec.Dto.GeneroDTO;
import com.planilla_DAWI.cibertec.Entity.Genero;
import com.planilla_DAWI.cibertec.Utils.Enums.EstadoEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GeneroService {
    Page<GeneroDTO> buscarPorEstado(EstadoEnum estado, String texto, Pageable pageable);
    GeneroDTO insertar(GeneroDTO dto);
    GeneroDTO actualizar(GeneroDTO dto);
    int cambiarEstado(Integer id);
    GeneroDTO obtenerPorId(Integer id);
}
