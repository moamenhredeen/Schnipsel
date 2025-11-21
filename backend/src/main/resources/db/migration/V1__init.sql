CREATE SEQUENCE IF NOT EXISTS comments_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE IF NOT EXISTS roles_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE IF NOT EXISTS snippets_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE IF NOT EXISTS users_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE roles
(
    id                 BIGINT       NOT NULL,
    name               VARCHAR(255) NOT NULL,
    created_by         VARCHAR(255),
    last_modified_by   VARCHAR(255),
    created_date       BIGINT,
    last_modified_date BIGINT,
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE users
(
    id                 BIGINT       NOT NULL,
    username           VARCHAR(255) NOT NULL,
    email              VARCHAR(255) NOT NULL,
    password           VARCHAR(255) NOT NULL,
    enabled            BOOLEAN,
    created_by         VARCHAR(255),
    last_modified_by   VARCHAR(255),
    created_date       BIGINT,
    last_modified_date BIGINT,
    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT uc_users_email UNIQUE (email),
    CONSTRAINT uc_users_username UNIQUE (username)
);

CREATE TABLE users_roles
(
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT pk_users_roles PRIMARY KEY (role_id, user_id),
    CONSTRAINT fk_userol_on_role FOREIGN KEY (role_id) REFERENCES roles (id),
    CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE operators
(
    user_id            BIGINT NOT NULL,
    name               VARCHAR(255),
    created_by         VARCHAR(255),
    last_modified_by   VARCHAR(255),
    created_date       BIGINT,
    last_modified_date BIGINT,
    CONSTRAINT pk_operators PRIMARY KEY (user_id),
    CONSTRAINT FK_OPERATORS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE profiles
(
    user_id            BIGINT NOT NULL,
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
    created_by         VARCHAR(255),
    last_modified_by   VARCHAR(255),
    created_date       BIGINT,
    last_modified_date BIGINT,
    CONSTRAINT pk_profiles PRIMARY KEY (user_id),
    CONSTRAINT FK_PROFILES_ON_USER FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE snippets
(
    id                 BIGINT       NOT NULL,
    title              VARCHAR(255) NOT NULL,
    description        VARCHAR(255),
    content            VARCHAR(255) NOT NULL,
    language           VARCHAR(255) NOT NULL,
    profile_user_id    BIGINT,
    created_by         VARCHAR(255),
    last_modified_by   VARCHAR(255),
    created_date       BIGINT,
    last_modified_date BIGINT,
    CONSTRAINT pk_snippets PRIMARY KEY (id),
    CONSTRAINT FK_SNIPPETS_ON_PROFILE_USER FOREIGN KEY (profile_user_id) REFERENCES profiles (user_id)
);

CREATE TABLE comments
(
    id                 BIGINT       NOT NULL,
    content            VARCHAR(255) NOT NULL,
    snippet_id         BIGINT,
    created_by         VARCHAR(255),
    last_modified_by   VARCHAR(255),
    created_date       BIGINT,
    last_modified_date BIGINT,
    CONSTRAINT pk_comments PRIMARY KEY (id),
    CONSTRAINT FK_COMMENTS_ON_SNIPPET FOREIGN KEY (snippet_id) REFERENCES snippets (id)
);