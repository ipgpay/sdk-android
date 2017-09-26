package ipg.sdk.models;

/**
 * Created by che19 on 2017/9/21.
 */
public class Error {
    int ErrorCode;
    String ErrorMessage;

    public Error(int errorCode, String errorMessage) {
        ErrorCode = errorCode;
        ErrorMessage = errorMessage;
    }

    public int getErrorCode() {
        return ErrorCode;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }
}
