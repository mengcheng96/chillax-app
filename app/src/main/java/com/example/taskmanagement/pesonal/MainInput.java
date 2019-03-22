package com.example.taskmanagement.pesonal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.allyants.notifyme.NotifyMe;
import com.example.taskmanagement.Activities.HomeActivity;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import com.example.taskmanagement.R;

import java.util.Calendar;

public class MainInput extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener  {
    EditText Acname;
    EditText Location;
    TextView Date;
    TextView Time;
    EditText Report;
    String format;
    dbHelper helper;
    int day,month,year,hour,minute;
   // Calendar mCurrentDate,mCurrenttime;
    Calendar now = Calendar.getInstance();
    TimePickerDialog tpd;
    DatePickerDialog dpd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_input);


        //Retrieve Data
        Acname = (EditText) findViewById(R.id.editname);
        Location = (EditText) findViewById(R.id.editlocation);
        Time=(TextView) findViewById(R.id.edittime);
        Report = (EditText) findViewById(R.id.editreport);
        Date=(TextView)findViewById(R.id.editdate);
        helper = new dbHelper(this);
        Button btnNotify = findViewById(R.id.btnt);
       // Time.setText("00:00AM");
        dpd = DatePickerDialog.newInstance(
                MainInput.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );

        tpd = TimePickerDialog.newInstance(
                MainInput.this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                now.get(Calendar.SECOND),

                false

        );
        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });
     /*   mCurrentDate= Calendar.getInstance();
        day=mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month=mCurrentDate.get(Calendar.MONTH);
        year=mCurrentDate.get(Calendar.YEAR);
        month=month+1;
        Date.setText(day+"/"+month+"/"+year);

        mCurrenttime=Calendar.getInstance();
        hour=mCurrenttime.get(Calendar.HOUR_OF_DAY);
        minute=mCurrenttime.get(Calendar.MINUTE);

        seletedTimeFormat(hour);*/


    }

    public void seletedTimeFormat(int hour){
        if(hour==0)
        {
            hour+=12;
            format="AM ";

        }
        else if(hour==12)
        {
            format="PM ";

        }
        else if(hour>12){
            hour-=12;
            format="PM ";

        }
        else{
            format="AM ";
        }
    }


 /*   public void date(View view)
    {
        DatePickerDialog datePickerDialog=new DatePickerDialog(MainInput.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear=monthOfYear+1;
                Date.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
            }
        },year,month,day
        );
        datePickerDialog.show();

    }
    public void time(View view)
    {
        final TimePickerDialog timePickerDialog= new TimePickerDialog(MainInput.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker View, int hourOfDay, int minute) {
                seletedTimeFormat(hourOfDay);
                Time.setText(hourOfDay+":"+minute+""+format);


            }
        },hour,minute,true);
        timePickerDialog.show();


    }*/
    public void cancel(View v)
    {  finish();

    }

    public void insertdata(View v) {
        String regexp="\\s+?";
        if(Acname.getText().toString().length()==0&&Date.getText().toString().length()==0&&Report.getText().toString().length()==0)
        {
            Acname.setError("Please Enter Activity Name");
            Date.setError("Please Enter Date");
            Report.setError("Enter Report");
        }
        else if(Acname.getText().toString().length()==0&&Report.getText().toString().length()==0)
        {
            Acname.setError("Please Enter Activity Name");
            Report.setError("Enter Report");
        }

        else if(Acname.getText().toString().matches(regexp))
        {
            Acname.setError("Please Enter Activity Name");
        }
        else if(Date.getText().toString().matches(regexp))
        {
            Date.setError("Please Enter Date");
        }
        else if(Report.getText().toString().matches(regexp))
        {
            Report.setError("Enter Report");
        }
        else {
            dbHelper helper = new dbHelper(MainInput.this);
            boolean isInserted = helper.insertData(

                    Acname.getText().toString(),
                    Location.getText().toString(),
                    Time.getText().toString(),
                    Report.getText().toString(),
                    Date.getText().toString());

            if (isInserted == true)
                Toast.makeText(MainInput.this, "Your Assignment has been Inserted", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainInput.this, "Your Assignment has not been Inserted", Toast.LENGTH_LONG).show();
            Intent homeIntent = new Intent(MainInput.this, ListTaskActivity.class);
            startActivity(homeIntent);
        }


    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        now.set(Calendar.YEAR, year);
        now.set(Calendar.MONTH, monthOfYear);
        now.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        tpd.show(getFragmentManager(), "Timepickerdialog");
        Date.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {

        now.set(Calendar.HOUR_OF_DAY, hourOfDay);
        now.set(Calendar.MINUTE, minute);
        now.set(Calendar.SECOND, second);
        seletedTimeFormat(hourOfDay);
        Time.setText(hourOfDay+":"+minute+""+format);
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.putExtra("test", "I am a String");
        NotifyMe notifyMe = new NotifyMe.Builder(getApplicationContext())
                .title( Acname.getText().toString())
                .content( Report.getText().toString())
                .color(255, 0, 0, 255)
                .led_color(255, 255, 255, 255)
                .time(now)
                .addAction(intent, "Snooze", false)
                .key("test")
                .addAction(new Intent(), "Dismiss", true, false)
                .addAction(intent, "Done")
                .large_icon(R.mipmap.ic_launcher_round)
                .build();

    }
}



