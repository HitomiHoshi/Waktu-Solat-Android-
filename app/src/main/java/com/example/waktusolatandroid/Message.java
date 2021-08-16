package com.example.waktusolatandroid;

import org.json.JSONException;
import org.json.JSONObject;

public class Message {
    public String message;


    public Message(JSONObject result) throws JSONException {
        message = result.getString("message");
    }
}
