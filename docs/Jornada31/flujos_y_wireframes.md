# Mapa de Flujos de Usuario y Wireframes

## Mapa de Flujo Principal (Técnico)

```mermaid
    A[Pantalla Login] -->|Credenciales OK| B(Bandeja de Trabajo)
    B -->|Clic en 'Nueva'| C[Formulario Alta Incidencia]
    C -->|Guardar| B
    B -->|Clic en 'Ver Detalles'| D[Detalle de Incidencia]
    D -->|Acción: Resolver| E[Cierre de Incidencia]
    E --> B
```

## Wireframes Funcionales

### Pantalla 1: Bandeja de Trabajo (Dashboard Técnico)

```text
+-----------------------------------------------------------------------+
|  [Logo] Gestión IT                                   [Perfil] [Salir] |
+-----------------------------------------------------------------------+
|                                                                       |
|  BANDEJA DE ENTRADA                            [+ Nueva Incidencia]   |
|  -------------------------------------------------------------------  |
|  Filtros: ( ) Todas  (x) Mis Asignadas  ( ) Críticas                  |
|                                                                       |
|  +-----------------------------------------------------------------+  |
|  | #101 | Fallo conexión VPN            | [ALTA] | [En Progreso]   |  |
|  | Solicitante: Ana Gómez | Hace 2h     |        | [Ver Detalles]  |  |
|  +-----------------------------------------------------------------+  |
|                                                                       |
|  +-----------------------------------------------------------------+  |
|  | #102 | Impresora sin tóner           | [BAJA] | [Abierto]       |  |
|  | Solicitante: Carlos P. | Hace 5h     |        | [Ver Detalles]  |  |
|  +-----------------------------------------------------------------+  |
|                                                                       |
+-----------------------------------------------------------------------+
```

### Pantalla 2: Detalle de Incidencia

```text
+-----------------------------------------------------------------------+
|  [Logo] Gestión IT                                  [Volver al listado]|
+-----------------------------------------------------------------------+
|                                                                       |
|  INCIDENCIA #101: Fallo conexión VPN                                  |
|  Estado actual: [En Progreso v]    Prioridad: [Alta v]                |
|  -------------------------------------------------------------------  |
|                                                                       |
|  [ DATOS DEL REPORTE ]                                                |
|  Descripción: El cliente Cisco AnyConnect devuelve el error 404...    |
|  Usuario: Ana Gómez (ana@empresa.com)                                 |
|                                                                       |
|  [ HISTORIAL Y RESOLUCIÓN ]                                           |
|  +-----------------------------------------------------------------+  |
|  | Añadir nuevo comentario o nota de resolución...                 |  |
|  |                                                                 |  |
|  |                                      [Guardar] [CERRAR TICKET]  |  |
|  +-----------------------------------------------------------------+  |
+-----------------------------------------------------------------------+
```
