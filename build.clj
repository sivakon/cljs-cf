;; this is build and deploy without clj -A:
(require '[cljs.build.api :as b]
         '[clojure.java.shell :as shell])

(println "Building...")

(let [start (System/nanoTime)]
  (b/build "src"
           {:target :nodejs
            :main 'cljs-gcf.core
            :output-to "gcf_deploy/index.js"
            :optimizations :simple})
  (println "... done. Elapsed" (/ (- (System/nanoTime) start) 1e9) "seconds"))

(println "Deploying to Google Cloud functions")
(println (shell/sh "sh" "-c" "gcloud functions deploy greet --runtime=nodejs10 --trigger-http --source=gcf_deploy"))



(System/exit 0)
