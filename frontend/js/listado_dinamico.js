// SCRIPT DE TABLA DINÁMICA CON DATOS SIMULADOS (JSON)

document.addEventListener('DOMContentLoaded', () => {
    const tbody = document.getElementById('tabla-incidencias-body');

    // Datos simulados (Mock JSON Local)
    const incidenciasJSON = [
        { id: 101, titulo: "Fallo conexión VPN", estado: "Abierto", prioridad: "Alta" },
        { id: 102, titulo: "Impresora sin tóner en planta 2", estado: "En Progreso", prioridad: "Baja" },
        { id: 103, titulo: "Pantalla azul en PC-05", estado: "Abierto", prioridad: "Crítica" },
        { id: 104, titulo: "No puedo acceder al ERP", estado: "Abierto", prioridad: "Media" }
    ];

    // Función para renderizar el cliente
    function renderizarTabla(datos) {
        if (!tbody) return;
        
        tbody.innerHTML = ''; // Limpiamos la tabla por seguridad

        datos.forEach(incidencia => {
            // Creamos la fila (tr)
            const fila = document.createElement('tr');

            // Rellenamos el HTML interno de la fila (DOM Manipulation)
            fila.innerHTML = `
                <td>${incidencia.id}</td>
                <td>${incidencia.titulo}</td>
                <td><strong>${incidencia.estado}</strong></td>
                <td>${incidencia.prioridad}</td>
                <td><a href="detalle.html" aria-label="Ver detalles de la incidencia ${incidencia.id}">Ver Detalles</a></td>
            `;

            // Añadimos la fila al cuerpo de la tabla
            tbody.appendChild(fila);
        });
    }

    // Ejecutamos la función al cargar la página
    renderizarTabla(incidenciasJSON);
});