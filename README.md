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
...

$ aws lambda invoke \
    --invocation-type RequestResponse \
    --function-name clojure-lambda \
    --region eu-west-1 \
    --log-type Tail \
    --client-context $(echo '{"custom": {"handler": "lambada.spike/my-lambda-fn"}}' | base64 -w 0) \
    --payload '{"key1":"value1", "key2":"value2", "key3":"value3"}' \
    outfile.txt
...

```
