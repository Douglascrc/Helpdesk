CREATE OR REPLACE PROCEDURE pr_create_user(
    IN u_id text,
    IN u_username text,
    IN u_password text,
    IN u_email text,
    IN u_name text
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO users (id, username, password, email, name, active, created_at, updated_at)
    VALUES (u_id::uuid, u_username, u_password, u_email, u_name, true, NOW(), NOW())
    ON CONFLICT (id) DO UPDATE
        SET username = EXCLUDED.username,
            password = EXCLUDED.password,
            email = EXCLUDED.email,
            name = EXCLUDED.name,
            updated_at = NOW();
END;
$$;