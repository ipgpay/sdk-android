package ipg.sdk.models;

/**
 * IPG
 * Created by Victor CC on 2017/9/20.
 * Copyright © 2017 Victor CC. All rights reserved.
 */

public class Options {
    private String ccPan;
    private String ccCvv;
    private String ccExpYear;
    private String ccExpMonth;

    public Options(String ccPan, String ccCvv, String ccExpYear, String ccExpMonth) {
        this.ccPan=ccPan;
        this.ccCvv=ccCvv;
        this.ccExpYear=ccExpYear;
        this.ccExpMonth=ccExpMonth;
    }

    public String getCcPan() {
        return ccPan;
    }

    public String getCcCvv() {
        return ccCvv;
    }

    public String getCcExpYear() {
        return ccExpYear;
    }

    public String getCcExpMonth() {
        return ccExpMonth;
    }
}
