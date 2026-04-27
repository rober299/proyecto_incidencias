# Checklist de Accesibilidad Básica

Se han implementado y verificado las siguientes pautas de accesibilidad en las 5 vistas iniciales:

- [x] **Jerarquía de Encabezados:** Respeto estricto del orden `<h1>` a `<h6>`. Único `<h1>` por página identificando la vista actual.
- [x] **Etiquetado de Formularios:** Uso innegociable de `<label for="id_del_input">` en cada elemento interactivo (`input`, `select`, `textarea`) para lectores de pantalla.
- [x] **Landmarks Semánticos:** Uso de `<header>`, `<nav>`, `<main>`, `<section>`, `<article>` y `<footer>` en lugar de divisiones genéricas `<div>`.
- [x] **Atributos ARIA:** Uso de `aria-label` en barras de navegación y enlaces genéricos (ej: `aria-label="Ver detalles de la incidencia 101"`).
- [x] **Semántica Tabular:** Las tablas utilizan `<thead>`, `<tbody>` y encabezados identificados con `<th scope="col">` para dictar correctamente columnas.
