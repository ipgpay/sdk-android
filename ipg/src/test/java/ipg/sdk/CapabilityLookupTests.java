package ipg.sdk;

import org.junit.Test;

import java.util.ArrayList;

import ipg.sdk.handlers.ResponseHandler;
import ipg.sdk.models.Currency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by che19 on 2017/9/29.
 */

public class CapabilityLookupTests {
    @Test
    public void get_Capabilities_Successful() {
        String capabilityUrl = "http://private-ed273e-ipg.apiary-mock.com/capability/123";
        CapabilityLookup capabilityLookup = new CapabilityLookup("", capabilityUrl);
        capabilityLookup.getCapabilities(new ResponseHandler<ArrayList<Currency>>() {
            @Override
            public void handle(ArrayList<Currency> handleObject) {
                assertNotNull(handleObject);
                assertEquals(handleObject.get(0).getCode(), "AUD");
                assertEquals(handleObject.get(1).getCode(), "USD");
                assertEquals(handleObject.get(0).getPayments().size(), 2);
                assertEquals(handleObject.get(1).getPayments().size(), 3);
                assertEquals(handleObject.get(0).getPayments().get(0).method, "OTT");
                assertEquals(handleObject.get(0).getPayments().get(1).method, "ApplePay");
                assertEquals(handleObject.get(1).getPayments().get(0).method, "OTT");
                assertEquals(handleObject.get(1).getPayments().get(1).method, "ApplePay");
                assertEquals(handleObject.get(1).getPayments().get(2).method, "SomeOtherMethod");
            }
        });
    }

    @Test
    public void get_Capabilities_Failed() {
        String capabilityUrl = "http://private-ed273e-ipg.apiary-mock.com/capability_failed";
        CapabilityLookup capabilityLookup = new CapabilityLookup("", capabilityUrl);
        capabilityLookup.getCapabilities(new ResponseHandler<ArrayList<Currency>>() {
            @Override
            public void handle(ArrayList<Currency> handleObject) {
                assertNull(handleObject);
            }
        });
    }
}
