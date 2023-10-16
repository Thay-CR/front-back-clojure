(ns twitter-api.tweets.database
  (:require [twitter-api.db.db :refer :all]
            [cheshire.core :as json]
            [twitter-api.tweets.validation :as v])
  (:import java.util.UUID)
  (:gen-class))

(defn post-tweet
  "Post a tweet to the audience"
  [tweet]
  (let [is-valid (v/validate-tweet tweet)]
    (when is-valid
      (sql-insert-tweet db (assoc tweet :id (java.util.UUID/randomUUID))))))

(defn post-user
  "Post a user"
  [user]
  (let [is-valid (v/validate-user user)]
    (when is-valid
      (sql-insert-user db user))))

(defn search-tweets-by-username
  "Find tweets from a specific username"
  [username]
  (let [result (sql-search-tweets-by-username db {:username (str "@" username)})]
    (map #(assoc % :id (str (:id %))) result)))

(defn search-user-by-email-and-user-password
  "Find a user by email and user password"
  [email user-password]
  (let [result (sql-search-user-by-email-and-user-password db {:email email, :user_password user-password})]
    (if (empty? result)
      nil
      (first result))))

(defn update-tweet
  "Update a tweet by ID"
  [tweet-id updated-tweet]
  (let [is-valid (v/validate-tweet updated-tweet)] 
    (when is-valid
      (sql-update-tweet db {:id (java.util.UUID/fromString tweet-id) :body  (get updated-tweet :body) }))))


(defn delete-tweet
  "Delete a tweet by ID"
  [tweet-id]
  (sql-delete-tweet db {:id  (java.util.UUID/fromString tweet-id)}))
