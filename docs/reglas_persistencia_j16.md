# Reglas de Persistencia y Restricciones de Integridad

Para cumplir estrictamente con los Requisitos Funcionales (v1), se imponen las siguientes reglas en el motor de base de datos:

## 1. Estrategia de Borrado (Lógico vs Físico)

- **Borrado Lógico (RF01):** Queda prohibido el uso de `DELETE` en las tablas `USUARIOS` y `ACTIVOS`. Se utilizará la columna `activo` (BOOLEAN) para darlos de baja sin romper el histórico de las incidencias.
- **Borrado Físico Controlado:** La tabla `INCIDENCIAS` solo se purgará físicamente por mantenimiento estructural. Si esto ocurre, se aplicará `ON DELETE CASCADE` hacia `COMENTARIOS` y `ADJUNTOS` para destruir la información huérfana.

## 2. Restricciones de Integridad (NOT NULL y UNIQUE)

- **Regla de Negocio 2 (Obligatoriedad):** A nivel de esquema, las columnas `titulo`, `descripcion`, `prioridad`, `id_creador` e `id_categoria` en la tabla `INCIDENCIAS` tendrán la restricción `NOT NULL`.
- **Identificadores Únicos:** Se aplica `UNIQUE` a `USUARIOS.email` y `ACTIVOS.codigo_inventario`.

## 3. Inmutabilidad y Auditoría

- La tabla `AUDITORIA` es estrictamente _Insert-Only_. No se permitirá ninguna operación `UPDATE` o `DELETE` sobre ella para garantizar la trazabilidad de seguridad.
