# lambada

Create more passionate AWS Lambda functions using clojure.

Current version:

[![Clojars Project](http://clojars.org/uswitch/lambada/latest-version.svg)](http://clojars.org/uswitch/lambada)

## Usage

You can define a Lambda handler using the `deflambdafn` macro provided
by `uswitch.lambada.core`:

```clojure
(ns example.lambda
  (:require [uswitch.lambada.core :refer [deflambdafn]]))

(deflambdafn example.lambda.MyLambdaFn
  [in out ctx]
  (println "OMG I'm running in the cloud!!!111oneone"))
```

When this namespace is AOT compiled, it will generate a class called
`example.lambda.MyLambdaFn` that implements the AWS Lambda
[`RequestStreamHandler`](http://docs.aws.amazon.com/lambda/latest/dg/java-handler-using-predefined-interfaces.html)
interface using the args and body provided.

Simplest way to deploy is to create an uberjar using leiningen or boot
and then use that as the JAR you upload to AWS Lambda. Assuming you
have an uberjar called `my-lambda-project.jar` in `target`, the
following commands will do the job:

```
$ aws lambda create-function \
    --region eu-west-1 \
    --function-name my-lambda-project \
    --zip-file fileb://$(pwd)/target/my-lambda-project.jar \
    --role arn:aws:iam::YOUR-AWS-ACCOUNT-ID:role/lambda_basic_execution \
    --handler example.lambda.MyLambdaFn \
    --runtime java8 \
    --timeout 15 \
    --memory-size 512
...

$ aws lambda invoke \
    --invocation-type RequestResponse \
    --function-name my-lambda-project \
    --region eu-west-1 \
    --log-type Tail \
    --payload '{"some":"input"}' \
    outfile.txt
...

```

See [example](https://github.com/uswitch/lambada/tree/master/example) for an example project.

# License

Copyright © 2015 Ragnar Dahlen.

Distributed under the Eclipse Public License, the same as Clojure.
