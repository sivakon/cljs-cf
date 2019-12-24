(ns cljs-gcf.core)

(defn greet [req res]
  (println "hey partner")
  (-> res
      (.status 200)
      (.send
       (or
        (.. req -query -message)
        (.. req -body -message)
        "Hello Siva"))))

(enable-console-print!)

(set! (.-exports js/module) #js {:greet greet})


