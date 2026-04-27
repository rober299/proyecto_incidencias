# Estructura del Frontend y Justificación Arquitectónica

Para cumplir con el criterio de "estructura escalable" y preparar el proyecto para futuras integraciones, el frontend se ha dividido en las siguientes carpetas dentro del directorio raíz `frontend/`:

- `/vistas/`: Contiene exclusivamente los archivos `.html`. Separa el esqueleto y el contenido semántico de la lógica y el diseño.
- `/css/`: Destinada a las hojas de estilo modulares. Separar el CSS del HTML evita código espagueti y facilita el rediseño.
- `/js/`: Contendrá los scripts de validación cliente y peticiones asíncronas (AJAX/Fetch) en jornadas posteriores.
- `/assets/`: Reservada para recursos estáticos invariables como logotipos, tipografías e iconografía.

Esta arquitectura basada en separación de responsabilidades (SoC - Separation of Concerns) garantiza que el proyecto sea mantenible a largo plazo y que distintos desarrolladores puedan trabajar en estilos y estructura sin generar conflictos en Git.
