# Informe Comparativo: CSV vs JSON

A la hora de persistir y transmitir datos fuera de una base de datos, CSV y JSON son los reyes indiscutibles. Elegir uno u otro depende totalmente de la complejidad de los datos y de quién los va a consumir.

## 1. Archivos CSV (Comma-Separated Values)

Es el formato de texto plano más simple, estructurado en filas y columnas separadas por un delimitador (normalmente una coma o punto y coma).

- **Ventajas:** Extremadamente ligero (ocupa muy poco en disco). Es el estándar universal para hojas de cálculo (Excel, Google Sheets). Su lectura/escritura es secuencial y rapidísima.
- **Limitaciones:** Estructura plana y rígida. No soporta jerarquías ni objetos anidados (no puedes meter un array dentro de una celda fácilmente). Los tipos de datos se pierden (todo es texto y hay que parsearlo al leer).
- **Casos de uso ideales:** Volcados masivos de datos (ej. exportar 100.000 registros de incidencias históricas), informes para analistas de negocio, migraciones entre bases de datos relacionales.
- **Riesgos habituales:** Errores de parseo si un campo de texto contiene la misma coma que se usa como separador (requiere entrecomillar bien los campos).

## 2. Archivos JSON (JavaScript Object Notation)

Es un formato basado en pares de `clave: valor` y listas, agrupados por llaves `{}` y corchetes `[]`.

- **Ventajas:** Estructura jerárquica. Puede representar modelos de datos complejos (ej. un Usuario que dentro tiene una lista de Incidencias). Mantiene los tipos de datos básicos (números, booleanos, nulos, cadenas). Es el estándar absoluto para las APIs web modernas.
- **Limitaciones:** Más pesado que el CSV (al repetir el nombre de las claves en cada objeto, el archivo engorda). Para leerlo de forma segura en lenguajes fuertemente tipados como Java, suele requerir librerías externas (como Jackson o Gson) o crear parsers manuales tediosos.
- **Casos de uso ideales:** Comunicación entre el frontend (web/móvil) y el backend, archivos de configuración complejos, bases de datos NoSQL (como MongoDB).
- **Riesgos habituales:** Consumo excesivo de memoria RAM si se intenta cargar un archivo JSON gigantesco de golpe, en lugar de procesarlo por trozos (streaming).

## Conclusión

Para el sistema de incidencias:

- Usaremos **CSV** para que el cliente pueda descargar un listado histórico y abrirlo en Excel.
- Usaremos **JSON** para enviar los datos de una incidencia concreta desde la aplicación móvil al servidor central.
