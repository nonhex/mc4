package ru.specialist.student.someapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BroadcasterOnBoot extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MC4", "Start Weather Service");
        context.startService(new Intent(context, WeatherService.class));
    }
}
