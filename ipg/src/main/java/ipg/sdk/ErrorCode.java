package ipg.sdk;

/*
 * Copyright (c) 2017 IPG Group Limited
 * All rights reserved.
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE.txt file for details.
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

