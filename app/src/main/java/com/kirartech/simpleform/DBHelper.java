package com.kirartech.simpleform;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by MOHIT on 10/12/2016.
 */
public class DBHelper extends SQLiteOpenHelper{
    private static final int Database_Version = 2;
    private static final String Database_Name = "mydatabase.db";
    private static final String Table_Name = "mytable";
    private static final String Create_Database_Query = "create table "+Table_Name+"(name text, email text, phone text,dob text);";
    private static final String Select_Query = "select * from "+Table_Name;

    //constructor
    DBHelper(Context context){
        super(context,Database_Name,null,Database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(Create_Database_Query);
    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("drop table if exists "+Table_Name);
        onCreate(db);
    }

    public boolean insertValues(String name,String phone,String email,String dob){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("email",email);
        contentValues.put("phone",phone);
        contentValues.put("dob",dob);
        db.insert(Table_Name,null,contentValues);
        return true;
    }

    public ArrayList<String> getAllData(String name){
        ArrayList<String> arrayList = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+Table_Name+" where name=\'"+name+"\';",null);
        res.moveToFirst();
        while(res.isAfterLast()==false){
            arrayList.add(res.getString(res.getColumnIndex("dob")));
            arrayList.add(res.getString(res.getColumnIndex("email")));
            arrayList.add(res.getString(res.getColumnIndex("phone")));
            res.moveToNext();
        }
        return arrayList;
    }

    public ArrayList<String> getAllNames(){
        ArrayList<String> arrayList = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(Select_Query,null);
        res.moveToFirst();
        while(res.isAfterLast()==false){
            arrayList.add(res.getString(res.getColumnIndex("name")));
            res.moveToNext();
        }
        return arrayList;
    }
}
