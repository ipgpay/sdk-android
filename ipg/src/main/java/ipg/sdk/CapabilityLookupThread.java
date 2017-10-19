package ipg.sdk;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import ipg.sdk.handlers.ResponseHandler;
import ipg.sdk.models.Currency;
import ipg.sdk.models.Payment;

/**
 * IPG
 * Created by Victor CC on 2017/9/27.
 * Copyright © 2017 Victor CC. All rights reserved.
 */


//Capability network thread
public class CapabilityLookupThread extends Thread {
    private String authKey;
    private String capabilityServiceUrl;
    private ResponseHandler responseHandler;

    public CapabilityLookupThread(String authKey, String capabilityServiceUrl, ResponseHandler responseHandler) {
        super();
        this.authKey = authKey;
        this.capabilityServiceUrl = capabilityServiceUrl;
        this.responseHandler = responseHandler;
    }

    @Override
    public void run() {
        responseHandler.handle(getCapabilities());
    }

    private ArrayList<Currency> getCapabilities() {
        int errorCode = 0;
        ArrayList<Currency> currencies = null;
        try {
            URL url = new URL(this.capabilityServiceUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            int code = conn.getResponseCode();
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                currencies = readXML(is);
            } else {
                currencies = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currencies;
    }

    private ArrayList<Currency> readXML(InputStream inStream) {
        XmlPullParser parser = Xml.newPullParser();
        Currency currency = null;
        Payment payment = null;
        ArrayList<Currency> currencies = new ArrayList<Currency>();
        try {
            parser.setInput(inStream, "UTF-8");
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        String name2 = parser.getName();
                        break;
                    case XmlPullParser.START_TAG:
                        String name = parser.getName();
                        switch (name.toLowerCase()) {
                            case "currency":
                                currency = new Currency();
                                String code = parser.getAttributeValue(null, "code");
                                currency.setCode(code);
                                break;
                            case "paymentmethod":
                                payment = new Payment();
                                String method = parser.getAttributeValue(null, "method");
                                payment.setMethod(method);
                                break;
                            case "paymenttype":
                                String type = parser.nextText();
                                payment.getTypes().add(type);
                                break;
                            default:
                                break;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        String endName = parser.getName();
                        switch (endName.toLowerCase()) {
                            case "currency":
                                if (currency != null) {
                                    currencies.add(currency);
                                }
                                currency = null;
                                break;
                            case "paymentmethod":
                                if (payment != null) {
                                    currency.getPayments().add(payment);
                                }
                                payment = null;
                                break;
                            case "paymenttype":
                                break;
                            default:
                                break;
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currencies;
    }
}
