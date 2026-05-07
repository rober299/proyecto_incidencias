document.addEventListener('DOMContentLoaded', () => {
    
    const formAlta = document.getElementById('form-alta');
    if (formAlta) {
        formAlta.addEventListener('submit', (e) => {
            e.preventDefault();
            let isValid = true;

            // Referencias
            const titulo = document.getElementById('incidencia-titulo');
            const categoria = document.getElementById('incidencia-categoria');
            const desc = document.getElementById('incidencia-desc');
            const btnSubmit = document.getElementById('btn-submit');
            const resumenErrores = document.getElementById('resumen-errores');
            const listaErrores = document.getElementById('lista-errores');

            // Reset inicial
            resetError(titulo, document.getElementById('error-titulo'));
            resetError(categoria, document.getElementById('error-categoria'));
            resetError(desc, document.getElementById('error-desc'));
            listaErrores.innerHTML = '';
            resumenErrores.style.display = 'none';

            // Validación de Título
            if (titulo.value.trim().length < 5) {
                showError(titulo, document.getElementById('error-titulo'), 'El título debe tener al menos 5 caracteres.', listaErrores);
                isValid = false;
            }

            // Validación de Categoría
            if (categoria.value === "") {
                showError(categoria, document.getElementById('error-categoria'), 'Debes seleccionar una categoría.', listaErrores);
                isValid = false;
            }

            // Validación de Descripción
            if (desc.value.trim().length < 10) {
                showError(desc, document.getElementById('error-desc'), 'La descripción debe tener al menos 10 caracteres.', listaErrores);
                isValid = false;
            }

            if (!isValid) {
                resumenErrores.style.display = 'block';
                window.scrollTo(0, 0); // Lleva al usuario al resumen de errores
                return;
            }

            // ESTADO DE CARGA (SIMULADO)
            btnSubmit.disabled = true;
            btnSubmit.classList.add('btn-loading');
            btnSubmit.textContent = 'Procesando...';

            setTimeout(() => {
                const panelExito = document.getElementById('panel-exito');
                panelExito.style.display = 'block';
                formAlta.style.opacity = '0.5';
                formAlta.style.pointerEvents = 'none';

                setTimeout(() => {
                    window.location.href = 'listado.html';
                }, 2000);
            }, 1500);
        });
    }

    function showError(inputElement, errorElement, message, listaErrores) {
        inputElement.classList.add('input-error');
        errorElement.textContent = message;
        errorElement.style.display = 'block';
        
        // Añadir al resumen de errores
        const li = document.createElement('li');
        li.textContent = message;
        listaErrores.appendChild(li);
    }

    function resetError(inputElement, errorElement) {
        inputElement.classList.remove('input-error');
        errorElement.textContent = '';
        errorElement.style.display = 'none';
    }
});