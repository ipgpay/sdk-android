package ipg.sdk;

import ipg.sdk.handlers.ResponseHandler;
import ipg.sdk.models.EncryptedData;
import ipg.sdk.models.Options;
import ipg.sdk.models.PaddedData;
import ipg.sdk.models.Payload;

/*
 * Copyright (c) 2017 IPG Group Limited
 * All rights reserved.
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE.txt file for details.
 */

/// The data include encrypted credit card details.

public class OneTimeTokenGenerator {
    private String serviceAuthKey;
    private String tokenServiceURL;
    private ErrorGenerator mErrorGenerator = new ErrorGenerator();

    public OneTimeTokenGenerator(String serviceAuthKey, String tokenServiceURL) {
        this.serviceAuthKey = serviceAuthKey;
        this.tokenServiceURL = tokenServiceURL;
    }

    /// Method to generate payload with API method.
    ///
    /// - Parameters:
    ///   - options: The data include credit card details.
    ///   - responseHandler: A callback interface for the client to handle the response object.
    public void getPayload(Options options, final ResponseHandler responseHandler) {
        int code = validate(options);
        Payload responsePayload = null;
        try {
            if (code != 0) {
                return;
            } else {
                final PaddedData padded;
                padded = getPaddedData(options.getCcPan(), options.getCcCvv(), options.getCcExpMonth(), options.getCcExpYear());
                if (padded != null) {
                    new GetTokenServiceThread(padded, this.tokenServiceURL, this.serviceAuthKey, mErrorGenerator, responseHandler).start();
                } else {
                    code += ErrorCode.invalidInput.getValue();
                    return;
                }
            }
        } finally {
            if (code != 0) {
                responsePayload = new Payload(mErrorGenerator.generateErrors(code));
                responseHandler.handle(responsePayload);
            }
        }
    }

    private int validate(Options options) {
        int code = 0;
        if (!InputsValidate.isValidLuhn(options.getCcPan())) {
            code += ErrorCode.invalidCreditCardNumber.getValue();
        }
        if (!InputsValidate.isValidCVV(options.getCcCvv())) {
            code += ErrorCode.invalidCVV.getValue();
        }
        if (!InputsValidate.isValidExpiryDate(options.getCcExpYear(), options.getCcExpMonth())) {
            code += ErrorCode.invalidExpiryDate.getValue();
        }
        return code;
    }

    /// Get the encrypted data from the card details.
    ///
    /// - Parameters:
    ///   - ccPan: Credit card pan.
    ///   - ccCvv: Credit card cvv.
    ///   - ccExpmonth: credit card expiry month.
    ///   - ccExpyear: credit card expiry year.
    /// - Returns: The encrypted data.
    /// - Throws: throws encrypt exeption.
    private PaddedData getPaddedData(String ccPan, String ccCvv, String ccExpmonth, String ccExpyear) {
        try {
            String cc_pan_bin = ccPan.substring(0, 6);
            String cc_pan_last4 = ccPan.substring(ccPan.length() - 4);
            String cc_pan_remainder = ccPan.substring(6);
            StringBuilder pad = new StringBuilder("");

            EncryptedData cvvEncrypted = getEncrypted(ccCvv);
            pad.append(cvvEncrypted.getPad());
            String cc_cvv = cvvEncrypted.getValue();

            EncryptedData expMonthEncrypted = getEncrypted(ccExpmonth);
            pad.append(expMonthEncrypted.getPad());
            String cc_exp_month = expMonthEncrypted.getValue();

            EncryptedData expYearEncrypted = getEncrypted(ccExpyear);
            pad.append(expYearEncrypted.getPad());
            String cc_exp_year = expYearEncrypted.getValue();

            EncryptedData ccRemainEncrypted = getEncrypted(cc_pan_remainder);
            pad.append(ccRemainEncrypted.getPad());
            cc_pan_remainder = ccRemainEncrypted.getValue();

            return new PaddedData(cc_cvv, cc_exp_month, cc_exp_year, cc_pan_remainder, pad.toString(), cc_pan_bin, cc_pan_last4);
        } catch (Exception e) {
            return null;
        }
    }

    /// Get encrypted data of the string.
    ///
    /// - Parameter input: The string that needs to be encrypted, has to be all numbers.
    /// - Returns: The encrypted data and pad.
    /// - Throws: Throws the invalid input error.
    private EncryptedData getEncrypted(String value) {
        // Encryption method to encrypt input string
        if (!InputsValidate.isValidInteger(value)) {
            throw new java.lang.Error(String.valueOf(ErrorCode.invalidInput.getValue()));
        }
        String newVal = "";
        String pad = "";
        for (int j = 0; j < value.length(); j++) {
            int padNum = (int) Math.floor(Math.random() * 10);
            pad += padNum;
            char digit = value.charAt(j);
            newVal += (Integer.parseInt(String.valueOf(digit)) + padNum) % 10;
        }
        return new EncryptedData(pad, newVal);
    }
}
