package com.example.waktusolatandroid;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class WaktuSolatJakimService {
    private final RequestQueue requestQueue;
    private final SharedPreferenceService sharedPreferenceService;
    private final String url;

    public WaktuSolatJakimService(Context context) {
        requestQueue = Volley.newRequestQueue(context);
        url = "https://www.e-solat.gov.my/index.php?r=esolatApi/xmlfeed&zon=";
        sharedPreferenceService = new SharedPreferenceService(context);
    }

    public void getWaktuSolat(final WaktuSolatListCallback waktuSolatListCallback){

        StringRequest request= new StringRequest(Request.Method.GET, url + "SGR01", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                WaktuSolatDetail waktuSolatDetail = new WaktuSolatDetail();
                WaktuSolatList waktuSolatList = new WaktuSolatList();
                XmlPullParserFactory factory;

                try {

                    factory = XmlPullParserFactory.newInstance();
                    factory.setNamespaceAware(true);
                    XmlPullParser xpp = factory.newPullParser();
                    xpp.setInput( new StringReader( response ) );
                    int eventType = xpp.getEventType();

                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        if(eventType == XmlPullParser.START_TAG){
                            switch (xpp.getName()) {
                                case "date":
                                    xpp.next();
                                    final String[] separated = xpp.getText().split(" ");
                                    waktuSolatList.date = separated[0];
                                    break;
                                case "item":
                                    waktuSolatDetail = new WaktuSolatDetail();
                                    break;
                                case "title":
                                    xpp.next();
                                    waktuSolatDetail.title = xpp.getText();
                                    break;
                                case "description":
                                    xpp.next();
                                    waktuSolatDetail.description = xpp.getText();
                                    break;
                            }
                        }
                        else if(eventType == XmlPullParser.END_TAG) {
                            if (xpp.getName().equals("item")){
                                waktuSolatList.addWaktuSolat(waktuSolatDetail);
                            }
                        }
                        eventType = xpp.next();
                    }
                    waktuSolatListCallback.onSuccess(waktuSolatList);
                }catch (XmlPullParserException | IOException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                error.getMessage();

                try {

                    Message resp = new Message(new JSONObject(new String(error.networkResponse.data)));

                    waktuSolatListCallback.onFailure(resp);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        requestQueue.add(request);
    }
}
