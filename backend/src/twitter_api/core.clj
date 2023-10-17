(ns twitter-api.core
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [ring.middleware.json :as mj]
            [clojure.pprint :as pp]
            [clojure.string :as str]
            [clojure.data.json :as json]
            [clojure.tools.logging :as log]
            [twitter-api.handlers :refer :all]
            [twitter-api.tweets.database :as d])
  (:gen-class))

(defroutes app-routes
  (POST "/register" [] (mj/wrap-json-body post-register-handler {:keywords? true :bigdecimals? true}))
  (POST "/login" [] (mj/wrap-json-body post-login-handler {:keywords? true :bigdecimals? true}))
  (POST "/tweets" [] (mj/wrap-json-body post-twitter-handler {:keywords? true :bigdecimals? true}))
  (GET "/tweets" [] get-twitter-handler)
  (PUT "/tweets/:id" [] (mj/wrap-json-body put-twitter-handler {:keywords? true :bigdecimals? true}))
  (DELETE "/tweets/:id" [] delete-twitter-handler))
  ;TODO: criptografar e descriptografar senha
  ;TODO: fazer middleware de autenticação
  ;TODO: fazer rota para salvamento de arquivos 
  ;TODO: fazer envio de e-mail 
  ;TODO: conexão datomic 
  ;TODO: consumo de API 
  ;TODO: Kafka
  ;TODO: Cache
  ;TODO: Datadog
  ;TODO: testes
  ;TODO: [environ "1.2.0"] variaveis de ambiente

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [port 3000]
    (server/run-server  (wrap-defaults #'app-routes api-defaults)  {:port port})
    (println (str "Running service on port " port))))