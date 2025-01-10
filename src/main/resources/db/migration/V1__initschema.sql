CREATE TABLE IF NOT EXISTS app_authority
(
    name VARCHAR(50) NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (name)
);

CREATE TABLE IF NOT EXISTS app_user
(
    activated          BIT(1)      NOT NULL,
    created_date       datetime NULL,
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    last_modified_date datetime NULL,
    reset_date         datetime NULL,
    lang_key           VARCHAR(10) NULL,
    activation_key     VARCHAR(20) NULL,
    reset_key          VARCHAR(20) NULL,
    created_by         VARCHAR(50) NOT NULL,
    first_name         VARCHAR(50) NULL,
    last_modified_by   VARCHAR(50) NULL,
    last_name          VARCHAR(50) NULL,
    login              VARCHAR(50) NOT NULL,
    password_hash      VARCHAR(60) NOT NULL,
    email              VARCHAR(254) NULL,
    image_url          VARCHAR(256) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS app_user_authority
(
    user_id        BIGINT      NOT NULL,
    authority_name VARCHAR(50) NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (user_id, authority_name)
);

CREATE TABLE IF NOT EXISTS event_publication
(
    completion_date  datetime NULL,
    publication_date datetime NULL,
    id               BLOB NOT NULL,
    event_type       VARCHAR(255) NULL,
    listener_id      VARCHAR(255) NULL,
    serialized_event VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS flyway_schema_history
(
    installed_rank INT                     NOT NULL,
    version        VARCHAR(50) NULL,
    `description`  VARCHAR(200)            NOT NULL,
    type           VARCHAR(20)             NOT NULL,
    script         VARCHAR(1000)           NOT NULL,
    checksum       INT NULL,
    installed_by   VARCHAR(100)            NOT NULL,
    installed_on   timestamp DEFAULT NOW() NOT NULL,
    execution_time INT                     NOT NULL,
    success        TINYINT(1)              NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (installed_rank)
);

CREATE TABLE IF NOT EXISTS products
(
    id    BIGINT AUTO_INCREMENT NOT NULL,
    name  VARCHAR(255)   NOT NULL,
    price DECIMAL(21, 2) NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

# ALTER TABLE  app_user
#     ADD CONSTRAINT UK1j9d9a06i600gd43uu3km82jw UNIQUE (email);
#
# ALTER TABLE app_user
#     ADD CONSTRAINT UKirayhia1ygarvmv7apksctnqn UNIQUE (login);
#
# CREATE INDEX flyway_schema_history_s_idx ON flyway_schema_history (success);
#
# ALTER TABLE app_user_authority
#     ADD CONSTRAINT FKfocpjrj1tmhlu9vcfo47nqanp FOREIGN KEY (user_id) REFERENCES app_user (id) ON DELETE NO ACTION;
#
# ALTER TABLE app_user_authority
#     ADD CONSTRAINT FKi2mlqu6q3ct0fy19j1c83sym3 FOREIGN KEY (authority_name) REFERENCES app_authority (name) ON DELETE NO ACTION;
#
# CREATE INDEX FKi2mlqu6q3ct0fy19j1c83sym3 ON app_user_authority (authority_name);