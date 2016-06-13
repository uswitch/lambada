package uswitch.lambada;

public class ClojureLambdaBase {
    static {
        Thread.currentThread().setContextClassLoader(ClojureLambdaBase.class.getClassLoader());
    }
}
