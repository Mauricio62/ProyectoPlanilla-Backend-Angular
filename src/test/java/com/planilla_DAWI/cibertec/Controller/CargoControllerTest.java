package com.planilla_DAWI.cibertec.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.planilla_DAWI.cibertec.Dto.CargoDTO;
import com.planilla_DAWI.cibertec.Service.CargoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas Unitarias - CargoController")
class CargoControllerTest {

    @Mock
    private CargoService cargoService;

    @InjectMocks
    private CargoController cargoController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(cargoController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Caso 1: Listar cargos exitosamente")
    void testListarCargos() throws Exception {
        // Arrange
        CargoDTO cargo1 = new CargoDTO();
        cargo1.setIdCargo(1);
        cargo1.setNombre("Desarrollador");
        cargo1.setActivo(true);

        CargoDTO cargo2 = new CargoDTO();
        cargo2.setIdCargo(2);
        cargo2.setNombre("Analista");
        cargo2.setActivo(true);

        List<CargoDTO> cargos = Arrays.asList(cargo1, cargo2);
        Pageable pageable = PageRequest.of(0, 10);
        Page<CargoDTO> page = new PageImpl<>(cargos, pageable, cargos.size());

        when(cargoService.buscarPorEstado(any(EstadoEnum.class), anyString(), any(Pageable.class)))
                .thenReturn(page);

        // Act & Assert
        mockMvc.perform(get("/api/cargos/listar")
                        .param("estado", "TODOS")
                        .param("Texto", "")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].idCargo").value(1))
                .andExpect(jsonPath("$.content[0].nombre").value("Desarrollador"));
    }

    @Test
    @DisplayName("Caso 2: Obtener cargo por ID exitosamente")
    void testObtenerCargoPorId() throws Exception {
        // Arrange
        CargoDTO cargo = new CargoDTO();
        cargo.setIdCargo(1);
        cargo.setNombre("Desarrollador Senior");
        cargo.setActivo(true);

        when(cargoService.obtenerPorId(1)).thenReturn(cargo);

        // Act & Assert
        mockMvc.perform(get("/api/cargos/obtenerById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCargo").value(1))
                .andExpect(jsonPath("$.nombre").value("Desarrollador Senior"));
    }

    @Test
    @DisplayName("Caso 3: Crear cargo exitosamente")
    void testCrearCargo() throws Exception {
        // Arrange
        CargoDTO cargoRequest = new CargoDTO();
        cargoRequest.setNombre("Nuevo Cargo");
        cargoRequest.setActivo(true);

        CargoDTO cargoResponse = new CargoDTO();
        cargoResponse.setIdCargo(1);
        cargoResponse.setNombre("Nuevo Cargo");
        cargoResponse.setActivo(true);

        when(cargoService.insertar(any(CargoDTO.class))).thenReturn(cargoResponse);

        // Act & Assert
        mockMvc.perform(post("/api/cargos/insertar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cargoRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Cargo creado exitosamente"))
                .andExpect(jsonPath("$.data.idCargo").value(1))
                .andExpect(jsonPath("$.data.nombre").value("Nuevo Cargo"));
    }

    @Test
    @DisplayName("Caso 4: Actualizar cargo exitosamente")
    void testActualizarCargo() throws Exception {
        // Arrange
        CargoDTO cargoRequest = new CargoDTO();
        cargoRequest.setNombre("Cargo Actualizado");
        cargoRequest.setActivo(true);

        CargoDTO cargoResponse = new CargoDTO();
        cargoResponse.setIdCargo(1);
        cargoResponse.setNombre("Cargo Actualizado");
        cargoResponse.setActivo(true);

        when(cargoService.actualizar(any(CargoDTO.class))).thenReturn(cargoResponse);

        // Act & Assert
        mockMvc.perform(put("/api/cargos/actualizar/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cargoRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Cargo actualizado exitosamente"))
                .andExpect(jsonPath("$.data.nombre").value("Cargo Actualizado"));
    }

    @Test
    @DisplayName("Caso 5: Cambiar estado de cargo exitosamente")
    void testCambiarEstadoCargo() throws Exception {
        // Arrange
        when(cargoService.cambiarEstado(1)).thenReturn(1);

        // Act & Assert
        mockMvc.perform(patch("/api/cargos/cambiarEstado/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Estado del cargo cambiado exitosamente"));
    }

    @Test
    @DisplayName("Caso 6: Error al obtener cargo inexistente")
    void testObtenerCargoInexistente() throws Exception {
        // Arrange
        when(cargoService.obtenerPorId(999))
                .thenThrow(new RuntimeException("Cargo no encontrado"));

        // Act & Assert
        mockMvc.perform(get("/api/cargos/obtenerById/999"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value(org.hamcrest.Matchers.containsString("Error al obtener cargo")));
    }
}
