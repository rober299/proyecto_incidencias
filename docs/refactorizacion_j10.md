# Informe de Depuración y Calidad - Jornada 10

## 1. Listado de Errores Detectados y Corregidos (Bloques 1 y 2)

Mediante la ejecución y el depurador, se localizaron los siguientes defectos en `GestorIncidencias`:

1. **Bug lógico (Validación errónea):** El sistema aceptaba IDs negativos porque no había condicionales de control.
2. **Excepción (NullPointerException):** La llamada a `titulo.trim()` reventaba si el título venía a `null`.
3. **Excepción (ArithmeticException):** La división `100 / urgencia` colapsaba el programa si la urgencia era 0.
4. **Ruido en consola:** Uso de `System.out.println` sin formato, dificultando la trazabilidad.

## 2. Resumen de Técnicas de Depuración Utilizadas

Para cazar el origen de las excepciones en lugar de solo ver el síntoma, se ha utilizado el depurador integrado de VS Code:

- **Breakpoints (Puntos de interrupción):** Para pausar la ejecución milisegundos antes de la línea conflictiva (ej. la división por cero).
- **Inspección de variables:** Observación en memoria de los valores exactos (`urgencia = 0`, `titulo = null`) para entender por qué iba a fallar el código.
- **Pila de llamadas (Call Stack):** Para trazar qué método había invocado a la función rota.

## 3. Refactorización Aplicada: Antes y Después (Bloques 3 y 4)

- **Diff / Cambio principal:** Se ha aplicado la técnica de **"Extracción de método"**.
  - _Antes:_ La lógica de validación brillaba por su ausencia o estaba mezclada con el cálculo de la incidencia.
  - _Después:_ Se ha creado un método privado `esValida(id, titulo, urgencia)` que centraliza todas las comprobaciones. Si algo falla, se aborta limpiamente con un `return;` sin lanzar excepciones.
- **Logging:** Se ha sustituido `System.out` por una utilidad propia `SimpleLogger` que inyecta un _timestamp_ y el nivel de severidad (`INFO`, `DEBUG`, `ERROR`).

## 4. Conclusiones de Mejora de Calidad

1. **Robustez:** La aplicación ya no se cierra abruptamente por datos de entrada sucios.
2. **Mantenibilidad:** Al separar la validación del procesamiento (Principio de Responsabilidad Única), es más fácil añadir nuevas reglas en el futuro.
3. **Trazabilidad:** El nuevo formato de logs permite filtrar visualmente los errores reales frente a los mensajes de pura información.
