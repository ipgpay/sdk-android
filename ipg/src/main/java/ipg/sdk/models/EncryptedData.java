package ipg.sdk.models;

/**
 * IPG
 * Created by Victor CC on 2017/9/21.
 * Copyright © 2017 Victor CC. All rights reserved.
 */

public class EncryptedData {
    private String pad;
    private String value;

    public EncryptedData(String pad, String value) {
        this.pad = pad;
        this.value = value;
    }

    public String getPad() {
        return pad;
    }

    public String getValue() {
        return value;
    }
}
