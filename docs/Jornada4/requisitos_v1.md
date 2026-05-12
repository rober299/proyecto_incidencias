# Documento de Requisitos v1 - Sistema de Gestión de Incidencias

## 1. Resumen Funcional del Proyecto (Definición)

El proyecto integrador consiste en el desarrollo de una aplicación web para la gestión interna de incidencias y activos tecnológicos de una empresa. El objetivo principal es centralizar y agilizar el soporte técnico, permitiendo a los empleados reportar problemas, y al departamento de TI gestionarlos eficientemente.
El alcance del proyecto, pensado para ejecutarse en un plazo realista de 3 meses, incluye la gestión de usuarios mediante roles, creación y seguimiento de tickets, categorización de incidencias, adjuntos básicos y un panel de control (dashboard) para medir los tiempos de resolución.

---

## 2. Requisitos del Sistema

### 2.1 Requisitos Funcionales

- **RF01 - Gestión de Usuarios:** El sistema permitirá el alta, modificación y baja lógica de usuarios, así como el login con credenciales.
- **RF02 - Creación de Incidencias:** Los usuarios podrán crear tickets indicando título, descripción, categoría, prioridad y adjuntar un archivo (imagen o documento).
- **RF03 - Asignación y Seguimiento:** Los tickets podrán ser asignados a un técnico específico. El sistema registrará la fecha de creación, actualización y cierre.
- **RF04 - Cambio de Estado:** Los técnicos podrán actualizar el estado del ticket a lo largo de su ciclo de vida.
- **RF05 - Consultas y Filtros:** Existirá un panel donde se podrán filtrar los tickets por estado, prioridad, creador o técnico asignado.
- **RF06 - Exportaciones:** El sistema permitirá exportar el listado de incidencias filtradas a formato CSV.

### 2.2 Requisitos No Funcionales

- **RNF01 - Seguridad Básica:** Las contraseñas se almacenarán encriptadas (ej. bcrypt). Solo los usuarios autenticados podrán acceder al sistema.
- **RNF02 - Rendimiento:** El tiempo de carga del panel de seguimiento no deberá superar los 2 segundos con un volumen de 10.000 incidencias en base de datos.
- **RNF03 - Disponibilidad y Backups:** El sistema debe contar con un script de copias de seguridad de la base de datos automatizado y documentado.
- **RNF04 - Usabilidad:** La interfaz deberá ser _responsive_ para poder consultarse correctamente desde tablets y monitores de escritorio.

---

## 3. Reglas de Negocio

Las siguientes reglas controlan la lógica principal del flujo de la aplicación:

1. **Gestión de Roles:**
   - **Usuario Estándar:** Solo puede crear tickets, ver sus propios tickets y añadir comentarios.
   - **Técnico:** Puede ver todos los tickets, autoasignárselos, cambiar sus estados y cerrarlos.
   - **Administrador:** Tiene acceso total, gestiona usuarios y activos tecnológicos.
2. **Datos Obligatorios:** Para que un ticket sea válido, debe contener obligatoriamente: Título, Descripción, Categoría y Prioridad.
3. **Transiciones de Estado Válidas:** El flujo normal de un ticket debe ser: `Abierto` -> `En Progreso` -> `Resuelto` -> `Cerrado`.
   - _Excepción:_ Un ticket puede pasar a `Cancelado` solo desde `Abierto`.
4. **Control de Errores:** Si un usuario intenta subir un adjunto mayor a 5MB o con un formato no permitido (ej. `.exe`), el sistema bloqueará la subida y mostrará un mensaje de error.
   - No se puede cerrar un ticket sin que el técnico haya añadido un comentario de resolución.
