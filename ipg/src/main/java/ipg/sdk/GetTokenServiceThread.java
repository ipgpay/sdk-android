package ipg.sdk;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import ipg.sdk.handlers.ResponseHandler;
import ipg.sdk.models.Error;
import ipg.sdk.models.PaddedData;
import ipg.sdk.models.Payload;
import ipg.sdk.utility.HttpUtility;

/*
 * Copyright (c) 2017 IPG Group Limited
 * All rights reserved.
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE.txt file for details.
 */



class GetTokenServiceThread extends Thread {
    private PaddedData paddedData;
    private String serviceAuthKey;
    private String tokenServiceURL;
    private ErrorGenerator errorGenerator;
    private ResponseHandler responseHandler;

    public GetTokenServiceThread(PaddedData paddedData, String tokenServiceURL, String serviceAuthKey, ErrorGenerator errorGenerator, ResponseHandler responseHandler) {
        super();
        this.paddedData = paddedData;
        this.serviceAuthKey = serviceAuthKey;
        this.tokenServiceURL = tokenServiceURL;
        this.errorGenerator = errorGenerator;
        this.responseHandler = responseHandler;
    }

    @Override
    public void run() {
        Payload responsePayload = null;
        responsePayload = getTokenServiceResponse();
        this.responseHandler.handle(responsePayload);
    }

    /// Get response from the token service.
    ///
    /// - Parameters:
    ///   - paddedData: Padded data contains all the fields we need for the token service, must be the encrypted data.
    ///   - responseHandler: A callback function for the client to handle the response object.
    private Payload getTokenServiceResponse() {
        URL url = null;
        PaddedData paddedData = this.paddedData;
        int errorCode = 0;
        Payload payload = null;
        try {
            url = new URL(this.tokenServiceURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("action", "NETWORK_POST_KEY_VALUE");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStream os = conn.getOutputStream();
            Map<String, String> dataMap = paddedData.toMap();
            dataMap.put("auth_key", this.serviceAuthKey);
            byte[] requestBody = HttpUtility.buildParamsString(dataMap).getBytes("UTF-8");
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
                if (!response.toString().isEmpty()) {
                    JSONObject responseObj = new JSONObject(response.toString());
                    payload = new Payload("1" + responseObj.get("token") + paddedData.getPad(), paddedData.getCc_pan_bin(), paddedData.getCc_pan_last4(), null);
                } else {
                    errorCode += ErrorCode.commsNoResponse.getValue();
                }
            } else {
                errorCode += ErrorCode.commsServerUnreachable.getValue();
            }
        } catch (JSONException je) {
            errorCode += ErrorCode.commsParseFailure.getValue();
        } catch (Exception e) {
            errorCode += ErrorCode.commsUnexpectedResponse.getValue();
            e.printStackTrace();
        } finally {
            if (errorCode != 0) {
                Error[] errors = this.errorGenerator.generateErrors(errorCode);
                payload = new Payload(errors);
            }
        }
        return payload;
    }
}
