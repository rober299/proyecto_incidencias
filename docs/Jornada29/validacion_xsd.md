# Validación de Esquema XSD

Se ha creado el esquema `incidencias.xsd` para garantizar la integridad estructural del XML.

**Errores que detecta el esquema (Casos Inválidos probados):**

1. **Falta de atributos obligatorios:** Si una `<incidencia>` no tiene el atributo `id`, el parser devuelve un error de tipo `cvc-complex-type.4`.
2. **Valores fuera del Enum:** Si en el atributo `estado` se introduce un valor como "Pausado", el XSD lanza error porque solo permite "Abierto", "En Progreso" o "Cerrado" mediante `<xs:restriction>`.
3. **Formato de fecha:** Si `<fecha_creacion>` tiene el formato `23/04/2026` en lugar del estándar ISO `2026-04-23`, la validación de `xs:date` falla automáticamente.
