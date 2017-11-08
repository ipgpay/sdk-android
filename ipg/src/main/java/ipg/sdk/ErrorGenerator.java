package ipg.sdk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ipg.sdk.models.Error;

/*
 * Copyright (c) 2017 IPG Group Limited
 * All rights reserved.
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE.txt file for details.
 */



class ErrorGenerator {
    public Error[] generateErrors(int code) {
        ArrayList<Error> errors = new ArrayList<Error>();
        JSONArray errorJsonArray = null;
        try {
            errorJsonArray = new JSONArray(ErrorConfig.ErrorMapping);
            for (int i = 0; i < errorJsonArray.length(); i++) {
                JSONObject jsonObj = errorJsonArray.getJSONObject(i);
                int errorCode = code & (int) jsonObj.get("ErrorCode");
                if (errorCode != 0) {
                    errors.add(new Error(errorCode, jsonObj.get("ErrorMessage").toString()));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Error[] errorsArray = new Error[errors.size()];
        return errors.toArray(errorsArray);
    }
}
