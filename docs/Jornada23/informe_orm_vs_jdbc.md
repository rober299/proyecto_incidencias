# Informe Comparativo: JDBC Manual vs Framework ORM (Hibernate)

| Criterio                | JDBC (Acceso Manual)                                                                                      | Framework ORM (Hibernate/JPA)                                                                               |
| :---------------------- | :-------------------------------------------------------------------------------------------------------- | :---------------------------------------------------------------------------------------------------------- |
| **Cantidad de Código**  | Alta. Requiere mucho código repetitivo para mapear `ResultSets` a objetos a mano.                         | Baja. El framework mapea las columnas a atributos de clase automáticamente con anotaciones.                 |
| **Control sobre SQL**   | Total. El desarrollador escribe la sentencia exacta, lo que permite exprimir funciones nativas del motor. | Delegado. El framework genera el SQL. A veces genera consultas ineficientes si no se configura bien.        |
| **Rendimiento**         | Máximo. Al no tener capas intermedias, es la forma más rápida de ejecutar una consulta.                   | Ligeramente inferior por el peso del framework, aunque se mitiga usando cachés.                             |
| **Claridad y Diseño**   | Baja. Se mezcla el paradigma relacional (tablas) con la programación orientada a objetos (clases).        | Alta. Mantiene un diseño 100% orientado a objetos. Se navega por las relaciones fácilmente.                 |
| **Complejidad y Curva** | Baja. Ideal para proyectos simples. Solo requiere saber SQL y manejo básico de Java.                      | Alta. Curva de aprendizaje empinada para entender la gestión de transacciones y los estados de los objetos. |

### Conclusión sobre el Modelo Relacional

El uso de un ORM **no elimina el modelo relacional subyacente**. Las tablas, las claves foráneas y las restricciones siguen existiendo en MySQL. El ORM simplemente actúa como un "traductor" inteligente entre el mundo de los objetos en memoria y el mundo de las filas y columnas en disco.
