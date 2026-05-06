-- Stored Procedure to calculate total availability for all resources
-- This is a PostgreSQL example

CREATE OR REPLACE FUNCTION calculate_total_availability()
RETURNS TABLE (
    total_capacity_sum INTEGER,
    available_capacity_sum INTEGER,
    utilization_percentage NUMERIC
) AS $$
BEGIN
    RETURN QUERY
    SELECT 
        SUM(total_capacity)::INTEGER,
        SUM(available_capacity)::INTEGER,
        CASE 
            WHEN SUM(total_capacity) > 0 
            THEN (1 - (SUM(available_capacity)::NUMERIC / SUM(total_capacity)::NUMERIC)) * 100
            ELSE 0 
        END as utilization_percentage
    FROM resources;
END;
$$ LANGUAGE plpgsql;
