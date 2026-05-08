# Tabla Terminológica de la Interfaz

Para garantizar la consistencia visual y cognitiva de los usuarios, se han definido los siguientes términos oficiales a utilizar en todo el proyecto. Queda terminantemente prohibido usar sinónimos o literales alternativos.

| Concepto Técnico   | Término Oficial (Español) | Término Oficial (Inglés) | Términos a Evitar (Incorrectos) |
| :----------------- | :------------------------ | :----------------------- | :------------------------------ |
| Elemento principal | **Incidencia**            | Issue                    | Ticket, Problema, Queja, Fallo  |
| Pantalla de inicio | **Panel de Control**      | Dashboard                | Home, Inicio, Resumen           |
| Acción de guardar  | **Crear Incidencia**      | Create Issue             | Añadir, Guardar, Enviar Ticket  |
| Listado general    | **Ver Incidencias**       | View Issues              | Lista, Catálogo, Todas          |

## Limpieza de Frontend (Mantenibilidad)

Durante la Jornada 35 se ha revisado el código HTML para:

- Reemplazar "Crear Ticket" por "Crear Incidencia".
- Extraer literales quemados en el HTML y sustituirlos por atributos `data-i18n`.
- Establecer un diccionario `i18n.js` que facilita a los traductores modificar textos sin tocar la estructura del DOM.
