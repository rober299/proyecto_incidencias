# Mapeo de Operaciones CRUD a HTTP (API REST)

Para garantizar la semántica de la API, se ha implementado el siguiente estándar de respuestas y verbos HTTP basándonos en las buenas prácticas REST.

| Operación CRUD   | Método HTTP | Endpoint de Ejemplo     | Código de Éxito  | Códigos de Error Esperados                |
| :--------------- | :---------- | :---------------------- | :--------------- | :---------------------------------------- |
| **Leer (Todos)** | `GET`       | `/api/v1/incidencias`   | `200 OK`         | `500` (Error interno)                     |
| **Leer (Uno)**   | `GET`       | `/api/v1/usuarios/1`    | `200 OK`         | `404` (No encontrado)                     |
| **Crear**        | `POST`      | `/api/v1/incidencias`   | `201 Created`    | `400` (Body inválido), `409` (Conflicto)  |
| **Actualizar**   | `PUT`       | `/api/v1/incidencias/1` | `200 OK`         | `400` (Datos erróneos), `404` (No existe) |
| **Borrar**       | `DELETE`    | `/api/v1/usuarios/1`    | `204 No Content` | `404` (No existe), `403` (No autorizado)  |

## Estructura de Errores

Todos los errores devueltos por la API (4xx y 5xx) siguen una estructura JSON predecible para facilitar su consumo desde el frontend:

```json
{
  "error": "Tipo de error (Ej: Recurso no encontrado)",
  "mensaje": "Mensaje detallado para el desarrollador/usuario",
  "codigo": 404
}
```
