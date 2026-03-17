# Modelo de Dominio

## 1. Tabla de Análisis de Entidades y Atributos Clave

| Entidad        | Atributos Clave                                              | Descripción / Restricciones                         |
| :------------- | :----------------------------------------------------------- | :-------------------------------------------------- |
| **Usuario**    | id, nombre, email, password, rol                             | `email` debe ser válido. `password` encapsulado.    |
| **Rol**        | id, nombre, nivelPermiso                                     | Define los privilegios dentro del sistema.          |
| **Activo**     | id, codigoInventario, tipo, estado                           | `codigoInventario` único.                           |
| **Incidencia** | id, titulo, descripcion, estado, prioridad, creador, tecnico | `estado` inicia en 'Abierta'.                       |
| **Comentario** | id, texto, fecha, autor                                      | Asociado a una incidencia específica.               |
| **Categoría**  | id, nombre                                                   | Clasificación del problema (Hardware, Software...). |
| **Adjunto**    | id, urlArchivo, tipo                                         | Archivos subidos a la incidencia.                   |
| **Auditoría**  | id, accion, fecha, usuarioId                                 | Registro inmutable de cambios críticos.             |
