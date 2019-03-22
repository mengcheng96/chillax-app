package com.example.taskmanagement.Activities;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.taskmanagement.R;
//import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
//import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import static com.example.taskmanagement.Activities.App.channel_1;

public class HomeActivity extends AppCompatActivity /*implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener */ {
   // TimePickerDialog tpd;
   // DatePickerDialog dpd;
    EditText etTitle,etContent;
    Calendar now = Calendar.getInstance();
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    private NotificationManagerCompat mNotificationManager;
    private EditText etT;
    private EditText etM;
    private int notificationid=1;
    TimePicker alarmTimePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button btnNotify = findViewById(R.id.btn);
        mNotificationManager=NotificationManagerCompat.from(this);

        etT = findViewById(R.id.etTitle);
        etM = findViewById(R.id.etContent);
        alarmTimePicker = (TimePicker) findViewById(R.id.timePicker);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

     /*   dpd = DatePickerDialog.newInstance(
                HomeActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );

        tpd = TimePickerDialog.newInstance(
                HomeActivity.this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                now.get(Calendar.SECOND),
                false
        );*/


        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



             //  dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });
    }

   /* @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        now.set(Calendar.YEAR,year);
        now.set(Calendar.MONTH,monthOfYear);
        now.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        tpd.show(getFragmentManager(), "Timepickerdialog");
    }*/
    long time;
    /*@Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {


      /*  Intent dialogIntent = new Intent(this, AlarmReceiver.class);
        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        // alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        // pendingIntent = PendingIntent.getBroadcast(mContext, 0, dialogIntent,PendingIntent.FLAG_CANCEL_CURRENT);
        Intent intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        time=(now.getTimeInMillis()-(now.getTimeInMillis()%60000));

        if(System.currentTimeMillis()>time)
        {
            if (now.AM_PM == 0)
                time = time + (1000*60*60*12);
            else
                time = time + (1000*60*60*24);


        }*/
        //  alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent);
     //   alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,time, 10000, pendingIntent);

      /*  Intent intent= new Intent(HomeActivity.this,AlarmReceiver.class);
        intent.putExtra("notificationid",notificationid);
        intent.putExtra("todo", etT.getText().toString());
        PendingIntent alarmIntent=PendingIntent.getBroadcast(HomeActivity.this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarm =(AlarmManager)getSystemService(ALARM_SERVICE);
        Calendar st= Calendar.getInstance();

        st.set(Calendar.HOUR_OF_DAY,hourOfDay);
        st.set(Calendar.MINUTE,minute);
        st.set(Calendar.SECOND,second);
        long alarmstartime= st.getTimeInMillis();
        alarm.set(AlarmManager.RTC_WAKEUP,alarmstartime,alarmIntent);
        Toast.makeText(this,"done",Toast.LENGTH_SHORT).show();
    }*/
    public void sendonchannel1 (View v) {
        TimePicker tp= findViewById(R.id.timePicker);
        int hour = tp.getCurrentHour();
        int min =tp.getCurrentMinute();
       /* Intent intent1= new Intent(HomeActivity.this,AlarmReceiver.class);
        intent1.putExtra("notificationid",notificationid);
        intent1.putExtra("todo", etT.getText().toString());
        PendingIntent alarmIntent=PendingIntent.getBroadcast(HomeActivity.this,0,intent1,PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarm =(AlarmManager)getSystemService(ALARM_SERVICE);
        Calendar st= Calendar.getInstance();

        st.set(Calendar.HOUR_OF_DAY,hour);
        st.set(Calendar.MINUTE,min);
        st.set(Calendar.SECOND,0);
        long alarmstartime= st.getTimeInMillis();
        alarm.set(AlarmManager.RTC_WAKEUP,alarmstartime,alarmIntent);
        Toast.makeText(this,"done",Toast.LENGTH_SHORT).show();*/


        Toast.makeText(HomeActivity.this, "ALARM ON", Toast.LENGTH_SHORT).show();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
        calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
        Intent intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
        if (System.currentTimeMillis() > time) {
            if (calendar.AM_PM == 0)
                time = time + (1000 * 60 * 60 * 12);
            else
                time = time + (1000 * 60 * 60 * 24);
        }


      //  alarm.setRepeating(AlarmManager.RTC_WAKEUP,time,1000,pendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent);


         /*String title = etT.getText().toString();
           String message = etM.getText().toString();

            Notification notification = new NotificationCompat.Builder(this, channel_1)
                    .setSmallIcon(R.drawable.ic_right)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setWhen(time)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)

                    .build();
            mNotificationManager.notify(1, notification);*/

    }
    public void sendonchannel2 (View v){
        alarmManager.cancel(pendingIntent);
        Toast.makeText(HomeActivity.this, "ALARM OFF", Toast.LENGTH_SHORT).show();
        Notification notification = new NotificationCompat.Builder(this, channel_1)
                .setSmallIcon(R.drawable.ic_right)
                .setContentTitle("alarm stop")
                .setContentText("alarm stoped")
                .setWhen(time)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)

                .build();
        mNotificationManager.notify(1, notification);
    }
    }




