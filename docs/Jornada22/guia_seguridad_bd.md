# Guía Breve de Seguridad y Robustez en Acceso a Datos (Jornada 22)

Para garantizar que el sistema de gestión de incidencias es seguro y no agota los recursos del servidor, se aplican las siguientes directrices en la capa DAO:

## 1. Prevención de Inyección SQL

**Nunca** se concatena texto introducido por el usuario directamente en las sentencias SQL (ej. `SELECT * FROM tabla WHERE nombre = '` + nombre + `'`).

- **Solución implementada:** Uso exclusivo de `PreparedStatement`. Las variables se inyectan mediante parámetros `?`. Esto obliga al driver JDBC a tratar el input estrictamente como datos, no como código ejecutable.

## 2. Gestión Eficiente de Recursos y Fugas de Memoria

Si no se cierran las conexiones (`Connection`), las sentencias (`Statement`) y los resultados (`ResultSet`), la memoria RAM del servidor se satura (Resource Leak).

- **Solución implementada:** Uso de la estructura `try-with-resources` de Java (introducida en Java 7). Esto garantiza que, incluso si ocurre una excepción catastrófica, los métodos `close()` de los objetos JDBC se ejecutan automáticamente.

## 3. Atomicidad y Consistencia (Transacciones)

Para operaciones que afectan a varias tablas (ej. crear incidencia + auditar), se desactiva el auto-commit (`conn.setAutoCommit(false)`).

- Si todas las operaciones tienen éxito, se consolida con `conn.commit()`.
- Si una operación falla a la mitad, se captura la excepción controlada y se ejecuta `conn.rollback()` para deshacer la transacción y evitar "datos huérfanos".

## 4. Próximos pasos a implementar en Producción

- **Connection Pooling:** En lugar de abrir y cerrar conexiones físicas constantemente (lo cual es lento), se recomienda usar una librería como **HikariCP** para mantener una "piscina" de conexiones reciclables.
- **Timeouts:** Configurar `stmt.setQueryTimeout(10)` para evitar que una consulta lenta bloquee el hilo de ejecución indefinidamente.
