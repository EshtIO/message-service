CREATE SEQUENCE users_id_seq;
CREATE TABLE
    users(
             id BIGINT PRIMARY KEY DEFAULT nextval('users_id_seq'),
             name VARCHAR(255)
);

CREATE SEQUENCE messages_id_seq;
CREATE TABLE
    messages(
        id BIGINT PRIMARY KEY DEFAULT nextval('messages_id_seq'),
        from_user_id BIGINT,
        to_user_id BIGINT,
        text VARCHAR(1024),
        status INT,

        CONSTRAINT fk_from_messages_users FOREIGN KEY (from_user_id) REFERENCES users(id),
        CONSTRAINT fk_to_messages_users FOREIGN KEY (to_user_id) REFERENCES users(id)
);
COMMENT ON COLUMN messages.status IS '1 - in progress, 2 - success';

CREATE INDEX message_status_idx ON messages(status);
