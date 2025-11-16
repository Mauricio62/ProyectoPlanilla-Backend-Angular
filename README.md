# Proyecto Planilla - Backend (Spring Boot)

Backend para la gestión de planillas (API REST con Spring Boot y Maven). Incluye autenticación JWT y documentación Swagger/OpenAPI.

## Requisitos
- JDK 17 (o superior compatible). El proyecto compila con release 17
- Maven Wrapper (incluido: `mvnw` / `mvnw.cmd`)
- MySQL 8.x
- Git (opcional)

## Estructura relevante
- Código fuente: `src/main/java`
- Configuración: `src/main/resources/application.properties`
- Scripts SQL: `BD-Mysql-script/`
  - `PlanillaMensualTablas.sql`
  - `StoredProceduresCRUD.sql`
  - `PlanillaMensualInsert.sql`

## 1) Configuración de Base de Datos (MySQL)
1. Cree una base de datos en MySQL (por ejemplo `planilla_db`).
2. Ejecute los scripts en este orden sobre esa base de datos:
   - `BD-Mysql-script/PlanillaMensualTablas.sql`
   - `BD-Mysql-script/StoredProceduresCRUD.sql`
   - `BD-Mysql-script/PlanillaMensualInsert.sql`

> Use su cliente preferido (MySQL Workbench, IntelliJ Database, consola, etc.).

## 2) Configurar `application.properties`
Edite `src/main/resources/application.properties` con los datos de su servidor MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/planilla_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=SU_USUARIO
spring.datasource.password=SU_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Puerto de la app (opcional)
server.port=8080
```

Notas:
- Mantenga `ddl-auto=none` si ya aplica los scripts SQL manualmente.
- Ajuste `server.port` si 8080 está ocupado.

## 3) Compilar y ejecutar
Desde la carpeta raíz del proyecto.

### Windows (PowerShell o CMD)
```bash
# Verificar Maven/Java
mvnw -v

# Compilar sin tests (opcional)
mvnw -DskipTests clean package

# Ejecutar en modo desarrollo
mvnw spring-boot:run

# O ejecutar el JAR empaquetado
java -jar target\cibertec-0.0.1-SNAPSHOT.jar
```

### Linux/Mac
```bash
# Verificar Maven/Java
./mvnw -v

# Compilar sin tests (opcional)
./mvnw -DskipTests clean package

# Ejecutar en modo desarrollo
./mvnw spring-boot:run

# O ejecutar el JAR empaquetado
java -jar target/cibertec-0.0.1-SNAPSHOT.jar
```

## 3.1) Detener la aplicación
- Cuando se ejecuta en el terminal (tanto con `mvnw spring-boot:run` como con `java -jar ...`):
  - Presione `Ctrl + C` para detenerla limpiamente.
- Si quedó un proceso en segundo plano ocupando el puerto:
  - Windows (PowerShell):
    ```powershell
    netstat -ano | findstr :8080
    Stop-Process -Id <PID>
    ```
  - Linux/Mac:
    ```bash
    lsof -i :8080
    kill -9 <PID>
    ```

## 4) Documentación de la API (Swagger/OpenAPI)
Con la aplicación levantada:
- UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## 5) Autenticación
El proyecto incluye autenticación basada en JWT. Flujo típico:
- Endpoint de login (usuario/clave) devuelve un token JWT.
- En las siguientes llamadas, enviar `Authorization: Bearer <token>`.

Revise los controladores bajo `src/main/java/com/planilla_DAWI/cibertec/Controller/` para ver rutas disponibles y los DTOs en `Dto/`.

## 6) Variables de entorno (opcional)
Puede externalizar credenciales con variables de entorno y referencias en `application.properties`, por ejemplo:
```properties
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```
Y exportarlas antes de levantar la app.

## 7) Problemas comunes
- Puerto ocupado: cambie `server.port` en `application.properties`.
- Credenciales MySQL incorrectas: verifique usuario/clave y permisos de la base.
- Versión de Java: use JDK 17+.
- Driver MySQL: asegúrese de usar `com.mysql.cj.jdbc.Driver` y MySQL 8.

## 8) Desarrollo
- Estructura por capas: `Controller`, `Service`, `Repository`, `Entity`, `Dto`, `Mappers`.
- Puede ejecutar los tests con: `mvnw test`.

---

Hecho con Spring Boot y Maven. Cualquier duda o mejora: abrir un issue o PR.
