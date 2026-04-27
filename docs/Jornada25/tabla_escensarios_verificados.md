# Tabla de Escenarios de Integración Verificados

| Escenario Validado      | Flujo Técnico Comprobado                                                                                          | Resultado Esperado                                                             |
| :---------------------- | :---------------------------------------------------------------------------------------------------------------- | :----------------------------------------------------------------------------- |
| **Alta de Incidencia**  | Transacción `em.persist()`. Inserción en base de datos. Mapeo correcto de entidades asociadas (Creador).          | Retorna `true`. Aumenta el contador de registros en BD.                        |
| **Búsqueda Paginada**   | Ejecución de JPQL con filtros (estado "Abierto"). Transformación dinámica a capa DTO.                             | Retorna lista con 2 incidencias (1 semilla + 1 insertada en el paso anterior). |
| **Cambio de Estado**    | Búsqueda por ID, modificación del atributo estado y guardado automático al realizar `commit()` de la transacción. | Retorna `true`. El estado en BD cambia a "En Progreso".                        |
| **Cierre y Comentario** | Validación de reglas de negocio (no cerrar si ya está cerrado). Concatenación de string en BD.                    | Retorna `true`. El estado cambia a "Cerrado" y se adjunta la cadena.           |
