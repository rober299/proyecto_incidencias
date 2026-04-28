// SCRIPT DE VALIDACIÓN EN CLIENTE Y MANIPULACIÓN DEL DOM
document.addEventListener('DOMContentLoaded', () => {
    
    // 1. VALIDACIÓN FORMULARIO DE LOGIN
    const formLogin = document.getElementById('form-login');
    if (formLogin) {
        formLogin.addEventListener('submit', (e) => {
            let isValid = true;
            
            // Referencias DOM
            const emailInput = document.getElementById('user-email');
            const passwordInput = document.getElementById('user-password');
            const errorEmail = document.getElementById('error-email');
            const errorPassword = document.getElementById('error-password');

            // Resetear errores (Manipulación DOM)
            resetError(emailInput, errorEmail);
            resetError(passwordInput, errorPassword);

            // Validación de Email (Formato)
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailInput.value.trim()) {
                showError(emailInput, errorEmail, 'El correo electrónico es obligatorio.');
                isValid = false;
            } else if (!emailRegex.test(emailInput.value.trim())) {
                showError(emailInput, errorEmail, 'Introduce un formato de correo válido (ej: usuario@empresa.com).');
                isValid = false;
            }

            // Validación de Contraseña (Obligatorio)
            if (!passwordInput.value.trim()) {
                showError(passwordInput, errorPassword, 'La contraseña es obligatoria.');
                isValid = false;
            }

            // Si hay errores, evitamos que el formulario se envíe
            if (!isValid) {
                e.preventDefault();
            }
        });
    }

    // 2. VALIDACIÓN FORMULARIO DE ALTA (INCIDENCIA)
    const formAlta = document.getElementById('form-alta');
    if (formAlta) {
        formAlta.addEventListener('submit', (e) => {
            e.preventDefault(); // Paramos siempre el envío real para hacer la demo
            let isValid = true;

            const titulo = document.getElementById('incidencia-titulo');
            const categoria = document.getElementById('incidencia-categoria');
            const desc = document.getElementById('incidencia-desc');

            resetError(titulo, document.getElementById('error-titulo'));
            resetError(categoria, document.getElementById('error-categoria'));
            resetError(desc, document.getElementById('error-desc'));

            // Longitud mínima del título
            if (titulo.value.trim().length < 5) {
                showError(titulo, document.getElementById('error-titulo'), 'El título debe tener al menos 5 caracteres.');
                isValid = false;
            }

            // Categoría obligatoria
            if (categoria.value === "") {
                showError(categoria, document.getElementById('error-categoria'), 'Debes seleccionar una categoría.');
                isValid = false;
            }

            // Longitud mínima de la descripción
            if (desc.value.trim().length < 10) {
                showError(desc, document.getElementById('error-desc'), 'Por favor, detalla más el problema (mínimo 10 caracteres).');
                isValid = false;
            }

            // Manipulación del DOM: Alternar paneles si todo está bien
            if (isValid) {
                const panelExito = document.getElementById('panel-exito');
                panelExito.style.display = 'block'; // Muestra el panel
                formAlta.reset(); // Limpia el formulario
                
                // Simula una redirección tras unos segundos
                setTimeout(() => {
                    window.location.href = 'listado.html';
                }, 2000);
            }
        });
    }

    // Funciones auxiliares para manipulación del DOM
    function showError(inputElement, errorElement, message) {
        inputElement.classList.add('input-error');
        errorElement.textContent = message;
        errorElement.style.display = 'block';
    }

    function resetError(inputElement, errorElement) {
        inputElement.classList.remove('input-error');
        errorElement.textContent = '';
        errorElement.style.display = 'none';
    }
});