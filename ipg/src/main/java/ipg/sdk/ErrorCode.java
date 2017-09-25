package ipg.sdk;
/**
 * Created by che19 on 2017/9/21.
 */

enum ErrorCode {
    invalidCreditCardNumber(1),
    invalidCVV(2),
    invalidExpiryDate(4),
    invalidInput(8),
    commsNoResponse(16),
    commsParseFailure(32),
    commsServerUnreachable(64),
    commsUnexpectedResponse(128);

    private final int value;

    ErrorCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
