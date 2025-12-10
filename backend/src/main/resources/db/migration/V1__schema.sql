
CREATE SEQUENCE IF NOT EXISTS comments_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS roles_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS snippets_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS users_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE comments
(
    id                 BIGINT       NOT NULL,
    created_by         BIGINT,
    last_modified_by   BIGINT,
    created_date       TIMESTAMP WITHOUT TIME ZONE,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    deleted            BOOLEAN      NOT NULL DEFAULT FALSE,
    deleted_at         TIMESTAMP WITHOUT TIME ZONE,
    content            VARCHAR(255) NOT NULL,
    snippet_id         BIGINT,
    CONSTRAINT pk_comments PRIMARY KEY (id)
);

CREATE TABLE operators
(
    user_id            BIGINT  NOT NULL,
    created_by         BIGINT,
    last_modified_by   BIGINT,
    created_date       TIMESTAMP WITHOUT TIME ZONE,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    deleted            BOOLEAN      NOT NULL DEFAULT FALSE,
    deleted_at         TIMESTAMP WITHOUT TIME ZONE,
    name               VARCHAR(255),
    CONSTRAINT pk_operators PRIMARY KEY (user_id)
);

CREATE TABLE profiles
(
    user_id            BIGINT  NOT NULL,
    created_by         BIGINT,
    last_modified_by   BIGINT,
    created_date       TIMESTAMP WITHOUT TIME ZONE,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    deleted            BOOLEAN      NOT NULL DEFAULT FALSE,
    deleted_at         TIMESTAMP WITHOUT TIME ZONE,
    bio                VARCHAR(255),
    avatar_url         VARCHAR(255),
    location           VARCHAR(255),
    website_url        VARCHAR(255),
    github_username    VARCHAR(255),
    twitter_username   VARCHAR(255),
    linkedin_url       VARCHAR(255),
    display_name       VARCHAR(255),
    company            VARCHAR(255),
    job_title          VARCHAR(255),
    skills             VARCHAR(1000),
    CONSTRAINT pk_profiles PRIMARY KEY (user_id)
);

CREATE TABLE roles
(
    id                 BIGINT       NOT NULL,
    created_by         BIGINT,
    last_modified_by   BIGINT,
    created_date       TIMESTAMP WITHOUT TIME ZONE,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    deleted            BOOLEAN      NOT NULL DEFAULT FALSE,
    deleted_at         TIMESTAMP WITHOUT TIME ZONE,
    name               VARCHAR(255) NOT NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE snippets
(
    id                 BIGINT       NOT NULL,
    created_by         BIGINT,
    last_modified_by   BIGINT,
    created_date       TIMESTAMP WITHOUT TIME ZONE,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    deleted            BOOLEAN      NOT NULL DEFAULT FALSE,
    deleted_at         TIMESTAMP WITHOUT TIME ZONE,
    title              VARCHAR(255) NOT NULL,
    description        VARCHAR(255),
    content            VARCHAR(255) NOT NULL,
    language           VARCHAR(255) NOT NULL,
    profile_user_id    BIGINT,
    CONSTRAINT pk_snippets PRIMARY KEY (id)
);

CREATE TABLE users
(
    id                 BIGINT       NOT NULL,
    created_by         BIGINT,
    last_modified_by   BIGINT,
    created_date       TIMESTAMP WITHOUT TIME ZONE,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    deleted            BOOLEAN      NOT NULL DEFAULT FALSE,
    deleted_at         TIMESTAMP WITHOUT TIME ZONE,
    username           VARCHAR(255) NOT NULL,
    email              VARCHAR(255) NOT NULL,
    password           VARCHAR(255) NOT NULL,
    enabled            BOOLEAN,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE users_roles
(
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT pk_users_roles PRIMARY KEY (role_id, user_id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT uc_users_username UNIQUE (username);

ALTER TABLE comments
    ADD CONSTRAINT FK_COMMENTS_ON_CREATED_BY FOREIGN KEY (created_by) REFERENCES operators (user_id);

ALTER TABLE comments
    ADD CONSTRAINT FK_COMMENTS_ON_LAST_MODIFIED_BY FOREIGN KEY (last_modified_by) REFERENCES operators (user_id);

ALTER TABLE comments
    ADD CONSTRAINT FK_COMMENTS_ON_SNIPPET FOREIGN KEY (snippet_id) REFERENCES snippets (id);

ALTER TABLE operators
    ADD CONSTRAINT FK_OPERATORS_ON_CREATED_BY FOREIGN KEY (created_by) REFERENCES operators (user_id);

ALTER TABLE operators
    ADD CONSTRAINT FK_OPERATORS_ON_LAST_MODIFIED_BY FOREIGN KEY (last_modified_by) REFERENCES operators (user_id);

ALTER TABLE operators
    ADD CONSTRAINT FK_OPERATORS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE profiles
    ADD CONSTRAINT FK_PROFILES_ON_CREATED_BY FOREIGN KEY (created_by) REFERENCES operators (user_id);

ALTER TABLE profiles
    ADD CONSTRAINT FK_PROFILES_ON_LAST_MODIFIED_BY FOREIGN KEY (last_modified_by) REFERENCES operators (user_id);

ALTER TABLE profiles
    ADD CONSTRAINT FK_PROFILES_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE roles
    ADD CONSTRAINT FK_ROLES_ON_CREATED_BY FOREIGN KEY (created_by) REFERENCES operators (user_id);

ALTER TABLE roles
    ADD CONSTRAINT FK_ROLES_ON_LAST_MODIFIED_BY FOREIGN KEY (last_modified_by) REFERENCES operators (user_id);

ALTER TABLE snippets
    ADD CONSTRAINT FK_SNIPPETS_ON_CREATED_BY FOREIGN KEY (created_by) REFERENCES operators (user_id);

ALTER TABLE snippets
    ADD CONSTRAINT FK_SNIPPETS_ON_LAST_MODIFIED_BY FOREIGN KEY (last_modified_by) REFERENCES operators (user_id);

ALTER TABLE snippets
    ADD CONSTRAINT FK_SNIPPETS_ON_PROFILE_USER FOREIGN KEY (profile_user_id) REFERENCES profiles (user_id);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_CREATED_BY FOREIGN KEY (created_by) REFERENCES operators (user_id);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_LAST_MODIFIED_BY FOREIGN KEY (last_modified_by) REFERENCES operators (user_id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES users (id);