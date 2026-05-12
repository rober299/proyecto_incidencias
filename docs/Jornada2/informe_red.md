# Informe de Conceptos Básicos de Red y Diagnóstico

Este documento resume los conceptos clave de conectividad necesarios para el desarrollo remoto y local de aplicaciones.

### Conceptos Fundamentales

- **Localhost (127.0.0.1):** Es el "yo mismo" del ordenador. Cuando programas un servidor web en tu máquina y quieres probarlo, te conectas a `localhost`. La petición nunca sale a internet, se queda en tu propio equipo.
- **Puertos:** Si la IP es la dirección de un edificio, el puerto es el número de la puerta del apartamento. Un servidor puede tener el puerto 80 abierto para la web, el 3306 para la base de datos, etc.
- **IP Privada:** Es la dirección que te asigna el router de tu casa u oficina (suele empezar por `192.168.x.x`). Solo es válida dentro de tu red local.
- **DNS (Domain Name System):** Es la "agenda telefónica" de internet. Traduce nombres fáciles de recordar (como `google.es`) a direcciones IP numéricas que entienden los ordenadores.

### Herramientas de Diagnóstico

- **`ping`:** Envía un "eco" a un servidor remoto para comprobar si está encendido, si hay conexión y cuánto tarda en responder (latencia en milisegundos).
- **`traceroute` (o `tracert` en Windows):** Muestra todos los "saltos" (routers intermedios) por los que pasa tu conexión hasta llegar al destino final. Sirve para ver dónde se corta la conexión si algo falla.
- **`netstat`:** Muestra las conexiones de red activas y, lo más importante en desarrollo, qué **puertos están abiertos y escuchando** (`LISTENING`) en tu ordenador.
