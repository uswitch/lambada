package lambada;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.lambda.runtime.Context;

import clojure.java.api.Clojure;
import clojure.lang.Compiler;
import clojure.lang.IFn;
import clojure.lang.Var;
import clojure.lang.RT;

public class ClojureLambdaFn implements RequestStreamHandler {
    
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context)
        throws IOException 
    {
        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());

        System.out.println("HELLO FROM JAVA :/");

        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("lambada.spike"));
        IFn handler = Clojure.var("lambada.spike", "my-lambda-fn");
        handler.invoke(inputStream, outputStream, context);
    }
}
