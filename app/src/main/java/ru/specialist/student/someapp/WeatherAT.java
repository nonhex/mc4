package ru.specialist.student.someapp;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import ru.specialist.student.someapp.weather.CityWeather;

public class WeatherAT extends AsyncTask<String, String, String> {

    public WeatherAT() {
    }

    @Override
    protected String doInBackground(String... params) {
        String for_ = params[0];
        Log.d("MC4", "Daemons");
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet request = new HttpGet();
        try {
            URI where = new URI("http://api.openweathermap.org/data/2.5/weather?q=" + for_ + ",ru&units=metric");
            request.setURI(where);
            HttpResponse response = httpclient.execute(request);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));
            String line, json = "";
            while ((line = in.readLine()) != null)
                json += line;
            Log.d("MC4", "json.l: " + json.length());
            return json;
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String info) {
//        CityWeather weather = gson.fromJson(info, CityWeather.class);
//        if (weather != null) {
//            write_to.setText("Min: " + weather.main.temp_min + ", Max: " + weather.main.temp_max);
//        } else {
//            write_to.setText("Json: " + info);
//        }
        super.onPostExecute(info);
    }
}
