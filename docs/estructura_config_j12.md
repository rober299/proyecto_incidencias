# Documentación del Fichero de Configuración (`app.properties`)

El fichero de configuración centraliza los parámetros variables del sistema para evitar tenerlos "hardcodeados" (escritos a fuego) en el código fuente de Java.

## Estructura de Claves

1. **Entorno y Red (`entorno`, `servidor.puerto`)**:
   - Define si el sistema se ejecuta en `desarrollo`, `test` o `produccion`.
   - Establece el puerto de escucha del servidor (por defecto `8080`).

2. **Rutas del Sistema (`rutas.exportacion`, `rutas.logs`)**:
   - Rutas relativas donde el programa guardará los ficheros CSV/JSON generados y los registros de ejecución.

3. **Comportamiento (`app.modo_depuracion`)**:
   - Booleano (`true`/`false`). Si está activo, el sistema imprimirá trazas detalladas para los desarrolladores.

4. **Valores por defecto (`defecto.incidencia.*`)**:
   - Valores que se asignarán automáticamente a una entidad si el usuario no los proporciona al crearla (ej. si no se indica estado, será `ABIERTA`).
