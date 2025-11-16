package com.planilla_DAWI.cibertec.Controller;

import com.planilla_DAWI.cibertec.Entity.IngresoTrabajador;
import com.planilla_DAWI.cibertec.Service.IngresoTrabajadorService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Ingreso-Trabajador")
@CrossOrigin(origins = "*")
public class IngresoTrabajadorController {
    private IngresoTrabajadorService ingresoTrabajadorService;

    @PostMapping("/Insertar")
    public int insertar(@RequestBody IngresoTrabajador Obj) {
        return ingresoTrabajadorService.insertar(Obj);
    }

    @PostMapping("/buscar/{id}")
    public IngresoTrabajador BuscarPorTrabajador(Integer id) {
        return ingresoTrabajadorService.BuscarPorTrabajador(id);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<IngresoTrabajador>> ListarIngresosActivos() {
        return ResponseEntity.ok(ingresoTrabajadorService.ListarIngresosActivos());
    }

    @PatchMapping("/cambiarEstado/{id}")
    public Integer cambiarEstado(Integer id) {
        return ingresoTrabajadorService.cambiarEstado(id);
    }
}
