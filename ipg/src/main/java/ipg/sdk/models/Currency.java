package ipg.sdk.models;

import java.util.ArrayList;

/*
 * Copyright (c) 2017 IPG Group Limited
 * All rights reserved.
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE.txt file for details.
 */

public class Currency {
    private String code;
    private ArrayList<Payment> payments = new ArrayList<Payment>();

    public void setCode(String code) {
        this.code = code;
    }

    public void setPayments(ArrayList<Payment> payments) {
        this.payments = payments;
    }

    public String getCode() {
        return code;
    }

    public ArrayList<Payment> getPayments() {
        return payments;
    }
}
