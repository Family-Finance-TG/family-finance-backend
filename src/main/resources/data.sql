INSERT INTO roles (role_id, role_friendly_name, role_value)
VALUES (1, 'Basic', 'BASIC')
ON CONFLICT(role_id) DO NOTHING;

INSERT INTO roles (role_id, role_friendly_name, role_value)
VALUES (2, 'Administrator', 'ADMIN')
ON CONFLICT(role_id) DO NOTHING;
