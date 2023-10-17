(ns twitter-api.utils.password
  (:require  [clojure-aes.core :as aes])
  (:gen-class))

(def key-256 "af9788d7d60be8bdef4982790de1d4bdc800d134b14ad1565786121e044220c3")
(def key-length 256)

(defn encrypt-message [message] ;TODO: substituir caracteres especiais por caracteres ASCII
  (aes/encrypt key-256 message key-length))

(defn decrypt-message [message] ;TODO: apos desencriptar desconverter os caracteres ASCII
  (aes/decrypt key-256 message key-length))
