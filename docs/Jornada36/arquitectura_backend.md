# Arquitectura del Backend

El proyecto sigue una arquitectura monolítica modular en capas (Layered Architecture). Esta separación de responsabilidades asegura que el código sea mantenible, testeable y que la lógica de negocio no se mezcle con el protocolo HTTP.

## Flujo de una Petición (Request Flow)

1. **Cliente HTTP (Frontend/Postman):** Envía una petición REST (ej. `POST /api/v1/incidencias`).
2. **Controlador (`api.controladores`):** Recibe el JSON, valida la estructura inicial y extrae los datos. Delega el trabajo pesado al Servicio.
3. **Servicio (`api.servicios`):** Aplica la lógica de negocio (Ej: Comprobar si el usuario tiene permisos, calcular la prioridad por defecto).
4. **Repositorio (`api.repositorios` / DAOs):** Se comunica con la base de datos mediante JDBC/ORM para realizar los `INSERT`, `SELECT`, etc.
5. **Base de Datos:** Ejecuta la acción y devuelve los resultados.
6. El flujo vuelve hacia arriba: Repositorio -> Servicio -> Controlador, convirtiendo las entidades en **DTOs** (Data Transfer Objects) para devolver una respuesta JSON limpia al cliente.
