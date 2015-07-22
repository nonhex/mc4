package ru.specialist.student.someapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import ru.specialist.student.someapp.MainActivity;
import ru.specialist.student.someapp.R;

/**
 * Created by xema on 22.07.15.
 */
public class WeatherWidgetBroadcast extends AppWidgetProvider {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MC4", getClass().getSimpleName());
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.d("MC4", "onUpdate:" + getClass().getSimpleName());
        for (int i = 0; i < appWidgetIds.length; i++) {
            int appWidgetId = appWidgetIds[i];

            // Create an Intent to launch ExampleActivity
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            // Get the layout for the App Widget and attach an on-click listener
            // to the button
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget);
            views.setOnClickPendingIntent(R.id.btn_wdgt, pendingIntent);

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
        Log.d("MC4", "onAppWidgetOptionsChanged:" + getClass().getSimpleName());
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.d("MC4", "onEnabled:" + getClass().getSimpleName());
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d("MC4", "onDisabled:" + getClass().getSimpleName());
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.d("MC4", "onDeleted:" + getClass().getSimpleName());
    }

    @Override
    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        super.onRestored(context, oldWidgetIds, newWidgetIds);
        Log.d("MC4", "onRestored:" + getClass().getSimpleName());
    }
}
