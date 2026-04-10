USE proyecto_incidencias;

-- 1. TABLAS MAESTRAS
CREATE TABLE ROLES (
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE CATEGORIAS (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE ACTIVOS (
    id_activo INT AUTO_INCREMENT PRIMARY KEY,
    codigo_inventario VARCHAR(50) NOT NULL UNIQUE,
    tipo VARCHAR(50) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE
);

-- 2. TABLA DE USUARIOS
CREATE TABLE USUARIOS (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    id_rol INT NOT NULL,
    CONSTRAINT fk_usuario_rol FOREIGN KEY (id_rol) REFERENCES ROLES (id_rol) ON DELETE RESTRICT
);

-- 3. NÚCLEO TRANSACCIONAL
CREATE TABLE INCIDENCIAS (
    id_incidencia INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    descripcion TEXT NOT NULL,
    prioridad INT NOT NULL,
    estado VARCHAR(50) NOT NULL DEFAULT 'Abierto',
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_cierre TIMESTAMP NULL,
    id_creador INT NOT NULL,
    id_tecnico INT NULL,
    id_categoria INT NOT NULL,
    id_activo INT NULL,
    CONSTRAINT fk_incidencia_creador FOREIGN KEY (id_creador) REFERENCES USUARIOS (id_usuario) ON DELETE RESTRICT,
    CONSTRAINT fk_incidencia_tecnico FOREIGN KEY (id_tecnico) REFERENCES USUARIOS (id_usuario) ON DELETE RESTRICT,
    CONSTRAINT fk_incidencia_categoria FOREIGN KEY (id_categoria) REFERENCES CATEGORIAS (id_categoria) ON DELETE RESTRICT,
    CONSTRAINT fk_incidencia_activo FOREIGN KEY (id_activo) REFERENCES ACTIVOS (id_activo) ON DELETE RESTRICT
);

CREATE TABLE COMENTARIOS (
    id_comentario INT AUTO_INCREMENT PRIMARY KEY,
    texto TEXT NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    id_incidencia INT NOT NULL,
    id_usuario INT NOT NULL,
    CONSTRAINT fk_comentario_incidencia FOREIGN KEY (id_incidencia) REFERENCES INCIDENCIAS (id_incidencia) ON DELETE CASCADE,
    CONSTRAINT fk_comentario_usuario FOREIGN KEY (id_usuario) REFERENCES USUARIOS (id_usuario) ON DELETE RESTRICT
);

CREATE TABLE ADJUNTOS (
    id_adjunto INT AUTO_INCREMENT PRIMARY KEY,
    url_archivo VARCHAR(255) NOT NULL,
    tipo_archivo VARCHAR(10) NOT NULL,
    id_incidencia INT NOT NULL,
    CONSTRAINT fk_adjunto_incidencia FOREIGN KEY (id_incidencia) REFERENCES INCIDENCIAS (id_incidencia) ON DELETE CASCADE
);

CREATE TABLE AUDITORIA (
    id_auditoria INT AUTO_INCREMENT PRIMARY KEY,
    accion VARCHAR(100) NOT NULL,
    fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    id_usuario INT NOT NULL,
    id_incidencia INT NULL,
    CONSTRAINT fk_auditoria_usuario FOREIGN KEY (id_usuario) REFERENCES USUARIOS (id_usuario) ON DELETE RESTRICT,
    CONSTRAINT fk_auditoria_incidencia FOREIGN KEY (id_incidencia) REFERENCES INCIDENCIAS (id_incidencia) ON DELETE SET NULL
);

-- 4. ÍNDICES DE OPTIMIZACIÓN
CREATE INDEX idx_incidencias_estado ON INCIDENCIAS (estado);

CREATE INDEX idx_incidencias_tecnico ON INCIDENCIAS (id_tecnico);

CREATE INDEX idx_incidencias_prioridad ON INCIDENCIAS (prioridad);

CREATE INDEX idx_incidencias_fecha ON INCIDENCIAS (fecha_creacion);

CREATE INDEX idx_usuarios_activo ON USUARIOS (activo);