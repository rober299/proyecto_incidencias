# Nota Técnica: Estrategia de Seguridad y Buenas Prácticas

## 1. Almacenamiento de Contraseñas

En un entorno de producción, las contraseñas **nunca** deben almacenarse en texto plano en la base de datos (como se hace en fases tempranas de desarrollo formativo).

- **Buenas prácticas:** Se debe aplicar una función de hash criptográfico unidireccional y robusta, como **Bcrypt**, **Argon2** o **PBKDF2**.
- **Salting:** Cada contraseña debe combinarse con un "salt" (una cadena aleatoria única) antes de ser hasheada. Esto previene ataques de fuerza bruta basados en tablas precalculadas (Rainbow Tables).

## 2. Estrategia de Sesiones y Tokens

Para la comunicación entre cliente y servidor en esta API REST, se ha optado por una arquitectura **Stateless** (sin estado) basada en **Tokens**.

- **Por qué Tokens:** Al ser una API RESTful, el servidor no debe mantener el estado de la sesión del cliente en memoria. El cliente envía sus credenciales (`/login`), el servidor las valida y emite un Token temporal (ej. UUID o JWT).
- **Uso:** El cliente debe adjuntar este Token en la cabecera HTTP `Authorization: Bearer <token>` en cada petición posterior a endpoints protegidos.
- **Expiración:** Los tokens tienen una fecha de caducidad configurada en el servidor (ej. 1 hora). Una vez expirados, el cliente debe volver a autenticarse, minimizando el riesgo si el token es interceptado.

## 3. Matriz de Permisos por Rol

El sistema implementa Control de Acceso Basado en Roles (RBAC):

| Operación             | Endpoint                | Método HTTP | Rol Requerido |
| :-------------------- | :---------------------- | :---------- | :------------ |
| Login                 | `/api/v1/login`         | POST        | _Público_     |
| Consultar Usuarios    | `/api/v1/usuarios`      | GET         | USER, ADMIN   |
| Crear Usuarios        | `/api/v1/usuarios`      | POST        | ADMIN         |
| Borrar Usuarios       | `/api/v1/usuarios/{id}` | DELETE      | ADMIN         |
| Consultar Incidencias | `/api/v1/incidencias`   | GET         | USER, ADMIN   |

## 4. Buenas Prácticas Mínimas (Entorno Formativo vs Producción)

Aunque en este entorno formativo se han simplificado ciertos aspectos para facilitar el desarrollo, el salto a producción requeriría:

1.  **Uso de HTTPS:** Todo tráfico HTTP debe ir cifrado mediante TLS (HTTPS) para evitar ataques _Man-in-the-Middle_ y el robo de tokens en tránsito.
2.  **No hardcodear credenciales:** Las credenciales de acceso a la BD o claves secretas para firmar tokens deben residir en variables de entorno o gestores de secretos.
3.  **JSON Web Tokens (JWT):** Evolucionar el sistema de tokens UUID en memoria a JWT, permitiendo una validación descentralizada y firmada criptográficamente.
4.  **Mensajes de Error Seguros:** Evitar devolver trazas de pila (stack traces) o detalles internos de la base de datos al cliente en los códigos 500 para evitar la fuga de información (Information Disclosure).
