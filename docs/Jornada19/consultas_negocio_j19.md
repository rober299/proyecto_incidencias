# Resumen de Consultas Orientadas al Negocio

Este documento mapea las preguntas funcionales de la empresa con las consultas SQL desarrolladas en el script de operaciones, justificando su lógica empresarial.

## 1. ¿Cuál es la carga de trabajo actual de nuestros técnicos?

- **Consulta SQL:** `SELECT u.email AS Tecnico, COUNT...`
- **Lógica:** Se realiza un `JOIN` entre Usuarios (filtrados por técnicos) e Incidencias, contando solo aquellas cuyo estado no sea "Cerrado". Esto permite al jefe de TI reasignar tickets si un técnico tiene un cuello de botella.

## 2. ¿Qué equipos nos están costando más dinero en mantenimiento?

- **Consulta SQL:** `SELECT a.codigo_inventario, COUNT... ORDER BY Numero_Averias DESC;`
- **Lógica:** Agrupa las incidencias vinculadas a un mismo `id_activo`. Un número alto aquí indica que el hardware está obsoleto y sale más rentable comprar uno nuevo que seguir pagando horas de técnico.

## 3. ¿Estamos cumpliendo los SLAs (Tiempos de respuesta)?

- **Consulta SQL:** `SELECT AVG(TIMESTAMPDIFF(HOUR, fecha_creacion, fecha_cierre))...`
- **Lógica:** Mide la distancia en horas entre la creación y el cierre del ticket. Vital para informes de rendimiento trimestrales.

## 4. ¿Qué usuarios tienen licencias/cuentas sin utilizar?

- **Consulta SQL:** `SELECT u.email FROM USUARIOS u LEFT JOIN INCIDENCIAS... WHERE i.id_incidencia IS NULL;`
- **Lógica:** Usa un `LEFT JOIN` para detectar usuarios que existen en la base de datos pero no tienen tickets asociados, lo que puede indicar cuentas que deberían darse de baja para ahorrar licencias.

## 5. Prevención de errores en BD (Updates Seguros)

- **Lógica:** Todos los `UPDATE` para cambiar estados exigen comprobar el estado previo en el `WHERE` (`WHERE estado = 'Abierto'`). Esto evita "condiciones de carrera" (ej. que dos técnicos intenten auto-asignarse el mismo ticket a la vez).
