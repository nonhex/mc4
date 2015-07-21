package ru.specialist.student.someapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BroadcasterOnBoot extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, WeatherService.class));
    }
}
