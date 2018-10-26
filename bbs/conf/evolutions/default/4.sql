# Example data
# --- !Ups
-- Insert rows into table 'post'
INSERT INTO users
( -- columns to insert data into
 email, password, fullname
)
VALUES
    ("user1@gmail.com", "user1@123", "User 1"),
    ("user2@gmail.com", "user2@123", "User 2")
# --- !Downs
DELETE FROM users