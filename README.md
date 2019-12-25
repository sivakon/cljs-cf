## GCF with local npm testing

- To build and deploy CLJS, use `clj build.clj`
- This creates `gcf_deploy/index.js`
- Deployed to GCF using

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
  (println "console hello on Stackdriver")
  (-> res
      (.status 200)
      (.send (or
              (.. req -query -message)
              (.. req -body -message)
              "Hello World"))))

(set! (.-exports js/module) #js {:greet greet})

```


Modified from [here](https://blog.colinwilliams.name/clojure/2017/05/13/clojurescript-on-google-cloud-functions.html)


# Notes on `deps.edn`
- Use aliases sparingly
- Only one main per alias `clojure -A:test:runner` should only contain one main


For NPM testing locally

- Install `functions framework` from npm
- move `index.js` from `gcf_deploy` folder into the root folder
- `npm start`


There is also build_deploy.clj in the root directory. This is not needed for this deployment.

When `deps.edn` is configured (with correct paths and aliases), we can execute the whole script using `clojure -Abuild`.
Remember only one `-m` (main file) in one alias. To overcome this, we can create one main file with command line args.

[tetris](https://github.com/djblue/tetris)
