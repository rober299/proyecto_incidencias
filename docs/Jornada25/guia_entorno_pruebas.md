# Guía de Reconstrucción del Entorno de Pruebas

Para garantizar que las pruebas de integración (`MainJornada25_Integracion.java`) se ejecutan siempre bajo el mismo estado conocido y no alteran los datos reales, siga este procedimiento:

## 1. Reconstrucción de la Base de Datos

1. Abra el cliente de base de datos (MySQL Workbench, DBeaver o terminal).
2. Localice el archivo `sql/init_test_env.sql` incluido en este repositorio.
3. Ejecute el script en su totalidad.
   - **Efecto:** El script contiene la sentencia `DROP DATABASE IF EXISTS proyecto_incidencias_test`. Esto purgará cualquier rastro de pruebas anteriores y levantará un esquema limpio con las tablas actualizadas y los datos semilla (2 usuarios de prueba y 1 incidencia base).

## 2. Configuración en la Aplicación

El proyecto cuenta con un archivo `src/META-INF/persistence.xml` que incluye dos unidades de persistencia separadas:

- `IncidenciasPU`: Conecta a `proyecto_incidencias` (Desarrollo).
- `IncidenciasTestPU`: Conecta a `proyecto_incidencias_test` (Pruebas).

Para ejecutar los tests, la clase de pruebas inicializa específicamente `EntityManagerFactory emf = Persistence.createEntityManagerFactory("IncidenciasTestPU");`.

## 3. Ejecución

Simplemente ejecute el archivo `MainJornada25_Integracion.java`. Al finalizar, si lo desea, puede volver al paso 1 para resetear el entorno.
