# Patrón Productor-Consumidor y Procesamiento Diferido

## 1. Escenario de Uso en Gestión IT (Bloque 1)

En nuestra API, la creación de una incidencia implica escribir en la base de datos y, posteriormente, **enviar un correo electrónico al administrador**.
Si hacemos esto de forma síncrona, el usuario que crea la incidencia se quedará bloqueado esperando a que el servidor SMTP conteste. Para solucionarlo, desacoplamos las fases:

- **Productor:** El endpoint `POST /incidencias` guarda en BD y publica un mensaje en la cola ("Notificar Incidencia X"). Responde al usuario inmediatamente con un `201 Created`.
- **Consumidor:** Un hilo en segundo plano (Worker) lee la cola y envía los correos a su ritmo sin afectar a la respuesta web.

## 2. Pruebas y Tabla de Comportamientos Observados (Bloque 3)

Se han probado distintos ritmos modificando los tiempos de retardo (`Thread.sleep`) en el código:

| Escenario            | Ritmo Productor | Ritmo Consumidor | Comportamiento Observado (Acumulación / Vaciado)                                               | Necesidad de Control Adicional                                                                                      |
| :------------------- | :-------------- | :--------------- | :--------------------------------------------------------------------------------------------- | :------------------------------------------------------------------------------------------------------------------ |
| **Picos de Tráfico** | Rápido (200ms)  | Lento (1000ms)   | **Acumulación:** La cola crece rápidamente. El productor llena la memoria si no hay un límite. | Sí. Se requiere una cola acotada (límite de tamaño) para evitar `OutOfMemoryError` o auto-escalado de consumidores. |
| **Valle / Noche**    | Lento (1000ms)  | Rápido (200ms)   | **Vaciado:** La cola suele estar en 0. El consumidor extrae inmediatamente la tarea.           | No. El sistema está sobredimensionado, se podrían apagar consumidores para ahorrar recursos.                        |
| **Equilibrado**      | Medio (500ms)   | Medio (500ms)    | **Estable:** La cola oscila entre 0 y 1. Entra una tarea y sale casi al instante.              | No, flujo óptimo.                                                                                                   |

## 3. Contextos Reales y Herramientas Industriales (Bloque 4)

En la industria profesional, no se suelen programar colas manuales en memoria RAM (`LinkedBlockingQueue`) para tareas críticas, ya que si el servidor se reinicia, se pierden las notificaciones pendientes.
En su lugar, este patrón se implementa utilizando **Message Brokers** (Intermediarios de Mensajes) externos, robustos y persistentes. Las herramientas industriales estándar que sustituyen nuestra solución formativa son:

- **RabbitMQ** (Protocolo AMQP, ideal para colas de tareas tradicionales).
- **Apache Kafka** (Procesamiento de flujos de datos masivos y eventos).
- **AWS SQS** o **Google Cloud Pub/Sub** (Soluciones serverless en la nube).
