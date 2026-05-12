# Experiencia y Conclusiones sobre TDD

## Histórico de pasos seguidos (Ciclo Red-Green-Refactor)

1. **Rojo (Fallo):** Se escribieron los tests en `TestTDD.java` definiendo las reglas de transición. Al no existir lógica en `ValidadorEstado.java` (devolvía `false` por defecto), los tests fallaron.
2. **Verde (Éxito):** Se programaron los `if` estrictamente necesarios en el validador para que los tests pasaran, sin añadir código extra ni sobreingeniería.
3. **Refactor:** Con la red de seguridad de los tests pasando en verde, se limpió el código extrayendo las condiciones a variables booleanas descriptivas (`deAbiertaAProgreso`, etc.) mejorando la legibilidad sin romper la funcionalidad.

## Lecciones Aprendidas y Reflexión Técnica

- **Ventajas:** Da muchísima seguridad. Al tener las pruebas hechas primero, sabes exactamente cuándo has terminado de programar (cuando todo está en verde). Evita escribir código "por si acaso" que luego no se usa.
- **Dificultades:** Cuesta cambiar la mentalidad. El impulso natural es escribir primero la solución y luego probarla. Además, pensar en todos los casos de prueba antes de programar requiere tener las reglas de negocio muy claras desde el minuto uno.
- **Aplicación en el proyecto:** \* _Mejor caso de uso:_ Lógica de negocio pura (cálculos de prioridad, validaciones de strings, algoritmos de asignación).
  - _Peor caso de uso:_ Interfaces gráficas (UI) o conexiones a bases de datos externas, ya que preparar el entorno de pruebas para eso es muy costoso y frena el desarrollo.
