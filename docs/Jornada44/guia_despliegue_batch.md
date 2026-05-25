# Guía de Despliegue y Ejecución Batch (Servidor Remoto)

Para automatizar este proceso en un entorno de producción (ej. servidor Ubuntu Linux), no utilizaremos ejecución manual, sino el programador de tareas del sistema operativo: **Cron**.

## 1. Dependencias Requeridas

- Máquina Virtual (VPS) con Linux.
- Java Runtime Environment (JRE) instalado (`apt install default-jre`).
- El archivo compilado `.class` o el `.jar` del proyecto, junto con la librería de SQLite (`sqlite-jdbc.jar`).

## 2. Script Ejecutor (.sh)

Se debe crear un archivo llamado `ejecutar_exportacion.sh` en la ruta `/opt/gestion_it/batch/` con el siguiente contenido:

```bash
#!/bin/bash
# Script de ejecución del Batch
FECHA=$(date +"%Y%m%d")
DIRECTORIO_OUT="/var/backups/gestion_it/exportaciones/reporte_$FECHA.csv"

# Se ejecuta pasando la ruta dinámica como argumento y volcando el log a un txt
java -cp "/opt/gestion_it/build:/opt/gestion_it/lib/*" api.batch.ExportadorBatch "$DIRECTORIO_OUT" >> /var/log/gestion_it/batch_export.log 2>&1
```

## 3. Configuración del Horario (Crontab)

Para que se ejecute todas las madrugadas a las 02:00 AM, introducimos en el servidor el comando `crontab -e` y añadimos esta regla:

```text
# Minuto | Hora | Día_Mes | Mes | Día_Semana | Comando
0 2 * * * /bin/bash /opt/gestion_it/batch/ejecutar_exportacion.sh
```

## 4. Ventajas de este Diseño

- **Ejecución no interactiva:** Funciona 100% en segundo plano.
- **Trazabilidad:** Al redirigir la salida `>>` a un archivo `.log`, podemos auditar posibles errores de validación sin estar presentes.
- **Dinamismo:** El uso de argumentos externos permite cambiar la ruta de guardado sin necesidad de recompilar el código Java.
