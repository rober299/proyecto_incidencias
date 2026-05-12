# Guía de Estándares del Proyecto

Para garantizar la mantenibilidad y un proyecto gobernable, todo el código debe ceñirse a las siguientes convenciones:

## 1. Convenciones de Nomenclatura

- **Clases y Modelos:** `PascalCase` (ej. `UsuarioController`, `TicketModel`).
- **Variables y Funciones:** `camelCase` (ej. `obtenerTickets()`, `listaUsuarios`).
- **Paquetes y Directorios:** Todo en minúsculas y sin espacios (ej. `src/controllers`, `docs`).
- **Base de Datos (Tablas y Columnas):** `snake_case` y en plural para tablas (ej. `usuarios`, `tickets_adjuntos`, `fecha_creacion`).

## 2. Estándares de Arquitectura y Diseño

- **Endpoints (API REST):** Usar sustantivos en plural, minúsculas y métodos HTTP correctos.
  - BIEN: `GET /api/v1/tickets` | `POST /api/v1/usuarios`
  - MAL: `GET /api/v1/getTickets` | `POST /api/v1/crearUsuario`
- **Estilos CSS:** Se utilizará la metodología **BEM** (Block Element Modifier) para evitar conflictos de clases (ej. `boton`, `boton__icono`, `boton--activo`).

## 3. Estructura de Documentación y Pruebas

- **Documentación:** El código complejo debe llevar comentarios formato Javadoc/Docstring. Todo cambio arquitectónico se registra en `docs/`.
- **Nomenclatura de Pruebas (Tests):** Formato descriptivo `Deberia_HacerX_Cuando_OcurreY` (ej. `Deberia_DevolverError_Cuando_PasswordEsCorta()`).
