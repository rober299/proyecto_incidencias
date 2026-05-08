// DICCIONARIO CENTRALIZADO DE MENSAJES E INTERNACIONALIZACIÓN (i18n)

const traducciones = {
    es: {
        "nav.dashboard": "Panel de Control",
        "nav.list": "Ver Incidencias",
        "nav.new": "Nueva Incidencia",
        "header.title": "Registrar Nueva Incidencia",
        "form.basic_info": "Información de la Incidencia",
        "form.title_label": "Título breve (Obligatorio):",
        "form.title_help": "Resume el problema en una frase (ej. 'Error login VPN').",
        "form.category_label": "Categoría:",
        "form.category_select": "-- Selecciona una categoría --",
        "form.cat_hardware": "Hardware",
        "form.cat_software": "Software",
        "form.cat_network": "Redes",
        "form.details": "Detalles del Problema",
        "form.desc_label": "Descripción detallada:",
        "form.desc_help": "Indica los pasos para reproducir el error.",
        "form.submit": "Crear Incidencia",
        "msg.error_summary": "Por favor, corrige los siguientes errores:"
    },
    en: {
        "nav.dashboard": "Dashboard",
        "nav.list": "View Issues",
        "nav.new": "New Issue",
        "header.title": "Register New Issue",
        "form.basic_info": "Issue Information",
        "form.title_label": "Brief title (Required):",
        "form.title_help": "Summarize the problem in one sentence (e.g., 'VPN login error').",
        "form.category_label": "Category:",
        "form.category_select": "-- Select a category --",
        "form.cat_hardware": "Hardware",
        "form.cat_software": "Software",
        "form.cat_network": "Network",
        "form.details": "Problem Details",
        "form.desc_label": "Detailed description:",
        "form.desc_help": "Provide the steps to reproduce the error.",
        "form.submit": "Create Issue",
        "msg.error_summary": "Please fix the following errors:"
    }
};

// Función para cambiar todo el texto de la pantalla según el idioma elegido
function cambiarIdioma(idioma) {
    document.querySelectorAll('[data-i18n]').forEach(elemento => {
        const clave = elemento.getAttribute('data-i18n');
        if (traducciones[idioma] && traducciones[idioma][clave]) {
            elemento.textContent = traducciones[idioma][clave];
        }
    });
    document.documentElement.lang = idioma; // Actualiza el lang del HTML para accesibilidad
}

// Inicializador
document.addEventListener('DOMContentLoaded', () => {
    const selectorIdioma = document.getElementById('selector-idioma');
    if (selectorIdioma) {
        selectorIdioma.addEventListener('change', (e) => {
            cambiarIdioma(e.target.value);
        });
        // Forzamos el español por defecto al cargar
        cambiarIdioma('es');
    }
});