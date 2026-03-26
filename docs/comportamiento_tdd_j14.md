# Definición de Comportamiento: Validador de Estados (TDD)

**Módulo seleccionado:** Validador de transiciones de estado de una incidencia.

**Comportamiento esperado (Reglas de negocio):**

1. Una incidencia `ABIERTA` solo puede pasar a `EN_PROGRESO`.
2. Una incidencia `EN_PROGRESO` puede pasar a `ESPERA` o a `CERRADA`.
3. Cualquier otra combinación (ej. `ABIERTA` a `CERRADA` directamente, o salir de `CERRADA`) se considera una transición INVÁLIDA.
