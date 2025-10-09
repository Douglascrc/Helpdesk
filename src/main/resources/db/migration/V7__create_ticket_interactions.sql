CREATE TABLE ticket_interactions(
    id uuid PRIMARY KEY,
    ticket_id uuid NOT NULL,
    sent_by_user_id uuid NOT NULL,
    status VARCHAR(50) NOT NULL,
    message text NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_by uuid NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_by uuid NULL
);