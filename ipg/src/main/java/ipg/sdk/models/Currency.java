package ipg.sdk.models;

import java.util.ArrayList;

/**
 * Created by che19 on 2017/9/27.
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
