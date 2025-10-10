CREATE OR REPLACE PROCEDURE pr_add_ticket_interaction(
    IN p_ticket_id UUID,
    IN p_user_id UUID,
    IN p_message TEXT,
    IN p_status TEXT,
    OUT p_interaction_id UUID
)
LANGUAGE plpgsql
AS $$

BEGIN
    INSERT INTO ticket_interactions (id, ticket_id, sent_by_user_id, message, status)
    VALUES (gen_random_uuid(), p_ticket_id, p_user_id, p_message, p_status)
    RETURNING id INTO p_interaction_id;

    IF p_interaction_id IS NULL THEN
        RAISE EXCEPTION 'Error adding ticket interaction: failed to generate ID';
    END IF;
END;
$$;
