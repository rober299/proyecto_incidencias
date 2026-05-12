# Listado de Índices y Justificación

Para garantizar el rendimiento de la aplicación al escalar, se han introducido índices útiles sin abusar, evitando sobrecargar las operaciones de inserción:

1. **`idx_incidencias_estado` (Tabla INCIDENCIAS, campo `estado`):**
   - **Justificación:** El filtro más común en el dashboard será ver las incidencias "Abiertas" o "En Progreso". Acelera drásticamente la carga de las vistas principales.
2. **`idx_incidencias_tecnico` (Tabla INCIDENCIAS, campo `id_tecnico`):**
   - **Justificación:** Los técnicos filtrarán constantemente su propia cola de trabajo (tickets asignados a ellos).
3. **`idx_incidencias_prioridad` (Tabla INCIDENCIAS, campo `prioridad`):**
   - **Justificación:** Permite ordenar rápidamente los listados para atender primero los problemas críticos (SLA).
4. **`idx_incidencias_fecha` (Tabla INCIDENCIAS, campo `fecha_creacion`):**
   - **Justificación:** Vital para la generación de reportes y exportaciones mensuales que los administradores solicitarán.
5. **`idx_usuarios_activo` (Tabla USUARIOS, campo `activo`):**
   - **Justificación:** Puesto que usamos borrado lógico, el login verificará continuamente si el usuario está activo (`WHERE email = ? AND activo = true`).
