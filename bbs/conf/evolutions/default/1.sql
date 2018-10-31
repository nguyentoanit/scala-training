# posts table
# --- !Ups
CREATE TABLE posts (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    email varchar(255) NOT NULL,
    title varchar(255),
    content text,
    PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE posts;