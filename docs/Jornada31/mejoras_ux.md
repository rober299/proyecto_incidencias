# Revisión UX: Fricciones y Mejoras Detectadas

Tras analizar los wireframes y los flujos de usuario, se han detectado los siguientes puntos de fricción (cuellos de botella) y se proponen mejoras funcionales:

### Fricción 1: Exceso de clics para cambiar de estado

- **Problema:** Actualmente, para que un técnico pase un ticket de "Abierto" a "En Progreso", tiene que entrar al "Detalle", buscar el desplegable, cambiarlo y guardar. Son 3-4 clics.
- **Mejora UX:** Implementar botones de **acción rápida** directamente en la tarjeta de la Bandeja de Trabajo (ej: un botón que diga "Asumir ticket" que lo pase a En Progreso con un solo clic).

### Fricción 2: Formulario de Alta redundante

- **Problema:** El usuario solicitante tiene que escribir su nombre y su email cada vez que crea una incidencia.
- **Mejora UX:** **Autocompletado automático**. El sistema debe leer el token de sesión del usuario logueado y rellenar esos campos sin que el usuario tenga que teclearlos.

### Fricción 3: Falta de Feedback del Sistema

- **Problema:** Al crear un ticket o guardar un comentario, el salto de pantalla es brusco y el usuario no sabe si se ha guardado bien.
- **Mejora UX:** Incorporar **Notificaciones Toast** (mensajes emergentes verdes de éxito o rojos de error) que confirmen las acciones antes de redirigir.
