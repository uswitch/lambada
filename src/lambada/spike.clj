(ns lambada.spike
  (:require [clojure.data.json :as json]
            [clojure.java.io :as io])
  (:import [com.amazonaws.services.lambda.runtime Context LambdaLogger RequestStreamHandler]))

(defn my-lambda-fn
  [in out ctx]
  (println "HELLO FROM CLOJURE :)")
  (println (.getClientContext ctx)))
