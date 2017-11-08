package ipg.sdk.models;

/*
 * Copyright (c) 2017 IPG Group Limited
 * All rights reserved.
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE.txt file for details.
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
