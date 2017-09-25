package ipg.sdk.utility;

import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by che19 on 2017/9/21.
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
