package ipg.sdk.models;

import java.util.ArrayList;

/**
 * Created by che19 on 2017/9/27.
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
