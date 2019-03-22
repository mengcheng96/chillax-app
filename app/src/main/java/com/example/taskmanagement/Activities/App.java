package com.example.taskmanagement.Activities;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {
    public static final String channel_1="chanel1";
    public static final String channel_2="chanel2";
    @Override
    public void onCreate(){
        super.onCreate();
        createNotifactionChannels();
    }
    private void createNotifactionChannels()
    {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(channel_1,"chanel1", NotificationManager.IMPORTANCE_HIGH);
          channel1.setDescription("this is channel 1");
            NotificationChannel channel2 = new NotificationChannel(channel_2,"chanel2", NotificationManager.IMPORTANCE_LOW);
            channel1.setDescription("this is channel 2");
            NotificationManager mr =getSystemService(NotificationManager.class);
            mr.createNotificationChannel(channel1);
            mr.createNotificationChannel(channel2);
        }

    }
}
