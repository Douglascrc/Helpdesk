CREATE TABLE ticket_attachments(
    id uuid PRIMARY KEY,
    ticket_id uuid NOT NULL,
    ticket_interaction_id uuid NULL,
    filename VARCHAR(200) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_by uuid NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_by uuid NULL
);
