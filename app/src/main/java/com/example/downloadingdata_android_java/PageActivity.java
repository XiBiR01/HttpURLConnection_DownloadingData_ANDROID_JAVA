package com.example.downloadingdata_android_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;

public class PageActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        getSupportActionBar().hide();
        webView=findViewById(R.id.webView);
        Runnable run=new Runnable() {
            @Override
            public void run() {
                Handler h=new Handler(getMainLooper());
                h.post(new Runnable() {
                    @Override
                    public void run() {
                        webView.loadUrl("https://www.google.com/");
                        webView.setWebViewClient(new WebViewClient());
                    }
                });
            }
        };
        Thread thread=new Thread(run);
        thread.start();
    }
}