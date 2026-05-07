# Mejoras Aplicadas y Checklist de Accesibilidad

## Listado de Mejoras Aplicadas (Formulario de Alta)

1. **Agrupación lógica:** Se ha dividido el formulario en `fieldset` con `legend` para estructurar la información (Básica y Detalles).
2. **Textos de ayuda (Help text):** Se añadieron instrucciones claras debajo de campos críticos asociados mediante `aria-describedby`.
3. **Validación progresiva y resumen:** En lugar de validaciones nativas bruscas, se usa JS para capturar errores, pintarlos en rojo (`.input-error`) y generar un resumen dinámico superior (`#resumen-errores`).
4. **Feedback de estado (Loading/Success):** Al enviar el formulario, el botón se deshabilita (`disabled=true`), cambia su estado visual a carga (⏳) y posteriormente a verde de éxito.

## Checklist de Revisión de Interfaz (Accesibilidad Básica)

Esta lista se utilizará para validar futuras pantallas del proyecto:

- [x] **Foco visible:** ¿Todos los elementos interactivos tienen un `outline` claro al navegar con el teclado (Tab)?
- [x] **Etiquetas asociadas:** ¿Todos los `<input>` y `<select>` tienen un `<label for="id_del_input">` explícito?
- [x] **Avisos para Screen Readers:** ¿Los mensajes de error importantes tienen atributos como `role="alert"` o `aria-live="assertive"`?
- [x] **Contraste:** ¿El contraste del texto sobre el fondo cumple mínimos (ej: rojo oscuro para alertas de error sobre fondo claro)?
- [x] **Dependencia del color:** ¿La información se transmite no solo mediante color? (Ej: Los errores tienen un borde rojo, pero también muestran un texto explícito).
- [x] **Estado de Sistema visible:** ¿Se informa al usuario de que el sistema está trabajando tras pulsar enviar?
