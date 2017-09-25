package ipg.sdk;

import android.renderscript.ScriptGroup;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import ipg.sdk.handlers.ResponseHandler;
import ipg.sdk.models.EncryptedData;
import ipg.sdk.models.Options;
import ipg.sdk.models.PaddedData;
import ipg.sdk.models.Payload;
import ipg.sdk.utility.HttpUtility;

/**
 * IPG
 * Created by Victor CC on 2017/9/20.
 * Copyright © 2017 Victor CC. All rights reserved.
 */

/// The data include encrypted credit card details.

public class OneTimeTokenGenerator {
    private String mServiceAuthKey;
    private String mTokenServiceURL;

    public OneTimeTokenGenerator(String serviceAuthKey, String tokenServiceURL) {
        this.mServiceAuthKey = serviceAuthKey;
        this.mTokenServiceURL = tokenServiceURL;
    }

    /// Method to generate payload with API method.
    ///
    /// - Parameters:
    ///   - options: The data include credit card details.
    ///   - responseHandler: A callback interface for the client to handle the response object.
    public void getPayload(Options options, final ResponseHandler responseHandler) {
        int code = validate(options);
        Payload responsePayload = null;
        if (code != 0) {

        } else {
            final PaddedData padded;
            padded = getPaddedData(options.getCcPan(), options.getCcCvv(), options.getCcExpMonth(), options.getCcExpYear());
            new Thread() {
                @Override
                public void run() {
                    getTokenServiceResponse(padded, responseHandler);
                }
            }.start();
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

        EncryptedData ccRemainEncrypted = getEncrypted(ccCvv);
        pad.append(ccRemainEncrypted.getPad());
        cc_pan_remainder = ccRemainEncrypted.getValue();

        return new PaddedData(cc_cvv, cc_exp_month, cc_exp_year, cc_pan_remainder, pad.toString(), cc_pan_bin, cc_pan_last4);
    }

    /// Get encrypted data of the string.
    ///
    /// - Parameter input: The string that needs to be encrypted, has to be all numbers.
    /// - Returns: The encrypted data and pad.
    /// - Throws: Throws the invalid input error.
    private EncryptedData getEncrypted(String value) {
        // Encryption method to encrypt input string
        if (!InputsValidate.isValidInteger(value)) {
            throw new Error(String.valueOf(ErrorCode.invalidInput.getValue()));
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

    /// Get response from the token service.
    ///
    /// - Parameters:
    ///   - paddedData: Padded data contains all the fields we need for the token service, must be the encrypted data.
    ///   - responseHandler: A callback function for the client to handle the response object.
    private void getTokenServiceResponse(PaddedData paddedData, ResponseHandler responseHandler) {
        URL url = null;
        try {
            url = new URL(mTokenServiceURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("action", "NETWORK_POST_KEY_VALUE");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStream os = conn.getOutputStream();
            byte[] requestBody = HttpUtility.buildParamsString(paddedData.toMap()).getBytes("UTF-8");
            os.write(requestBody);
            os.flush();
            os.close();
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
                StringBuilder response = new StringBuilder();
                String line = null;
                while ((line = buffer.readLine()) != null) {
                    response.append(line);
                }
                JSONObject responseObj = new JSONObject(response.toString());
                responseHandler.handle(new Payload("1" + responseObj.get("token") + paddedData.getPad(), paddedData.getCc_pan_bin(), paddedData.getCc_pan_last4(), null));
            } else {

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
