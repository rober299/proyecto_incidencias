// Lógica de carga del Dashboard utilizando componentes reutilizables

document.addEventListener("DOMContentLoaded", () => {
    // 1. Datos simulados (como si vinieran de la API)
    const statsData = [
        { titulo: "Incidencias Abiertas", valor: 14, icono: "🚨" },
        { titulo: "Urgentes", valor: 3, icono: "🔥" },
        { titulo: "Asignadas a mí", valor: 5, icono: "👨‍💻" },
        { titulo: "Resueltas hoy", valor: 8, icono: "✅" }
    ];

    const recientesData = [
        { id: 105, titulo: "Fallo en servidor de correo", estado: "Abierto", prioridad: "Alta" },
        { id: 104, titulo: "Ratón no funciona", estado: "En Progreso", prioridad: "Baja" },
        { id: 103, titulo: "Licencia de software caducada", estado: "Cerrado", prioridad: "Media" }
    ];

    // 2. Renderizar las tarjetas de estadísticas reutilizando UI.crearTarjetaStat
    const statsContainer = document.getElementById("stats-container");
    let statsHTML = "";
    statsData.forEach(stat => {
        statsHTML += UI.crearTarjetaStat(stat.titulo, stat.valor, stat.icono);
    });
    statsContainer.innerHTML = statsHTML;

    // 3. Renderizar la tabla reutilizando UI.crearFilaTabla
    const tablaContainer = document.getElementById("tabla-recientes");
    let tablaHTML = "";
    recientesData.forEach(inc => {
        tablaHTML += UI.crearFilaTabla(inc.id, inc.titulo, inc.estado, inc.prioridad);
    });
    tablaContainer.innerHTML = tablaHTML;
});