package com.example.waktusolatandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView date, waktuImsak, waktuSubuh, waktuSyuruk, waktuZohor, waktuAsar, waktuMaghrib, waktuIsyak, timeImsak, timeSubuh, timeSyuruk, timeZohor, timeAsar, timeMaghrib, timeIsyak;
    WaktuSolatJakimService waktuSolatJakimService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date = findViewById(R.id.date);
        waktuImsak = findViewById(R.id.waktu_imsak);
        waktuSubuh = findViewById(R.id.waktu_subuh);
        waktuSyuruk = findViewById(R.id.waktu_syuruk);
        waktuZohor = findViewById(R.id.waktu_zohor);
        waktuAsar = findViewById(R.id.waktu_asar);
        waktuMaghrib = findViewById(R.id.waktu_maghrib);
        waktuIsyak = findViewById(R.id.waktu_isyak);
        timeImsak = findViewById(R.id.time_imsak);
        timeSubuh = findViewById(R.id.time_subuh);
        timeSyuruk = findViewById(R.id.time_syuruk);
        timeZohor = findViewById(R.id.time_zohor);
        timeAsar = findViewById(R.id.time_asar);
        timeMaghrib = findViewById(R.id.time_maghrib);
        timeIsyak = findViewById(R.id.time_isyak);

        waktuSolatJakimService = new WaktuSolatJakimService(this);

        waktuSolatJakimService.getWaktuSolat(new WaktuSolatListCallback() {
            @Override
            public void onSuccess(WaktuSolatList waktuSolatList) {

                date.setText(waktuSolatList.date);
                waktuImsak.setText(waktuSolatList.waktuSolatDetailArrayList.get(0).title);
                waktuSubuh.setText(waktuSolatList.waktuSolatDetailArrayList.get(1).title);
                waktuSyuruk.setText(waktuSolatList.waktuSolatDetailArrayList.get(2).title);
                waktuZohor.setText(waktuSolatList.waktuSolatDetailArrayList.get(3).title);
                waktuAsar.setText(waktuSolatList.waktuSolatDetailArrayList.get(4).title);
                waktuMaghrib.setText(waktuSolatList.waktuSolatDetailArrayList.get(5).title);
                waktuIsyak.setText(waktuSolatList.waktuSolatDetailArrayList.get(6).title);
                timeImsak.setText(waktuSolatList.waktuSolatDetailArrayList.get(0).description);
                timeSubuh.setText(waktuSolatList.waktuSolatDetailArrayList.get(1).description);
                timeSyuruk.setText(waktuSolatList.waktuSolatDetailArrayList.get(2).description);
                timeZohor.setText(waktuSolatList.waktuSolatDetailArrayList.get(3).description);
                timeAsar.setText(waktuSolatList.waktuSolatDetailArrayList.get(4).description);
                timeMaghrib.setText(waktuSolatList.waktuSolatDetailArrayList.get(5).description);
                timeIsyak.setText(waktuSolatList.waktuSolatDetailArrayList.get(6).description);
            }

            @Override
            public void onFailure(Message message) {
                Toast.makeText(getApplicationContext(), message.message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}