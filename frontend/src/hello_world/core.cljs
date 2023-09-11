(ns hello-world.core)

(enable-console-print!)

(defn foo [a b]
  (+ a b))

(. js/console (log "Hello World" (foo 1 10)))

(println "Hello world!")
