# Matriz de Cobertura Funcional Mínima - Jornada 13

## Estado Actual de las Pruebas Unitarias

A continuación se detalla qué partes del núcleo del dominio están blindadas por el framework automatizado de pruebas y cuáles quedan pendientes para futuras iteraciones del desarrollo.

| Funcionalidad / Módulo      | Estado de Cobertura | Casos de Prueba Implementados                       | Prioridad para futuras iteraciones            |
| :-------------------------- | :------------------ | :-------------------------------------------------- | :-------------------------------------------- |
| **Cálculo de Prioridad**    | 🟢 Cubierto         | Valores normales y excepción por urgencia cero.     | Baja                                          |
| **Máquina de Estados**      | 🟢 Cubierto         | Transición prohibida (CERRADA -> EN_PROGRESO).      | Baja                                          |
| **Validación de Usuarios**  | 🟢 Cubierto         | Rechazo de nombres vacíos o nulos.                  | Baja                                          |
| **Seguridad y Permisos**    | 🟢 Cubierto         | Restricción de asignación de técnicos según el rol. | Baja                                          |
| **Persistencia (CSV/JSON)** | 🔴 Pendiente        | Ninguno (verificado manualmente en J12).            | Alta (Urge añadir tests de lectura/escritura) |
| **Filtrado de Colecciones** | 🔴 Pendiente        | Ninguno (verificado manualmente en J09).            | Media                                         |
| **Autenticación (Login)**   | 🔴 Pendiente        | Módulo aún no implementado en el sistema.           | Alta                                          |
