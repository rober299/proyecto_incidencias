USE proyecto_incidencias;

-- ==========================================
-- VISTAS (Lectura Frecuente)
-- ==========================================

-- Vista 1: Resumen del Panel del Técnico (Tickets Abiertos y En Progreso)
CREATE OR REPLACE VIEW v_panel_tecnico AS
SELECT
    i.id_incidencia AS Ticket,
    i.titulo AS Asunto,
    i.prioridad AS Nivel_Urgencia,
    c.nombre AS Categoria,
    u.email AS Asignado_A,
    i.fecha_creacion AS Fecha_Apertura
FROM
    INCIDENCIAS i
    JOIN CATEGORIAS c ON i.id_categoria = c.id_categoria
    LEFT JOIN USUARIOS u ON i.id_tecnico = u.id_usuario
WHERE
    i.estado IN ('Abierto', 'En Progreso');

-- Vista 2: Histórico de Incidencias Resueltas
CREATE OR REPLACE VIEW v_historico_resueltas AS
SELECT
    i.id_incidencia,
    i.titulo,
    a.codigo_inventario AS Equipo_Afectado,
    TIMESTAMPDIFF(
        HOUR,
        i.fecha_creacion,
        i.fecha_cierre
    ) AS Horas_Resolucion
FROM INCIDENCIAS i
    LEFT JOIN ACTIVOS a ON i.id_activo = a.id_activo
WHERE
    i.estado = 'Cerrado';

-- ==========================================
-- PROCEDIMIENTO ALMACENADO (Rutina)
-- ==========================================
-- Operación repetitiva: Cerrar un ticket y auditarlo al mismo tiempo de forma segura.

DELIMITER $$

CREATE PROCEDURE sp_cerrar_incidencia(IN p_id_incidencia INT, IN p_id_tecnico INT)
BEGIN
    -- Actualizamos el estado y sellamos la fecha
    UPDATE INCIDENCIAS 
    SET estado = 'Cerrado', fecha_cierre = CURRENT_TIMESTAMP 
    WHERE id_incidencia = p_id_incidencia;
    
    -- Registramos la acción en auditoría automáticamente
    INSERT INTO AUDITORIA (accion, id_usuario, id_incidencia) 
    VALUES ('CIERRE_TICKET_PROCEDIMIENTO', p_id_tecnico, p_id_incidencia);
END$$

DELIMITER;

-- ==========================================
-- TRIGGER (Disparador Automático)
-- ==========================================
-- Valor real: Si alguien (o alguna app) cambia el estado de un ticket,
-- el trigger lo detecta y lo guarda en el historial de auditoría SÍ o SÍ.

DELIMITER $$

CREATE TRIGGER trg_auditoria_estado
AFTER UPDATE ON INCIDENCIAS
FOR EACH ROW
BEGIN
    -- Solo disparamos si el estado realmente ha cambiado
    IF OLD.estado != NEW.estado THEN
        INSERT INTO AUDITORIA (accion, id_usuario, id_incidencia)
        VALUES (
            CONCAT('CAMBIO_ESTADO: ', OLD.estado, ' -> ', NEW.estado), 
            COALESCE(NEW.id_tecnico, NEW.id_creador), -- Usa el técnico, o el creador si no hay técnico
            NEW.id_incidencia
        );
    END IF;
END$$

DELIMITER;