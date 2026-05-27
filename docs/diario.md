# Diario de Prácticas

## Jornada 01 - Lunes 9 de marzo de 2026

### 📝 ¿Qué he hecho hoy?

- Preparación del entorno de trabajo remoto.
- Instalación, verificación y documentación de herramientas: IntelliJ IDEA, Visual Studio Code, Android Studio, JDK, Git, MySQL Workbench, Postman y Google Chrome.
- Creación de la estructura base del proyecto (`proyecto_incidencias`) con las carpetas profesionales: `/docs`, `/src`, `/bd`, `/api`, `/frontend`, `/movil`, `/tests` y `/evidencias`.
- Redacción del archivo `README.md` inicial definiendo el propósito del proyecto, el stack tecnológico y las reglas de nomenclatura.
- Inicialización del repositorio Git local, creación de las ramas `main` y `desarrollo`, y configuración de archivos clave (`.gitignore`, `LICENSE` y `.gitmessage`).
- Subida del código al repositorio remoto en GitHub.

### ⚠️ ¿Qué problemas he tenido?

- Error en Git (`not a valid object name: 'main'`) al intentar crear la rama de desarrollo.
- Error de autenticación (`Connection refused` / `Password authentication is not supported`) al intentar hacer el `git push` al repositorio remoto en GitHub desde la terminal.

### 💡 ¿Cómo los he resuelto?

- El problema de la rama se resolvió haciendo un primer commit inicial con el archivo `README.md` para que la rama `main` existiera realmente en el historial antes de ramificar.
- El problema de GitHub se solucionó utilizando el inicio de sesión integrado del navegador ("Sign in with your browser") que ofrece Visual Studio Code para generar el acceso seguro sin usar contraseña plana.

### ⏳ ¿Qué queda pendiente?

- Mañana toca la Jornada 02: Repaso de sistemas y terminal (creación de chuleta de comandos, scripts de automatización para backups/evidencias y conceptos de red).

## Jornada 02 - Martes 10 de marzo de 2026

### 📝 Resumen del día

- **Bloque 1:** Creación de documento de comandos y pruebas prácticas de consola (navegación, copias, borrado, permisos, compresión y procesos).
- **Bloque 2:** Desarrollo de scripts de automatización en Batch (`backup.bat` y `carpeta_diaria.bat`). Diferenciación entre rutas relativas (`.\`) y rutas absolutas (`%cd%`).
- **Bloque 3:** Diagnóstico de red mediante comandos, `ping`, `tracert` y `netstat`. Revisar conceptos como localhost, IP privada y puertos.

### Bloque 4: Variables de Entorno (Registro y Explicación)

Se ha configurado la variable de entorno `JAVA_HOME` apuntando al directorio de instalación del JDK.

- **¿Qué son?** Las variables de entorno son unos "atajos" globales o etiquetas de texto que guarda el sistema operativo. Contienen información sobre cómo y dónde deben ejecutarse los programas.
- **¿Para qué sirven?** Sirven para que cualquier programa como Visual Studio Code pueda encontrar herramientas necesarias (como el compilador de Java) sin tener que decirle la ruta exacta absoluta cada vez. Si Java se actualiza y cambia de carpeta, solo cambias la variable y todos los programas se enteran a la vez.
- **¿Cómo comprobar que están activas?** Se puede verificar desde la terminal. En PowerShell, basta con ejecutar el comando: `echo $env:JAVA_HOME`. Si devuelve la ruta configurada, la variable está activa y lista para usarse.

## Jornada 03 - Miércoles 11 de marzo de 2026

### 📝 Resumen del día

- **Bloque 1:** Creación de la guía interna de uso de Git (`guia_git.md`) definiendo la estrategia de ramas y Conventional Commits.
- **Bloque 2:** Simulación de ciclos de trabajo reales creando ramas `feature`, realizando cambios y fusionando (merge) con la rama `desarrollo`.
- **Bloque 3:** Provocación y resolución manual de un conflicto de Git documentado paso a paso en `resolucion_conflicto.md`.

### Bloque 4: Tabla de seguimiento de commits del día

| Hora  | Objetivo         | Rama                        | Descripción Funcional (Mensaje del commit)                     |
| :---- | :--------------- | :-------------------------- | :------------------------------------------------------------- |
| 11:00 | Documentación    | `feature/actualizar-readme` | `docs: añade estado de desarrollo al README`                   |
| 11:40 | Corrección       | `desarrollo`                | `fix: corrige formato de texto corrupto en el README`          |
| 11:50 | BBDD             | `feature/documentacion-bd`  | `feat: crea archivo inicial para el esquema de base de datos`  |
| 12:35 | Prueba conflicto | `feature/cambio-a`          | `feat: añade texto del primer programador`                     |
| 12:40 | Prueba conflicto | `feature/cambio-b`          | `feat: añade texto del segundo programador`                    |
| 12:50 | Resolución       | `desarrollo`                | `fix: resuelve conflicto manualmente conservando ambos lineas` |
| 13:00 | Mantenimiento    | `desarrollo`                | `chore: limpia archivo de prueba de conflictos`                |

## Jornada 04 - Jueves 12 de marzo de 2026

### 📝 Resumen del día

- **Bloques 1, 2 y 3:** Levantamiento de requisitos (Funcionales y No Funcionales) y definición de las reglas de negocio estructurales. Creación del diagrama visual de casos de uso para los 3 roles del sistema.
- **Bloque 4:** Elaboración del Backlog inicial priorizado utilizando el formato estándar de Historias de Usuario (HU) con sus respectivos criterios de aceptación, ajustado a un MVP de 3 meses.

## Jornada 05 - Viernes 13 de marzo de 2026

### 📝 Resumen del día

- **Bloques 1 y 2:** Conversión del backlog en un cronograma de 6 Sprints (3 meses), identificando dependencias y riesgos técnicos. Redacción de la guía de estándares del proyecto (nomenclatura, arquitectura BEM, etc.).
- **Bloques 3 y 4:** Diseño de plantillas de seguimiento diario y revisión del tutor. Creación de la carpeta `semana_1` con la revisión rellena. Limpieza del repositorio y etiquetado (Tag) de la versión base inicial (v1.0.0) cerrando la primera iteración semanal con un proyecto gobernable.

## Jornada 06 - Lunes 16 de marzo de 2026

### 📝 Resumen del día

- **Bloques 1 y 2:** Planteamiento lógico de tres algoritmos fundamentales del proyecto (validación de usuario, cálculo de prioridad y filtrado de tickets) empleando pseudocódigo y tablas de decisión.
- **Bloque 3:** Implementación de las soluciones en Java, asegurando el uso de nombres semánticos y métodos independientes sin entradas manuales por consola.
- **Bloque 4:** Refactorización del código inicial aplicando principios de Clean Code, condensando validaciones lógicas y separando estrictamente la capa de lógica de la capa de presentación (salida por consola). Se han generado las capturas de ejecución y el informe correspondientes.

## Jornada 07 - Martes 17 de marzo de 2026

### 📝 Resumen del día

- **Bloque 1:** Se han identificado las entidades principales del proyecto (Usuario, Rol, Activo, Incidencia, etc.) y se han definido sus atributos clave y restricciones iniciales en una tabla de análisis.
- **Bloque 2:** Implementación del modelo de dominio en Java. Se han creado las clases `Usuario` e `Incidencia` aplicando encapsulación estricta (atributos privados) y validaciones en los constructores/setters. Se han añadido métodos de negocio con responsabilidad única (`asignarTecnico`, `cerrarIncidencia`).
- **Bloque 3:** Desarrollo de una aplicación de consola funcional para probar la instanciación de objetos. Se han ejecutado cuatro escenarios: alta de incidencia, asignación, cierre y captura de errores por datos inválidos.
- **Bloque 4:** Creación del diagrama de clases version 1 en formato visual reflejando las multiplicidades y relaciones entre entidades. Además, se han documentado las posibles futuras restricciones lógicas del sistema.

## Jornada 08 - Miércoles 18 de marzo de 2026

### 📝 Resumen del día

- **Bloque 1:** Se ha analizado el modelo de dominio para identificar puntos clave de abstracción, justificando el uso de la clase abstracta `UsuarioBase` y las interfaces `Exportable` y `Notificable`.
- **Bloque 2:** Implementación de la jerarquía de herencia (`UsuarioBase`, `Tecnico`, `Cliente`) y las interfaces funcionales, asegurando una reutilización real de código y evitando la herencia artificial.
- **Bloque 3:** Creación de un ejercicio práctico que demuestra el uso del polimorfismo, procesando diferentes tipos de usuarios de forma genérica para exportar datos y enviar notificaciones.
- **Bloque 4:** Redacción del informe técnico con reflexiones sobre las decisiones de diseño, corrección de defectos estructurales, ventajas/inconvenientes del polimorfismo y actualización del diagrama de clases UML.

## Jornada 09 - Jueves 19 de marzo de 2026

### 📝 Resumen del día

- **Bloque 1:** Se ha creado un dataset en memoria utilizando estructuras de datos como `Set` (para evitar categorías duplicadas), `Map` (para búsquedas rápidas en el inventario) y `List` (para incidencias y usuarios).
- **Bloque 2:** Se han implementado con éxito operaciones de negocio como búsquedas personalizadas, agrupación y conteo por estados, y ordenación por pesos de prioridad numérica.
- **Bloque 3:** Creación de un método genérico (`<T>`) apoyado en la interfaz `Predicate` para centralizar la lógica de filtrado de cualquier tipo de lista, evitando duplicidad de código.
- **Bloque 4:** Redacción de la tabla teórica comparando el uso de Listas, Conjuntos, Mapas, Colas y Pilas aplicados a casos de uso concretos del proyecto de incidencias.

## Jornada 10 - Viernes 20 de marzo de 2026

### 📝 Resumen del día

- **Bloque 1:** Se ha inyectado código defectuoso a propósito en la gestión de incidencias (nulos, divisiones por cero, falta de validaciones).
- **Bloque 2:** Uso intensivo del depurador de VS Code (breakpoints e inspección de variables en memoria) para localizar el origen exacto de los fallos antes de que reviente la ejecución.
- **Bloque 3:** Sustitución de los _prints_ sucios por una solución de _logging_ estructurada con formato temporal y niveles de severidad (`INFO`, `ERROR`, `DEBUG`).
- **Bloque 4:** Refactorización del código mediante la extracción de un método centralizado para validaciones (`esValida`), mejorando la robustez sin alterar la funcionalidad.

## Jornada 11 - Lunes 23 de marzo de 2026

### 📝 Resumen del día

- **Bloque 1:** Redacción del catálogo de validaciones por entidad y definición de los principales errores de negocio.
- **Bloque 2:** Implementación de clases personalizadas heredando de `RuntimeException` para mapear los errores de dominio (`ValidacionDatosException`, `EstadoInvalidoException`, etc.).
- **Bloque 3:** Creación de un _script_ de pruebas para forzar estas excepciones y demostrar la separación entre el log técnico y el mensaje amigable de cara al usuario.
- **Bloque 4:** Refactorización de la clase `GestorIncidencias` del viernes, sustituyendo los retornos improvisados (`return false;`) por una estrategia sólida de lanzamiento y captura de excepciones propias.

## Jornada 12 - Martes 24 de marzo de 2026

### 📝 Resumen del día

- **Bloque 1:** Se ha creado el fichero de configuración `app.properties` para extraer del código duro los parámetros de entorno y rutas, acompañándolo de su documentación.
- **Bloque 2:** Desarrollo del módulo `GestorCSV` para importar y exportar datos tabulares. Se ha implementado tolerancia a fallos para ignorar líneas corruptas sin interrumpir la ejecución.
- **Bloque 3:** Creación del módulo `GestorJSON` con un serializador y deserializador manual para transformar objetos Java complejos (Usuarios) a formato de intercambio y viceversa.
- **Bloque 4:** Redacción de un informe técnico detallando las diferencias, casos de uso ideales y riesgos de utilizar CSV frente a JSON en el proyecto.

## Jornada 13 - Miércoles 25 de marzo de 2026

### 📝 Resumen del día

- **Bloque 1:** Se han seleccionado los métodos críticos del dominio (cálculo de prioridad, estados, validación y roles) y se han diseñado casos de prueba concretos (escenarios normales y de error).
- **Bloque 2:** Ante la decisión de no incluir librerías externas pesadas, se ha construido un Test Runner propio para ejecutar pruebas unitarias automatizadas con aserciones precisas.
- **Bloque 3:** Ejecución de la batería de pruebas, detectando y documentando un bug real en el cálculo matemático de la prioridad (una suma en lugar de una multiplicación).
- **Bloque 4:** Elaboración de la tabla de cobertura funcional mínima, mapeando las áreas seguras del código y las pendientes para futuras iteraciones.

## Jornada 14 - Jueves 26 de marzo de 2026

### 📝 Resumen del día

- **Bloque 1:** Se seleccionó el validador de transiciones de estado como módulo acotado para aplicar TDD, definiendo claramente sus reglas de negocio.
- **Bloque 2:** Se ejecutó la fase Roja (escribir tests que fallan al principio) y la fase Verde (implementar la lógica mínima de los `if` para que los tests pasaran con éxito), generando las capturas correspondientes.
- **Bloque 3:** Se refactorizó el código del validador para mejorar su legibilidad mediante variables booleanas, validando que los tests seguían pasando correctamente.
- **Bloque 4:** Se redactó un informe técnico con el histórico de pasos, lecciones aprendidas, ventajas y dificultades de aplicar el ciclo TDD en el proyecto integrador.

## Jornada 16 - Lunes 6 de abril de 2026

### 📝 Resumen del día

- **Bloque 1 y 2:** Se releyeron los requisitos (v1) y el modelo de dominio. Se generó un diccionario de datos estableciendo 8 entidades claras y traduciendo la herencia de objetos a claves foráneas.
- **Bloque 3:** Se establecieron reglas de persistencia estrictas: borrado lógico (`activo` en Usuarios y Activos), `NOT NULL` en campos obligatorios (título, categoría) y tabla de auditoría inmutable.
- **Bloque 4:** Se dibujó y exportó el Modelo Entidad-Relación v1 reflejando la arquitectura final que soportará el negocio.

## Jornada 17 - Martes 7 de abril de 2026

### 📝 Resumen del día

- **Bloque 1:** Se transformó el modelo conceptual ER en un esquema relacional completo, definiendo tipos de datos, PKs y FKs.
- **Bloques 2 y 3:** Se aplicaron las reglas de normalización (1NF, 2NF, 3NF), detectando posibles anomalías en campos de texto libre (roles, categorías) y extrayéndolos a tablas maestras. Se extrajeron adjuntos y comentarios a tablas transaccionales.
- **Bloque 4:** Se redactó el informe de normalización, justificando la denormalización intencionada del campo `estado` en la tabla incidencias para priorizar el rendimiento del sistema (evitando JOINs innecesarios), demostrando un diseño usable y no puramente mecánico.

## Jornada 18 - Miércoles 8 de abril de 2026

### 📝 Resumen del día

- **Bloque 1 y 3:** Se generaron los scripts DDL reproducibles. Se creó un archivo de recreación limpia (`DROP DATABASE`) y el esquema completo respetando estrictamente las restricciones del negocio (NOT NULL, UNIQUE, claves foráneas).
- **Bloque 2:** Se introdujeron índices de optimización justificados por las necesidades de negocio (filtrado por estado, técnico y orden de prioridad).
- **Bloque 4:** Se ejecutó el esquema en el gestor de base de datos resolviendo conflictos de dependencias en la creación de FKs, registrando los cambios en la bitácora correspondiente y generando las evidencias visuales.

## Jornada 19 - Jueves 9 de abril de 2026

### 📝 Resumen del día

- **Bloque 1:** Se creó un script de inserción de datos masiva (DML) para popular las tablas con escenarios coherentes (roles, activos, usuarios, tickets y comentarios).
- **Bloques 2 y 3:** Se programó una batería de consultas SQL usando `JOIN` y agrupaciones para resolver dudas de negocio. Se aplicaron `UPDATES` condicionales seguros y borrados lógicos sin usar `DELETE`.
- **Bloque 4:** Se redactó la documentación cruzando las preguntas funcionales de la empresa con la lógica de las sentencias SQL.

## Jornada 20 - Viernes 10 de abril de 2026

### 📝 Resumen del día

- **Bloque 1:** Se diseñaron vistas (`v_panel_tecnico` y `v_historico_resueltas`) para facilitar la lectura de datos frecuentes al Frontend.
- **Bloques 2 y 3:** Se creó el procedimiento almacenado `sp_cerrar_incidencia` para agilizar tareas repetitivas y un trigger `trg_auditoria_estado` que garantiza el registro de auditoría inmutable ante cualquier cambio.
- **Bloque 4:** Se simuló y documentó una transacción de múltiples pasos (UPDATE en cascada), comprobando el funcionamiento del `ROLLBACK` para asegurar la atomicidad y evitar datos corruptos si falla la capa de aplicación.

## Jornada 21 - Lunes 13 de abril de 2026

### 📝 Resumen del día

- **Bloque 1:** Se configuró la conexión a MySQL unificando las credenciales en el archivo `config/app.properties` existente para no dejar datos sensibles en el código.
- **Bloques 2 y 3:** Se implementó el patrón DAO en Java separando interfaces (`UsuarioDAO`, `IncidenciaDAO`) de sus implementaciones con JDBC. Se usaron `PreparedStatements` para evitar inyección SQL y se hizo el mapeo manual a objetos puros (POJOs).
- **Bloque 4:** Se creó y ejecutó la suite `MainPruebas.java` comprobando con éxito la inserción, lectura y borrado lógico de registros en la base de datos desde la aplicación.

## Jornada 22 - Martes 14 de abril de 2026

### 📝 Resumen del día

- **Bloques 1 y 2:** Se creó la clase `TransaccionService.java` aplicando el bloque `try-with-resources` para garantizar la liberación automática de conexiones y sentencias. Se implementó una transacción manual (`setAutoCommit(false)`) que inserta una incidencia y su registro de auditoría de forma atómica, usando `PreparedStatement` para prevenir la inyección SQL.
- **Bloque 3:** Se programó la clase `MainJornada22.java` para testear la consistencia de los datos. Se forzó una excepción a mitad de la transacción y se demostró, comparando el número de registros antes y después, que el comando `rollback()` funciona correctamente y no deja datos huérfanos.
- **Bloque 4:** Se redactó la guía `guia_seguridad_bd.md` resumiendo las medidas implementadas para asegurar la robustez del acceso a datos, incluyendo el cierre de recursos, la prevención de inyecciones SQL y el control de transacciones.

## Jornada 23 - Miércoles 15 de abril de 2026

### 📝 Resumen del día

- **Bloque 1:** Se configuró la unidad de persistencia (`META-INF/persistence.xml`) y se mapearon las entidades `UsuarioORM` e `IncidenciaORM` usando anotaciones JPA (`@Entity`, `@Table`, `@OneToMany`, `@ManyToOne`). Se superaron problemas de compatibilidad configurando manualmente el classpath con las librerías necesarias.
- **Bloque 2:** Se implementó el patrón Repository con `IncidenciaRepository`, utilizando `EntityManager` y JPQL para ejecutar consultas (por estado y por técnico) sin escribir SQL nativo.
- **Bloques 3 y 4:** Se redactó un informe técnico (`docs/informe_orm_vs_jdbc.md`) elaborando una tabla comparativa exhaustiva entre JDBC puro y ORM, analizando rendimiento y cantidad de código.

## Jornada 24 - Jueves 16 de abril de 2026

### 📝 Resumen del día

- **Bloque 1:** Se ha creado la capa `IncidenciaService` para aislar la lógica de negocio. Se han implementado los casos de uso transaccionales requeridos: registrar incidencia, asignar técnico, listar pendientes y cerrar con comentario, garantizando el uso de `em.getTransaction().begin()` y `commit()`.
- **Bloque 2:** Se amplió `IncidenciaRepository` implementando un buscador dinámico en JPQL que soporta filtros por estado, prioridad, texto libre (LIKE), categoría y relaciones de usuario. Se integró paginación nativa usando la API de JPA (`setFirstResult` / `setMaxResults`).
- **Bloque 3:** Se definió `IncidenciaResumenDTO` para evitar exponer la entidad `IncidenciaORM` (y su relación con `UsuarioORM`) en las capas de presentación, justificando la selección de campos (ID, título, estado y email).
- **Bloque 4:** Se realizaron pruebas funcionales a través del controlador (`MainJornada24`), comprobando que el rendimiento de las consultas paginadas es óptimo y verificando que el controlador se comunica exclusivamente con el Servicio, sin conocer la existencia del DAO/Repositorio.

## Jornada 25 - Viernes 17 de abril de 2026

### 📝 Resumen del día

- **Bloque 1:** Se configuró un entorno de base de datos completamente aislado (`proyecto_incidencias_test`) mediante la adición de la unidad `IncidenciasTestPU` en el `persistence.xml`, previniendo la contaminación cruzada de datos con el entorno de desarrollo.
- **Bloque 2:** Se elaboró el script `init_test_env.sql` que actúa como fixture, reseteando la estructura e inyectando datos semilla (usuarios y roles) para arrancar los test desde un estado predecible y eliminando la dependencia de estados manuales.
- **Bloque 3:** Se codificó `MainJornada25_Integracion.java`, validando flujos de negocio end-to-end (alta, listado, asignación y cierre), confirmando la robustez de las transacciones de Hibernate y asegurando que las reglas del `IncidenciaService` se reflejan en la base de datos de pruebas.
- **Bloque 4:** Se redactó la documentación técnica obligatoria, incluyendo la guía de reconstrucción del entorno y la tabla de verificación de los escenarios, dejando la capa de persistencia validada y preparada para la fase web.

## Jornada 26 - Lunes 20 de abril de 2026

### 📝 Resumen del día

- **Bloque 1:** Se diseñó e implementó la maqueta inicial de las 5 vistas requeridas: login, dashboard, listado, detalle y alta.
- **Bloque 2:** Se garantizó un HTML estrictamente semántico usando etiquetas nativas de HTML5, evitando el uso excesivo de `divs` y estructurando lógicamente el contenido.
- **Bloque 3:** Se superó el checklist de accesibilidad verificando labels, jerarquías de título y propiedades ARIA para navegación.
- **Bloque 4:** Se modularizó el directorio frontend separando vistas, css, js y assets, dejando el proyecto escalable y documentando la decisión técnica para el tutor.

## Jornada 27 - Martes 21 de abril de 2026

### 📝 Resumen del día

- **Bloque 1:** Se redactó la guía visual básica documentando decisiones de color, tipografía y espaciado, implementándolas como variables CSS nativas.
- **Bloque 2:** Se programó el archivo `style.css` aplicando selectores semánticos (evitando la "div-itis") y dotando de coherencia gráfica a los formularios, tablas y tarjetas del dashboard usando Flexbox y CSS Grid.
- **Bloque 3:** Se añadió comportamiento Responsive mediante Media Queries (`max-width: 768px`). La navegación se colapsa en vertical y las tablas adquieren scroll horizontal preventivo para evitar roturas de layout en móviles.
- **Bloque 4:** Se inspeccionó el resultado final en navegador y se documentó el listado de mejoras de UI pendientes para futuras iteraciones de frontend.

## Jornada 28 - Miércoles 22 de abril de 2026

### 📝 Resumen del día

- **Bloque 1:** Se definieron y programaron las validaciones de cliente para login y alta, controlando formatos (regex de email) y longitudes mínimas para evitar peticiones vacías.
- **Bloque 2:** Se implementó manipulación directa del DOM usando Vanilla JS. Se alternaron clases CSS para el pintado de errores, se previno el comportamiento por defecto de los envíos (`e.preventDefault()`) y se crearon paneles dinámicos de éxito.
- **Bloque 3:** Se reemplazó la tabla de HTML estático por un renderizado dinámico (`listado_dinamico.js`), inyectando nodos DOM desde un JSON simulado que imita la futura respuesta del backend.
- **Bloque 4:** Se redactó la justificación técnica de la división de responsabilidades entre cliente y servidor, documentando el límite de seguridad del frontend.

## Jornada 29 - Jueves 23 de abril de 2026

### 📝 Resumen del día

- **Bloque 1:** Se diseñó un documento XML estructurado (`incidencias.xml`) utilizando atributos (`id`, `estado`, `prioridad`) para metadatos y nodos internos para la información descriptiva del ticket.
- **Bloque 2:** Se implementó un esquema de validación estricto (`incidencias.xsd`) para tipificar los datos (enteros, fechas) y restringir estados mediante enumeraciones.
- **Bloque 3:** Se formularon y documentaron expresiones XPath para la extracción quirúrgica de datos del árbol XML.
- **Bloque 4:** Se programó una hoja de transformación XSLT (`transformacion.xslt`) que convierte el XML en un reporte HTML dinámico, utilizando condicionales (`xsl:if`) para aplicar estilos visuales a incidencias críticas o urgentes.

## Jornada 30 - Viernes 24 de abril de 2026

### 📝 Resumen del día

- **Bloque 1:** Se definieron los contratos JSON iniciales para las operaciones CRUD principales, estandarizando nombres en camelCase.
- **Bloque 2:** Se configuró el frontend para consumir datos asíncronamente mediante la API `fetch`, separando definitivamente la capa de presentación de los datos.
- **Bloque 3:** Se realizó un análisis de exposición de datos (Modelo vs DTO), determinando qué campos sensibles de la base de datos (como contraseñas o flags internos) deben ocultarse en las respuestas de la API.
- **Bloque 4:** Se redactó la especificación preliminar de los endpoints que servirán de guía para construir el backend RESTful la próxima semana.

## Jornada 31 - Lunes 27 de abril de 2026

### 📝 Resumen del día

- **Bloque 1:** Se realizó un análisis profundo de los perfiles de usuario (Administrador, Técnico, Solicitante), detallando tareas y frecuencia de uso.
- **Bloque 2:** Se diseñó un mapa de flujos (Mermaid) y wireframes funcionales en formato ASCII para las pantallas clave (Dashboard y Detalles), enfocados en la disposición de los datos.
- **Bloque 3:** Se auditaron los wireframes detectando fricciones de uso, proponiendo soluciones como botones de acción rápida, autocompletado y notificaciones _toast_.
- **Bloque 4:** Se volcaron las mejoras de interfaz detectadas al `backlog.md` para su futura implementación.

## Jornada 32 - Martes 28 de abril de 2026

### 📝 Resumen del día

- **Bloque 1 y 2:** Se ha diseñado e implementado una Interfaz Rica de escritorio utilizando Java Swing (`VistaAdmin.java`), incluyendo componentes clave como `JTable`, `JButton` y cuadros de diálogo (`JOptionPane`) para la validación de usuario.
- **Bloque 3:** Se aplicó estrictamente el patrón de diseño MVC, aislando la lógica de negocio en `ControladorAdmin.java`. Se implementaron ActionListeners para modificar el estado de los datos simulados sin acoplar la vista.
- **Bloque 4:** Se redactó un documento comparativo técnico analizando las ventajas, limitaciones y casos de uso óptimos entre clientes de escritorio (stateful, alta productividad) y clientes web (stateless, alta accesibilidad).

## Jornada 33 - Miércoles 29 de abril de 2026

### 📝 Resumen del día

- **Bloques 1 y 2:** Se ha implementado un Dashboard web funcional. Se ha diseñado un archivo `design_system.css` con variables y clases reutilizables, y una librería `componentes.js` que genera HTML dinámico (Cards, Badges, Tablas).
- **Bloque 3:** Se ha garantizado la consistencia visual, asegurando que todos los estados (Ej. "Abierto" = Rojo, "Resuelto" = Verde) comparten exactamente las mismas reglas CSS y funciones de renderizado, evitando discrepancias de diseño.
- **Bloque 4:** Se redactó la documentación técnica explicando el uso del _Design System_ propio, justificando cómo este patrón arquitectónico del frontend mejora la mantenibilidad del proyecto a largo plazo.

## Jornada 34 - Jueves 30 de abril de 2026

### 📝 Resumen del día

- **Bloques 1 y 2:** Se han refactorizado los formularios del frontend (`alta.html`) para convertirlos en "formularios complejos" funcionales. Se implementaron validaciones visuales con JavaScript, mostrando estados claros de carga (`btn-loading`), errores por campo (`input-error`) y mensajes de éxito contextuales.
- **Bloque 3:** Se incorporaron principios básicos de accesibilidad web (a11y). Se definieron outlines claros para la navegación por teclado en el CSS, se usaron etiquetas `<label for="">`, `<fieldset>` y atributos ARIA (`aria-describedby`, `aria-live`) para lectores de pantalla.
- **Bloque 4:** Se redactó un documento con un checklist de accesibilidad y buenas prácticas de UI que servirá como estándar de aceptación para futuras entregas del proyecto.

## Jornada 35 - Viernes 1 de mayo de 2026

### 📝 Resumen del día

- **Bloques 1 y 2:** Se implementó una estrategia de internacionalización (i18n) básica. Se centralizaron los textos del formulario principal en un diccionario JSON (`i18n.js`) y se vincularon mediante atributos `data-i18n`. Se configuraron los idiomas Español e Inglés.
- **Bloque 3:** Se definió una tabla de terminología para estandarizar el lenguaje de la aplicación (Ej: usar siempre "Incidencia" en lugar de "Ticket").
- **Bloque 4:** Se hizo una limpieza de literales dispersos en el frontend (`alta.html`), asegurando un código más limpio, unificado y fácil de mantener para futuras iteraciones y traducciones.

## Jornada 36 - Lunes 4 de mayo de 2026

### 📝 Resumen del día

- **Bloque 1:** Se ha creado la estructura de paquetes para el backend (`config`, `controladores`, `servicios`, `repositorios`, `dtos`, etc.), preparando el terreno para una arquitectura limpia.
- **Bloque 2:** Se ha implementado un servidor HTTP ligero nativo en Java (sin dependencias oscuras) con un endpoint de salud (`/health`) para monitorizar que arranca correctamente y que devuelve JSON.
- **Bloques 3 y 4:** Se ha diseñado la API REST (versión 1) documentando los recursos principales (`/usuarios`, `/incidencias`, etc.) y sus métodos. Además, se redactó un documento de arquitectura explicando el flujo Petición -> Controlador -> Servicio -> Repositorio.

## Jornada 37 - Martes 5 de mayo de 2026

### 📝 Resumen del día

- **Bloques 1 y 3:** Se ha implementado el CRUD REST para `Usuario` e `Incidencia` usando controladores nativos (`HttpHandler`). Se ha conectado conceptualmente la capa web con la capa de servicios, devolviendo JSON limpios.
- **Bloque 2:** Se mapearon correctamente los métodos HTTP a códigos de estado semánticos: GET (`200`), POST (`201`), DELETE (`204`) y errores estructurados (`400`, `404`, `405`).
- **Bloque 4:** Se redactó la tabla de correspondencias HTTP y se generó una colección de pruebas en Postman (`coleccion_api_gestion_it.json`) para facilitar el testeo de los endpoints.

## Jornada 38 - Miércoles 6 de mayo de 2026

### 📝 Resumen del día

- **Bloque 1:** Se ha diseñado el flujo de autenticación creando un middleware (`FiltroAutenticacion`) que intercepta las peticiones exigiendo un token Bearer válido. Se implementó el `LoginController` y un gestor para emitir tokens temporales.
- **Bloque 2:** Se implementó el control de acceso basado en roles (RBAC). Se modificó el `UsuarioController` para restringir operaciones críticas (como `POST` y `DELETE`), devolviendo un error `403 Prohibido` a los usuarios que no son `ADMIN`.
- **Bloque 3:** Se han probado los accesos permitidos y denegados desde Postman (errores `401` y `403`, y acceso `200 OK`), guardando las capturas de evidencia requeridas y actualizando la colección exportable del proyecto.
- **Bloque 4:** Se redactó una nota técnica en la documentación detallando la estrategia de seguridad: almacenamiento de contraseñas, arquitectura stateless basada en tokens, matriz de roles y buenas prácticas.

## Jornada 39 - Jueves 7 de mayo de 2026

### 📝 Resumen del día

- **Bloque 1:** Se crearon las validaciones centralizadas en la capa de entrada de datos (`ValidadorDTO`), controlando de forma estricta los formatos obligatorios de email (presencia de '@' y dominio) y longitudes mínimas para contraseñas.
- **Bloque 2:** Se implementó un middleware centralizado (`ManejadorErroresGlobal`) para interceptar todas las excepciones del dominio y del sistema, unificando las salidas en un formato JSON homogéneo, predecible y trazable.
- **Bloque 3:** Se generó la documentación técnica manual de la API en el archivo `documentacion_api.md`, detallando el catálogo completo de endpoints, los cuerpos JSON requeridos y la matriz de permisos por rol.
- **Bloque 4:** Se ejecutó la batería de pruebas de errores provocando fallos controlados a propósito (email inválido, contraseña corta e ID inexistente), verificando la consistencia de las respuestas y guardando las capturas como evidencias.

## Jornada 40 - Viernes 8 de mayo de 2026

### 📝 Resumen del día

- **Bloque 1 y 2:** Se implementó la automatización de pruebas básicas utilizando el Runner de Postman y scripts de validación (Tests). Se verificaron los códigos de estado HTTP (200 OK, 400 Bad Request) y la estructura del cuerpo de las respuestas (existencia de tokens, arrays y mensajes de error).
- **Bloque 3:** Se definió la estrategia de versionado de la API (v1, v2) basada en URI y se establecieron las normas para mantener el contrato JSON estable y retrocompatible.
- **Bloque 4:** Se redactó el informe final de integración detallando los endpoints operativos, sus métodos, requisitos de autenticación y consideraciones clave para el equipo de Frontend. El backend queda testeado, documentado y listo para consumo.

## Jornada 41 - Lunes 11 de mayo de 2026

### 📝 Resumen del día

- **Bloque 1 y 4:** Se redactó la documentación técnica (`analisis_concurrencia.md`) definiendo las diferencias entre concurrencia, paralelismo, hilos y procesos en el entorno del servidor HTTP. Se establecieron las conclusiones sobre los beneficios de la concurrencia (procesamiento por lotes) frente a los riesgos en código sin estado.
- **Bloque 2 y 3:** Se desarrolló el caso práctico `SimuladorExportacion.java` ejecutando 1000 hilos paralelos. Se demostró la "condición de carrera" evidenciando pérdida de datos al acceder a una variable compartida. Posteriormente, se refactorizó el código implementando un bloque `synchronized` (bloqueo mutuo), resolviendo el acceso simultáneo y garantizando la integridad de los datos.

## Jornada 42 - Martes 12 de mayo de 2026

### 📝 Resumen del día

- **Bloque 1 y 2:** Se implementó el patrón de coordinación Productor-Consumidor mediante una `LinkedBlockingQueue`. Se desacopló la lógica simulando un entorno de Gestión IT donde un hilo encola tareas (creación de incidencias) y otro las procesa de forma diferida (envío de emails).
- **Bloque 3:** Se ejecutaron pruebas variando los ritmos de carga. Se observó cómo un productor rápido genera cuellos de botella (acumulación) requiriendo control de tamaño máximo, mientras que un consumidor rápido mantiene la cola vaciada.
- **Bloque 4:** Se redactó la documentación (`patron_productor_consumidor.md`) proyectando la arquitectura a contextos empresariales reales y listando herramientas de sustitución profesional para Message Brokering, como RabbitMQ y Apache Kafka.

## Jornada 43 - Miércoles 13 de mayo de 2026

### 📝 Resumen del día

- **Bloques 1 y 2:** Se programó un cliente nativo en Java (`ClienteIntegracion.java`) empleando la clase `HttpClient` para consumir la propia API. Se gestionaron diferentes respuestas: 200 OK (éxito), 403 Forbidden (errores semánticos no bloqueantes) y simulaciones de Timeouts, implementando un sistema robusto de reintentos lógicos en la conexión.
- **Bloque 3:** Se aplicó la serialización en el cliente para montar los cuerpos HTTP y la deserialización mediante expresiones regulares para extraer propiedades clave de los JSON recibidos (como el token JWT), validando así el contrato bidireccional.
- **Bloque 4:** Se elaboró un documento estratégico (`esquema_integracion.md`) mapeando las arquitecturas de consumo futuras (Frontend SPA, Apps móviles, scripts batch y sistemas de terceros), afianzando el concepto de sistema distribuido.

## Jornada 44 - Jueves 14 de mayo de 2026

### 📝 Resumen del día

- **Bloques 1, 2 y 3:** Se programó una herramienta CLI independiente (`ExportadorBatch.java`) para gestionar la exportación nocturna de incidencias a formato CSV. El script recibe argumentos externos para definir la ruta de salida, se conecta por JDBC a la base de datos, valida la integridad de los datos (descartando registros anómalos o vacíos) y arroja un log detallado y un resumen final por consola.
- **Bloque 4:** Se redactó la documentación técnica (`guia_despliegue_batch.md`) especificando el protocolo para montar esta tarea en un servidor Linux. Se detallaron las dependencias, la creación de un script `.sh` ejecutor, la configuración de la expresión en `Cron` para su disparo automático a las 02:00 AM y la redirección de logs al sistema.

## Jornada 45 - Viernes 15 de mayo de 2026

### 📝 Resumen del día

- **Bloques 1, 2 y 4:** Se implementó una demo técnica (`ProcesoResiliente.java`) enfocada en la observabilidad y tolerancia a fallos. Se diseñó un logger estructurado por niveles (INFO, WARN, ERROR). Se introdujeron patrones de resiliencia como la validación de variables de entorno y un bucle de reintentos con tiempos de espera (`Thread.sleep`) ante caídas de servicios dependientes, culminando en mensajes de error accionables.
- **Bloque 3:** Se redactó la documentación operativa técnica (`checklist_supervision.md`) proporcionando a los equipos de soporte (Troubleshooting) una guía rápida de diagnóstico y recuperación ante caídas de la API, interrupciones en procesos Batch y fallos silenciosos en tareas programadas por Cron.

## Jornada 46 - Lunes 18 de mayo de 2026

### 📝 Resumen del día

- **Bloques 1 y 2:** Se configuró el entorno nativo en Android Studio (Java/XML). Se diseñaron las 5 pantallas base (Login, Listado, Detalle, Creación, Perfil) aplicando estándares de Material Design 3 (CardViews, TextInputs outlinelined) y validación de formularios.
- **Bloques 3 y 4:** Se implementó la navegación mediante `Intents` y el manejo de la pila de actividades con botones de retroceso. Se documentó la arquitectura en `arquitectura_movil.md` justificando el enfoque nativo.
