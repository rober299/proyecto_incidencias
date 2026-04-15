USE proyecto_incidencias;

-- ==========================================
-- OPERACIÓN TRANSACCIONAL
-- ==========================================
-- Caso: Un equipo portátil (PC-002) que estaba en reparación se da de baja definitiva.
-- Hay que cerrar su incidencia asociada (Ticket 2) Y dar de baja el activo.
-- Si una de las dos falla, NO se debe hacer ninguna.

-- 1. DESACTIVAMOS AUTOCOMMIT para controlar nosotros el flujo
SET autocommit = 0;

-- 2. INICIO DE LA TRANSACCIÓN
START TRANSACTION;

-- Paso A: Actualizamos el activo
UPDATE ACTIVOS
SET
    activo = FALSE,
    estado = 'Baja Definitiva'
WHERE
    codigo_inventario = 'PC-002';

-- Paso B: Simulamos un cierre de su ticket asociado
UPDATE INCIDENCIAS
SET
    estado = 'Cerrado',
    fecha_cierre = CURRENT_TIMESTAMP
WHERE
    id_incidencia = 2;

-- ESCENARIO DE ÉXITO (Para guardarlo todo descomentaríamos COMMIT)
-- COMMIT;

-- ESCENARIO DE FALLO (Simulamos que el programa detectó un error antes de terminar)
-- Al hacer ROLLBACK, la base de datos deshace el Paso A y el Paso B mágicamente.
ROLLBACK;

-- 3. VOLVEMOS A ACTIVAR AUTOCOMMIT (Buenas prácticas)
SET autocommit = 1;