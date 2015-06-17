(ns example.lambda
  (:require [uswitch.lambada.core :refer [deflambdafn]]))

(deflambdafn example.lambda.MyLambdaFn
  [in out ctx]
  (println "OMG I'm running in the cloud!!!111oneone"))
