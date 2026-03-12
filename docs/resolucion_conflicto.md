# Documento de Resolución de Conflicto Controlado

### 1. Descripción del Conflicto

Se ha simulado un escenario donde dos ramas independientes (`feature/cambio-a` y `feature/cambio-b`) han creado y modificado la misma línea del mismo archivo (`archivo_conflicto.txt`).
Al intentar fusionar la segunda rama hacia `desarrollo`, Git ha detenido el proceso. Debido a que el archivo se generó mediante el comando de consola en Windows (PowerShell), se creó con una codificación que Git interpretó como un archivo binario (`warning: Cannot merge binary files`), impidiendo la fusión automática de texto.

### 2. Pasos Seguidos para la Resolución

1. Se identificó el error en la consola, que indicaba la imposibilidad de fusionar el archivo binario automáticamente.
2. Al no disponer de las marcas de conflicto estándar de texto de Git (`<<<<<<< HEAD`), se abrió el archivo directamente en el editor Visual Studio Code.
3. Para cumplir con el criterio de "no pérdida de información", se reescribió manualmente el archivo para que contuviera las líneas exactas aportadas por los dos programadores

### 3. Resultado Final

El archivo se guardó con el contenido íntegro de ambas ramas. Posteriormente, se forzó la resolución del conflicto añadiendo el archivo al área de preparación (`git add archivo_conflicto.txt`) y ejecutando un commit de cierre (`git commit -m "fix: resuelve conflicto manualmente conservando ambas lineas"`). Las ramas temporales se han eliminado, dejando la rama `desarrollo` estable.
