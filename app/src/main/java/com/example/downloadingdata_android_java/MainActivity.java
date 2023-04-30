package com.example.downloadingdata_android_java;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button getPageSourceCode,nextPage;
    TextView responseCode;
    ConnectivityManager connMgr;
    NetworkInfo info;
    public int responseAvailable=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPageSourceCode=findViewById(R.id.getPageSourceCode);
        nextPage=findViewById(R.id.nextPage);
        responseCode=findViewById(R.id.responseCode);

    }

    @Override
    protected void onResume() {
        super.onResume();
        connMgr=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        info=connMgr.getActiveNetworkInfo();

    }

    @SuppressLint("NonConstantResourceId")
    public void runTask(View view) {
        switch (view.getId()){
            case R.id.getPageSourceCode:
                if (info!=null && info.isConnected()){
                    responseCode=findViewById(R.id.responseCode);
                    GetPageSourceCode getPageSourceCodeObj=new GetPageSourceCode(responseCode);
                    Thread t=new Thread(getPageSourceCodeObj);
                    t.start();
                    responseAvailable=1;
                    Toast.makeText(this, "Retrieving Data, Wait", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.nextPage:
                if (responseAvailable==1){
                    Intent intent=new Intent(MainActivity.this,PageActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(this, "Get Page Source Code", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}