package com.example.servisdeneme1;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MyBackgroundService extends Service {
    private Timer timer;
    public static int sayacMyBackgroundService = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        // Servisiniz başlatıldığında burada çalışacak kodu ekleyin.
        Log.d("MyBackgroundService", "Servis başlatıldı.");

        // Zamanlayıcıyı başlatın
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Her dakikada bir log yazdır
                Log.d("MyBackgroundService", "Çalıştı");
                sayacMyBackgroundService++;


            }
        }, 0, 60 * 1000); // 60 saniye (1000 ms) aralıklarla çalışacak
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        // Servis durdurulduğunda zamanlayıcıyı iptal edin
        if (timer != null) {
            timer.cancel();
        }
        Log.d("MyBackgroundService", "Servis durduruldu.");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
