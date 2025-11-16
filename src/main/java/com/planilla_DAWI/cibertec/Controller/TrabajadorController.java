
package com.planilla_DAWI.cibertec.Controller;

import com.planilla_DAWI.cibertec.Dto.TrabajadorDTO;
import com.planilla_DAWI.cibertec.Service.TrabajadorService;
import com.planilla_DAWI.cibertec.Utils.Enums.EstadoEnum;
import jakarta.validation.Valid;
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
@RequestMapping("/api/trabajador")
public class TrabajadorController {

    @Autowired
    private TrabajadorService trabajadorService;

    @PostMapping("/insertar")
    public ResponseEntity<?> insert(@Valid @RequestBody TrabajadorDTO request) {
        try {
            TrabajadorDTO result=trabajadorService.insert(request);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Trabajador creado exitosamente");
            response.put("data", result);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception ex) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al crear trabajador: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                    @RequestBody TrabajadorDTO request) {
        try {
            request.setIdTrabajador(id);
            TrabajadorDTO result = trabajadorService.update(id,request);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Trabajador actualizado exitosamente");
            response.put("data", result);
            return ResponseEntity.ok(response);

        } catch (Exception ex) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al actualizar cargo: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PatchMapping("/cambiar-estado/{id}")
    public ResponseEntity<?> cambiarEstado(@PathVariable Integer id) {
        try {
            int result = trabajadorService.cambiarEstado(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Estado del trabajador cambiado exitosamente");
            response.put("data", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al cambiar estado del trabajador: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar(@RequestParam(required = false, defaultValue = "TODOS") EstadoEnum estado,
                                    @RequestParam(required = false,defaultValue = "") String Texto,
                                    @PageableDefault(size = 10) Pageable pageable) {
        try {
            Page<TrabajadorDTO> result = trabajadorService.busqueda(estado, Texto, pageable);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al listar Trabajador: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/obtenerById/{id}")
    public ResponseEntity<?> obtenerById(@PathVariable Integer id) {
        try {
            TrabajadorDTO result = trabajadorService.getById(id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al obtener cargo: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}

