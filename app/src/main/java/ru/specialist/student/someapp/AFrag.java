package ru.specialist.student.someapp;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.nio.channels.AsynchronousCloseException;

import de.greenrobot.event.EventBus;
import ru.specialist.student.someapp.weather.CityWeather;

/**
 * Created by student on 21.07.2015.
 */
public abstract class AFrag extends Fragment {
    private TextView weather_tv, city_name;
    private Button btn_get_weather;
    private ProgressDialog dialog;

    public void l(String m) {
        Log.d("MC4", hashCode() + ":" + getClass().getSimpleName() + ": " + m);
    }

    public abstract int layout_id();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(layout_id(), container, false);
        l("onCreateView");
        ((TextView) root.findViewById(R.id.ff_text_view)).setText(
                "Класс: " + getClass().getSimpleName());
        btn_get_weather = (Button) root.findViewById(R.id.ff_btn_0);
        btn_get_weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnClick(v);
            }
        });
        weather_tv = (TextView) root.findViewById(R.id.ff_text_view);
        city_name = (TextView) root.findViewById(R.id.ff_text_input);
        EventBus.getDefault().register(this);
        return root;
    }

    public void onBtnClick(View r) {
        EventBus.getDefault().post(new OnResumeInFragment(this.getClass()));
        btn_get_weather.setEnabled(false);
        city_name.setEnabled(false);
        dialog = new ProgressDialog(getActivity());
        dialog.setProgress(0);
        dialog.setMax(100);
        dialog.setTitle("Опрашиваем спутники");
        dialog.setIndeterminate(false);
        dialog.show();
        Intent wint = new Intent(getActivity(), WeatherService.class);
        wint.putExtra("city", city_name.getText().toString());
        getActivity().startService(wint);
    }

    class GetWeather extends AsyncTask<Void, Void, CityWeather> {

        @Override
        protected CityWeather doInBackground(Void... objects) {
            return WeatherManager.getWeather();
        }

        @Override
        protected void onPostExecute(CityWeather weather) {
            if (weather != null && weather.main != null)
                weather_tv.setText("Min: " + weather.main.temp_min + ", Max: " + weather.main.temp_max);
            btn_get_weather.setEnabled(true);
            city_name.setEnabled(true);
            if (dialog != null) {
                dialog.dismiss();
                dialog = null;
            }
        }
    }

    private GetWeather task;

    public void onEvent(CityWeather weather) {
        if (task == null || task.getStatus() == AsyncTask.Status.FINISHED) {
            task = new GetWeather();
            task.execute();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        onEvent(null);
    }
}
