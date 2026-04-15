# Análisis de Lógica de Negocio y Transacciones (Jornada 20)

Este documento justifica la implementación de Vistas, Procedimientos, Triggers y Transacciones, distinguiendo responsabilidades entre capas.

## 1. Distinción de Capas: ¿BD o Aplicación?

- **Lógica en Base de Datos (SQL):** Se utiliza estrictamente para proteger la integridad de los datos (Triggers de auditoría), acelerar lecturas masivas (Vistas precalculadas) y asegurar atomicidad en operaciones de múltiples tablas (Transacciones).
- **Lógica en Aplicación (Backend - NodeJS/Java):** Se encargará del envío de emails, validación de formatos de texto, autenticación de usuarios y cálculos de negocio complejos que requieran consumir APIs externas.
- **Criterio de Aceptación:** _No se han usado triggers confusos_. El trigger `trg_auditoria_estado` aporta un valor crítico: impide que un desarrollador desde la capa de aplicación cambie un estado "silenciosamente" olvidando registrarlo. La BD se autoprotege.

## 2. Caso Transaccional Simulado (Commit vs Rollback)

- **Escenario:** Dar de baja un equipo dañado y cerrar simultáneamente su ticket de soporte.
- **El Riesgo:** Si la aplicación falla a la mitad (ej. caída de red tras dar de baja el equipo pero antes de cerrar el ticket), tendríamos datos huérfanos e inconsistentes.
- **La Solución (Atomicidad):** Se envuelve en un bloque `START TRANSACTION`.
- **Resultado del `ROLLBACK`:** Durante la simulación en `05_transaccion_simulada.sql`, el rollback deshizo la baja del equipo y el cierre del ticket exitosamente al simular una interrupción, manteniendo la base de datos en su estado original íntegro.

## 3. Elementos Implementados

- **Vista `v_panel_tecnico`:** Abstrae los JOINs complejos para que la aplicación cargue el dashboard rápidamente.
- **Procedimiento `sp_cerrar_incidencia`:** Encapsula dos operaciones (UPDATE y un INSERT de auditoría) en una sola llamada desde el backend.
