BEGIN;

CREATE TABLE users (
    ID BIGINT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at datetime NOT NULL DEFAULT now(),
    updated_at datetime NOT NULL DEFAULT now,
    deleted_at datetime
);


CREATE TABLE tags (
    ID BIGINT PRIMARY KEY,
    name VARCHAR(55) NOT NULL
);

CREATE TYPE issue_statuses AS ENUM ('todo', 'in_progress', 'done');

CREATE TABLE issues (
    ID BIGINT PRIMARY KEY,
    summary VARCHAR(1024) NOT NULL,
    content TEXT NOT NULL,
    status issue_statuses NOT NULL DEFAULT 'todo',
    created_at datetime NOT NULL DEFAULT now(),
    updated_at datetime NOT NULL DEFAULT now(),
    deleted_at datetime
);

CREATE TABLE issues_tags (
    issue_id BIGINT,
    tag_id BIGINT,
    PRIMARY KEY (issue_id, tag_id),
    FOREIGN KEY (issue_id) REFERENCES issues(id),
    FOREIGN KEY (tag_id) REFERENCES tags(id)
);

COMMIT;