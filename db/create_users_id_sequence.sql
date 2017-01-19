CREATE SEQUENCE seq_users_id;
SELECT setval('seq_users_id', max(users_id)) FROM tbl_users;