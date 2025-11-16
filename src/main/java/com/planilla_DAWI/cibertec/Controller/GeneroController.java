package com.planilla_DAWI.cibertec.Controller;

import com.planilla_DAWI.cibertec.Dto.GeneroDTO;
import com.planilla_DAWI.cibertec.Service.GeneroService;
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
@RequestMapping("/api/generos")
public class GeneroController {

    @Autowired
    private GeneroService service;

    @GetMapping("/listar")
    public ResponseEntity<?> listar(
            @RequestParam(required = false, defaultValue = "TODOS") EstadoEnum estado,
            @RequestParam(required = false, defaultValue = "") String texto,
            @PageableDefault(size = 10) Pageable pageable) {
        try {
            Page<GeneroDTO> result = service.buscarPorEstado(estado, texto, pageable);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al listar géneros: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/obtenerById/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        try {
            GeneroDTO genero = service.obtenerPorId(id);
            return ResponseEntity.ok(genero);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al obtener género: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/insertar")
    public ResponseEntity<?> crear(@RequestBody GeneroDTO dto) {
        try {
            GeneroDTO result = service.insertar(dto);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Género creado exitosamente");
            response.put("data", result);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al crear género: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Integer id, @RequestBody GeneroDTO dto) {
        try {
            dto.setIdGenero(id);
            GeneroDTO result = service.actualizar(dto);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Género actualizado exitosamente");
            response.put("data", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al actualizar género: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PatchMapping("/cambiarEstado/{id}")
    public ResponseEntity<?> cambiarEstado(@PathVariable Integer id) {
        try {
            int result = service.cambiarEstado(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Estado del género cambiado exitosamente");
            response.put("data", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al cambiar estado del género: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}