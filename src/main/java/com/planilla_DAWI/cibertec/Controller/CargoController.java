package com.planilla_DAWI.cibertec.Controller;

import com.planilla_DAWI.cibertec.Dto.CargoDTO;

import com.planilla_DAWI.cibertec.Service.CargoService;
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
@RequestMapping("/api/cargos")
public class CargoController {

    @Autowired
    private CargoService service;

    @GetMapping("/listar")
    public ResponseEntity<?> listar(
            @RequestParam(required = false, defaultValue = "TODOS") EstadoEnum estado,
            @RequestParam(required = false,defaultValue = "") String Texto,
            @PageableDefault(size = 10) Pageable pageable) {

        try {
            Page<CargoDTO> result = service.buscarPorEstado(estado, Texto, pageable);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al listar cargos: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/obtenerById/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        try {
            CargoDTO cargo = service.obtenerPorId(id);
            return ResponseEntity.ok(cargo);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al obtener cargo: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/insertar")
    public ResponseEntity<?> crear(@RequestBody CargoDTO dto) {
        try {
            CargoDTO result = service.insertar(dto);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Cargo creado exitosamente");
            response.put("data", result);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al crear cargo: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Integer id, @RequestBody CargoDTO dto) {
        try {
            dto.setIdCargo(id);
            CargoDTO result = service.actualizar(dto);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Cargo actualizado exitosamente");
            response.put("data", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al actualizar cargo: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PatchMapping("/cambiarEstado/{id}")
    public ResponseEntity<?> cambiarEstado(@PathVariable Integer id) {
        try {
            int result = service.cambiarEstado(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Estado del cargo cambiado exitosamente");
            response.put("data", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al cambiar estado del cargo: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
