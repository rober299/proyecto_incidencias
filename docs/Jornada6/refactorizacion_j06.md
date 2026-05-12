# Informe de Refactorización - Jornada 06

A continuación se detalla la comparativa entre la versión inicial y la refactorizada:

1. **Eliminación de duplicidades:** - Se ha condensado la validación del usuario en un retorno directo de la condición lógica (`return email.contains("@") && password.length() >= 8;`), eliminando los múltiples `if` redundantes.
2. **Mejora de nombres (Clean Code):**
   - El método original pasó a llamarse `esUsuarioValido` y la función de filtrado a `filtrarTicketsPorEstadoYFecha` para eliminar ambigüedades.
3. **Separación de responsabilidades:**
   - Se ha sacado toda la lógica de los `System.out.println` fuera de los métodos de negocio y del `main` principal, encapsulándolos en métodos privados (`ejecutarPruebas...`). Esto separa la entrada/salida del procesamiento puro.
