-- ==============================================================================
-- SCRIPT DE RECONSTRUCCIÓN DEL ENTORNO DE PRUEBAS Y DATOS SEMILLA (JORNADA 25)
-- ==============================================================================

-- 1. Destrucción del entorno anterior (Garantiza un estado inicial limpio)
DROP DATABASE IF EXISTS proyecto_incidencias_test;

CREATE DATABASE proyecto_incidencias_test;

USE proyecto_incidencias_test;

-- 2. Creación de tablas (Esquema separado para pruebas)
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    id_rol INT NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE incidencias (
    id_incidencia INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    descripcion TEXT,
    estado VARCHAR(50) NOT NULL,
    prioridad INT NOT NULL DEFAULT 1,
    id_creador INT NOT NULL,
    id_categoria INT NOT NULL,
    FOREIGN KEY (id_creador) REFERENCES usuarios (id_usuario)
);

-- 3. Carga de Datos Semilla (Fixtures)
INSERT INTO
    usuarios (
        email,
        password,
        id_rol,
        activo
    )
VALUES (
        'test_admin@empresa.com',
        '1234',
        1,
        true
    ),
    (
        'test_tecnico@empresa.com',
        '1234',
        2,
        true
    );

INSERT INTO
    incidencias (
        titulo,
        descripcion,
        estado,
        prioridad,
        id_creador,
        id_categoria
    )
VALUES (
        'Fallo de red en Pruebas',
        'No hay conexión en la sala de test',
        'Abierto',
        3,
        1,
        1
    );

COMMIT;