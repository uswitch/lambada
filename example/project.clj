(defproject my-lambda-project "0.1.0-SNAPSHOT"
  :description "Example lambada project."
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [uswitch/lambada "0.1.0"]]
  :profiles {:uberjar {:aot :all}}
  :uberjar-name "my-lambda-project.jar")
