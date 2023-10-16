(ns twitter-api.handlers
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [clojure.pprint :as pp]
            [clojure.string :as str]
            [clojure.data.json :as json]
            [clojure.tools.logging :as log]
            [twitter-api.tweets.database :as d])
  (:gen-class))

(defn post-twitter-handler
  [req]
  (let [tweet-json (:body req)
        saved (try
                (d/post-tweet tweet-json)
                (catch Exception e
                  (do
                    (log/error e)
                    false)))]
    (log/info tweet-json)
    {:status  (if saved 201 400)
     :headers {"Content-Type" "text/html"}
     :body    (when (not saved)
                "error or saving tweet")}))

(defn post-register-handler
  [req]
  (let [user-json (:body req)
        saved (try
                (d/post-user user-json)
                (catch Exception e
                  (do
                    (log/error e)
                    false)))]
    (log/info user-json)
    {:status  (if saved 201 400)
     :headers {"Content-Type" "text/html"}
     :body    (when (not saved)
                "error or saving user")}))

(defn get-twitter-handler
  [req]
  (log/info req)
  (let
   [username (-> req
                 :params
                 :username)
    tweets (d/search-tweets-by-username username)]

    {:status 200
     :headers {"Content-type" "application/json"}
     :body (json/write-str tweets)}))

(defn put-twitter-handler
  [req]
  (let [id (:id (:params req)) 
        tweet-json (:body req)
        updated (try
                   (d/update-tweet id tweet-json) 
                   (catch Exception e
                     (do
                       (log/error e)
                       false)))]
    (log/info tweet-json)
    {:status  (if updated 200 400)
     :headers {"Content-Type" "text/html"}
     :body    (when (not updated)
                "Error updating tweet")}))

(defn delete-twitter-handler
  [req]
  (let [id (:id (:params req)) 
        deleted (try
                   (d/delete-tweet id) 
                   (catch Exception e
                     (do
                       (log/error e)
                       false)))]
    {:status  (if deleted 204 400)
     :headers {"Content-Type" "text/html"}
     :body    (when (not deleted)
                "Error deleting tweet")}))