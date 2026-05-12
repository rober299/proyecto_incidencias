# Guía Interna de Uso de Git y Flujo de Trabajo

Esta guía define las normativas de control de versiones para el desarrollo del Proyecto de Incidencias.

## 1. Estrategia de Ramas (Branching)

Utilizamos un modelo basado en ramas funcionales:

- **`main`**: Es la rama de producción. El código aquí debe ser 100% estable y funcional. Nunca se programa directamente en `main`.
- **`desarrollo`**: Es la rama de integración. Aquí se unen todas las nuevas funcionalidades antes de pasar a producción.
- **Ramas funcionales (`feature/...` o `fix/...`)**: Cada tarea nueva o corrección se hace en una rama temporal que nace de `desarrollo`.

## 2. Cuándo abrir rama y cuándo fusionar

- **Abrir rama nueva:** Siempre que vayas a empezar una tarea, funcionalidad o corrección nueva. El nombre de la rama debe ser descriptivo (ej: `feature/login`, `fix/error-base-datos`).
- **Fusionar (Merge):** Solo se fusiona una rama funcional hacia `desarrollo` cuando la tarea está completamente terminada y probada. Una vez fusionada, la rama temporal se puede eliminar.

## 3. Formato de Commits (Conventional Commits)

Los mensajes de commit deben ser claros, en minúscula y seguir esta estructura: `<tipo>: <descripción corta>`.

**Tipos válidos:**

- `feat:` Para nuevas características o funcionalidades.
- `fix:` Para solucionar errores (bugs).
- `docs:` Para cambios exclusivos de documentación (ej. README).
- `chore:` Para tareas de mantenimiento o configuración (ej. .gitignore).

## 4. Ejemplos Prácticos

### Ejemplos Incorrectos (No permitidos):

- `git commit -m "subiendo cosas"` _(No describe qué "cosas" son)_
- `git commit -m "arreglado"` _(No dice qué se ha arreglado)_
- `git commit -m "feat: login terminado y arreglo en la base de datos y cambio color del boton"` _(Demasiadas cosas en un solo commit, deberían ser tres separados)_

### Ejemplos Correctos (Obligatorios):

- `git commit -m "feat: añade formulario de inicio de sesión"`
- `git commit -m "fix: corrige error de conexión a MySQL"`
- `git commit -m "docs: actualiza guía de instalación en README"`
