package com.planilla_DAWI.cibertec.Service;

import com.planilla_DAWI.cibertec.Dto.AsistenciaTrabajadorDTO;
import com.planilla_DAWI.cibertec.Dto.AsistenciaTrabajadorResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AsistenciaTrabajadorService {
    List<AsistenciaTrabajadorResponse> buscarPorPeriodo(Integer año, Integer mes);
    boolean guardarAsistencias(List<AsistenciaTrabajadorDTO> asistencias);
    byte[] generarExcel(Integer año, Integer mes, List<AsistenciaTrabajadorResponse> datos);
    List<AsistenciaTrabajadorResponse> procesarExcel(MultipartFile archivo, Integer año, Integer mes);
}
