(require '[cljs.build.api :as b])

(b/watch "src"
  {:main 'twitter-front.core
   :output-to "out/twitter_front.js"
   :output-dir "out"})
