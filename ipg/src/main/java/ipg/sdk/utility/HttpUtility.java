package ipg.sdk.utility;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/*
 * Copyright (c) 2017 IPG Group Limited
 * All rights reserved.
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE.txt file for details.
 */



public class HttpUtility {


    public static String buildParamsString(Map<String, String> paramsMap) {
        String params = "";
        for (Map.Entry<String, String> map : paramsMap.entrySet()) {
            if (params != "") {
                params += "&";
            }
            params += map.getKey() + "=" + map.getValue();
        }
        return params;
    }

    private static String getStringByBytes(byte[] bytes) {
        String str = "";
        try {
            str = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }
}
