package com.example.taskmanagement.pesonal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.taskmanagement.Object_transaction.Object;
import com.example.taskmanagement.ListView.CustomListAdapter;
import com.example.taskmanagement.R;

import java.util.ArrayList;

public class ListTaskActivity extends AppCompatActivity {
    dbHelper helper;

    public static View inflatedlayout;
    TextView Activityname, Location, Time, Report, Date, ActivityNameTxt;
    private int recID;
    private int primaryID;
    ListAdapter listAdapter;
    TextView tx;
    ListView list;

    CustomListAdapter customadapter;
    ArrayList<Object> mObjects = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_task);
        helper = new dbHelper(this);
        list = (ListView) findViewById(R.id.list);

        customadapter = new CustomListAdapter(this, mObjects);

        customadapter.setOnMasterSelectedListener(new CustomListAdapter.OnOptionSelectedListener() {
            @Override
            public void onOptionSelected(int message) {
                getselectedIndext(message);
            }
        });

        this.getlist(null);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()){
            case R.id.input:
                finish();
                Intent intent=new Intent(this,MainInput.class);startActivity(intent);break;

            case R.id.delete:
                AlertDialog.Builder dgbox = new AlertDialog.Builder(this);
                dgbox.setTitle("Confirm?");
                dgbox.setMessage("Do you want to delete?");
                dgbox.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dbHelper db = new dbHelper(ListTaskActivity.this);
                        db.deleteAll();
                        getlist(null);

                    }
                });
                dgbox.setNegativeButton("Cancel", null);
                dgbox.show();break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        MenuItem searchItem=menu.findItem(R.id.menuSearch);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                getlist(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public void getselectedIndext(int position) {
        if(position==0)
        {

        }
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            populateAssignmentDetails(position);

        else {

            Intent intent = new Intent(this, DetialActivity.class);
            intent.putExtra("_id", position);
            startActivity(intent);

        }
    }


    private void populateAssignmentDetails(int position) {
        TextView Actvityname = (TextView) inflatedlayout.findViewById(R.id.activityname);
        TextView Location = (TextView) inflatedlayout.findViewById(R.id.location);
        TextView Time = (TextView) inflatedlayout.findViewById(R.id.time);
        TextView Report = (TextView) inflatedlayout.findViewById(R.id.report);
        TextView Date = (TextView) inflatedlayout.findViewById(R.id.date);
        Object ob = new Object();
        // int assignmentID = customadapter.getSelectedItemID(); //get details from the first assignment to be shown at the right side

        Cursor data = helper.getAllData(position);
        data.moveToFirst();
        Actvityname.setText(data.getString(1));
        Location.setText(data.getString(2));
        Time.setText(data.getString(3));
        Report.setText(data.getString(4));
        Date.setText(data.getString(5));
    }

    private void displaydetial() {

        Activityname = (TextView) findViewById(R.id.activityname);
        Location = (TextView) findViewById(R.id.location);
        Time = (TextView) findViewById(R.id.time);
        Report = (TextView) findViewById(R.id.report);
        Date = (TextView) findViewById(R.id.date);
    }

    private void displayAssignDetails(int primaryID) {

        Cursor data = helper.getAllData(primaryID);
        Toast.makeText(getApplicationContext(), "Selected item: " + " " + primaryID, Toast.LENGTH_SHORT).show();
        data.moveToFirst();
        Activityname.setText(data.getString(1));
        Location.setText(data.getString(2));
        Time.setText(data.getString(3));
        Report.setText(data.getString(4));
        Date.setText(data.getString(5));
    }


    private void getlist(String searchitem) {
        mObjects.clear();
        dbHelper db = new dbHelper(this);
        db.openDB();
        Cursor c = db.retrieve(searchitem);
        Object ob = null;

        while (c.moveToNext()) {
            int id = c.getInt(0);
            String name = c.getString(1);

            ob = new Object();
            ob.setId(id);
            ob.setAcName(name);
            mObjects.add(ob);


        }

        db.closeDB();
        list.setAdapter(customadapter);


    }




    public void Update()
    {
        Cursor data = helper.getAllData(customadapter.getSelectedItemID());

        data.moveToFirst();
        int id = customadapter.getSelectedItemID();
        String Acname = data.getString(1);
        String location= data.getString(2);
        String time= data.getString(3);
        String report= data.getString(4);
        String date = data.getString(5);

        // Launching new Activity on selecting single List Item
        Intent i = new Intent(getApplicationContext(), Mainupdate.class);
        ;
        // sending data to new activity
        i.putExtra("id",id);
        i.putExtra("acname", Acname);
        i.putExtra("location",location);
        i.putExtra("time",time);
        i.putExtra("report",report);
        i.putExtra("date",date);


        startActivity(i);


    }
    private void delete()
    {
        //GET ID
        int id=customadapter.getSelectedItemID();

        //DELETE FROM DB
        dbHelper db=new dbHelper(this);
        db.openDB();
        boolean deleted=db.delete(id);
        db.closeDB();

        if(deleted)
        {
            getlist(null);
            Toast.makeText(this," Successful Delete",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"Unable To Delete",Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public boolean onContextItemSelected(MenuItem index) {
        CharSequence Indexname=index.getTitle();
        if(Indexname=="Add New Item")
        {
            Intent intent = new Intent(this, MainInput.class);

            startActivity(intent);


        }else  if(Indexname=="EDIT")
        {Update();
            // IntentMaininput(updateitem);

        }else  if(Indexname=="DELETE")
        {  AlertDialog.Builder dgbox = new AlertDialog.Builder(this);
            dgbox.setTitle("Confirm?");
            dgbox.setMessage("Do you want to delete?");
            dgbox.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int whichButton) {
                    delete();

                }
            });
            dgbox.setNegativeButton("Cancel", null);
            dgbox.show();
        }


        return super.onContextItemSelected(index);
    }



}
