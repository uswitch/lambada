(ns uswitch.lambada.core
  (:import [uswitch.lambada.ClojureLambdaBase]
           [com.amazonaws.services.lambda.runtime Context LambdaLogger RequestStreamHandler]))

(defmacro deflambdafn
  "Create a named class that can be invoked as a AWS Lambda function."
  [name args & body]
  (assert (= (count args) 3) "lambda function must have exactly three args")
  (let [prefix (gensym)
        handleRequestMethod (symbol (str prefix "handleRequest"))]
    `(do
       (gen-class
        :name   ~name
        :prefix ~prefix
        :extends uswitch.lambada.ClojureLambdaBase
        :implements [com.amazonaws.services.lambda.runtime.RequestStreamHandler])
       (defn ~handleRequestMethod
         ~(into ['this] args)
         ~@body))))
