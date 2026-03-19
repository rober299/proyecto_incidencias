# Análisis de Colecciones y Genéricos - Jornada 09

## 1. Tabla Comparativa de Estructuras de Datos

| Estructura         | Caso de uso ideal en el proyecto                                      | Ejemplo práctico                                                                      |
| :----------------- | :-------------------------------------------------------------------- | :------------------------------------------------------------------------------------ |
| **Lista (List)**   | Cuando importa el orden de llegada y puede haber elementos repetidos. | Historial de comentarios en una incidencia. Lista general de usuarios.                |
| **Conjunto (Set)** | Cuando es crítico evitar elementos duplicados de forma automática.    | Etiquetas o categorías asignadas a una avería (Hardware, Software, Redes).            |
| **Mapa (Map)**     | Para búsquedas ultrarrápidas a través de un identificador único.      | Buscar un equipo en el inventario metiendo directamente su número de serie (`PC-01`). |
| **Cola (Queue)**   | Cuando hay que procesar elementos en orden de llegada estricto.       | Cola de tickets en espera de ser asignados a un técnico libre.                        |
| **Pila (Stack)**   | Cuando lo último en entrar es lo primero en salir.                    | Funcionalidad de "Deshacer" (Ctrl+Z) el último cambio de estado de una incidencia.    |

## 2. Reflexión sobre Utilidades Genéricas

**¿Cuándo merece la pena generalizar?**

- Cuando la operación es puramente estructural y se va a repetir en varias partes del código. En nuestro caso, el método `filtrarListaGen<T>` sirve igual para sacar los usuarios de un rol concreto, o los activos de un tipo concreto. Nos ahorra escribir un bucle `for` específico para cada entidad.

**¿Cuándo NO merece la pena?**

- Cuando la lógica es tan específica del negocio que generalizarla lo complica más que ayudar. Por ejemplo, la lógica de _cambiar el estado de una incidencia_ (que implica comprobar quién es el técnico, notificar por email, etc.) debe ser un método de la clase `Incidencia`, no un genérico oscuro, porque solo aplica a las incidencias.
