# Registro de Incidencias Descubiertas por Testing (Bloque 3)

Durante la ejecución de la batería de pruebas unitarias (`TestRunner.java`), se detectó la siguiente incidencia en la lógica del dominio:

- **ID del Defecto:** BUG-001
- **Método Afectado:** `LogicaNegocio.calcularPrioridad(int urgencia, int impacto)`
- **Test que lo descubrió:** `test01_calcularPrioridad_ValoresNormales_DevuelveMultiplicacion`
- **Descripción del Fallo:** Al introducir los valores Urgencia=2 e Impacto=3, el resultado esperado de la regla de negocio era 6 (multiplicación). Sin embargo, la aserción falló porque el método devolvió 5.
- **Causa Raíz:** Error tipográfico en el operador matemático. El código estaba usando `urgencia + impacto` en lugar de `urgencia * impacto`.
- **Solución Aplicada:** Se ha corregido el operador en `LogicaNegocio.java`. Al volver a ejecutar el framework de pruebas, el test se ha iluminado en verde (OK), confirmando la resolución.
