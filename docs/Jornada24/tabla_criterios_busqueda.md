# Tabla de Criterios de Búsqueda Soportados

| Filtro / Criterio   | Implementación en JPQL                              | Descripción del comportamiento                                                                                 |
| :------------------ | :-------------------------------------------------- | :------------------------------------------------------------------------------------------------------------- |
| **Estado**          | `i.estado = :est`                                   | Coincidencia exacta (ej. "Abierto", "Cerrado"). Ignorado si es nulo.                                           |
| **Prioridad**       | `i.prioridad = :prio`                               | Búsqueda por nivel numérico de urgencia.                                                                       |
| **Categoría**       | `i.idCategoria = :cat`                              | Filtrado por el ID del departamento o área de la incidencia.                                                   |
| **Técnico/Creador** | `i.creador.idUsuario = :idTec`                      | Navegación de la relación `@ManyToOne` para buscar incidencias ligadas a un usuario específico.                |
| **Texto Libre**     | `i.titulo LIKE :texto OR i.descripcion LIKE :texto` | Búsqueda parcial (`%texto%`) en los campos descriptivos.                                                       |
| **Paginación**      | `setFirstResult()` y `setMaxResults()`              | División nativa de resultados usando las herramientas de limitación de la base de datos a través de Hibernate. |
| **Ordenación**      | `ORDER BY i.idIncidencia DESC`                      | Los registros más recientes aparecen primero por defecto.                                                      |
