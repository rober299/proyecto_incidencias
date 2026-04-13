USE proyecto_incidencias;

-- 1. Insertar Maestras (Roles, Categorías, Activos)
INSERT INTO
    ROLES (nombre)
VALUES ('ADMIN'),
    ('TECNICO'),
    ('CLIENTE');

INSERT INTO
    CATEGORIAS (nombre)
VALUES ('Hardware'),
    ('Software'),
    ('Redes y Conectividad');

INSERT INTO
    ACTIVOS (
        codigo_inventario,
        tipo,
        estado,
        activo
    )
VALUES (
        'PC-001',
        'Portátil',
        'Operativo',
        TRUE
    ),
    (
        'PC-002',
        'Portátil',
        'En reparación',
        TRUE
    ),
    (
        'SRV-01',
        'Servidor',
        'Operativo',
        TRUE
    ),
    (
        'SW-001',
        'Licencia',
        'Operativo',
        TRUE
    );

-- 2. Insertar Usuarios (1 Admin, 2 Técnicos, 2 Clientes)
-- Passwords simulados en formato hash
INSERT INTO
    USUARIOS (
        email,
        password,
        activo,
        id_rol
    )
VALUES (
        'admin@empresa.com',
        '$2y$10$abc123...',
        TRUE,
        1
    ),
    (
        'tecnico1@empresa.com',
        '$2y$10$abc123...',
        TRUE,
        2
    ),
    (
        'tecnico2@empresa.com',
        '$2y$10$abc123...',
        TRUE,
        2
    ),
    (
        'cliente1@empresa.com',
        '$2y$10$abc123...',
        TRUE,
        3
    ),
    (
        'cliente2@empresa.com',
        '$2y$10$abc123...',
        TRUE,
        3
    );

-- 3. Insertar Incidencias (Diferentes estados)
INSERT INTO
    INCIDENCIAS (
        titulo,
        descripcion,
        prioridad,
        estado,
        fecha_creacion,
        id_creador,
        id_tecnico,
        id_categoria,
        id_activo
    )
VALUES (
        'Pantalla rota',
        'El portátil se ha caído y la pantalla no enciende.',
        1,
        'Abierto',
        '2026-04-01 10:00:00',
        4,
        NULL,
        1,
        1
    ),
    (
        'No hay internet',
        'El cable de red de mi mesa no da señal.',
        2,
        'En Progreso',
        '2026-04-05 11:30:00',
        5,
        2,
        3,
        NULL
    ),
    (
        'Licencia caducada',
        'El programa de diseño me pide renovación.',
        3,
        'Cerrado',
        '2026-04-02 09:00:00',
        4,
        3,
        2,
        4
    );

-- Actualizar fecha de cierre para la incidencia cerrada
UPDATE INCIDENCIAS
SET
    fecha_cierre = '2026-04-03 16:00:00'
WHERE
    id_incidencia = 3;

-- 4. Insertar Comentarios
INSERT INTO
    COMENTARIOS (
        texto,
        fecha_creacion,
        id_incidencia,
        id_usuario
    )
VALUES (
        'Estoy revisando el cableado de la planta 2.',
        '2026-04-05 12:00:00',
        2,
        2
    ),
    (
        'Gracias, quedo a la espera.',
        '2026-04-05 12:15:00',
        2,
        5
    );

-- 5. Insertar Auditoría
INSERT INTO
    AUDITORIA (
        accion,
        fecha,
        id_usuario,
        id_incidencia
    )
VALUES (
        'CREACION_TICKET',
        '2026-04-01 10:00:00',
        4,
        1
    ),
    (
        'ASIGNACION_TECNICO',
        '2026-04-05 11:45:00',
        2,
        2
    );