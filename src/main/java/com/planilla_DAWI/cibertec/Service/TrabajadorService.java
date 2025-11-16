
package com.planilla_DAWI.cibertec.Service;

import com.planilla_DAWI.cibertec.Dto.TrabajadorDTO;
import com.planilla_DAWI.cibertec.Utils.Enums.EstadoEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TrabajadorService {
    TrabajadorDTO insert(TrabajadorDTO request);
    TrabajadorDTO update(Integer id, TrabajadorDTO request);
    int cambiarEstado(Integer id);
    Page<TrabajadorDTO> busqueda(EstadoEnum estado, String texto, Pageable pageable);
    TrabajadorDTO getById(Integer id);
}

