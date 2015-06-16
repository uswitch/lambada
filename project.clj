(defproject lambada "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.7.0-RC1"]
                 [org.clojure/data.json "0.2.6"]
                 [com.amazonaws/aws-lambda-java-core "1.0.0"]
                 [com.amazonaws/aws-lambda-java-events "1.0.0"]]
  :java-source-paths ["src-java"]
  :uberjar-name "lambada.jar"
  :profiles {:uberjar {;:aot [lambada.spike]
                       }})
