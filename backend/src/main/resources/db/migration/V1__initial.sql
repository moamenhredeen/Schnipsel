CREATE SEQUENCE IF NOT EXISTS comments_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS roles_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS snippets_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS users_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE roles
(
    id   BIGINT       NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE users
(
    id       BIGINT       NOT NULL,
    username VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    enabled  BOOLEAN,
    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT uc_users_username UNIQUE (username),
    CONSTRAINT uc_users_email UNIQUE (email)
);

CREATE TABLE users_roles
(
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT pk_users_roles PRIMARY KEY (role_id, user_id),
    CONSTRAINT fk_userol_on_role FOREIGN KEY (role_id) REFERENCES roles (id),
    CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES users (id)
);


CREATE TABLE snippets
(
    id          BIGINT       NOT NULL,
    title       VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    content     VARCHAR(255) NOT NULL,
    language    VARCHAR(255) NOT NULL,
    CONSTRAINT pk_snippets PRIMARY KEY (id)
);

CREATE TABLE comments
(
    id         BIGINT       NOT NULL,
    content    VARCHAR(255) NOT NULL,
    snippet_id BIGINT,
    CONSTRAINT pk_comments PRIMARY KEY (id),
    CONSTRAINT fk_comments_on_snippet FOREIGN KEY (snippet_id) REFERENCES snippets (id)
);
