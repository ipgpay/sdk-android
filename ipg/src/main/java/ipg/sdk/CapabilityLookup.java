package ipg.sdk;

import ipg.sdk.handlers.ResponseHandler;

/**
 * IPG
 * Created by Victor CC on 2017/9/28.
 * Copyright © 2017 Victor CC. All rights reserved.
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
