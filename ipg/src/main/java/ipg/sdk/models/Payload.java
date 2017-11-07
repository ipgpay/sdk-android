package ipg.sdk.models;

/**
 * IPG
 * Created by Victor CC on 2017/9/21.
 * Copyright © 2017 Victor CC. All rights reserved.
 */

public class Payload {
    private String payload = null;
    private String ccPanBin = null;
    private String ccPanLast4 = null;
    private Error[] errors;

    public String getPayload() {
        return payload;
    }

    public String getCcPanBin() {
        return ccPanBin;
    }

    public String getCcPanLast4() {
        return ccPanLast4;
    }

    public Error[] getErrors() {
        return errors;
    }

    public Payload(String payload, String ccPanBin, String ccPanLast4, Error[] errors) {
        this.payload = payload;
        this.ccPanBin = ccPanBin;
        this.ccPanLast4 = ccPanLast4;
        this.errors = errors;
    }

    public Payload(Error[] errors) {
        this.errors = errors;
    }
}
