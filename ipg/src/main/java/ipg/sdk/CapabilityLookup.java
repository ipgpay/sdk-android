package ipg.sdk;

import ipg.sdk.handlers.ResponseHandler;

/*
 * Copyright (c) 2017 IPG Group Limited
 * All rights reserved.
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE.txt file for details.
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
