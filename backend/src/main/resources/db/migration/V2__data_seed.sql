-- roles
insert into roles (id, name, created_date) values (1, 'superadmin', now());
insert into roles (id, name, created_date) values (2, 'admin', now());
insert into roles (id, name, created_date) values (3, 'customer', now());

-- users
insert into users (id, username, email, password, enabled, created_date) 
values (1, 'superadmin', 'superadmin@schnipsel.me', '{noop}superadmin', true, now());

insert into users (id, username, email, password, enabled, created_date) 
values (2, 'admin', 'admin@schnipsel.me', '{noop}admin', true, now());

insert into users (id, username, email, password, enabled, created_date) 
values (3, 'john_doe', 'john.doe@example.com', '{noop}john_doe', true, now());

insert into users (id, username, email, password, enabled, created_date) 
values (4, 'jane_smith', 'jane.smith@example.com', '{noop}jane_smith', true, now());

-- user roles mapping
insert into users_roles (user_id, role_id) values (1, 1); -- superadmin role
insert into users_roles (user_id, role_id) values (2, 2); -- admin role
insert into users_roles (user_id, role_id) values (3, 3); -- customer role
insert into users_roles (user_id, role_id) values (4, 3); -- customer role

-- operators
insert into operators (user_id, name, created_date) 
values (1, 'Super Administrator', now());

insert into operators (user_id, name, created_date) 
values (2, 'System Administrator', now());