-- Procedimiento Almacenado: Calcular Índice de Accidentabilidad por Proyecto
-- Fórmula simplificada: (N° Accidentes / Total Horas Hombre) * Factor_K

CREATE OR REPLACE FUNCTION calculate_accident_index(project_id_param BIGINT)
RETURNS NUMERIC AS $$
DECLARE
    total_accidents INTEGER;
    total_hours_man BIGINT;
    accident_index NUMERIC;
    FACTOR_K CONSTANT INTEGER := 200000; -- Constante estándar de seguridad industrial
BEGIN
    -- Contar hallazgos graves o accidentes registrados
    SELECT COUNT(*) INTO total_accidents 
    FROM hallazgos_preventivos 
    WHERE project_id = project_id_param AND severity = 'GRAVE';

    -- Sumar horas hombre asignadas al proyecto
    SELECT COALESCE(SUM(allocated_hours), 0) INTO total_hours_man 
    FROM assignments 
    WHERE project_id = project_id_param;

    IF total_hours_man > 0 THEN
        accident_index := (total_accidents::NUMERIC / total_hours_man::NUMERIC) * FACTOR_K;
    ELSE
        accident_index := 0;
    END IF;

    RETURN accident_index;
END;
$$ LANGUAGE plpgsql;
