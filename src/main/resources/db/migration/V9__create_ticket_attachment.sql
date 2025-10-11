CREATE OR REPLACE PROCEDURE pr_add_ticket_attachment(
    p_ticket_id UUID,
    p_created_by UUID,
    p_filename TEXT,
    OUT p_attachment_id UUID
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO ticket_attachments (id, ticket_id, created_by, filename)
    VALUES (gen_random_uuid(),p_ticket_id, p_created_by, p_filename)
    RETURNING id INTO p_attachment_id;
END;
$$;