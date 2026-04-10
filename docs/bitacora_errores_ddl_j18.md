# Bitácora de Errores en la Creación del Esquema (DDL)

Durante la ejecución del script desde cero en la base limpia, se detectaron y corrigieron los siguientes errores de diseño y sintaxis:

- **Error 1215: Cannot add foreign key constraint.**
  - **Causa:** Al principio, el script intentaba crear la tabla `USUARIOS` antes que la tabla `ROLES`.
  - **Solución:** Se reordenó el script DDL para que las tablas maestras (`ROLES`, `CATEGORIAS`, `ACTIVOS`) se generen primero, permitiendo que las Claves Foráneas de las tablas transaccionales se resuelvan correctamente.
- **Error de borrado cíclico (Drop tables):**
  - **Causa:** Al probar el script de limpieza, no se podían hacer `DROP TABLE` individualmente por los bloqueos de restricciones `FOREIGN KEY`.
  - **Solución:** Se optó por una estrategia de recreación limpia a nivel de esquema (`DROP DATABASE IF EXISTS`), lo que garantiza un entorno de desarrollo 100% pulcro sin conflictos de herencia.
- **Ajuste de integridad en Auditoría:**
  - **Causa:** La tabla `AUDITORIA` tenía `ON DELETE RESTRICT` sobre la incidencia. Esto impedía el purgado físico de tickets muy antiguos por mantenimiento.
  - **Solución:** Se modificó a `ON DELETE SET NULL` para `id_incidencia` en la auditoría. Si el ticket desaparece, la traza de quién hizo la acción y cuándo permanece intacta, simplemente desvinculada del ticket original.
