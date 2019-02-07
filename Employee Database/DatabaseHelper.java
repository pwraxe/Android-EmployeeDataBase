package com.example.akshay.employeedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "IT_Industry.db";
    private static final String TABLE_NAME = "Employee";
    private static final String COLUMN_1 = "ID";
    private static final String COLUMN_2 = "FirstName";
    private static final String COLUMN_3 = "LastName";
    private static final String COLUMN_4 = "EmailID";
    private static final String COLUMN_5 = "MobileNo";
    private static final String COLUMN_6 = "Department";
    private static final String COLUMN_7 = "Salary";
    private static final String COLUMN_8 = "City";
    private static final String COLUMN_9 = "Experience";





    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table "+TABLE_NAME+" ("+COLUMN_1+" INTEGER PRIMARY KEY,"+COLUMN_2+" TEXT,"+COLUMN_3+" TEXT,"+COLUMN_4+" TEXT,"+COLUMN_5+" LONG,"+COLUMN_6+" TEXT,"+COLUMN_7+" INTEGER,"+COLUMN_8+" TEXT,"+COLUMN_9+" INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists "+TABLE_NAME);
    }

    public boolean insertData(int id,String fname,String lname,String email,long mobileNo,String dept,int salary,String city,int experience)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_1,id);
        cv.put(COLUMN_2,fname);
        cv.put(COLUMN_3,lname);
        cv.put(COLUMN_4,email);
        cv.put(COLUMN_5,mobileNo);
        cv.put(COLUMN_6,dept);
        cv.put(COLUMN_7,salary);
        cv.put(COLUMN_8,city);
        cv.put(COLUMN_9,experience);

        Long result = db.insert(TABLE_NAME,null,cv);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllDatabaseData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from "+TABLE_NAME,null);
        return c;
    }

    public boolean updateAllData(int ID,String fname,String lname,String email, long mobile,String dept,int salary,String city,int exp)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_1,ID);
        cv.put(COLUMN_2,fname);
        cv.put(COLUMN_3,lname);
        cv.put(COLUMN_4,email);
        cv.put(COLUMN_5,mobile);
        cv.put(COLUMN_6,dept);
        cv.put(COLUMN_7,salary);
        cv.put(COLUMN_8,city);
        cv.put(COLUMN_9,exp);
        db.update(TABLE_NAME,cv,"ID = ?",new String[] { ID+"" });
        return true;
    }

    public int deleteData(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[] {id+""});
    }


}
