// SCRIPT DE TABLA DINÁMICA CONSUMIENDO JSON EXTERNO

document.addEventListener('DOMContentLoaded', () => {
    const tbody = document.getElementById('tabla-incidencias-body');

    // Función para renderizar el HTML
    function renderizarTabla(datos) {
        if (!tbody) return;
        tbody.innerHTML = ''; // Limpiamos la tabla

        datos.forEach(incidencia => {
            const fila = document.createElement('tr');
            fila.innerHTML = `
                <td>${incidencia.id}</td>
                <td>${incidencia.titulo}</td>
                <td><strong>${incidencia.estado}</strong></td>
                <td>${incidencia.prioridad}</td>
                <td><a href="detalle.html" aria-label="Ver detalles de la incidencia ${incidencia.id}">Ver Detalles</a></td>
            `;
            tbody.appendChild(fila);
        });
    }

    // Petición asíncrona simulando una llamada a una API real (Fetch API)
    fetch('../data/incidencias.json')
        .then(response => {
            if (!response.ok) {
                throw new Error('Error en la red al intentar cargar el JSON');
            }
            return response.json();
        })
        .then(data => {
            renderizarTabla(data);
        })
        .catch(error => {
            console.error('Error cargando las incidencias:', error);
            if(tbody) {
                tbody.innerHTML = '<tr><td colspan="5" style="color:red; text-align:center;">Error cargando los datos. Asegúrate de usar un servidor local (Go Live).</td></tr>';
            }
        });
});