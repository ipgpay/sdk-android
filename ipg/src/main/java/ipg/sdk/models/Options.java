package ipg.sdk.models;

/*
 * Copyright (c) 2017 IPG Group Limited
 * All rights reserved.
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE.txt file for details.
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
