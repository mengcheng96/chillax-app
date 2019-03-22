package com.example.taskmanagement.Activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import com.example.taskmanagement.Activities.HomeActivity;
import com.example.taskmanagement.R;

import static com.example.taskmanagement.Activities.App.channel_1;

public class AlarmReceiver extends BroadcastReceiver {
    private NotificationManagerCompat mNotificationManager;
    @Override

    public void onReceive(Context context, Intent intent)
    {
        Toast.makeText(context, "Alarm! Wake up! Wake up!", Toast.LENGTH_LONG).show();
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null)
        {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();

      /* int notificationid=intent.getIntExtra("notificationid",0);
       String message= intent.getStringExtra("todo");
       Intent mainIntent= new Intent(context, HomeActivity.class);
       PendingIntent contentIntent= PendingIntent.getActivity(context,0,mainIntent,0);
        NotificationManager mynoti=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder build =new Notification.Builder(context);

        build.setSmallIcon(R.drawable.ic_right)
                .setContentTitle("is Time")
                .setContentText(message)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentIntent(contentIntent);
        mynoti.notify(notificationid,build.build());*/

       /* Notification notification = new NotificationCompat.Builder(context, channel_1)
                .setSmallIcon(R.drawable.ic_right)
                .setContentTitle("is Time")
                .setContentText("hi")
                .setWhen(System.currentTimeMillis())
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)

                .build();
        mNotificationManager.notify(1, notification);*/

    }
}
