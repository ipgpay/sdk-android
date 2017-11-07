package ipg.sdk.models;

import java.util.ArrayList;

/**
 * IPG
 * Created by Victor CC on 2017/9/21.
 * Copyright © 2017 Victor CC. All rights reserved.
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
