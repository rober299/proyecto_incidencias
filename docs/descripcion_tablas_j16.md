# Descripción Funcional y Diagrama ER (Versión 1)

## Justificación del Diseño

El modelo relacional soporta el dominio completo:

- **Maestras (`ROLES`, `CATEGORIAS`, `ACTIVOS`):** Normalizan los datos evitando que el usuario introduzca textos libres propensos a errores.
- **Seguridad (`USUARIOS`, `AUDITORIA`):** Garantizan el control de acceso y la trazabilidad inmutable de acciones críticas.
- **Transaccional (`INCIDENCIAS`, `COMENTARIOS`, `ADJUNTOS`):** Orquestan el ciclo de vida del ticket, permitiendo relacionar al creador, al técnico asignado y el equipo afectado, albergando la comunicación íntegra.

## Diagrama Entidad-Relación

![Diagrama Entidad-Relación Versión 1](../evidencias/semana_4/jornada_2026-04-06/diagrama_er_v1_j16.png)
