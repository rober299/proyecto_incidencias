# Diagrama de Casos de Uso

```mermaid
graph LR
    %% Actores
    U((Usuario Estándar))
    T((Técnico))
    A((Administrador))

    %% Casos de Uso
    C1(Crear Incidencia)
    C2(Ver mis tickets)
    C3(Comentar ticket)

    T1(Ver todos los tickets)
    T2(Autoasignar ticket)
    T3(Cambiar estado y cerrar)

    A1(Gestionar Usuarios)
    A2(Gestionar Categorías)

    %% Relaciones Usuario
    U --> C1
    U --> C2
    U --> C3

    %% Relaciones Técnico
    T --> T1
    T --> T2
    T --> T3
    T --> C3

    %% Relaciones Administrador
    A --> A1
    A --> A2
    A --> T1
```
