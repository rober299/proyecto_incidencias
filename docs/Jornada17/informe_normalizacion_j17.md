# Informe de Normalización Aplicada y Excepciones

Este documento justifica las decisiones de normalización aplicadas al diseño de la base de datos para evitar redundancias y anomalías de actualización.

## 1. Aplicación de las Formas Normales (1NF, 2NF, 3NF)

- **1NF (Atomicidad):** Se ha evitado crear columnas como `adjuntos` o `comentarios` dentro de la tabla `INCIDENCIAS` que contengan valores separados por comas. En su lugar, se han creado las tablas independientes `ADJUNTOS` y `COMENTARIOS`.
- **2NF (Dependencia Funcional Completa):** Todas las tablas utilizan claves primarias artificiales (`id_...`). Esto garantiza que todos los atributos no clave dependan funcionalmente del identificador único.
- **3NF (Dependencias Transitivas):** Se ha extraído la tabla `CATEGORIAS`. Si el nombre de una categoría cambia (ej. de "Fallos de Red" a "Networking"), se actualiza en un solo lugar (la maestra) y no en las miles de incidencias creadas, evitando anomalías de actualización.

## 2. Tabla de Posibles Anomalías Evitadas

| Campo Conflictivo   | Problema / Anomalía Detectada                                                                                                   | Solución Estructural Adoptada                                      |
| :------------------ | :------------------------------------------------------------------------------------------------------------------------------ | :----------------------------------------------------------------- |
| **Rol del Usuario** | Guardar "ADMIN" o "TECNICO" en texto plano en `USUARIOS` provoca errores tipográficos y dificulta el cambio masivo de permisos. | Extracción a la tabla `ROLES`. Se guarda solo el `id_rol` (FK).    |
| **Categoría**       | Mismo problema que los roles. Anomalía de modificación si cambia el nombre del departamento.                                    | Extracción a la tabla maestra `CATEGORIAS`.                        |
| **Comentarios**     | Guardarlos en un campo `TEXT` largo concatenando strings rompe la 1NF y hace imposible saber quién escribió qué y cuándo.       | Creación de la tabla transaccional `COMENTARIOS` vinculada por FK. |

## 3. Excepciones Justificadas (Criterio Práctico)

La normalización no se ha aplicado de forma mecánica. Se han tomado decisiones basadas en rendimiento y simplicidad:

- **Estado de la Incidencia:** Aunque teóricamente los estados ("Abierta", "Cerrada") podrían ir en una tabla maestra `ESTADOS`, se ha decidido mantener como un campo `VARCHAR` (o `ENUM`) dentro de `INCIDENCIAS`. Dado que los estados están fuertemente acoplados a la lógica de negocio en el código fuente (requisitos v1), hacer un `JOIN` adicional constante para leer el estado degradaría el rendimiento de las consultas masivas sin aportar valor real.
- **Prioridad:** Se mantiene como un `INT` en `INCIDENCIAS` por la misma razón de simplicidad operativa.
