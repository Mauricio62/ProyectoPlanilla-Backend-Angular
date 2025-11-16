package com.planilla_DAWI.cibertec.Service;

import com.planilla_DAWI.cibertec.Dto.IngresoTrabajadorDTO;
import com.planilla_DAWI.cibertec.Entity.IngresoTrabajador;

import java.util.List;

public interface IngresoTrabajadorService {
    IngresoTrabajador BuscarPorTrabajador(Integer id);
    List<IngresoTrabajador> ListarIngresosActivos();
    Integer insertar(IngresoTrabajador ingresoTrabajador);
    Integer cambiarEstado(Integer id);
}
