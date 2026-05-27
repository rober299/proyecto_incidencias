# Escenarios de Red, Integración y Resiliencia en Cliente Móvil

## 1. Control de Rutas y Métodos API (Errores 404 y 405)

- **Desafío detectado:** Durante las primeras pruebas de integración, el emulador registró errores de tipo `404 Not Found` y `405 Method Not Allowed` al intentar alcanzar los endpoints base.
- **Diagnóstico y Solución:** Mediante auditoría activa con Postman, se verificó la estructura exacta expuesta por el backend. Se corrigieron las URLs de llamadas asíncronas en el cliente móvil incorporando el prefijo de versionado correcto (`/api/v1/login` y `/api/v1/incidencias`) y el puerto real de escucha (`8082`), garantizando el emparejamiento exacto de los métodos HTTP (POST y GET).

## 2. Gestión del Formato de Autenticación (Parseo de JSON Token)

- **Desafío detectado:** El servidor no responde en texto plano, sino que devuelve un objeto estructurado JSON tras un login correcto.
- **Solución implementada:** Se modificó la lectura del flujo de entrada (`InputStream`) en `MainActivity.java` para procesar la respuesta completa. Se implementó la instanciación de un `JSONObject` para extraer exclusivamente el valor de la clave `"token"`, saneando la cadena antes de ser transmitida en las cabeceras de autorización de llamadas posteriores.

## 3. Mitigación de Persistencia Corrupta (Token Envenenado)

- **Escenario crítico:** Almacenamiento involuntario de respuestas de error o estructuras JSON completas en la caché local del dispositivo.
- **Solución implementada:** Se programó una limpieza explícita en `PerfilActivity.java`. Al accionar el botón de "Cerrar Sesión", se invoca el método `prefs.edit().remove("TOKEN").apply()`, lo que invalida de raíz la sesión local, destruye pases corruptos en `SharedPreferences` y fuerza el retorno seguro a la vista de login.

## 4. Escenario Offline / Backend Apagado

- **Comportamiento esperado:** Evitar el bloqueo del hilo principal de la interfaz de usuario (UI Thread) y prevenir caídas de tipo ANR (Application Not Responding).
- **Solución implementada:** Las peticiones de red se encapsulan en un pool de hilos secundario controlado por un `ExecutorService`. Se define un tiempo límite de conexión (`setConnectTimeout(3000)`). En caso de ausencia de red o caída del servidor, la excepción `IOException` es interceptada de forma segura, notificando al usuario mediante alertas visuales (`Toast`) controladas por el hilo de ejecución principal.
