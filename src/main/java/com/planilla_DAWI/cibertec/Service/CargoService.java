package com.planilla_DAWI.cibertec.Service;
import com.planilla_DAWI.cibertec.Dto.CargoDTO;
import com.planilla_DAWI.cibertec.Utils.Enums.EstadoEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface CargoService {
    Page<CargoDTO> buscarPorEstado(EstadoEnum estado,String texto, Pageable pageable);
    CargoDTO insertar(CargoDTO dto);
    CargoDTO actualizar(CargoDTO dto);
    int cambiarEstado(Integer id);
    CargoDTO obtenerPorId(Integer id);
}
