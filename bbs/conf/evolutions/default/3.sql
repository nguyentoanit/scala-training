# Example data
# --- !Ups
-- Insert rows into table 'post'
INSERT INTO post
( -- columns to insert data into
 userid, title, content
)
VALUES
    (1, "Title 1", "Content 1"),
    (1, "Title 2", "Content 2"),
    (1, "Title 3", "Content 3"),
    (1, "Title 4", "Content 4"),
    (1, "Title 5", "Content 5"),
    (1, "Title 6", "Content 6")
# --- !Downs
DELETE FROM post