(require '[cljs.build.api :as b])

(b/build "src"
         {:target :nodejs
          :main 'cljs-gcf.core
          :output-to "gcf_deploy/index.js"
          :optimizations :simple})

