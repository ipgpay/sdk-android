package ipg.sdk.models;

/**
 * IPG
 * Created by Victor CC on 2017/9/20.
 * Copyright © 2017 Victor CC. All rights reserved.
 */

public class Error {
    int errorCode;
    String errorMessage;

    public Error(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
