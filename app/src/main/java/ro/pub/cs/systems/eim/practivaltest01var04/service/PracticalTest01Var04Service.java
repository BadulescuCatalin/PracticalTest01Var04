package ro.pub.cs.systems.eim.practivaltest01var04.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class PracticalTest01Var04Service extends Service {
    ProcessingThread processingThread = null;

    @SuppressLint("ForegroundServiceType")
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Service", "onCreate() method was invoked");

        if (Build.VERSION.SDK_INT >= 26) {
            String CHANNEL_ID = "my_channel_01";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);

            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);

            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("")
                    .setContentText("").build();

            startForeground(1, notification);
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Service", "onBind() method was invoked");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("Service", "onUnbind() method was invoked");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d("Service", "onRebind() method was invoked");
    }

    @Override
    public void onDestroy() {
        Log.d("Service", "onDestroy() method was invoked");
        processingThread.stopThread();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Service", "onStartCommand() method was invoked");
        // de extras din intent
        String nume = intent.getStringExtra("nume");
        String grupa = intent.getStringExtra("grupa");
        ProcessingThread processingThread = new ProcessingThread(this, nume, grupa);
        processingThread.start();
        return START_REDELIVER_INTENT;
    }
}

