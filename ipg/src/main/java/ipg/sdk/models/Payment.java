package ipg.sdk.models;

import java.util.ArrayList;

/*
 * Copyright (c) 2017 IPG Group Limited
 * All rights reserved.
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE.txt file for details.
 */



public class Payment {
    public String method;
    public ArrayList<String> types = new ArrayList<String>();

    public String getMethod() {
        return method;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }
}
