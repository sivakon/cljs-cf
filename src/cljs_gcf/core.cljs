(ns cljs-gcf.core)

(defn greet [req res]
  (println "console hello on Stackdriver")
  (-> res
      (.status 200)
      (.send
       (or
        (.. req -query -message)
        (.. req -body -message)
        "Hello World"))))

(enable-console-print!)

(set! (.-exports js/module) #js {:greet greet})


