package com.example.waktusolatandroid;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceService {
    private final Context context;

    public SharedPreferenceService(Context context) {
        this.context = context;
    }

    public void setDaerahCode(String status) {
        SharedPreferences prefs = context.getSharedPreferences("preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("daerahCode", status);
        editor.apply();
    }

    public String getDaerahCode() {
        SharedPreferences prefs = context.getSharedPreferences("preference", Context.MODE_PRIVATE);
        return prefs.getString("daerahCode", "SGR01");
    }


}
