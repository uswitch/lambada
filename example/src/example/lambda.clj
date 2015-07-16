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
    (with-open [writer (io/writer out)]
      (json/write res writer))))

;; fibonacci number example

(defn fib-seq
  ([] (fib-seq 0 1))
  ([a b] (lazy-seq (cons a (fib-seq b (+' a b))))))

(defn fibonacci-number-response
  [event]
  (let [index (get event "index" 0)]
    {:index index
     :fibonacci-number (nth (fib-seq) index)}))

(deflambdafn example.lambda.FibonacciFn
  [in out ctx]
  (let [event (json/read (io/reader in))
        res (fibonacci-number-response event)]
    (with-open [writer (io/writer out)]
      (json/write res writer))))
