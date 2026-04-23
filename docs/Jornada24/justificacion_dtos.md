# Justificación de Modelos de Salida (DTOs)

## IncidenciaResumenDTO

Se ha implementado el patrón **Data Transfer Object (DTO)** para resolver el listado de incidencias pendientes.

**¿Por qué no devolver `IncidenciaORM` directamente?**
Si devolvemos la entidad completa a la vista, estamos arrastrando información confidencial y pesada. La entidad `IncidenciaORM` contiene la relación completa con `UsuarioORM` (el cual incluye contraseñas, roles y estado de activación).

**Campos expuestos en la vista y justificación:**

1. `id`: Necesario para que el usuario pueda seleccionar la incidencia y entrar al detalle.
2. `titulo`: Permite una identificación rápida del problema.
3. `estado`: Crucial en un listado de pendientes para ver en qué fase se encuentra.
4. `emailCreador`: Extraído de la entidad relacionada `UsuarioORM`. Se muestra el email como contacto rápido, ocultando por completo la contraseña y el ID del usuario.
