# Example data
# --- !Ups
-- Insert rows into table 'posts'
INSERT INTO posts
( -- columns to insert data into
 email, title, content
)
VALUES
    ("user1@example.com", "Title 1", "Content 1"),
    ("user1@example.com", "Title 2", "Content 2"),
    ("user1@example.com", "Title 3", "Content 3"),
    ("user1@example.com", "Title 4", "Content 4"),
    ("user1@example.com", "Title 5", "Content 5"),
    ("user1@example.com", "Title 6", "Content 6")
# --- !Downs
DELETE FROM posts