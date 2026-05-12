# Registro de Cambios respecto al Modelo Inicial

Tras aplicar el proceso de normalización (1NF, 2NF, 3NF), el modelo conceptual inicial (Jornada 7) y la primera versión del diccionario (Jornada 16) han evolucionado hacia la versión definitiva.

## Cambios Aplicados

1. **Consolidación de Identificadores:** Se han estandarizado todas las claves primarias como enteros auto-incrementales (`id_usuario`, `id_incidencia`, etc.) para asegurar la Segunda Forma Normal (2NF) y optimizar los índices de búsqueda.
2. **Denormalización controlada del Estado:** El campo `estado` se ha consolidado dentro de `INCIDENCIAS` en lugar de una tabla propia, priorizando la velocidad de lectura (evitar JOINs excesivos) sobre la normalización académica pura.
3. **Independencia de Adjuntos:** Se confirma la necesidad de la tabla `ADJUNTOS` para cumplir con la Primera Forma Normal (1NF), evitando campos multivaluados en la tabla principal.

_Nota: El diccionario de datos consolidado (`diccionario_datos_j16.md`) ya incluye la versión final corregida con estos tipos de datos y restricciones aplicadas._
