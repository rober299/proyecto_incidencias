# Estrategia de Sincronización y Resolución de Conflictos

## 1. Estrategia de Sincronización

La aplicación implementa una estrategia de **"Caché Primero, Red en Segundo Plano"** (Cache-First, Network-Background). Esto garantiza que el usuario nunca vea una pantalla de carga vacía si ya ha utilizado la aplicación anteriormente.

### Flujo de ejecución:

1. **Inicio:** Al abrir la vista de listado, la app consulta inmediatamente las `SharedPreferences` en busca de la clave `CACHE_INCIDENCIAS`.
2. **Lectura Local:** Si existe caché, se deserializa el JSON y se renderiza la interfaz al instante.
3. **Lectura Remota (Silenciosa):** Simultáneamente, se lanza un hilo secundario que solicita los datos frescos al servidor (`GET /api/v1/incidencias`).
4. **Invalidación/Actualización:** - Si el servidor responde con `200 OK`, el nuevo JSON sobrescribe la caché antigua y la interfaz se repinta automáticamente.
   - Si no hay conexión o el servidor falla, se mantiene la caché y se notifica al usuario mediante un `Toast` ("Modo sin conexión: Mostrando caché local").

### Tabla de Decisiones de Sincronización

| Estado de Red    | Estado de Caché Local     | Acción del Sistema                                                            | Experiencia del Usuario                                          |
| :--------------- | :------------------------ | :---------------------------------------------------------------------------- | :--------------------------------------------------------------- |
| **Conectado**    | Vacía (Primera vez)       | Bloquea UI con `ProgressBar` hasta descargar datos. Guarda caché.             | Ve indicador de carga seguido de los datos frescos.              |
| **Conectado**    | Llena (Visita recurrente) | Muestra caché al instante. Descarga en 2º plano. Sobrescribe caché y repinta. | Carga instantánea. Los datos se actualizan solos si hay cambios. |
| **Desconectado** | Llena                     | Muestra caché. Falla petición HTTP. Muestra alerta de modo offline.           | Carga instantánea. Sabe que está viendo datos antiguos.          |
| **Desconectado** | Vacía                     | Falla petición HTTP. Muestra alerta de error de conexión.                     | Pantalla vacía con aviso de error. Necesita internet la 1ª vez.  |

---

## 2. Riesgos y Conflictos Potenciales

El modelo actual es unidireccional (Servidor -> Móvil) para lectura. Sin embargo, al introducir operaciones de escritura (crear o editar incidencias) en modo offline, nos enfrentaríamos a los siguientes conflictos:

- **Riesgo de Datos Obsoletos (Stale Data):** Si el usuario se queda sin red durante horas, podría estar tomando decisiones basadas en incidencias que ya han sido cerradas por otro técnico desde la plataforma web.
- **Condición de Carrera (Race Condition):** Si el usuario en modo offline intenta actualizar una incidencia y, simultáneamente, otro técnico la modifica en el servidor. Al recuperar la conexión, el móvil sobrescribiría el trabajo del compañero sin saberlo (Last-Write-Wins).
- **Falsos Positivos en Escritura:** Si la app permitiera "Crear Incidencia" sin conexión guardándola en caché, el usuario creería que está reportada, pero si cierra la app o borra los datos antes de recuperar la red, esa incidencia se perdería (Data Loss).

**Solución a nivel formativo actual:** La persistencia local se limita estrictamente a la **lectura y mejora de rendimiento (caché)**. Cualquier operación de mutación (crear/editar) sigue requiriendo conexión directa al backend para evitar discrepancias de estado.
