USE proyecto_incidencias;

-- ==========================================
-- BATERÍA DE CONSULTAS (SELECTs)
-- ==========================================

-- 1. Incidencias abiertas por técnico
SELECT u.email AS Tecnico, COUNT(i.id_incidencia) AS Total_Abiertas
FROM USUARIOS u
    JOIN INCIDENCIAS i ON u.id_usuario = i.id_tecnico
WHERE
    i.estado IN ('Abierto', 'En Progreso')
GROUP BY
    u.id_usuario;

-- 2. Activos con más incidencias (Detectar equipos problemáticos)
SELECT a.codigo_inventario, COUNT(i.id_incidencia) AS Numero_Averias
FROM ACTIVOS a
    JOIN INCIDENCIAS i ON a.id_activo = i.id_activo
GROUP BY
    a.id_activo
ORDER BY Numero_Averias DESC;

-- 3. Tiempo medio de resolución (en horas)
SELECT AVG(
        TIMESTAMPDIFF(
            HOUR, fecha_creacion, fecha_cierre
        )
    ) AS Horas_Medias_Resolucion
FROM INCIDENCIAS
WHERE
    estado = 'Cerrado';

-- 4. Usuarios sin actividad reciente (Clientes sin tickets creados)
SELECT u.email
FROM USUARIOS u
    LEFT JOIN INCIDENCIAS i ON u.id_usuario = i.id_creador
WHERE
    i.id_incidencia IS NULL
    AND u.id_rol = 3;

-- ==========================================
-- ACTUALIZACIONES Y BORRADOS SEGUROS
-- ==========================================

-- UPDATE SEGURO: Asignar técnico solo si el ticket sigue abierto y no tiene técnico ya
UPDATE INCIDENCIAS
SET
    id_tecnico = 3,
    estado = 'En Progreso'
WHERE
    id_incidencia = 1
    AND estado = 'Abierto'
    AND id_tecnico IS NULL;

-- BORRADO LÓGICO: Dar de baja un equipo que no se puede reparar
UPDATE ACTIVOS
SET
    activo = FALSE,
    estado = 'Baja Definitiva'
WHERE
    codigo_inventario = 'PC-002'
    AND estado = 'En reparación';