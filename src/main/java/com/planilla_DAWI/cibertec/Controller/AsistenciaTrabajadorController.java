package com.planilla_DAWI.cibertec.Controller;

import com.planilla_DAWI.cibertec.Dto.AsistenciaTrabajadorDTO;
import com.planilla_DAWI.cibertec.Dto.AsistenciaTrabajadorResponse;
import com.planilla_DAWI.cibertec.Service.AsistenciaTrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/asistencias")
public class AsistenciaTrabajadorController {

    @Autowired
    private AsistenciaTrabajadorService service;

    @GetMapping
    public ResponseEntity<?> cargarAsistencia(
            @RequestParam(required = false) Integer año,
            @RequestParam(required = false) Integer mes) {

        try {
            if (año == null) año = java.time.Year.now().getValue();
            if (mes == null) mes = java.time.MonthDay.now().getMonthValue();

            List<AsistenciaTrabajadorResponse> result = service.buscarPorPeriodo(año, mes);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al cargar asistencias: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarAsistencia(
            @RequestParam Integer año,
            @RequestParam Integer mes) {
        try {
            List<AsistenciaTrabajadorResponse> result = service.buscarPorPeriodo(año, mes);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al buscar asistencias: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/descargar-excel")
    public ResponseEntity<?> descargarExcel(
            @RequestParam Integer año,
            @RequestParam Integer mes) {

        try {
            List<AsistenciaTrabajadorResponse> datos = service.buscarPorPeriodo(año, mes);
            byte[] excelBytes = service.generarExcel(año, mes, datos);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=asistencias_" + año + "_" + mes + ".xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(excelBytes);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al descargar Excel: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/cargar-excel")
    public ResponseEntity<?> cargarExcel(
            @RequestParam Integer año,
            @RequestParam Integer mes,
            @RequestParam("archivo") MultipartFile archivo) {

        try {
            List<AsistenciaTrabajadorResponse> result = service.procesarExcel(archivo, año, mes);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Archivo Excel procesado exitosamente");
            response.put("data", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al cargar archivo Excel: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> grabarAsistencia(
            @RequestBody List<AsistenciaTrabajadorDTO> asistencias) {

        try {
            boolean resultado = service.guardarAsistencias(asistencias);
            if (resultado) {
                Map<String, Object> response = new HashMap<>();
                response.put("success", true);
                response.put("message", "Asistencias guardadas correctamente");
                return ResponseEntity.ok(response);
            } else {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("success", false);
                errorResponse.put("message", "No se pudo guardar las asistencias");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al guardar asistencias: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}