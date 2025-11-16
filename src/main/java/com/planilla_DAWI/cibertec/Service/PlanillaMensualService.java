package com.planilla_DAWI.cibertec.Service;

import com.planilla_DAWI.cibertec.Dto.PlanillaMensualDTO;
import com.planilla_DAWI.cibertec.Dto.PlanillaMensualResponse;
import com.planilla_DAWI.cibertec.Dto.PlanillaPorDocumentoDTO;


import java.util.List;

public interface PlanillaMensualService {

        public List<PlanillaMensualDTO> listarPlanillas(Integer anio, Integer mes);

        public PlanillaPorDocumentoDTO ObtenerPlanillaPorDocumento(String documento,Integer anio, Integer mes);

        public List<PlanillaMensualResponse> calcularPlanilla (Integer anio, Integer mes);

        public boolean insertarListaCalculo (List<PlanillaMensualResponse> planillaMensualResponses);


}
