# Análisis de Concurrencia y Paralelismo en la API

## 1. Conceptos Básicos aplicados al Proyecto (Bloque 1)

En nuestro backend de Gestión IT, es vital distinguir estos conceptos:

- **Proceso:** Es la instancia de nuestra aplicación ejecutándose (el archivo `.jar` o la clase `MainAPI` en ejecución). Tiene su propia memoria reservada.
- **Hilo (Thread):** Es la unidad más pequeña de ejecución dentro de nuestro proceso. Nuestro servidor HTTP nativo usa un "Thread Pool" (piscina de hilos) donde cada petición HTTP entrante es procesada por un hilo distinto.
- **Concurrencia:** Es la capacidad del servidor para gestionar múltiples peticiones (hilos) "a la vez" intercalando el tiempo de CPU entre ellas.
- **Paralelismo:** Ocurre si nuestro servidor corre en una máquina con varios núcleos (multi-core), permitiendo que dos peticiones HTTP se calculen físicamente en el mismo milisegundo exacto.

## 2. El Riesgo: Acceso Compartido y Condición de Carrera

Si dos peticiones HTTP concurrentes (dos hilos) intentan acceder y modificar una misma variable en memoria (por ejemplo, un contador de incidencias globales) al mismo tiempo, se produce una **condición de carrera**. Las lecturas y escrituras se pisan entre sí, provocando pérdida de datos e inconsistencias impredecibles.

## 3. Conclusiones Técnicas (Bloque 4)

Tras las pruebas prácticas, definimos la siguiente normativa para el desarrollo del backend:

- **Cuándo compensa usar concurrencia/paralelismo:** En procesos de I/O bloqueantes (consultas pesadas a bases de datos) o en el procesamiento por lotes (ej. exportar miles de incidencias a un Excel), donde dividir el trabajo en hilos paralelos reduce drásticamente el tiempo de espera del usuario.
- **Cuándo añade complejidad innecesaria:** No debemos usar hilos manuales para operaciones CRUD simples ni crear variables globales mutables. Añade riesgo de bloqueos (_deadlocks_) y dificulta la depuración. Los estados deben guardarse en la base de datos (que ya gestiona la concurrencia) y la API debe mantenerse _stateless_.
