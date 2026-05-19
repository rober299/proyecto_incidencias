# Estrategia de Versionado y Control de Contratos JSON

Para garantizar que los clientes (Frontend Web y App Móvil) no se rompan ante futuros cambios en el backend, se establece la siguiente normativa:

## 1. Versionado por URI

Toda la API se expone actualmente bajo la ruta base `/api/v1/`.

- **Regla:** Cualquier cambio que "rompa" el contrato actual (eliminar un campo del JSON, cambiar el tipo de dato de un ID de numérico a string, etc.) obligará a desplegar los nuevos endpoints bajo `/api/v2/`.
- La `v1` se mantendrá operativa durante un periodo de deprecación de 6 meses para dar tiempo a la migración de los clientes.

## 2. Cambios Retrocompatibles (No cambian de versión)

Se permite hacer modificaciones en caliente sobre `/api/v1/` solo si son aditivas:

- Añadir nuevos endpoints.
- Añadir nuevos campos opcionales en las respuestas JSON.
- Añadir nuevos parámetros de filtrado en las query strings.

## 3. Control del Contrato JSON

Cualquier modificación en la estructura de entrada (DTOs) o salida debe ser notificada mediante el changelog de la documentación y validada mediante la suite de pruebas automatizadas en Postman (Runner) antes de pasar a producción.
