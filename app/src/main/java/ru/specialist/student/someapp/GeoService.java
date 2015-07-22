package ru.specialist.student.someapp;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class GeoService extends Service implements LocationListener {
    private LocationManager location_manager;

    public void l(String m) {
        Log.d("MC4", hashCode() + ":" + getClass().getSimpleName() + ": " + m);
    }

    public GeoService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        l(": onCreate");
        location_manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        location_manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        l("onLocCh: " + location.getLatitude() + ":" + location.getLongitude());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        l("onStatusChanged: " + s + ", " + i);
    }

    @Override
    public void onProviderEnabled(String s) {
        l("onProviderEnabled: " + s);
    }

    @Override
    public void onProviderDisabled(String s) {
        l("onProviderDisabled: " + s);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        location_manager.removeUpdates(this);
    }
}
