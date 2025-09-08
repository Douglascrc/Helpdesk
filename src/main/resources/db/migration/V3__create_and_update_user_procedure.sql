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
    IF EXISTS (SELECT 1 FROM users WHERE id = u_id::uuid) THEN
        UPDATE users
        SET username = u_username,
            password = u_password,
            email = u_email,
            name = u_name,
            updated_at = NOW()
        WHERE id = u_id::uuid;
    ELSE
        INSERT INTO users (id, username, password, email, name, active, created_at, updated_at)
        VALUES (u_id::uuid, u_username, u_password, u_email, u_name, true, NOW(), NOW());
    END IF;
END;
$$;