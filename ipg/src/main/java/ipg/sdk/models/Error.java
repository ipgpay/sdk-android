package ipg.sdk.models;

/**
 * Created by che19 on 2017/9/21.
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
