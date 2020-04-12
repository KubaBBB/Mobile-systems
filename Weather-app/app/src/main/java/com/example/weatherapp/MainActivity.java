package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private TextView latTextView;
    private TextView lonTextView;
    private TextView cityTextView;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latTextView = findViewById(R.id.latShowTextView);
        lonTextView = findViewById(R.id.longShowTextView);
        webView = findViewById(R.id.webView);

        webView.loadUrl("http://www.tlsszarek.pl/");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }


    private static class MyHandler extends Handler{
        private final WeakReference<MainActivity> mActivity;
        MyHandler(MainActivity activity) {
            mActivity = new WeakReference<MainActivity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = mActivity.get();
            String lat = msg.getData().getString("lat");
            String lon = msg.getData().getString("lon");
            //String woeid = msg.getData().getString("woeid");
            String web = msg.getData().getString("web");
            String city = msg.getData().getString("city");
            //referencje pobrane wcześniej w metodzie onCreate(...)
            activity.latTextView.setText(lat);
            activity.lonTextView.setText(lon);
            //activity.woeidTextView.setText(woeid);
            activity.cityTextView.setText(city);
            activity.webView.loadDataWithBaseURL(null, web, "text/html", "utf-8", null);
        }
    }

    Handler myHandler = new MyHandler(this);
}
