# Mapa de Recursos REST (API v1)

Diseño inicial de los recursos y operaciones que soportará el backend en la primera iteración. Todo colgará bajo el prefijo `/api/v1`.

| Recurso         | Método HTTP | Endpoint                               | Descripción                                              |
| :-------------- | :---------- | :------------------------------------- | :------------------------------------------------------- |
| **Salud**       | `GET`       | `/health`                              | Verifica si el servidor está levantado y operativo.      |
| **Auth**        | `POST`      | `/api/v1/auth/login`                   | Autentica un usuario y devuelve un token de sesión.      |
| **Usuarios**    | `GET`       | `/api/v1/usuarios`                     | Lista todos los técnicos y administradores.              |
|                 | `GET`       | `/api/v1/usuarios/{id}`                | Devuelve el detalle de un usuario concreto.              |
| **Incidencias** | `GET`       | `/api/v1/incidencias`                  | Lista todas las incidencias (soporta filtros por query). |
|                 | `POST`      | `/api/v1/incidencias`                  | Crea una nueva incidencia.                               |
|                 | `PUT`       | `/api/v1/incidencias/{id}`             | Actualiza estado o prioridad de una incidencia.          |
| **Comentarios** | `POST`      | `/api/v1/incidencias/{id}/comentarios` | Añade un comentario a un ticket abierto.                 |
| **Activos**     | `GET`       | `/api/v1/activos`                      | Lista los equipos informáticos de la empresa.            |
