package com.obe.quranid2.module.qibla;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.obe.quranid2.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GPSTracker extends Service implements LocationListener {
    private static final String TAG = GPSTracker.class.getName();
    private final Context context;

    public GPSTracker(Context context) {
        this.context = context;
        getLocation();
    }

    boolean isGpsEnabled = false;
    boolean isNetworkEnabled = false;
    boolean isGPSTrackingEnabled = false;
    Location location;
    double longitude;
    double latitude;
    int geoCoderMaxResults = 1;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; //meters
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60; //Milliseconds

    protected LocationManager locationManager;
    private String providerInfo;

    public void getLocation() {
        try {
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (isGpsEnabled) {
                this.isGPSTrackingEnabled = true;
                Log.d(TAG, "GPS Tracking is enabled!!");
                providerInfo = LocationManager.GPS_PROVIDER;
            } else if (isNetworkEnabled) {
                this.isGPSTrackingEnabled = true;
                Log.d(TAG, "Application uses network to Track location!");
                providerInfo = LocationManager.NETWORK_PROVIDER;
            }
            if (!providerInfo.isEmpty()) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                locationManager.requestLocationUpdates(providerInfo, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
            }
        }catch (Exception e){
            Log.e(TAG, "Impossible to connect to Location Manager", e);
        }
    }

    public void updateGPSCoordinates(){
        if (location != null){
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
    }

    public double getLatitude(){
        if (location != null){
            latitude = location.getLatitude();
        }
        return latitude;
    }

    public double getLongitude(){
        if (location != null){
            longitude = location.getLongitude();
        }
        return longitude;
    }

    public  boolean getIsGPSTrackingEnabled(){
        return this.isGPSTrackingEnabled;
    }

    public  void stopUsingGPS(){
        if(locationManager !=null){
            locationManager.removeUpdates(GPSTracker.this);

        }
    }

    public void showSettingsAlert(){
        Toast.makeText(context, "toot", Toast.LENGTH_LONG).show();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(R.string.GPSAlertDialogTitle);
        alertDialog.setMessage(R.string.GPSAlertDialogMessage);
        alertDialog.setPositiveButton(R.string.action_settings, (dialog, which) -> {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            context.startActivity(intent);
        });
        alertDialog.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.cancel());
        alertDialog.show();
    }

    public List<Address> getGeocoderAddress(Context context) throws IOException {
        if(location != null){
            Geocoder geocoder = new Geocoder(context, Locale.ENGLISH);

            return geocoder.getFromLocation(latitude,longitude, this.geoCoderMaxResults);
        }
        return null;
    }


    public String getAddressLine(Context context) throws IOException {
        List<Address>addresses= getGeocoderAddress(context);
        if(addresses != null && addresses.size()>0){
            Address address = addresses.get(0);
            return address.getAddressLine(0);
        }else {
            return null;
        }

    }


    public String getLocality(Context context) throws IOException {
        List<Address>addresses = getGeocoderAddress(context);
        if(addresses != null && addresses.size()>0){
            Address address = addresses.get(0);
            return address.getLocality();
        }else{
            return null;
        }
    }

    public String getPostalCode(Context context) throws IOException {
        List<Address>addresses= getGeocoderAddress(context);
        if (addresses != null && addresses.size()>0) {
            Address address = addresses.get(0);
            return address.getPostalCode();

        }else {
            return null;
        }
    }

    public String getCountryName(Context context) throws IOException {
        List<Address>addresses = getGeocoderAddress(context);
        if (addresses!=null && addresses.size()>0) {
            Address address=addresses.get(0);
            return address.getCountryName();
        }else{
            return null;
        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {
        LocationListener.super.onLocationChanged(locations);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }
}
