# Decisiones de Exposición de Datos (Modelo de Dominio vs JSON Frontend)

Al comparar nuestro modelo de base de datos (`IncidenciaORM`, `UsuarioORM`) con la información que requiere el cliente (Frontend), se han tomado las siguientes decisiones de seguridad y eficiencia:

1. **Campos Omitidos (NO exponer nunca en JSON):**
   - `UsuarioORM.password`: Obvio por seguridad. Nunca debe viajar de vuelta al frontend.
   - `UsuarioORM.activo`: Dato de gestión interna del backend que el usuario no necesita ver.
   - Ids internos de tablas relacionales secundarias que no aportan valor a la vista.

2. **Aplanamiento de datos (Flattening):**
   - Para mejorar la velocidad, en el listado no enviaremos objetos anidados complejos. Por ejemplo, en lugar de enviar todo el objeto del técnico, enviaremos solo "nombreTecnico": "Robert Chislea". Esto facilita la carga en el navegador.
