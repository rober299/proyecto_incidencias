# Manual de Uso de Scripts de Automatización

Este documento explica el funcionamiento de los scripts de automatización creados para el proyecto. Ambos scripts deben ejecutarse desde la raíz del proyecto.

### 1. Script de Copia de Seguridad (`backup.bat`)

- **Propósito:** Generar un archivo `.zip` con el código fuente del proyecto para tener un respaldo seguro.
- **Instrucciones de uso:** Hacer doble clic sobre `backup.bat` o ejecutarlo desde la terminal escribiendo `.\backup.bat`.
- **Concepto técnico aplicado:** Este script utiliza **rutas relativas** (ej. `.\api`, `.\docs`). La ruta relativa busca los archivos tomando como punto de partida la carpeta donde estamos ubicados actualmente, sin importar en qué disco duro o ruta completa esté el proyecto.

### 2. Script de Carpeta Diaria (`carpeta_diaria.bat`)

- **Propósito:** Crear automáticamente una subcarpeta dentro de `/evidencias` con la fecha del día actual (formato YYYY-MM-DD) para organizar las capturas.
- **Instrucciones de uso:** Hacer doble clic sobre `carpeta_diaria.bat` o ejecutarlo desde la terminal escribiendo `.\carpeta_diaria.bat`.
- **Concepto técnico aplicado:** Este script utiliza **rutas absolutas**. A través de la variable del sistema `%cd%`, el script averigua la ruta completa y exacta desde la raíz del disco duro (ej. `C:\Users\rober\proyecto_incidencias\evidencias\...`) garantizando que la carpeta se cree siempre en el lugar físico exacto.
