package lambada;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import clojure.lang.Symbol;
import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public class ClojureLambdaFn implements RequestStreamHandler {
    
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context)
        throws IOException 
    {
        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
        ClientContext clientContext = context.getClientContext();

        Map<String, String> customData = clientContext.getCustom();
        if(customData == null) {
            throw new RuntimeException("No custom data in client context. Need provide custom data with a 'handler' key pointing to a clojure var.");
        }
        if(!customData.containsKey("handler")) {
            throw new RuntimeException("No 'handler' key in client context custom data. Need provide custom data with a 'handler' key pointing to a clojure var.");
        }

        String handlerName = clientContext.getCustom().get("handler");
        Object handlerObject = Clojure.read(handlerName);
        if(!(handlerObject instanceof Symbol)) {
            throw new RuntimeException(
                    String.format("Handler should be a symbol, was '%s'", handlerName));
        }

        Symbol handlerSymbol = (Symbol)handlerObject;

        String namespace = handlerSymbol.getNamespace();
        if(namespace == null) {
            throw new RuntimeException(
                    String.format("Handler symbol needs to be namespace qualified, was '%s'", handlerSymbol));
        }

        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read(namespace));

        IFn handler = Clojure.var(handlerSymbol.getNamespace(), handlerSymbol.getName());
        handler.invoke(inputStream, outputStream, context);
    }
}
