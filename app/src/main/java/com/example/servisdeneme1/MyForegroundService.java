package com.example.servisdeneme1;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

public class MyForegroundService extends Service {
    private static final int NOTIFICATION_ID = 1;

    private Timer timer;
    public static int sayacMyForegroundService = 0;

    @Override
    public void onCreate() {
        super.onCreate();

        System.out.println("MyForegroundService oncreat çalıştı");

        bildirim();




    }
    public void bildirim(){

        // Bildirim açıldığında uygulamanın başlatılması için bir Intent oluşturun
        Intent notificationIntent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getActivity(this,
                    0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);

        }else {
            pendingIntent = PendingIntent.getActivity(this,
                    0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        }

        // Bildirim kanalı oluştur
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("my_channel_id", "My Channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
// Bildirimi gönder
        Notification notification = new NotificationCompat.Builder(this, "my_channel_id")
                .setContentTitle("Foreground Servis")
                .setContentText("Servis arka planda çalışıyor")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(NOTIFICATION_ID, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Her dakikada bir log yazdır
                sayacMyForegroundService++;
                Log.d("MyForegroundService", "Çalıştı sayac: "+sayacMyForegroundService);
                //bildirim();



            }
        }, 0, 60 * 1000); // 60 saniye (1000 ms) aralıklarla çalışacak
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void startForegroundService() {
        // Foreground servis olarak ayarlamak için bir bildirim oluşturun.
        Intent notificationIntent = new Intent(this, MainActivity.class); // MainActivity yerine kendi başlatmak istediğiniz aktiviteyi belirtin
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("Foreground Servis")
                .setContentText("Servis arka planda çalışıyor")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(NOTIFICATION_ID, notification);
    }
}
