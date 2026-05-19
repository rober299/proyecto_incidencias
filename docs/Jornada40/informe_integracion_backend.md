# Informe de Integración: Backend Listo para Consumo

**Estado General:** VERDE (Operativo y Desplegado en entorno de desarrollo local).
**Host:** `http://localhost:8082`

El backend ha superado la batería de pruebas automatizadas y cumple con los requisitos de seguridad, validación y control de errores. Queda listo para ser consumido por los equipos de Frontend (Web/Móvil).

## Resumen de Endpoints Operativos para Integración

| Recurso      | Endpoint                | Método | Estado    | Requiere Token | Notas para Frontend                                                  |
| :----------- | :---------------------- | :----- | :-------- | :------------- | :------------------------------------------------------------------- |
| **Health**   | `/health`               | `GET`  | ✅ Activo | No             | Usar para verificar si el servidor está levantado.                   |
| **Login**    | `/api/v1/login`         | `POST` | ✅ Activo | No             | Enviar credenciales. Guardar el `token` devuelto en el LocalStorage. |
| **Usuarios** | `/api/v1/usuarios`      | `GET`  | ✅ Activo | Sí             | Enviar cabecera `Authorization: Bearer <token>`.                     |
| **Usuarios** | `/api/v1/usuarios/{id}` | `GET`  | ✅ Activo | Sí             | Devuelve 400 si el ID no existe (controlado).                        |
| **Usuarios** | `/api/v1/usuarios`      | `POST` | ✅ Activo | Sí (ADMIN)     | Valida email y longitud de password. Devuelve 403 a usuarios rasos.  |
| **Usuarios** | `/api/v1/usuarios/{id}` | `DEL`  | ✅ Activo | Sí (ADMIN)     | Borrado lógico (204 No Content).                                     |

## Consideraciones para el Frontend

- **Manejo de Errores:** Todos los errores (400, 401, 403, 404, 500) devuelven un JSON homogéneo. El frontend debe leer la propiedad `message` de la respuesta de error para mostrar un feedback amigable al usuario (ej: alertas en pantalla o _snackbars_).
- **Autenticación HTTP:** No olvidar inyectar el Token Bearer en los interceptores de las peticiones HTTP (Axios/Fetch) para cualquier ruta protegida.
