# Listado de Validaciones Implementadas (JavaScript)

Se han aplicado las siguientes validaciones DOM en los formularios principales:

**1. Formulario de Login (`login.html`):**

- _Correo electrónico:_ Campo obligatorio. Verificación de formato usando Expresiones Regulares (Regex).
- _Contraseña:_ Campo obligatorio (no vacío).

**2. Formulario de Alta (`alta.html`):**

- _Título:_ Longitud mínima de 5 caracteres.
- _Categoría:_ Obligatorio seleccionar una opción válida (no vacía).
- _Descripción:_ Longitud mínima de 10 caracteres para garantizar contexto útil a los técnicos.

**Manipulación DOM asociada:**

- Adición de bordes rojos a los inputs que fallan (`.input-error`).
- Inyección de mensajes de error explicativos dinámicamente en etiquetas `<span>`.
- Aparición de panel verde de éxito (`#panel-exito`) si la validación pasa, reseteando el formulario completo de manera automática.
