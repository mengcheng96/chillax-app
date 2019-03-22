package com.example.taskmanagement.pesonal;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.taskmanagement.R;

import java.util.Calendar;

public class Mainupdate extends AppCompatActivity {
    dbHelper helper;
    public int getIdselected;
    EditText getactivityname, getlocation,getreport;
    TextView gettime,getdate;
    String id;
    String format;
    int day,month,year,hour,minute;
    Calendar mCurrentDate,mCurrenttime;
    // private int idselected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainupdate);
        getactivityname = (EditText) findViewById(R.id.updatename);
        getlocation = (EditText) findViewById(R.id.updatelocation);
        gettime = (TextView) findViewById(R.id.updatetime);
        getreport = (EditText)findViewById(R.id.updatereport);
        getdate = (TextView) findViewById(R.id.updatedate);
        // mPickDate = (Button) findViewById(R.id.pickDate);


        mCurrentDate= Calendar.getInstance();
        day=mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month=mCurrentDate.get(Calendar.MONTH);
        year=mCurrentDate.get(Calendar.YEAR);
        month=month+1;

        mCurrenttime=Calendar.getInstance();
        hour=mCurrenttime.get(Calendar.HOUR_OF_DAY);
        minute=mCurrenttime.get(Calendar.MINUTE);
        seletedTimeFormat(hour);

        helper = new dbHelper(this);
        Intent i = getIntent();
        // getting attached intent data
        getIdselected= i.getIntExtra("id",0);
        String acname = i.getStringExtra("acname");
        String location= i.getStringExtra("location");
        String nowtime = i.getStringExtra("time");
        String report = i.getStringExtra("report");
        String nowdate = i.getStringExtra("date");


        // displaying selected product name
        Toast.makeText(getApplicationContext(), "Your ID" + " - " + getIdselected , Toast.LENGTH_SHORT).show();

        getactivityname.setText(acname,TextView.BufferType.EDITABLE);
        getlocation.setText(location,TextView.BufferType.EDITABLE);
        gettime.setText(nowtime);
        getreport.setText(report,TextView.BufferType.EDITABLE);
        getdate.setText(nowdate);


    }
    public void timeupdate(View view)
    {
        TimePickerDialog timePickerDialog= new TimePickerDialog(Mainupdate.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker View, int hourOfDay, int minute) {
                seletedTimeFormat(hourOfDay);
                gettime.setText(hourOfDay+":"+minute+""+format);
            }
        },hour,minute,true);
        timePickerDialog.show();

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
    public void dateupate(View view)
    {DatePickerDialog datePickerDialog=new DatePickerDialog(Mainupdate.this, new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            monthOfYear=monthOfYear+1;
            getdate.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
        }
    },year,month,day
    );
        datePickerDialog.show();

    }
    public void onClickUpdate(View v){
        String regexp="\\s+?";
        if(getactivityname.getText().toString().length()==0&&getdate.getText().toString().length()==0&&getreport.getText().toString().length()==0)
        {
            getactivityname.setError("Please Enter Activity Name");
            getdate.setError("Please Enter Date");
            getreport.setError("Enter Report");
        }
        else if(getactivityname.getText().toString().length()==0&&getreport.getText().toString().length()==0)
        {
            getactivityname.setError("Please Enter Activity Name");
            getreport.setError("Enter Report");
        }

        else if(getactivityname.getText().toString().matches(regexp))
        {
            getactivityname.setError("Please Enter Activity Name");
        }
        else if(getdate.getText().toString().matches(regexp))
        {
            getdate.setError("Please Enter Date");
        }
        else if(getreport.getText().toString().matches(regexp))
        {
            getreport.setError("Enter Report");
        }
        else {

            dbHelper helper = new dbHelper(Mainupdate.this);
            id = Integer.toString(getIdselected);
            Toast.makeText(getApplicationContext(), "Potato" + " - " + id, Toast.LENGTH_SHORT).show();

            Integer updateRow = helper.updateData(
                    id,
                    getactivityname.getText().toString(),
                    getlocation.getText().toString(),
                    gettime.getText().toString(),
                    getreport.getText().toString(),
                    getdate.getText().toString());
            //Log.d("Test", "" + id);
            if (updateRow > 0) {
                //   Log.d("Test", "Didnt make it");
                Toast.makeText(getApplicationContext(), "Successfully Updated." + id, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ListTaskActivity.class);
                startActivity(intent);
                finish();

            } else {
                //  Log.d("Test", "Didnt make it2");
                Toast.makeText(getApplicationContext(), "Something went wrong :(", Toast.LENGTH_SHORT).show();
            }
        }


    }

}