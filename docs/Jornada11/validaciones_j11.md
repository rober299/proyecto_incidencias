# Catálogo de Validaciones y Errores de Negocio - Jornada 11

## 1. Catálogo de Validaciones por Entidad

### Entidad: Incidencia

- **ID:** Debe ser un número entero mayor que 0. No puede estar vacío.
- **Título:** Campo obligatorio. Longitud mínima de 5 caracteres y máxima de 50. No puede contener solo espacios en blanco.
- **Descripción:** Opcional, pero si se incluye, no debe superar los 500 caracteres.
- **Urgencia:** Solo admite valores numéricos entre 1 (Baja) y 5 (Crítica).
- **Transiciones de Estado Válidas:**
  - `ABIERTA` -> `EN_PROGRESO` o `CERRADA`
  - `EN_PROGRESO` -> `CERRADA` o `ESPERA`
  - `ESPERA` -> `EN_PROGRESO` o `CERRADA`
  - _Transición inválida:_ Pasar directamente de `ABIERTA` a `ESPERA` sin pasar por `EN_PROGRESO`. Intentar cambiar el estado de una incidencia que ya está `CERRADA`.

### Entidad: Usuario

- **ID:** Entero mayor que 0.
- **Nombre:** Obligatorio. Entre 3 y 30 caracteres.
- **Email:** Formato válido de correo (presencia de '@' y '.').
- **Rol:** Solo puede ser 'ADMIN', 'TECNICO' o 'CLIENTE'.

---

## 2. Resumen de Errores de Negocio Contemplados

Para evitar excepciones genéricas de Java (como `NullPointerException` o `IllegalArgumentException`), se diseñan los siguientes errores propios de nuestro dominio:

1. **IncidenciaNoEncontradaException:** \* _Cuándo salta:_ Al intentar buscar, actualizar o cerrar un ID de incidencia que no existe en el sistema.
   - _Tipo:_ Error de usuario/cliente.
2. **EstadoInvalidoException:** \* _Cuándo salta:_ Al intentar forzar una transición de estado no permitida (ej. reabrir una incidencia ya cerrada sin permisos).
   - _Tipo:_ Regla de negocio.
3. **UsuarioNoAutorizadoException:** \* _Cuándo salta:_ Un usuario con rol 'CLIENTE' intenta cambiar la urgencia de una incidencia o asignarse a sí mismo como técnico.
   - _Tipo:_ Regla de negocio / Seguridad.
4. **ValidacionDatosException:**
   - _Cuándo salta:_ Al recibir campos vacíos, nulos o fuera de los rangos definidos en el catálogo de validaciones.
   - _Tipo:_ Error de usuario.
