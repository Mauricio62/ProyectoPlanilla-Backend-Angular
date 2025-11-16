package com.planilla_DAWI.cibertec.Service.Impl;

import com.planilla_DAWI.cibertec.Dto.AsistenciaTrabajadorDTO;
import com.planilla_DAWI.cibertec.Dto.AsistenciaTrabajadorResponse;
import com.planilla_DAWI.cibertec.Entity.AsistenciaTrabajador;
import com.planilla_DAWI.cibertec.Entity.Trabajador;
import com.planilla_DAWI.cibertec.Repository.AsistenciaTrabajadorRepository;
import com.planilla_DAWI.cibertec.Service.AsistenciaTrabajadorService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AsistenciaTrabajadorServiceImpl implements AsistenciaTrabajadorService {

    @Autowired
    private AsistenciaTrabajadorRepository repository;

    @Override
    public List<AsistenciaTrabajadorResponse> buscarPorPeriodo(Integer año, Integer mes) {
        return repository.findByPeriodo(año, mes);
    }

    @Override
    @Transactional
    public boolean guardarAsistencias(List<AsistenciaTrabajadorDTO> asistencias) {
        if (asistencias == null || asistencias.isEmpty()) {
            return false;
        }

        // Eliminar registros existentes del período
        repository.deleteByPeriodo(asistencias.get(0).getAño(), asistencias.get(0).getMes());

        // Convertir DTOs a entidades y guardar
        List<AsistenciaTrabajador> entidades = new ArrayList<>();


        for (AsistenciaTrabajadorDTO dto : asistencias) {
            AsistenciaTrabajador entity = new AsistenciaTrabajador();
           Trabajador trabajador = new Trabajador();
           trabajador.setIdTrabajador(dto.getIdTrabajador());
            entity.setTrabajador(trabajador);
            entity.setAño(dto.getAño());
            entity.setMes(dto.getMes());
            entity.setDiasLaborales(dto.getDiasLaborales());
            entity.setDiasDescanso(dto.getDiasDescanso());
            entity.setDiasInasistencia(dto.getDiasInasistencia());
            entity.setDiasFeriados(dto.getDiasFeriados());
            entity.setHorasExtra25(dto.getHorasExtra25());
            entity.setHorasExtra35(dto.getHorasExtra35());
            entity.setFecCreacion(LocalDateTime.now());
            entity.setActivo(true);

            entidades.add(entity);
        }

        repository.saveAll(entidades);
        return true;
    }

    @Override
    public byte[] generarExcel(Integer año, Integer mes, List<AsistenciaTrabajadorResponse> datos) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Asistencias");

            // Crear fila de encabezados
            Row headerRow = sheet.createRow(0);
            String[] headers = {"DNI", "Días Laborales", "Días Descanso", "Días Inasistencia",
                    "Días Feriados", "Horas Extra 25%", "Horas Extra 35%"};

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Llenar datos
            if (datos != null && !datos.isEmpty()) {
                int rowNum = 1;
                for (AsistenciaTrabajadorResponse item : datos) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(item.getDocumento());
                    row.createCell(1).setCellValue(item.getDiasLaborales() != null ? item.getDiasLaborales() : 0);
                    row.createCell(2).setCellValue(item.getDiasDescanso() != null ? item.getDiasDescanso() : 0);
                    row.createCell(3).setCellValue(item.getDiasInasistencia() != null ? item.getDiasInasistencia() : 0);
                    row.createCell(4).setCellValue(item.getDiasFeriados() != null ? item.getDiasFeriados() : 0);
                    row.createCell(5).setCellValue(item.getHorasExtra25() != null ? item.getHorasExtra25().doubleValue() : 0);
                    row.createCell(6).setCellValue(item.getHorasExtra35() != null ? item.getHorasExtra35().doubleValue() : 0);
                }
            }

            workbook.write(out);
            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error al generar el archivo Excel", e);
        }
    }

    @Override
    public List<AsistenciaTrabajadorResponse> procesarExcel(MultipartFile archivo, Integer año, Integer mes) {
        if (archivo.isEmpty()) {
            throw new IllegalArgumentException("El archivo está vacío");
        }

        List<AsistenciaTrabajadorResponse> asistencias = new ArrayList<>();
        List<AsistenciaTrabajadorResponse> existentes = buscarPorPeriodo(año, mes);

        try (Workbook workbook = WorkbookFactory.create(archivo.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Saltar encabezados

                String dni = getCellValue(row.getCell(0));
                if (dni == null || dni.isEmpty()) continue;

                AsistenciaTrabajadorResponse existente = existentes.stream()
                        .filter(e -> dni.equals(e.getDocumento()))
                        .findFirst()
                        .orElse(null);

                if (existente != null) {
                    AsistenciaTrabajadorResponse actualizado = new AsistenciaTrabajadorResponse();
                    actualizado.setIdTrabajador(existente.getIdTrabajador());
                    actualizado.setDocumento(existente.getDocumento());
                    actualizado.setNombre(existente.getNombre());
                    actualizado.setDiasLaborales(getIntCellValue(row.getCell(1)));
                    actualizado.setDiasDescanso(getIntCellValue(row.getCell(2)));
                    actualizado.setDiasInasistencia(getIntCellValue(row.getCell(3)));
                    actualizado.setDiasFeriados(getIntCellValue(row.getCell(4)));
                    actualizado.setHorasExtra25(getBigDecimalCellValue(row.getCell(5)));
                    actualizado.setHorasExtra35(getBigDecimalCellValue(row.getCell(6)));
                    actualizado.setIdAsistencia(existente.getIdAsistencia());

                    asistencias.add(actualizado);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al procesar el archivo Excel", e);
        }

        // Agregar los no modificados
        existentes.stream()
                .filter(e -> asistencias.stream().noneMatch(a -> a.getDocumento().equals(e.getDocumento())))
                .forEach(asistencias::add);

        return asistencias;
    }

    private String getCellValue(Cell cell) {
        if (cell == null) return null;
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue().trim();
            case NUMERIC: return String.valueOf((int) cell.getNumericCellValue());
            default: return null;
        }
    }

    private Integer getIntCellValue(Cell cell) {
        if (cell == null) return 0;
        switch (cell.getCellType()) {
            case NUMERIC: return (int) cell.getNumericCellValue();
            case STRING:
                try {
                    return Integer.parseInt(cell.getStringCellValue());
                } catch (NumberFormatException e) {
                    return 0;
                }
            default: return 0;
        }
    }

    private BigDecimal getBigDecimalCellValue(Cell cell) {
        if (cell == null) return BigDecimal.ZERO;
        switch (cell.getCellType()) {
            case NUMERIC: return BigDecimal.valueOf(cell.getNumericCellValue());
            case STRING:
                try {
                    return new BigDecimal(cell.getStringCellValue());
                } catch (NumberFormatException e) {
                    return BigDecimal.ZERO;
                }
            default: return BigDecimal.ZERO;
        }
    }
}
