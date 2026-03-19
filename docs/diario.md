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
