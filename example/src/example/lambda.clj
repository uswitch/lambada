(ns example.lambda
  (:require [uswitch.lambada.core :refer [deflambdafn]]
            [clojure.data.json :as json]
            [clojure.java.io :as io]))

(defn handle-event
  [event]
  (println "Got the following event: " (pr-str event))
  {:status "ok"})

(deflambdafn example.lambda.MyLambdaFn
  [in out ctx]
  (let [event (json/read (io/reader in))
        res (handle-event event)]
    (json/write res (io/writer out))))
