package com.planilla_DAWI.cibertec.Controller;

import com.planilla_DAWI.cibertec.Dto.SistemaPensionDTO;
import com.planilla_DAWI.cibertec.Service.SistemaPensionService;
import com.planilla_DAWI.cibertec.Utils.Enums.EstadoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/sistemas-pension")
public class SistemaPensionController {

    @Autowired
    private SistemaPensionService service;

    @GetMapping("/listar")
    public ResponseEntity<?> listar(
            @RequestParam(required = false, defaultValue = "TODOS") EstadoEnum estado,
            @RequestParam(required = false, defaultValue = "") String texto,
            @PageableDefault(size = 10) Pageable pageable) {
        try {
            Page<SistemaPensionDTO> result = service.buscarPorEstado(estado, texto, pageable);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al listar sistemas de pensión: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/obtenerById/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        try {
            SistemaPensionDTO sistemaPension = service.obtenerPorId(id);
            return ResponseEntity.ok(sistemaPension);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al obtener sistema de pensión: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/insertar")
    public ResponseEntity<?> crear(@RequestBody SistemaPensionDTO dto) {
        try {
            SistemaPensionDTO result = service.insertar(dto);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Sistema de pensión creado exitosamente");
            response.put("data", result);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al crear sistema de pensión: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Integer id, @RequestBody SistemaPensionDTO dto) {
        try {
            dto.setIdSistemaPension(id);
            SistemaPensionDTO result = service.actualizar(dto);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Sistema de pensión actualizado exitosamente");
            response.put("data", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al actualizar sistema de pensión: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PatchMapping("/cambiarEstado/{id}")
    public ResponseEntity<?> cambiarEstado(@PathVariable Integer id) {
        try {
            int result = service.cambiarEstado(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Estado del sistema de pensión cambiado exitosamente");
            response.put("data", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al cambiar estado del sistema de pensión: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
