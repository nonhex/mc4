package ru.specialist.student.someapp;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.greenrobot.event.EventBus;
import ru.specialist.student.someapp.weather.CityWeather;

public class WeatherService extends Service {
    private Handler handler;
    ;
    private String city = "London";
    private Gson gson = new GsonBuilder().create();
    private WeatherAT weather_at;

    public void l(String m) {
        Log.d("MC4", hashCode() + ":" + getClass().getSimpleName() + ": " + m);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        l("onBind");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        l("onStartCommand");
        if (intent != null && intent.hasExtra("city")) {
            city = intent.getStringExtra("city");
            retrive();
        }
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        l("onCreate");
        handler = new Handler();
        super.onCreate();
    }

    public void retrive() {
        l("city is: " + city);
        weather_at = new WeatherAT() {
            @Override
            protected void onPostExecute(String info) {
                l("info is: " + info);
                EventBus.getDefault().post(gson.fromJson(info, CityWeather.class));
                super.onPostExecute(info);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        retrive();
                    }
                }, 2500);
            }
        };
        weather_at.execute(city);
    }

    @Override
    public void onDestroy() {
        l("onDestroy");
        super.onDestroy();
        if (weather_at.getStatus() != AsyncTask.Status.FINISHED) {
            weather_at.cancel(true);
            weather_at = null;
        }
        handler.removeCallbacksAndMessages(null);
    }
}
