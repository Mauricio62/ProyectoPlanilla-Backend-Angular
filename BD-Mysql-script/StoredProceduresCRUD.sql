USE BDPLANILLA;

-- ============================================
-- STORED PROCEDURES PARA CARGO
-- ============================================

DELIMITER $$

DROP PROCEDURE IF EXISTS SP_CARGO_INSERTAR$$
CREATE PROCEDURE SP_CARGO_INSERTAR(
    IN p_nombre VARCHAR(50)
)
BEGIN
    INSERT INTO Cargos (Nombre, Activo, FecCreacion)
    VALUES (p_nombre, TRUE, NOW());
    
    SELECT IdCargo, Nombre, Activo, FecCreacion, FecUltimaModificacion
    FROM Cargos
    WHERE IdCargo = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS SP_CARGO_ACTUALIZAR$$
CREATE PROCEDURE SP_CARGO_ACTUALIZAR(
    IN p_id_cargo INT,
    IN p_nombre VARCHAR(50),
    IN p_activo BOOLEAN
)
BEGIN
    UPDATE Cargos
    SET Nombre = p_nombre,
        Activo = p_activo,
        FecUltimaModificacion = NOW()
    WHERE IdCargo = p_id_cargo;
    
    SELECT IdCargo, Nombre, Activo, FecCreacion, FecUltimaModificacion
    FROM Cargos
    WHERE IdCargo = p_id_cargo;
END$$

DROP PROCEDURE IF EXISTS SP_CARGO_CAMBIAR_ESTADO$$
CREATE PROCEDURE SP_CARGO_CAMBIAR_ESTADO(
    IN p_id_cargo INT
)
BEGIN
    UPDATE Cargos
    SET Activo = NOT Activo,
        FecUltimaModificacion = NOW()
    WHERE IdCargo = p_id_cargo;
    
    SELECT ROW_COUNT() as affected_rows;
END$$

DROP PROCEDURE IF EXISTS SP_CARGO_OBTENER_POR_ID$$
CREATE PROCEDURE SP_CARGO_OBTENER_POR_ID(
    IN p_id_cargo INT
)
BEGIN
    SELECT IdCargo, Nombre, Activo, FecCreacion, FecUltimaModificacion
    FROM Cargos
    WHERE IdCargo = p_id_cargo;
END$$

DROP PROCEDURE IF EXISTS SP_CARGO_LISTAR$$
CREATE PROCEDURE SP_CARGO_LISTAR(
    IN p_estado INT,
    IN p_texto VARCHAR(255)
)
BEGIN
    SELECT IdCargo, Nombre, Activo, FecCreacion, FecUltimaModificacion
    FROM Cargos
    WHERE (p_estado = 2 OR Activo = IF(p_estado = 1, TRUE, FALSE))
    AND (p_texto IS NULL OR p_texto = '' OR LOWER(Nombre) LIKE LOWER(CONCAT('%', p_texto, '%')))
    ORDER BY IdCargo DESC;
END$$

-- ============================================
-- STORED PROCEDURES PARA GENERO
-- ============================================

DROP PROCEDURE IF EXISTS SP_GENERO_INSERTAR$$
CREATE PROCEDURE SP_GENERO_INSERTAR(
    IN p_nombre VARCHAR(50)
)
BEGIN
    INSERT INTO Generos (Nombre, Activo, FecCreacion)
    VALUES (p_nombre, TRUE, NOW());
    
    SELECT IdGenero, Nombre, Activo, FecCreacion, FecUltimaModificacion
    FROM Generos
    WHERE IdGenero = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS SP_GENERO_ACTUALIZAR$$
CREATE PROCEDURE SP_GENERO_ACTUALIZAR(
    IN p_id_genero INT,
    IN p_nombre VARCHAR(50),
    IN p_activo BOOLEAN
)
BEGIN
    UPDATE Generos
    SET Nombre = p_nombre,
        Activo = p_activo,
        FecUltimaModificacion = NOW()
    WHERE IdGenero = p_id_genero;
    
    SELECT IdGenero, Nombre, Activo, FecCreacion, FecUltimaModificacion
    FROM Generos
    WHERE IdGenero = p_id_genero;
END$$

DROP PROCEDURE IF EXISTS SP_GENERO_CAMBIAR_ESTADO$$
CREATE PROCEDURE SP_GENERO_CAMBIAR_ESTADO(
    IN p_id_genero INT
)
BEGIN
    UPDATE Generos
    SET Activo = NOT Activo,
        FecUltimaModificacion = NOW()
    WHERE IdGenero = p_id_genero;
    
    SELECT ROW_COUNT() as affected_rows;
END$$

DROP PROCEDURE IF EXISTS SP_GENERO_OBTENER_POR_ID$$
CREATE PROCEDURE SP_GENERO_OBTENER_POR_ID(
    IN p_id_genero INT
)
BEGIN
    SELECT IdGenero, Nombre, Activo, FecCreacion, FecUltimaModificacion
    FROM Generos
    WHERE IdGenero = p_id_genero;
END$$

DROP PROCEDURE IF EXISTS SP_GENERO_LISTAR$$
CREATE PROCEDURE SP_GENERO_LISTAR(
    IN p_estado INT,
    IN p_texto VARCHAR(255)
)
BEGIN
    SELECT IdGenero, Nombre, Activo, FecCreacion, FecUltimaModificacion
    FROM Generos
    WHERE (p_estado = 2 OR Activo = IF(p_estado = 1, TRUE, FALSE))
    AND (p_texto IS NULL OR p_texto = '' OR LOWER(Nombre) LIKE LOWER(CONCAT('%', p_texto, '%')))
    ORDER BY IdGenero DESC;
END$$

-- ============================================
-- STORED PROCEDURES PARA TIPO DOCUMENTO
-- ============================================

DROP PROCEDURE IF EXISTS SP_TIPO_DOCUMENTO_INSERTAR$$
CREATE PROCEDURE SP_TIPO_DOCUMENTO_INSERTAR(
    IN p_nombre VARCHAR(50)
)
BEGIN
    INSERT INTO TipoDocumentos (Nombre, Activo, FecCreacion)
    VALUES (p_nombre, TRUE, NOW());
    
    SELECT IdTipoDocumento, Nombre, Activo, FecCreacion, FecUltimaModificacion
    FROM TipoDocumentos
    WHERE IdTipoDocumento = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS SP_TIPO_DOCUMENTO_ACTUALIZAR$$
CREATE PROCEDURE SP_TIPO_DOCUMENTO_ACTUALIZAR(
    IN p_id_tipo_documento INT,
    IN p_nombre VARCHAR(50),
    IN p_activo BOOLEAN
)
BEGIN
    UPDATE TipoDocumentos
    SET Nombre = p_nombre,
        Activo = p_activo,
        FecUltimaModificacion = NOW()
    WHERE IdTipoDocumento = p_id_tipo_documento;
    
    SELECT IdTipoDocumento, Nombre, Activo, FecCreacion, FecUltimaModificacion
    FROM TipoDocumentos
    WHERE IdTipoDocumento = p_id_tipo_documento;
END$$

DROP PROCEDURE IF EXISTS SP_TIPO_DOCUMENTO_CAMBIAR_ESTADO$$
CREATE PROCEDURE SP_TIPO_DOCUMENTO_CAMBIAR_ESTADO(
    IN p_id_tipo_documento INT
)
BEGIN
    UPDATE TipoDocumentos
    SET Activo = NOT Activo,
        FecUltimaModificacion = NOW()
    WHERE IdTipoDocumento = p_id_tipo_documento;
    
    SELECT ROW_COUNT() as affected_rows;
END$$

DROP PROCEDURE IF EXISTS SP_TIPO_DOCUMENTO_OBTENER_POR_ID$$
CREATE PROCEDURE SP_TIPO_DOCUMENTO_OBTENER_POR_ID(
    IN p_id_tipo_documento INT
)
BEGIN
    SELECT IdTipoDocumento, Nombre, Activo, FecCreacion, FecUltimaModificacion
    FROM TipoDocumentos
    WHERE IdTipoDocumento = p_id_tipo_documento;
END$$

DROP PROCEDURE IF EXISTS SP_TIPO_DOCUMENTO_LISTAR$$
CREATE PROCEDURE SP_TIPO_DOCUMENTO_LISTAR(
    IN p_estado INT,
    IN p_texto VARCHAR(255)
)
BEGIN
    SELECT IdTipoDocumento, Nombre, Activo, FecCreacion, FecUltimaModificacion
    FROM TipoDocumentos
    WHERE (p_estado = 2 OR Activo = IF(p_estado = 1, TRUE, FALSE))
    AND (p_texto IS NULL OR p_texto = '' OR LOWER(Nombre) LIKE LOWER(CONCAT('%', p_texto, '%')))
    ORDER BY IdTipoDocumento DESC;
END$$

-- ============================================
-- STORED PROCEDURES PARA ESTADO CIVIL
-- ============================================

DROP PROCEDURE IF EXISTS SP_ESTADO_CIVIL_INSERTAR$$
CREATE PROCEDURE SP_ESTADO_CIVIL_INSERTAR(
    IN p_nombre VARCHAR(50)
)
BEGIN
    INSERT INTO EstadosCiviles (Nombre, Activo, FecCreacion)
    VALUES (p_nombre, TRUE, NOW());
    
    SELECT IdEstadoCivil, Nombre, Activo, FecCreacion, FecUltimaModificacion
    FROM EstadosCiviles
    WHERE IdEstadoCivil = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS SP_ESTADO_CIVIL_ACTUALIZAR$$
CREATE PROCEDURE SP_ESTADO_CIVIL_ACTUALIZAR(
    IN p_id_estado_civil INT,
    IN p_nombre VARCHAR(50),
    IN p_activo BOOLEAN
)
BEGIN
    UPDATE EstadosCiviles
    SET Nombre = p_nombre,
        Activo = p_activo,
        FecUltimaModificacion = NOW()
    WHERE IdEstadoCivil = p_id_estado_civil;
    
    SELECT IdEstadoCivil, Nombre, Activo, FecCreacion, FecUltimaModificacion
    FROM EstadosCiviles
    WHERE IdEstadoCivil = p_id_estado_civil;
END$$

DROP PROCEDURE IF EXISTS SP_ESTADO_CIVIL_CAMBIAR_ESTADO$$
CREATE PROCEDURE SP_ESTADO_CIVIL_CAMBIAR_ESTADO(
    IN p_id_estado_civil INT
)
BEGIN
    UPDATE EstadosCiviles
    SET Activo = NOT Activo,
        FecUltimaModificacion = NOW()
    WHERE IdEstadoCivil = p_id_estado_civil;
    
    SELECT ROW_COUNT() as affected_rows;
END$$

DROP PROCEDURE IF EXISTS SP_ESTADO_CIVIL_OBTENER_POR_ID$$
CREATE PROCEDURE SP_ESTADO_CIVIL_OBTENER_POR_ID(
    IN p_id_estado_civil INT
)
BEGIN
    SELECT IdEstadoCivil, Nombre, Activo, FecCreacion, FecUltimaModificacion
    FROM EstadosCiviles
    WHERE IdEstadoCivil = p_id_estado_civil;
END$$

DROP PROCEDURE IF EXISTS SP_ESTADO_CIVIL_LISTAR$$
CREATE PROCEDURE SP_ESTADO_CIVIL_LISTAR(
    IN p_estado INT,
    IN p_texto VARCHAR(255)
)
BEGIN
    SELECT IdEstadoCivil, Nombre, Activo, FecCreacion, FecUltimaModificacion
    FROM EstadosCiviles
    WHERE (p_estado = 2 OR Activo = IF(p_estado = 1, TRUE, FALSE))
    AND (p_texto IS NULL OR p_texto = '' OR LOWER(Nombre) LIKE LOWER(CONCAT('%', p_texto, '%')))
    ORDER BY IdEstadoCivil DESC;
END$$

-- ============================================
-- STORED PROCEDURES PARA SITUACION TRABAJADOR
-- ============================================

DROP PROCEDURE IF EXISTS SP_SITUACION_TRABAJADOR_INSERTAR$$
CREATE PROCEDURE SP_SITUACION_TRABAJADOR_INSERTAR(
    IN p_nombre VARCHAR(50)
)
BEGIN
    INSERT INTO SituacionTrabajador (Nombre, Activo, FecCreacion)
    VALUES (p_nombre, TRUE, NOW());
    
    SELECT IdSituacion, Nombre, Activo, FecCreacion, FecUltimaModificacion
    FROM SituacionTrabajador
    WHERE IdSituacion = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS SP_SITUACION_TRABAJADOR_ACTUALIZAR$$
CREATE PROCEDURE SP_SITUACION_TRABAJADOR_ACTUALIZAR(
    IN p_id_situacion INT,
    IN p_nombre VARCHAR(50),
    IN p_activo BOOLEAN
)
BEGIN
    UPDATE SituacionTrabajador
    SET Nombre = p_nombre,
        Activo = p_activo,
        FecUltimaModificacion = NOW()
    WHERE IdSituacion = p_id_situacion;
    
    SELECT IdSituacion, Nombre, Activo, FecCreacion, FecUltimaModificacion
    FROM SituacionTrabajador
    WHERE IdSituacion = p_id_situacion;
END$$

DROP PROCEDURE IF EXISTS SP_SITUACION_TRABAJADOR_CAMBIAR_ESTADO$$
CREATE PROCEDURE SP_SITUACION_TRABAJADOR_CAMBIAR_ESTADO(
    IN p_id_situacion INT
)
BEGIN
    UPDATE SituacionTrabajador
    SET Activo = NOT Activo,
        FecUltimaModificacion = NOW()
    WHERE IdSituacion = p_id_situacion;
    
    SELECT ROW_COUNT() as affected_rows;
END$$

DROP PROCEDURE IF EXISTS SP_SITUACION_TRABAJADOR_OBTENER_POR_ID$$
CREATE PROCEDURE SP_SITUACION_TRABAJADOR_OBTENER_POR_ID(
    IN p_id_situacion INT
)
BEGIN
    SELECT IdSituacion, Nombre, Activo, FecCreacion, FecUltimaModificacion
    FROM SituacionTrabajador
    WHERE IdSituacion = p_id_situacion;
END$$

DROP PROCEDURE IF EXISTS SP_SITUACION_TRABAJADOR_LISTAR$$
CREATE PROCEDURE SP_SITUACION_TRABAJADOR_LISTAR(
    IN p_estado INT,
    IN p_texto VARCHAR(255)
)
BEGIN
    SELECT IdSituacion, Nombre, Activo, FecCreacion, FecUltimaModificacion
    FROM SituacionTrabajador
    WHERE (p_estado = 2 OR Activo = IF(p_estado = 1, TRUE, FALSE))
    AND (p_texto IS NULL OR p_texto = '' OR LOWER(Nombre) LIKE LOWER(CONCAT('%', p_texto, '%')))
    ORDER BY IdSituacion DESC;
END$$

DELIMITER ;

