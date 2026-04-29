# Consultas XPath

Se han diseñado las siguientes expresiones XPath para extraer información concreta del archivo `incidencias.xml`:

1. **Obtener todas las incidencias urgentes (Alta o Crítica):**
   `//incidencia[@prioridad='Alta' or @prioridad='Critica']`

2. **Obtener incidencias activas (Que no estén cerradas):**
   `//incidencia[@estado='Abierto' or @estado='En Progreso']`

3. **Obtener los títulos de las incidencias asignadas a un técnico concreto:**
   `//incidencia[tecnico_asignado='robert.chislea@soporte.com']/titulo/text()`

4. **Obtener incidencias filtradas por categoría (ej. Redes):**
   `//incidencia[categoria='Redes']`
