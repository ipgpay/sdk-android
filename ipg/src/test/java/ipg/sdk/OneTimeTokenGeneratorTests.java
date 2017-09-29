package ipg.sdk;

import org.junit.Test;
import org.mockito.Mock;

import ipg.sdk.handlers.ResponseHandler;
import ipg.sdk.models.Error;
import ipg.sdk.models.Options;
import ipg.sdk.models.Payload;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;


/**
 * Created by che19 on 2017/9/29.
 */

public class OneTimeTokenGeneratorTests {
    @Test
    public void payload_Real_Successful() {
        String url = "http://private-ed273e-ipg.apiary-mock.com/tokensuccess";
        Options optons = new Options("4111111111111111", "134", "17", "09");
        OneTimeTokenGenerator ott = new OneTimeTokenGenerator("key", url);
        ott.getPayload(optons, new ResponseHandler<Payload>() {
            @Override
            public void handle(Payload handleObject) {
                assertNull(handleObject.getErrors());
                assertEquals(handleObject.getCcPanBin(), "411111");
                assertEquals(handleObject.getCcPanLast4(), "1111");
                assertEquals((handleObject.getPayload().indexOf("1test token") != -1), true);
            }
        });
    }

    @Test
    public void payload_Real_ServerUnreachable() {
        String url = "unreachable";
        Options optons = new Options("4111111111111111", "134", "17", "09");
        OneTimeTokenGenerator ott = new OneTimeTokenGenerator("key", url);
        ott.getPayload(optons, new ResponseHandler<Payload>() {
            @Override
            public void handle(Payload handleObject) {
                assertNull(handleObject.getCcPanBin());
                assertNull(handleObject.getCcPanLast4());
                assertNull(handleObject.getPayload());
                assertNotNull(handleObject.getErrors());
                assertEquals(handleObject.getErrors()[0].getErrorCode(), ErrorCode.commsServerUnreachable.getValue());
            }
        });
    }

    @Test
    public void payload_Mock_NoResponse() {
        String url = "NoResponse";
        Options optons = new Options("4111111111111111", "134", "17", "09");
        OneTimeTokenGenerator ott = new OneTimeTokenGenerator("key", url);
        ott.getPayload(optons, new ResponseHandler<Payload>() {
            @Mock
            Payload mockPayload;

            @Override
            public void handle(Payload handleObject) {
                handleObject = mockPayload;
                when(handleObject.getCcPanBin()).thenReturn(null);
                when(handleObject.getCcPanLast4()).thenReturn(null);
                when(handleObject.getPayload()).thenReturn(null);
                when(handleObject.getErrors()).thenReturn(new Error[]{
                        new Error(ErrorCode.commsNoResponse.getValue(), "")
                });
                assertNull(handleObject.getCcPanBin());
                assertNull(handleObject.getCcPanLast4());
                assertNull(handleObject.getPayload());
                assertNotNull(handleObject.getErrors());
                assertEquals(handleObject.getErrors()[0].getErrorCode(), ErrorCode.commsNoResponse.getValue());
            }
        });
    }

    @Test
    public void payload_Real_Error() {
        String url = "http://private-ed273e-ipg.apiary-mock.com/tokensuccess";
        Options optons = new Options("wrong card number", "wrong cvv", "error date", "error date");
        OneTimeTokenGenerator ott = new OneTimeTokenGenerator("key", url);
        ott.getPayload(optons, new ResponseHandler<Payload>() {
            @Override
            public void handle(Payload handleObject) {
                assertNull(handleObject.getCcPanBin());
                assertNull(handleObject.getCcPanLast4());
                assertNull(handleObject.getPayload());
                assertNotNull(handleObject.getErrors());
            }
        });
    }
}
