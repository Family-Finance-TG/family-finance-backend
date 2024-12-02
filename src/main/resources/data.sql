INSERT INTO roles (role_id, role_friendly_name, role_value)
VALUES (1, 'Basic', 'BASIC')
ON CONFLICT(role_id) DO NOTHING;

INSERT INTO roles (role_id, role_friendly_name, role_value)
VALUES (2, 'Administrator', 'ADMIN')
ON CONFLICT(role_id) DO NOTHING;

INSERT INTO genders (gender_id, gender_friendly_name, gender_value)
VALUES (1, 'Not defined', 'NOT_DEFINED')
ON CONFLICT(gender_id) DO NOTHING;

INSERT INTO genders (gender_id, gender_friendly_name, gender_value)
VALUES (2, 'Female', 'FEMALE')
ON CONFLICT(gender_id) DO NOTHING;

INSERT INTO genders (gender_id, gender_friendly_name, gender_value)
VALUES (3, 'Male', 'MALE')
ON CONFLICT(gender_id) DO NOTHING;

INSERT INTO payment_statuses (payment_status_id, payment_status_friendly_name, payment_status_value)
VALUES (1, 'To pay', 'TO_PAY')
ON CONFLICT(payment_status_id) DO NOTHING;

INSERT INTO payment_statuses (payment_status_id, payment_status_friendly_name, payment_status_value)
VALUES (2, 'Paid', 'PAID')
ON CONFLICT(payment_status_id) DO NOTHING;

INSERT INTO payment_statuses (payment_status_id, payment_status_friendly_name, payment_status_value)
VALUES (3, 'Overdue', 'OVERDUE')
ON CONFLICT(payment_status_id) DO NOTHING;

INSERT INTO payment_statuses (payment_status_id, payment_status_friendly_name, payment_status_value)
VALUES (4, 'Cancelled', 'CANCELLED')
ON CONFLICT(payment_status_id) DO NOTHING;