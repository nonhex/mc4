package ru.specialist.student.someapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by xema on 22.07.15.
 */
public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context ctx() {
        return context;
    }
}
