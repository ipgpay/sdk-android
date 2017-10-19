package ipg.sdk.models;

import java.util.Hashtable;
import java.util.Map;

/**
 * IPG
 * Created by Victor CC on 2017/9/20.
 * Copyright © 2017 Victor CC. All rights reserved.
 */

public class PaddedData {
    private String cc_cvv;
    private String cc_expmonth;
    private String cc_expyear;
    private String cc_pan_remainder;
    private String pad;
    private String cc_pan_bin;
    private String cc_pan_last4;

    public PaddedData(String cc_cvv, String cc_expmonth, String cc_expyear, String cc_pan_remainder, String pad, String cc_pan_bin, String cc_pan_last4) {
        this.cc_cvv = cc_cvv;
        this.cc_expmonth = cc_expmonth;
        this.cc_expyear = cc_expyear;
        this.cc_pan_remainder = cc_pan_remainder;
        this.pad = pad;
        this.cc_pan_bin = cc_pan_bin;
        this.cc_pan_last4 = cc_pan_last4;
    }

    public String getCc_cvv() {
        return cc_cvv;
    }

    public String getCc_expmonth() {
        return cc_expmonth;
    }

    public String getCc_expyear() {
        return cc_expyear;
    }

    public String getCc_pan_remainder() {
        return cc_pan_remainder;
    }

    public String getPad() {
        return pad;
    }

    public String getCc_pan_bin() {
        return cc_pan_bin;
    }

    public String getCc_pan_last4() {
        return cc_pan_last4;
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new Hashtable<String, String>();
        map.put("cc_pan_bin", this.cc_pan_bin);
        map.put("cc_cvv", this.cc_cvv);
        map.put("cc_expmonth", this.cc_expmonth);
        map.put("cc_expyear", this.cc_expyear);
        map.put("cc_pan_remainder", this.cc_pan_remainder);
        return map;
    }
}

