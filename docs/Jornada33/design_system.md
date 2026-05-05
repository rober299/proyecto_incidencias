# Documentación del Mini 'Design System'

Para garantizar la mantenibilidad del código y la coherencia visual en todo el frontend web, se ha implementado un mini Sistema de Diseño (Design System).

## 1. Variables Globales

Ubicadas en `:root` dentro de `design_system.css`. Definen la paleta de colores corporativa y espaciados estandarizados.

- Ventaja: Si el cliente decide cambiar el color principal de azul a rojo, solo se cambia en una línea (`--color-primary`) y se actualiza toda la web al instante.

## 2. Clases Utilitarias

Se ha usado una convención simple prefijando con `ui-` los componentes globales:

- `.ui-card`: Estructura estándar para bloques de información.
- `.ui-badge`: Etiquetas de estado que cambian de color según su clase adjunta (`.abierto`, `.progreso`, `.cerrado`).
- `.ui-table`: Diseño de tabla limpio y responsivo.

## 3. Biblioteca de Componentes UI (JavaScript)

En `componentes.js` se ha abstraído el HTML. En lugar de escribir etiquetas `<div>` o `<span>` repetidas veces, las vistas invocan funciones JS puras:

- `UI.crearTarjetaStat(titulo, valor, icono)`
- `UI.crearBadge(estado)`
- `UI.crearFilaTabla(id, titulo, estado, prioridad)`

**Beneficio principal:** Reducción drástica del código duplicado (DRY - _Don't Repeat Yourself_). Si el día de mañana queremos que las etiquetas (badges) sean cuadradas en lugar de redondeadas, solo modificamos la función en `componentes.js` y el cambio se propaga a todos los dashboards y listados del sistema de forma automática.
