package com.example.ingatobat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Code to execute when the alarm goes off
        Log.d("AlarmReceiver", "Alarm received!");
        Toast.makeText(context, "Time to take your medicine!", Toast.LENGTH_LONG).show();
    }
}