# Instrucciones de Compilación y Prueba (Jornada 15)

Para garantizar un proceso de _build_ reproducible e independiente del IDE (Eclipse, IntelliJ, VSCode), se ha creado un script de automatización (`build.bat`).

## Requisitos Previos

1. Tener instalado el Java Development Kit (JDK 17 o superior).
2. Tener configurada la variable de entorno `JAVA_HOME`.
3. Tener descargado el código fuente del proyecto.

## Cómo ejecutar una reconstrucción limpia

1. Abrir la terminal de Windows (CMD o PowerShell) en el directorio raíz del proyecto.
2. Ejecutar el script tecleando:
   `.\build.bat`
3. El sistema realizará tres acciones automáticamente:
   - Eliminación de archivos `.class` antiguos.
   - Recompilación de todo el directorio `src\`.
   - Ejecución del framework de pruebas unitarias (`TestTDD`).
4. Si el proceso finaliza sin errores de sintaxis y con todos los tests en verde, se mostrará el mensaje `[BUILD CORRECTO]`.
