CREATE TABLE tickets (
    id uuid PRIMARY KEY,
    atendent_user_id uuid NULL,
    subject VARCHAR(255) NOT NULL,
    description text NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_by uuid NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_by uuid NULL
);