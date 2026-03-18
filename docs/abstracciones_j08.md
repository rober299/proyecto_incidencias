# Diseño de Abstracciones - Jornada 08

## 1. Puntos de Abstracción Detectados

Tras analizar el modelo de dominio creado en la jornada anterior, se han identificado los siguientes puntos donde la abstracción aporta reutilización real y evita un diseño rígido:

### Jerarquía de Clases (Herencia)

- **`UsuarioBase` (Clase Abstracta):**
  - _Justificación:_ En el sistema existen distintos perfiles (ej. Técnico, Cliente, Administrador). Todos comparten atributos básicos (nombre, email) y lógica común (como validar el email), pero tendrán comportamientos específicos (un Técnico resuelve incidencias, un Cliente solo las crea). Usar una clase abstracta `UsuarioBase` centraliza el código común y evita que se instancien "Usuarios genéricos" sin un rol definido, evitando herencias forzadas.

### Interfaces Funcionales (Composición y Contratos)

- **`Exportable` (Interfaz):**
  - _Justificación:_ Necesitamos exportar la información del sistema para auditorías o copias de seguridad. Distintas entidades, como `Incidencia` o `UsuarioBase`, implementarán esta interfaz. Esto garantiza que todas tengan un método `exportarDatos()`, permitiendo tratarlas de forma polimórfica (ej. exportar listas mixtas a formato CSV o JSON) sin importar de qué clase exacta sean.
- **`Notificable` (Interfaz):**
  - _Justificación:_ El sistema debe avisar a los usuarios cuando una incidencia cambia de estado. En lugar de meter la lógica de correos electrónicos dentro de la clase `Incidencia` (lo cual generaría un acoplamiento excesivo), creamos una interfaz `Notificable`. Esto permite crear implementaciones distintas (NotificacionEmail, NotificacionSMS) y pasárselas a la incidencia.
