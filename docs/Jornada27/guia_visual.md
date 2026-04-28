# Guía Visual Básica (UI Kit)

Se ha establecido un sistema visual coherente apoyado en variables CSS (`:root`) para garantizar un mantenimiento rápido:

1. **Paleta de Colores:**
   - **Primario:** `#0056b3` (Azul corporativo, transmite seguridad y profesionalidad técnica).
   - **Fondo:** `#e9ecef` (Gris muy claro, reduce la fatiga visual frente al blanco puro).
   - **Texto Principal:** `#333333` (Mejora el contraste y la legibilidad).

2. **Tipografía:**
   - Se utiliza `system-ui, sans-serif` para cargar la fuente nativa del sistema operativo del usuario. Esto mejora drásticamente los tiempos de carga y asegura un renderizado perfecto de las letras.

3. **Espaciados y Componentes:**
   - Espaciado modular basado en `rem` (0.5rem, 1rem, 2rem).
   - Bordes redondeados sutiles (`6px`) en tarjetas y botones para suavizar la interfaz.
   - Distribución usando `Flexbox` en navegación y `CSS Grid` en las tarjetas del Dashboard para lograr un diseño fluido.
