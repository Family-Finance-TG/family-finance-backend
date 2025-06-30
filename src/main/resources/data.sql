INSERT INTO permissions (permission_value, permission_description)
SELECT 'PERMISSION_MANAGE', 'Gerenciar permissões'
WHERE NOT EXISTS (SELECT 1 FROM permissions WHERE permission_value = 'PERMISSION_MANAGE');

INSERT INTO permissions (permission_value, permission_description)
SELECT 'DEBT_ADD', 'Adicionar débito'
WHERE NOT EXISTS (SELECT 1 FROM permissions WHERE permission_value = 'DEBT_ADD');

INSERT INTO permissions (permission_value, permission_description)
SELECT 'DEBT_EDIT', 'Editar débito'
WHERE NOT EXISTS (SELECT 1 FROM permissions WHERE permission_value = 'DEBT_EDIT');

INSERT INTO permissions (permission_value, permission_description)
SELECT 'DEBT_DELETE', 'Excluír débito'
WHERE NOT EXISTS (SELECT 1 FROM permissions WHERE permission_value = 'DEBT_DELETE');

INSERT INTO permissions (permission_value, permission_description)
SELECT 'MEMBER_INVITE', 'Convidar membros'
WHERE NOT EXISTS (SELECT 1 FROM permissions WHERE permission_value = 'MEMBER_INVITE');

INSERT INTO permissions (permission_value, permission_description)
SELECT 'MEMBER_REMOVE', 'Remover membros'
WHERE NOT EXISTS (SELECT 1 FROM permissions WHERE permission_value = 'MEMBER_REMOVE');