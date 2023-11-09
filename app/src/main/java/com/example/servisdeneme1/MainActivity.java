package com.example.servisdeneme1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ekranaBas();


        Intent serviceIntent = new Intent(this, MyForegroundService.class);
        startService(serviceIntent);

        Button startServiceButton = findViewById(R.id.startServiceButton);
        startServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(MainActivity.this, MyBackgroundService.class);
                startService(serviceIntent);
            }
        });

        Button stopServiceButton = findViewById(R.id.stopServiceButton);
        stopServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(MainActivity.this, MyBackgroundService.class);
                stopService(serviceIntent);
            }
        });

        Button ekranaYazButton = findViewById(R.id.button2);
        ekranaYazButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              ekranaBas();
            }
        });

    }

    public void ekranaBas(){
        TextView textView;
        TextView textView2;
        textView=(TextView) findViewById(R.id.textView);
        textView2=(TextView) findViewById(R.id.textView2);
        textView.setText("sayacMyBackgroundService: "+MyBackgroundService.sayacMyBackgroundService);
        textView2.setText("sayacMyForegroundService: "+MyForegroundService.sayacMyForegroundService);
    }





}