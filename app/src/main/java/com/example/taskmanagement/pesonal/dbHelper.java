package com.example.taskmanagement.pesonal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class dbHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;
    private static final String DATABASE_NAME="assignment2.db";
    public static final String TABLE_NAME = "mykid";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="Activity_Name";
    public static final String COL_3 = "Location";
    public static final String COL_4 = "Time";
    public static final String COL_5 = "Report";
    public static final String COL_6 = "Date";




    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL Query
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, ACTIVITY_NAME TEXT NOT NULL,LOCATION TEXT NOT NULL,TIME TEXT NOT NULL,REPORT TEXT NOT NULL, DATE TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String activity_name, String location, String time, String report,String date) {
        boolean isSuccess = false;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_2, activity_name);
            contentValues.put(COL_3, location);
            contentValues.put(COL_4, time);
            contentValues.put(COL_5, report);
            contentValues.put(COL_6, date);


            String query = String.format("INSERT INTO %s(Activity_Name,Location,Time,Report,Date)VALUES('%s','%s','%s','%s','%s')", TABLE_NAME,activity_name, location,  time, report,  date);
            db.execSQL(query);

            isSuccess = true;
        }
        catch (Exception e)
        {
            Log.e("test", e.getMessage());
        }
        return isSuccess;
    }

    private String SQLSelectAssignment(int id)
    {
        StringBuilder sb = new StringBuilder();
        StringBuilder sbFilter = new StringBuilder();

        sb.append(String.format("SELECT * FROM %s ", TABLE_NAME));

        if (id > 0)
        {
            if (sbFilter.length() > 0) sbFilter.append(" AND ");
            sbFilter.append(String.format("%s = '%s' ", COL_1, id));
        }

        if (sbFilter.length() > 0) sb.append(String.format("WHERE %s ", sbFilter));
        sb.append("ORDER BY Activity_Name ASC ");

        return sb.toString();
    }

    public Cursor getAllData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(SQLSelectAssignment(id), null);
        return res;
    }
    public Cursor retrieve(String searchterm)
    {
        String[] columns={COL_1,COL_2};
        Cursor c=null;
        if(searchterm!=null&&searchterm.length()>0)
        {
            String sql="SELECT * FROM "+TABLE_NAME+" WHERE "+COL_2+" LIKE '%"+searchterm+"%'";
            c=db.rawQuery(sql,null);
            return c;
        }

         c=db.query(TABLE_NAME,columns,null,null,null,null,COL_4+","+COL_6 +" ASC");
        return c;
    }
    public void openDB()
    {
        try
        {
            db=this.getWritableDatabase();
        }catch (SQLException e)
        {

        }
    }
    public void closeDB()
    {
        try
        {
            this.close();
        }catch (SQLException e)
        {

        }
    }
    public Integer deleteData (int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COL_1+" =?",new String[]{String.valueOf(id)});
    }
    public boolean delete(int id)
    {
        try
        {
            int result=db.delete(TABLE_NAME, COL_1+" =?",new String[]{String.valueOf(id)});
            if(result>0)
            {
                return true;
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }

        public void deleteAll()
        {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("delete from "+ TABLE_NAME);
            db.close();
        }

    //Update Query '-'
    //Update Query '-'

        public Integer updateData(String id, String activity_name, String location, String time, String report, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //  contentValues.put(C1, id);
        contentValues.put(COL_2, activity_name);
        contentValues.put(COL_3, location);
        contentValues.put(COL_4, time);
        contentValues.put(COL_5, report);
        contentValues.put(COL_6, date);
        return db.update(TABLE_NAME,contentValues, "ID = ?", new String[] {id});
    }
}

