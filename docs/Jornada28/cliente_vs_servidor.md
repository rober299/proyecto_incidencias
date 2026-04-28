# Documento: Validaciones Cliente vs Servidor

## Límite del lado cliente

La validación en el cliente (navegador mediante JavaScript) se implementa **exclusivamente para mejorar la Experiencia de Usuario (UX)**. Permite dar feedback inmediato sin recargar la página y ahorra peticiones innecesarias al servidor.

## Por qué NO se puede confiar solo en el navegador

Cualquier usuario con conocimientos básicos puede saltarse estas validaciones en segundos:

1. Desactivando JavaScript en su navegador.
2. Usando las DevTools (F12) para quitar el atributo `required` o saltarse las reglas de longitud.
3. Usando herramientas como Postman o cURL para enviar peticiones HTTP directamente al backend, saltándose el formulario HTML por completo.

## Validaciones que deben repetirse OBLIGATORIAMENTE en el Servidor (Java)

1. **Existencia y longitud:** Comprobar que los campos requeridos no vienen vacíos ni superan el límite de la base de datos (ej: Título no nulo, max 150 chars).
2. **Tipos de datos:** Confirmar que un ID es numérico o que una fecha tiene formato válido antes de guardarlo en la Base de Datos.
3. **Reglas de Negocio / Seguridad:** Comprobar que un usuario no intente cerrar una incidencia que no es suya, o verificar contraseñas.
