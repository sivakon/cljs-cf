## GCF with local npm testing

- To build CLJS, use `clj build.clj`
- This creates `gcf_deploy/index.js`
- Deploy to GCF using

```shell
gcloud functions deploy greet --runtime nodejs10 --trigger-http --source gcf_deploy
```

JS <-> CLJ

```js
exports.greet = (req, res) => {
  let message = req.query.message || req.body.message || 'Hello World!';
  res.status(200).send(message);
};
```

```clj
(defn greet [req res]
  (println "hey partner")
  (-> res
      (.status 200)
      (.send "Hello Siva")))

(enable-console-print!)

(set! (.-exports js/module) #js {:greet greet})

```


Got inspiration from [here](https://blog.colinwilliams.name/clojure/2017/05/13/clojurescript-on-google-cloud-functions.html)


For NPM testing locally

- Install `functions framework` from npm
- move `index.js` from `gcf_deploy` folder into the root folder
- `npm start`
