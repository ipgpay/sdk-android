package ipg.sdk;

import ipg.sdk.handlers.ResponseHandler;

/**
 * Created by che19 on 2017/9/28.
 */

public class CapabilityLookup {
    private String authKey;
    private String capabilityServiceUrl;

    public CapabilityLookup(String authKey, String capabilityServiceUrl) {
        this.authKey = authKey;
        this.capabilityServiceUrl = capabilityServiceUrl;
    }

    public void getCapabilities(ResponseHandler handler) {
        new CapabilityLookupThread(authKey, capabilityServiceUrl, handler).start();
    }
}
