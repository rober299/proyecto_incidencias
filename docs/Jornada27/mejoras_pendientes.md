# Listado de Mejoras UI Pendientes

Tras la inspección del DOM con las herramientas de desarrollador (DevTools), se proponen las siguientes iteraciones futuras para mejorar la Interfaz de Usuario:

1. **Feedback Visual Avanzado:** Implementar estados `:focus-visible` más accesibles en los inputs y mensajes tipo "Toast" para cuando se guarda una incidencia.
2. **Iconografía:** Integrar una librería ligera (como FontAwesome o iconos SVG) para acompañar los menús y los botones de acción en la tabla.
3. **Paginación en Tablas:** El diseño de la tabla actual funciona, pero requerirá estilos específicos para un componente de paginación numérico cuando el volumen de incidencias crezca.
4. **Modo Oscuro (Dark Mode):** Al haber estructurado los colores con variables CSS (`:root`), la futura implementación de un tema oscuro será trivial aplicando un selector `[data-theme="dark"]`.
