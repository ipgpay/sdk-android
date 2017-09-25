package ipg.sdk.handlers;

import ipg.sdk.models.Payload;

/**
 * Created by che19 on 2017/9/21.
 */

public interface ResponseHandler {
    public void handle(Payload payload);
}
