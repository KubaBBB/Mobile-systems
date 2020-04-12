package com.example.weatherapp;

import android.app.Service;
import android.content.Intent;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LocalizationService extends Service {

    private final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        LocalizationService getService() {
            // Return this instance of LocalService so clients can call public methods
            return LocalizationService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private final String appid = "4526d487f12ef78b82b7a7d113faea64";
    public static String OPENWEATHER_WEATHER_QUERY = "http://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&mode=html&appid=4526d487f12ef78b82b7a7d113faea64";
    // usage String.format(OPENWEATHER_WEATHER_QUERY, lat,lon)

    public String getContentFromUrl(String addr) {
        String content = null;

        Log.v("[GEO WEATHER ACTIVITY]", addr); // TODO: formulate REQUEST
        HttpURLConnection urlConnection = null;
        URL url = null;
        try {
            url = new URL(addr);
            urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = in.readLine()) != null)
            {
                stringBuilder.append(line + "\n");
            }
            content = stringBuilder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(urlConnection!= null) urlConnection.disconnect();
        }
        return content;
    }

    public void onLocationChanged(Location location) {
        final double lat = (location.getLatitude());
        final double lon = location.getLongitude();
        new Thread(new Runnable() {
            @Override
            public void run() {
                updateWeather(lat,lon);
            }
        }).start();
    }

    private void updateWeather(double lat, double lon) {

    }

    ;

//    private final LocationListener locationListener = new LocationListener() {
//        @Override
//        public void onLocationChanged(Location location) {
//            final double lat = (location.getLatitude());
//            final double lon = location.getLongitude();
//            //kod obslugi najlepiej w osobnym wątku…
//        }
//    };
}
