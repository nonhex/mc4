package ru.specialist.student.someapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import de.greenrobot.event.EventBus;
import ru.specialist.student.someapp.utils.http.OnSuccess;
import ru.specialist.student.someapp.utils.http.Request;
import ru.specialist.student.someapp.weather.CityWeather;

import ru.specialist.student.someapp.utils.http.HttpService;

public class WeatherService extends Service {
    private Gson gson = new GsonBuilder().create();

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
        String city;
        if (intent != null && intent.hasExtra("city")) {
            city = intent.getStringExtra("city");
        } else {
            city = "London";
        }
        HttpService.self().put(new Request("http://api.openweathermap.org/data/2.5/weather?q=" +
                        city.replace(" ", "+") + ",ru&units=metric"),
                new OnSuccess() {
                    @Override
                    public void run(String data) {
                        if (data != null) {
                            try (OutputStreamWriter osw = new OutputStreamWriter(openFileOutput("weather.log", MODE_APPEND))) {
                                osw.write(data + "\n");
                                osw.flush();
                                Toast.makeText(getApplicationContext(), "Weather log successfull writed", Toast.LENGTH_LONG).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                                l(e.getLocalizedMessage());
                            }
                            EventBus.getDefault().post(gson.fromJson(data, CityWeather.class));
                        }
                    }
                });
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        l("onCreate");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        l("onDestroy");
        super.onDestroy();
    }
}
