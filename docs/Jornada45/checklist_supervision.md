# Checklist Operativo de Supervisión y Soporte

Esta guía rápida (Troubleshooting) define los pasos que debe seguir el equipo de soporte Técnico (Nivel 1 y Nivel 2) ante comportamientos anómalos en el sistema de Gestión IT.

## 1. Si la API REST no responde (Timeouts o 502 Bad Gateway)

- [ ] **Verificar estado del proceso:** Comprobar si el proceso de Java está corriendo en el servidor (`ps aux | grep java`).
- [ ] **Revisar Logs de Aplicación:** Leer las últimas líneas de `/var/log/gestion_it/api_error.log` buscando excepciones de `OutOfMemoryError` o conexiones a BD rotas.
- [ ] **Conectividad a BD:** Lanzar un ping desde el servidor de la API hacia el servidor de Base de Datos para descartar caídas de red internas.
- [ ] **Acción Correctiva:** Si el proceso está muerto, reiniciar el servicio (`systemctl restart gestion_it_api`).

## 2. Si el Batch de exportación falla o se interrumpe

- [ ] **Revisar Logs de Ejecución:** Mirar el log de salida del script (`batch_export.log`) y buscar la etiqueta `[CRÍTICO]` o `[ERROR]`.
- [ ] **Verificar Permisos de Directorio:** Comprobar que el usuario del sistema que ejecuta el Cron tiene permisos de escritura en la carpeta de destino `/var/backups/gestion_it/`.
- [ ] **Comprobar espacio en disco:** Ejecutar `df -h` para asegurar que el disco no está al 100%.
- [ ] **Acción Correctiva:** Solucionar el bloqueo y lanzar el batch manualmente pasando la ruta por parámetro para recuperar el volcado perdido.

## 3. Si una Tarea Programada (Cron) no genera salida

- [ ] **Verificar servicio Cron:** Asegurar que el demonio `cron` está activo (`systemctl status cron`).
- [ ] **Revisar el registro del sistema:** Buscar ejecuciones fallidas en los logs del sistema (`grep CRON /var/log/syslog`).
- [ ] **Verificar rutas absolutas:** Confirmar que el archivo `.sh` dentro del crontab utiliza rutas absolutas hacia los binarios de Java, ya que Cron no carga las variables de entorno del usuario por defecto.
