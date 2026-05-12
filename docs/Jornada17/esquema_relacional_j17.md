# Esquema Relacional Completo (Jornada 17)

Tras aplicar las reglas de transformación del modelo ER al modelo relacional, el esquema resultante con claves primarias (PK), foráneas (FK) y tipos de datos orientativos es el siguiente:

- **ROLES** (id_rol PK INT, nombre VARCHAR(50) UK)
- **CATEGORIAS** (id_categoria PK INT, nombre VARCHAR(100) UK)
- **ACTIVOS** (id_activo PK INT, codigo_inventario VARCHAR(50) UK, tipo VARCHAR(50), estado VARCHAR(50), activo BOOLEAN)
- **USUARIOS** (id_usuario PK INT, email VARCHAR(150) UK, password VARCHAR(255), activo BOOLEAN, id_rol FK INT)
- **INCIDENCIAS** (id_incidencia PK INT, titulo VARCHAR(150), descripcion TEXT, prioridad INT, estado VARCHAR(50), fecha_creacion TIMESTAMP, fecha_cierre TIMESTAMP, id_creador FK INT, id_tecnico FK INT, id_categoria FK INT, id_activo FK INT)
- **COMENTARIOS** (id_comentario PK INT, texto TEXT, fecha_creacion TIMESTAMP, id_incidencia FK INT, id_usuario FK INT)
- **ADJUNTOS** (id_adjunto PK INT, url_archivo VARCHAR(255), tipo_archivo VARCHAR(10), id_incidencia FK INT)
- **AUDITORIA** (id_auditoria PK INT, accion VARCHAR(100), fecha TIMESTAMP, id_usuario FK INT, id_incidencia FK INT)

## Representación Visual del Esquema Relacional

![Esquema Relacional](../evidencias/semana_4/jornada_2026-04-07/esquema_relacional_j17.png)
