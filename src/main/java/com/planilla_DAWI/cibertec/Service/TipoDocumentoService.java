package com.planilla_DAWI.cibertec.Service;

import com.planilla_DAWI.cibertec.Dto.TipoDocumentoDTO;
import com.planilla_DAWI.cibertec.Utils.Enums.EstadoEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TipoDocumentoService {
    Page<TipoDocumentoDTO> buscarPorEstado(EstadoEnum estado, String texto, Pageable pageable);
    TipoDocumentoDTO insertar(TipoDocumentoDTO dto);
    TipoDocumentoDTO actualizar(TipoDocumentoDTO dto);
    int cambiarEstado(Integer id);
    TipoDocumentoDTO obtenerPorId(Integer id);
}
