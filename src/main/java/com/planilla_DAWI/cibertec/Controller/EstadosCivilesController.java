package com.planilla_DAWI.cibertec.Controller;

import com.planilla_DAWI.cibertec.Dto.EstadoCivilDTO;
import com.planilla_DAWI.cibertec.Service.EstadoCivilService;
import com.planilla_DAWI.cibertec.Utils.Enums.EstadoEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/estados-civiles")
@CrossOrigin(origins = "*")
public class EstadosCivilesController {

    private final EstadoCivilService estadoCivilService;

    @Autowired
    public EstadosCivilesController(EstadoCivilService estadoCivilService) {
        this.estadoCivilService = estadoCivilService;
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarEstadosCiviles(@RequestParam(defaultValue = "TODOS") EstadoEnum estado,
                                                                    @RequestParam(required = false, defaultValue = "") String texto,
                                                                    @PageableDefault(size = 10) Pageable pageable) {

        try {
            // Crear el objeto Pageable

            Page<EstadoCivilDTO> estadosCivilesPage = estadoCivilService.listarEstadosCiviles(pageable, estado, texto);
            return ResponseEntity.ok(estadosCivilesPage);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al obtener los estados civiles: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/obtenerById/{id}")
    public ResponseEntity<?> obtenerEstadoCivil(@PathVariable Integer id) {
        try {
            EstadoCivilDTO estadoCivilDTO = estadoCivilService.obtenerPorId(id);

            return ResponseEntity.ok(estadoCivilDTO);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error al obtener el estado civil: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/insertar")
    public ResponseEntity<?> crearEstadoCivil(
            @Valid @RequestBody EstadoCivilDTO estadoCivilDTO,
            BindingResult result) {



        try {
            EstadoCivilDTO nuevoEstadoCivil = estadoCivilService.crear(estadoCivilDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Estado civil creado exitosamente");
            response.put("data", nuevoEstadoCivil);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al crear el estado civil: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarEstadoCivil(
            @PathVariable Integer id,
            @Valid @RequestBody EstadoCivilDTO estadoCivilDTO,
            BindingResult result) {
        try {
            // Verificar si existe el estado civil
            Map<String, Object> response = new HashMap<>();
            // Asegurar que el ID del path coincida con el del objeto
            estadoCivilDTO.setIdEstadoCivil(id);
            EstadoCivilDTO estadoCivilActualizado = estadoCivilService.actualizar(estadoCivilDTO);
            response.put("success", true);
            response.put("message", "Estado civil actualizado exitosamente");
            response.put("data", estadoCivilActualizado);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al actualizar el estado civil: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PatchMapping("/cambiarEstado/{id}")
    public ResponseEntity<?> cambiarEstado(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Verificar si existe el estado civil
            EstadoCivilDTO estadoCivilExistente = estadoCivilService.obtenerPorId(id);
            if (estadoCivilExistente == null) {
                response.put("success", false);
                response.put("message", "Estado civil no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            estadoCivilService.cambiarEstado(id);
            response.put("success", true);
            response.put("message", "Estado del estado civil cambiado exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error al cambiar el estado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEstadoCivil(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Verificar si existe el estado civil
            EstadoCivilDTO estadoCivilExistente = estadoCivilService.obtenerPorId(id);
            if (estadoCivilExistente == null) {
                response.put("success", false);
                response.put("message", "Estado civil no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            // Si tu servicio tiene un método de eliminación física, úsalo aquí
            // estadoCivilService.eliminar(id);

            // Por ahora, usamos cambiar estado como eliminación lógica
            estadoCivilService.cambiarEstado(id);
            response.put("success", true);
            response.put("message", "Estado civil eliminado exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error al eliminar el estado civil: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}