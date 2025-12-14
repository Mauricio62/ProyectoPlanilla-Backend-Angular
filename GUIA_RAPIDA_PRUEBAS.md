# Guía Rápida - Ejecución de Pruebas

## 🚀 Inicio Rápido

### 1. Pruebas Unitarias (JUnit)

```bash
# Ejecutar todas las pruebas
mvn test

# Ejecutar pruebas específicas
mvn test -Dtest=AuthControllerTest
mvn test -Dtest=CargoControllerTest
```

### 2. Pruebas con Postman

1. **Importar colección:**
   - Abrir Postman → Import → Seleccionar `Postman_Collection_Planilla.json`

2. **Configurar variables:**
   - Crear environment con variable `base_url = http://localhost:8080`

3. **Ejecutar:**
   - Seleccionar colección → Click "Run" → Verificar resultados

### 3. Pruebas Manuales

```bash
# Iniciar servidor
mvn spring-boot:run

# Acceder a Swagger
http://localhost:8080/swagger-ui.html
```

## 📋 Casos de Prueba Principales

| # | Caso | Endpoint | Método |
|---|------|----------|--------|
| 1 | Login Exitoso | `/api/auth/login` | POST |
| 2 | Login Fallido | `/api/auth/login` | POST |
| 3 | Registro Usuario | `/api/auth/register` | POST |
| 4 | Listar Cargos | `/api/cargos/listar` | GET |
| 5 | Crear Cargo | `/api/cargos/insertar` | POST |

## 📁 Archivos de Prueba

- **Unitarias:** `src/test/java/com/planilla_DAWI/cibertec/Controller/`
- **Integración:** `src/test/java/com/planilla_DAWI/cibertec/Integration/`
- **Postman:** `Postman_Collection_Planilla.json`
- **Documentación:** `../PRUEBAS_DE_CALIDAD.md`

## ✅ Verificación Rápida

```bash
# Verificar que las pruebas pasen
mvn test | grep "Tests run"

# Resultado esperado:
# Tests run: 14, Failures: 0, Errors: 0, Skipped: 0
```
