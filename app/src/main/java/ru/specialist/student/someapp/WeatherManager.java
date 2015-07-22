package ru.specialist.student.someapp;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;

import ru.specialist.student.someapp.weather.CityWeather;
import ru.specialist.student.someapp.weather.Weather;

/**
 * Created by xema on 22.07.15.
 */
public class WeatherManager {
    public static final String WEATHER_FILE = "weather.log";
    private static Gson gson = new GsonBuilder().create();

    public static void saveWeather(String json) {
        try (OutputStreamWriter osw = new OutputStreamWriter(App.ctx().openFileOutput(WEATHER_FILE, Context.MODE_APPEND))) {
            osw.write(json + "\n");
            osw.flush();
            Toast.makeText(App.ctx(), "Weather log successfull writed", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CityWeather getWeather() {
        try {
            FileInputStream fis = App.ctx().openFileInput(WEATHER_FILE);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line, data = "";
            while ((line = br.readLine()) != null)
                data += line;
            if (data.length() > 0) {
                return gson.fromJson(data, CityWeather.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
