package com.example.downloadingdata_android_java;

import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class GetPageSourceCode extends Handler implements Runnable {
    TextView responseCode;

    public GetPageSourceCode(TextView responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public void run() {
        String url="https://www.google.com/";
        final int[] isDone = {0};
        try {
            URL requestUrl=new URL(url);
            HttpURLConnection connection=(HttpURLConnection)requestUrl.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            int response=connection.getResponseCode();
            InputStream stream=connection.getInputStream();
            StringBuilder builder=new StringBuilder();
            BufferedReader reader=new BufferedReader(new InputStreamReader(stream));
            String line;
            while((line=reader.readLine())!=null){
                builder.append(line).append("\n");
            }
            String resultString=builder.toString();
            Handler handler=new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    responseCode.setText(resultString);
                    isDone[0] =1;
                }
            });

            if (isDone[0] ==1){
                connection.disconnect();
                if (stream!=null){
                    stream.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
