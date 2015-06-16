# lambada

Spike for running clojure on AWS lambda.

## Usage

```
$ lein uberjar
...

$ aws lambda create-function \
    --region eu-west-1 \
    --function-name clojure-lambda \
    --zip-file fileb://$(pwd)/target/lambada.jar \
    --role arn:aws:iam::account-id:role/lambda_basic_execution \
    --handler lambada.ClojureLambdaFn \
    --runtime java8 \
    --timeout 15 \
    --memory-size 512

```
