package ru.specialist.student.someapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import de.greenrobot.event.EventBus;
import ru.specialist.student.someapp.utils.http.HttpService;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent srv = new Intent(this, WeatherService.class);
        srv.putExtra("city", "Moscow");
        startService(srv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    public void onEvent(MessageInFrag msg) {
        Log.d("MC4", "Message in frag got to activity");
    }

    public void onEvent(OnResumeInFragment orif) {
        Log.d("MC4", "OnResumeInFragment got to activity" + orif.fragmentClass.getSimpleName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HttpService.destroy();
    }
}
