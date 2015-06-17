# lambada

Create AWS Lambda functions in clojure.

## Usage

```clojure
(ns my.lambda.project
  (:require [uswitch.lambada.core :refer [deflambdafn]]))

(deflambdafn my.lambda.project.MyLambdaFn
  [in out ctx]
  (println "OMG I'm running in the cloud!!!111oneone"))
```



```
...

$ aws lambda create-function \
    --region eu-west-1 \
    --function-name my-lambda-fn \
    --zip-file fileb://$(pwd)/target/lambada.jar \
    --role arn:aws:iam::account-id:role/lambda_basic_execution \
    --handler my.lambda.project.MyLambdaFn \
    --runtime java8 \
    --timeout 15 \
    --memory-size 512
...

$ aws lambda invoke \
    --invocation-type RequestResponse \
    --function-name my-lambda-fn \
    --region eu-west-1 \
    --log-type Tail \
    --payload '{"some":"input"}' \
    outfile.txt
...

```
