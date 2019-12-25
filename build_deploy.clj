(ns build-deploy
  (:require [cljs.build.api :as b]))

(println "Building...")

(defn build []
  (let [start (System/nanoTime)]
    (b/build "src"
             {:target :nodejs
              :main 'cljs-gcf.core
              :output-to "gcf_deploy/index.js"
              :optimizations :simple})
    (println "... done. Elapsed" (/ (- (System/nanoTime) start) 1e9) "seconds")))

(defn -main [& args]
  (println (build)))
