# Post table
# --- !Ups
CREATE TABLE post (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    userid bigint(20) NOT NULL,
    title varchar(255),
    content text,
    inserted_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE post;