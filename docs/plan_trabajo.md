# Plan de Trabajo y Cronograma (Proyecto 3 meses)

## 1. Cronograma Resumido por Iteraciones (Sprints)

El desarrollo se divide en 6 Sprints de 2 semanas cada uno, siguiendo metodología Ágil.

- **Sprint 1-2 (Semanas 1-4): Base de Datos y Autenticación**
  - _Entregable mínimo:_ Esquema BBDD desplegado y API de Login/Registro funcional.
- **Sprint 3 (Semanas 5-6): Backend y Lógica de Negocio (Tickets)**
  - _Entregable mínimo:_ CRUD de incidencias, subida de adjuntos y cambio de estados.
- **Sprint 4-5 (Semanas 7-10): Frontend y Panel de Control**
  - _Entregable mínimo:_ Interfaz de usuario conectada al backend, vistas filtradas por rol.
- **Sprint 6 (Semanas 11-12): Pruebas, Corrección y Despliegue**
  - _Entregable mínimo:_ Aplicación estable, exportación a CSV implementada y despliegue final.

## 2. Dependencias entre Tareas

1. **Base de Datos -> Backend:** No se pueden programar los _endpoints_ sin tener las tablas y relaciones definidas.
2. **Autenticación -> Tickets:** No se puede desarrollar la creación de tickets sin tener el ID del usuario logueado.
3. **Backend -> Frontend:** El panel visual requiere que la API REST entregue datos en formato JSON previamente.

## 3. Riesgos Técnicos Identificados

- **Curva de aprendizaje:** Posibles retrasos si hay problemas con el manejo de archivos adjuntos. _Mitigación:_ Hacer una prueba de concepto en el Sprint 3.
- **Pérdida de datos:** Fallos en el servidor. _Mitigación:_ Implementar el script de backups diarios (`backup.bat`) desde el día 1.
