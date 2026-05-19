# Documentación Técnica de la API - Gestión de Incidencias IT

Este documento detalla los endpoints disponibles en el sistema, las reglas de validación de negocio aplicadas en los DTOs y la estructura unificada de respuestas de error.

---

## Estructura Homogénea de Respuestas de Error

Toda petición que falle en el sistema por validación, falta de permisos o errores internos devolverá un código de estado HTTP semántico junto con un cuerpo JSON estructurado bajo el siguiente formato:

```json
{
  "timestamp": "AAAA-MM-DD HH:MM:SS",
  "status": 4XX_o_5XX,
  "error": "Tipo de Error Breve",
  "message": "Descripción detallada del fallo para el desarrollador",
  "path": "/api/v1/recurso"
}
```

---

## Catálogo de Endpoints y Matriz de Permisos

### 1. Autenticación (`LoginController`)

- **Endpoint:** `POST /api/v1/login`
- **Acceso:** Público (Sin Token).
- **Cuerpo de entrada (JSON):**
  ```json
  {
    "email": "usuario@empresa.com",
    "password": "password123"
  }
  ```
- **Respuestas del Servidor:**
  - **`200 OK`**: Devuelve el token de sesión válido.
  - **`400 Bad Request`**: Formato de credenciales inválido o campos vacíos.

---

### 2. Gestión de Usuarios (`UsuarioController`)

#### **GET /api/v1/usuarios**

- **Acceso:** Cualquier usuario autenticado (`ADMIN` o `USER`).
- **Respuesta exitosa:** **`200 OK`** con el listado completo de usuarios en JSON.

#### **GET /api/v1/usuarios/{id}**

- **Acceso:** Cualquier usuario autenticado (`ADMIN` o `USER`).
- **Respuestas del Servidor:**
  - **`200 OK`**: JSON con los datos del usuario real extraído de la base de datos.
  - **`400 Bad Request`**: Controlado globalmente si el ID especificado no existe en el sistema.

#### **POST /api/v1/usuarios**

- **Acceso:** Exclusivo para usuarios con rol `ADMIN`.
- **Reglas de Validación Técnicas (Capa de Entrada DTO):**
  - **`email`**: Campo obligatorio. No puede estar vacío y debe contener una arroba (`@`) y un punto (`.`).
  - **`password`**: Campo obligatorio. No puede estar vacío y requiere una longitud mínima de 6 caracteres.
- **Respuestas del Servidor:**
  - **`201 Created`**: Usuario registrado con éxito en la base de datos.
  - **`400 Bad Request`**: Lanzado por incumplimiento de las reglas de formato de email o longitud de contraseña.
  - **`403 Forbidden`**: Lanzado si un usuario autenticado con rol `USER` intenta realizar la acción.

#### **DELETE /api/v1/usuarios/{id}**

- **Acceso:** Exclusivo para usuarios con rol `ADMIN`.
- **Respuestas del Servidor:**
  - **`204 No Content`**: Borrado lógico completado con éxito en la base de datos (atributo modificado a inactivo).
  - **`400 Bad Request`**: Si no se especifica un ID numérico correcto o si el usuario no existe en la base de datos.
  - **`403 Forbidden`**: Lanzado si un usuario con rol `USER` intenta eliminar un recurso.
