# Sistema Integral de Gestión de Incidencias y Activos Tecnológicos

## 🎯 Propósito del Proyecto

El objetivo de este proyecto es desarrollar una aplicación integral para la gestión de incidencias y activos tecnológicos. El sistema permitirá la administración de usuarios y roles, así como la creación, asignación y seguimiento de tickets, gestión de categorías, adjuntos y control de estados a través de un panel de seguimiento.

## 💻 Stack Tecnológico

Para el desarrollo de los distintos módulos del proyecto se utilizarán las siguientes tecnologías principales:

- **Backend (API):** Java 21 (JDK) desarrollado en IntelliJ IDEA.
- **Frontend (Web):** Tecnologías web desarrolladas en Visual Studio Code.
- **Móvil:** Aplicación Android desarrollada en Android Studio.
- **Base de Datos:** MySQL (con MySQL Workbench).
- **Herramientas de prueba y control:** Postman para peticiones API y Git para el control de versiones.

## 📏 Reglas de Nomenclatura

Para mantener un código limpio, ordenado y escalable, se seguirán las siguientes convenciones en el proyecto:

1. **Estructura de Carpetas y Archivos:**
   - Las carpetas raíz se escribirán siempre en **minúsculas** (ej. `/api`, `/frontend`).
   - Los archivos genéricos y de documentación usarán minúsculas o _kebab-case_ (ej. `guia-instalacion.md`).

2. **Código Backend (Java):**
   - **Clases e Interfaces:** `PascalCase` (ej. `GestorIncidencias`, `UsuarioController`).
   - **Métodos y Variables:** `camelCase` (ej. `crearTicket()`, `numeroIncidencia`).
   - **Constantes:** `SCREAMING_SNAKE_CASE` (ej. `MAX_INTENTOS`, `ESTADO_ACTIVO`).

3. **Base de Datos (MySQL):**
   - **Tablas y Columnas:** `snake_case` y en singular (ej. `usuario`, `fecha_creacion`, `id_ticket`).

4. **Control de Versiones (Git):**
   - Los mensajes de commit deberán ser descriptivos, estar en minúsculas y usar el formato: `tipo: mensaje corto` (ej. `feat: añade login de usuario`, `fix: corrige error en conexión a bd`).
