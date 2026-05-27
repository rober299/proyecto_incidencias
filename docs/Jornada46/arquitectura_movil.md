# Arquitectura y Navegación del Cliente Móvil (Android Nativo)

## 1. Decisiones de Arquitectura

Para el desarrollo del cliente móvil de Gestión IT, se ha optado por una arquitectura **Nativa en Android utilizando Java**. Esta decisión permite un rendimiento óptimo, acceso directo al hardware del dispositivo y una integración perfecta con los componentes visuales de Material Design 3.

### Estrategia Online vs Local

- **Resolución Online:** La autenticación (con validación de campos en el propio cliente), obtención de incidencias y creación de las mismas se resolverá de forma asíncrona consumiendo nuestra API REST. Se utilizarán hilos secundarios (o librerías como Retrofit/Volley en el futuro) para no bloquear el hilo principal (UI Thread).
- **Resolución Local (Modo Offline):** Se utilizará `SharedPreferences` de Android para almacenar de forma segura el Token JWT de la sesión. Esto evitará que el técnico tenga que hacer login cada vez que abre la aplicación.

## 2. Esquema de Navegación

La aplicación utiliza un sistema de múltiples `Activities` gestionadas a través de `Intents`, haciendo uso del ciclo de vida nativo y el vaciado de pila (`finish()`) para controlar la navegación hacia atrás.

```text
[ESTADO: SIN SESIÓN]
  └── MainActivity (Login con validación estricta de formato)
        └── (Éxito) ──> Guarda Token en SharedPreferences ──> Lanza ListadoActivity

[ESTADO: CON SESIÓN]
  ├── ListadoActivity (Vista principal maquetada con ScrollView)
  │     ├── Click en tarjeta ──> Lanza DetalleActivity
  │     └── Click en FAB (+) ──> Lanza CreacionActivity
  └── PerfilActivity (Accesible desde botón "PERFIL" en la Toolbar)
        └── Botón "Cerrar Sesión" ──> Borra Token ──> Limpia pila y vuelve a MainActivity
```
