CREATE OR REPLACE PROCEDURE pr_create_ticket(
    IN t_subject VARCHAR,
    IN t_description VARCHAR,
    IN t_created_by UUID,
    OUT new_ticket_id UUID
)
LANGUAGE plpgsql
AS $$
DECLARE
    v_id uuid;
BEGIN
    INSERT INTO tickets (id, subject, description, created_by, status, created_at, updated_at)
    VALUES (gen_random_uuid(), t_subject, t_description, t_created_by::uuid, 'OPEN', NOW(), NOW())
    RETURNING id INTO v_id;
    new_ticket_id := v_id;

    IF new_ticket_id IS NULL THEN
        RAISE EXCEPTION 'Error creating ticket: failed to generate ID';
    END IF;

END;
$$;
