# posts table
# --- !Ups
CREATE TABLE posts (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    user_id bigint(20) NOT NULL,
    title varchar(255),
    content text,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE posts;