package com.example.waktusolatandroid;

import java.util.ArrayList;

public class WaktuSolatList {
    public String date;
    public ArrayList<WaktuSolatDetail> waktuSolatDetailArrayList;

    public WaktuSolatList(){
        date = "";
        waktuSolatDetailArrayList = new ArrayList<>();
    }

    public void addWaktuSolat(WaktuSolatDetail detail){
        waktuSolatDetailArrayList.add(detail);
    }
}
