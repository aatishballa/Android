package com.example.mc9509dw.finalapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by mc9509dw on 4/9/2017.
 */

public class DBAdapter {

    private static final String KEY_ROWID = "_id";  //Give constant names to the rows
    private static final String KEY_NUM = "deers";
    private static final String KEY_DISTANCE = "distance";
    private static final String KEY_TIME = "time";
    private static final String KEY_DIRECTION = "direction";
    private static final String KEY_TYPE = "type";
    private static final String KEY_POINTS="points";
    private static final String KEY_AGE="age";
    private static final String KEY_SEX="sex";


    private static final String TAG = "DBAdapter";
    private static final String DATABASE_NAME = "MyDB";
    private static final String DATABASE_TABLE = "details";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =  //SQL commands are a pain, so make a string constant to do it
            "create table details (_id integer primary key autoincrement, "
                    + "deers text not null, distance text not null,time text not null,direction text not null,type text not null, points text not null,age text not null,sex text not null );";

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    DBAdapter(Context context) //SQLiteOpenHelper requires a Context to create it, so we need one, too
    {
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                Log.d(TAG,DATABASE_CREATE);
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }

    //---opens the database---
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    void close()
    {
        DBHelper.close();
    }

    //---insert details into the database---
    long insertDetails(String num_deers, String dis, String time, String direction,String type, String points, String age,String sex)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NUM,num_deers);
        initialValues.put(KEY_DISTANCE, dis);
        initialValues.put(KEY_TIME, time);
        initialValues.put(KEY_DIRECTION, direction);
        initialValues.put(KEY_TYPE,type);
        initialValues.put(KEY_POINTS,points);
        initialValues.put(KEY_AGE,age);
        initialValues.put(KEY_SEX,sex);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---retrieves all the contacts---
    Cursor getAllDetails()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID,KEY_NUM,KEY_DISTANCE,KEY_TIME,
                KEY_DIRECTION,KEY_TYPE,KEY_POINTS,KEY_AGE,KEY_SEX}, null, null, null, null, null);
    }

    //---retrieves a particular contact---
    public Cursor getDetail(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,KEY_NUM,KEY_DISTANCE,KEY_TIME,
                                KEY_DIRECTION,KEY_TYPE,KEY_POINTS,KEY_AGE,KEY_SEX}, KEY_ROWID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }


}
