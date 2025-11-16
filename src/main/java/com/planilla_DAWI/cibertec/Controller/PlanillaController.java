package com.planilla_DAWI.cibertec.Controller;

import com.planilla_DAWI.cibertec.Dto.PlanillaMensualDTO;
import com.planilla_DAWI.cibertec.Dto.PlanillaMensualResponse;
import com.planilla_DAWI.cibertec.Dto.PlanillaPorDocumentoDTO;
import com.planilla_DAWI.cibertec.Service.PlanillaMensualService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/planilla-mensual")
public class PlanillaController {

    private final PlanillaMensualService planillaMensualService;

    public PlanillaController(PlanillaMensualService planillaMensualService) {
        this.planillaMensualService = planillaMensualService;
    }


    @GetMapping("/listarPlanilla")
    public ResponseEntity<?> listarPlanillaMensual(@RequestParam(value = "anio") Integer anio,
                                                   @RequestParam(value = "mes") Integer mes) {
        try {
            List<PlanillaMensualDTO> result = planillaMensualService.listarPlanillas(anio, mes);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al listar planillas mensuales: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/buscarBoleta")
    public ResponseEntity<?> ObtenerPlanillaPorDocumento(@RequestParam(value = "año") Integer anio,
                                                         @RequestParam(value = "mes") Integer mes,
                                                         @RequestParam(value = "documento") String documento) {
        try {
            PlanillaPorDocumentoDTO result = planillaMensualService.ObtenerPlanillaPorDocumento(documento, anio, mes);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al obtener planilla por documento: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/calcularPlanilla")
    public ResponseEntity<?> calcularPlanillas(@RequestParam(value = "año") Integer anio,
                                               @RequestParam(value = "mes") Integer mes) {
        try {
            List<PlanillaMensualResponse> result = planillaMensualService.calcularPlanilla(anio, mes);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al calcular planillas: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/guardarPlanilla")
    public ResponseEntity<?> insertarPlaneillas(@RequestBody List<PlanillaMensualResponse> planResponse) {
        try {
            boolean resultado = planillaMensualService.insertarListaCalculo(planResponse);

            Map<String, Object> response = new HashMap<>();
            if (resultado) {
                response.put("success", true);
                response.put("message", "Lista de planillas insertada correctamente");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "La lista está vacía o es nula");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al guardar planillas: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
