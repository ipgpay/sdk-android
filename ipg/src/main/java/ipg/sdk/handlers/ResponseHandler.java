package ipg.sdk.handlers;

/**
 * Created by che19 on 2017/9/21.
 */

public interface ResponseHandler<T> {
    public void handle(T handleObject);
}
