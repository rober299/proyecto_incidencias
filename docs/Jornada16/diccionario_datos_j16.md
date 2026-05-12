# Diccionario de Datos y Relaciones (Jornada 16)

## 1. Relectura de Requisitos y Modelo de Clases (Bloque 1)

Se ha analizado el documento de requisitos (v1) y el modelo de dominio original para definir la persistencia estricta:

- **Entidades a almacenar:** Roles, Categorías, Activos, Usuarios, Incidencias, Comentarios, Adjuntos y Auditoría.
- **Datos derivados (No almacenados):** El "Tiempo de resolución" no será una columna; se derivará restando dinámicamente `fecha_cierre` y `fecha_creacion`. El "Número total de incidencias por activo" se calculará mediante un `COUNT` agrupado, evitando redundancia.

## 2. Definición de Relaciones (Bloque 2)

El modelo relacional se articula mediante las siguientes dependencias (1:M):

- **Maestras:** Un `Rol` tiene múltiples Usuarios. Una `Categoría` agrupa múltiples Incidencias. Un `Activo` sufre múltiples Incidencias.
- **Transaccionales:** Un `Usuario` puede crear y resolver múltiples Incidencias, escribir Comentarios y generar registros de Auditoría. Una `Incidencia` aloja múltiples Comentarios y Adjuntos.

## 3. Diccionario de Datos y Tipos

### Tablas Maestras

| Tabla          | Campo               | Tipo         | Clave | Nulo | Descripción              |
| :------------- | :------------------ | :----------- | :---- | :--- | :----------------------- |
| **ROLES**      | `id_rol`            | INT (Auto)   | PK    | No   | Identificador.           |
|                | `nombre`            | VARCHAR(50)  | UK    | No   | ADMIN, TECNICO, CLIENTE. |
| **CATEGORIAS** | `id_categoria`      | INT (Auto)   | PK    | No   | Identificador.           |
|                | `nombre`            | VARCHAR(100) | UK    | No   | Hardware, Software, etc. |
| **ACTIVOS**    | `id_activo`         | INT (Auto)   | PK    | No   | ID interno.              |
|                | `codigo_inventario` | VARCHAR(50)  | UK    | No   | Código único empresa.    |
|                | `estado`            | VARCHAR(50)  |       | No   | Operativo, Baja.         |

### Tabla: USUARIOS

| Campo        | Tipo         | Clave | Nulo | Descripción (Origen)                |
| :----------- | :----------- | :---- | :--- | :---------------------------------- |
| `id_usuario` | INT (Auto)   | PK    | No   | Identificador único.                |
| `email`      | VARCHAR(150) | UK    | No   | Login único (RNF01).                |
| `password`   | VARCHAR(255) |       | No   | Hash bcrypt (RNF01).                |
| `activo`     | BOOLEAN      |       | No   | Baja lógica, default `true` (RF01). |
| `id_rol`     | INT          | FK    | No   | Referencia a ROLES.                 |

### Tabla: INCIDENCIAS (Núcleo)

| Campo            | Tipo         | Clave | Nulo | Descripción (Origen)               |
| :--------------- | :----------- | :---- | :--- | :--------------------------------- |
| `id_incidencia`  | INT (Auto)   | PK    | No   | Número de ticket.                  |
| `titulo`         | VARCHAR(150) |       | No   | Resumen corto (Regla 2).           |
| `descripcion`    | TEXT         |       | No   | Detalle (Regla 2).                 |
| `prioridad`      | INT          |       | No   | Nivel de urgencia (Regla 2).       |
| `estado`         | VARCHAR(50)  |       | No   | Inicia en 'Abierto' (Regla 3).     |
| `fecha_creacion` | TIMESTAMP    |       | No   | Default CURRENT_TIMESTAMP (RF03).  |
| `fecha_cierre`   | TIMESTAMP    |       | Sí   | Sello de resolución (RF03).        |
| `id_creador`     | INT          | FK    | No   | Referencia a USUARIOS.             |
| `id_tecnico`     | INT          | FK    | Sí   | Referencia a USUARIOS.             |
| `id_categoria`   | INT          | FK    | No   | Referencia a CATEGORIAS (Regla 2). |
| `id_activo`      | INT          | FK    | Sí   | Referencia a ACTIVOS.              |
