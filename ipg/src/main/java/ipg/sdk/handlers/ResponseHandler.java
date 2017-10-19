package ipg.sdk.handlers;

/**
 * IPG
 * Created by Victor CC on 2017/9/21.
 * Copyright © 2017 Victor CC. All rights reserved.
 */

//Call back handler
public interface ResponseHandler<T> {
    public void handle(T handleObject);
}
