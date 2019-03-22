package com.example.taskmanagement.pesonal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taskmanagement.R;

public class DetialActivity extends AppCompatActivity {


        dbHelper helper;
        TextView Activityname, Location, Time, Report, Date;
        private int primaryID;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detial);
            helper = new dbHelper(this);
            declaretion();
            Intent i = getIntent();
            primaryID=i.getIntExtra("_id", 0);
            displaydetail(primaryID);

        }
        private void displaydetail(int primaryID) {
            Cursor data = helper.getAllData(primaryID);
            Toast.makeText(getApplicationContext(), "Selected item: " + " " +primaryID, Toast.LENGTH_SHORT).show();
            data.moveToFirst();
            Activityname.setText(data.getString(1));
            Location.setText(data.getString(2));
            Time.setText(data.getString(3) );
            Report.setText(data.getString(4));
            Date.setText(data.getString(5) );
        }
        private void declaretion() {

            Activityname = (TextView) findViewById(R.id.activityname);
            Location = (TextView) findViewById(R.id.location);
            Time = (TextView) findViewById(R.id.time);
            Report= (TextView) findViewById(R.id.report);
            Date= (TextView) findViewById(R.id.date);
        }



    }