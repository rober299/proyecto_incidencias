# Diseño de Pruebas Unitarias - Jornada 13

## 1. Selección de Métodos Críticos

Para asegurar la estabilidad del núcleo del sistema, se han diseñado pruebas para las siguientes reglas de negocio:

1. **Cálculo de prioridad:** Asegurar que la matemática no falla ni devuelve valores ilógicos.
2. **Cambio de estado:** Verificar que las transiciones de las incidencias siguen el flujo correcto.
3. **Validación de usuario:** Comprobar que no se tragan nombres vacíos o IDs negativos.
4. **Asignación de técnico:** Garantizar que solo usuarios con el rol adecuado (ADMIN o TECNICO) pueden ser asignados a un ticket.

## 2. Tabla de Casos de Prueba y Resultados (OK/KO)

| ID Test  | Método a probar     | Escenario (Entrada)       | Resultado Esperado   | Resultado Real       | Estado | Observaciones (Regla de negocio)                                 |
| :------- | :------------------ | :------------------------ | :------------------- | :------------------- | :----- | :--------------------------------------------------------------- |
| `test01` | `calcularPrioridad` | Urgencia = 2, Impacto = 3 | Devuelve 6           | Devuelve 6           | **OK** | _Caso normal:_ Prioridad = Urgencia \* Impacto.                  |
| `test02` | `calcularPrioridad` | Urgencia = 0              | Lanza Excepción      | Lanza Excepción      | **OK** | _Caso error:_ No se permite urgencia nula o cero.                |
| `test03` | `cambiarEstado`     | De ABIERTA a EN_PROGRESO  | Estado = EN_PROGRESO | Estado = EN_PROGRESO | **OK** | _Caso normal:_ Transición válida.                                |
| `test04` | `cambiarEstado`     | De CERRADA a EN_PROGRESO  | Lanza Excepción      | Lanza Excepción      | **OK** | _Caso error:_ Un ticket cerrado no puede reabrirse directamente. |
| `test05` | `validarUsuario`    | Nombre = " " (vacío)      | Lanza Excepción      | Lanza Excepción      | **OK** | _Caso error:_ El sistema no admite nombres en blanco.            |
| `test06` | `asignarTecnico`    | Rol = "TECNICO"           | Asignación exitosa   | Asignación exitosa   | **OK** | _Caso normal:_ El usuario tiene permisos suficientes.            |
| `test07` | `asignarTecnico`    | Rol = "CLIENTE"           | Lanza Excepción      | Lanza Excepción      | **OK** | _Caso error:_ Un cliente no puede ser asignado como técnico.     |
