// Biblioteca de componentes UI reutilizables
const UI = {
    // Genera una tarjeta de estadística
    crearTarjetaStat: (titulo, valor, icono) => `
        <div class="ui-card">
            <div style="font-size: 2rem;">${icono}</div>
            <div>
                <h3>${valor}</h3>
                <p>${titulo}</p>
            </div>
        </div>
    `,

    crearBadge: (estado) => {
        let clase = "cerrado";
        if (estado.toLowerCase() === "abierto") clase = "abierto";
        if (estado.toLowerCase() === "en progreso") clase = "progreso";
        return `<span class="ui-badge ${clase}">${estado}</span>`;
    },

    // Genera una fila de tabla estandarizada
    crearFilaTabla: (id, titulo, estado, prioridad) => `
        <tr>
            <td>#${id}</td>
            <td>${titulo}</td>
            <td>${UI.crearBadge(estado)}</td>
            <td>${prioridad}</td>
        </tr>
    `
};