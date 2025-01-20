CREATE TABLE IF NOT EXISTS event_publication
(
    id               BINARY(16) NOT NULL,
    listener_id      TEXT       NOT NULL,
    completion_date  TIMESTAMP(6),
    event_type       varchar(512),
    publication_date TIMESTAMP(6),
    serialized_event varchar(4000),
    PRIMARY KEY (id)
);
