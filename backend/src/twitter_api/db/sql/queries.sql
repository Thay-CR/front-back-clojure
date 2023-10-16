-- A :result value of :n below will return affected rows:
-- :name sql-insert-tweet :! :m
-- :doc Persist a tweet on datbase
insert into tweets (id, body, username, created_at)
values ((:id)::uuid , :body, :username, NOW())

-- A :result value of :n below will return affected rows:
-- :name sql-insert-user :! :m
-- :doc Persist a user on datbase
insert into users (user_name, email, user_password)
values (:user_name, :email, :user_password)


-- A :result value of :n below will return affected rows:
-- :name sql-search-user-by-email-and-user-password :?
-- :doc Find users from a specific email and password
SELECT * FROM users
WHERE email = :email
AND user_password = :user_password;

-- A :result value of :n below will return affected rows:
-- :name sql-search-tweets-by-username :?
-- :doc Find tweets from a specific username
select id, body, username from tweets
where username = :username

-- :name sql-update-tweet :! :m
-- :doc Update a tweet in the database
-- :param id :value
-- :param tweet-json :json
UPDATE tweets
SET body = :body
WHERE id = :id

-- :name sql-delete-tweet :! :m
-- :doc Delete a tweet from the database
-- :param id :value
DELETE FROM tweets
WHERE id = :id
