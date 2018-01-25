package com.example.mc9509dw.finalapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DeerSightings extends AppCompatActivity {
    private static String TAG= "DeerApp";
    private DBAdapter db;
    long activeID; //used to keep track of whether an item has been selected and if so, its id in the DB

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deer_sightings);

        db = new DBAdapter(this);
        activeID = -1;
    }

    public void addDetails(){

        Log.d(TAG, "Adding to DB");

        EditText txt_num_deers = (EditText) findViewById(R.id.txt_num_deers);
        EditText txt_distance = (EditText) findViewById(R.id.txt_dis);
        EditText txt_time = (EditText) findViewById(R.id.txt_time);
        EditText txt_direction = (EditText) findViewById(R.id.txt_direction);
        EditText txt_type=(EditText) findViewById(R.id.txt_type);
        EditText txt_points=(EditText) findViewById(R.id.txt_points);
        EditText txt_age=(EditText) findViewById(R.id.txt_age);
        EditText txt_sex=(EditText)findViewById(R.id.txt_sex);

        String numDeers = txt_num_deers.getText().toString();
        String dis = txt_distance.getText().toString();
        String time= txt_time.getText().toString();
        String direction= txt_direction.getText().toString();
        String type=txt_type.getText().toString();
        String points=txt_points.getText().toString();
        String age=txt_age.getText().toString();
        String sex=txt_sex.getText().toString();

        //do some sanitizing and error checking before actually adding anything to a DB
        db.open();

        long id = db.insertDetails(numDeers,dis,time,direction,type,points,age,sex);
        db.close();

        //Toast.makeText(this, "No. Deers: " + numDeers + "Distance: " + dis + " Time: "+time+" Direction: "+ direction + "ID: " + id, Toast.LENGTH_SHORT).show();

        //reset the text boxes to take in the next values
        txt_num_deers.setText("");
        txt_distance.setText("");
        txt_time.setText("");
        txt_direction.setText("");
        txt_type.setText("");
        txt_points.setText("");
        txt_age.setText("");
        txt_sex.setText("");


        activeID = -1; //make sure that if the fields were pre-populated, that that entry is not
        //removed from the DB

    }


    public void addDetails(View v){
        addDetails();
    }

    public void gotomain(View v){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);

    }
    //test db

    private void Display(Cursor c) {
        Toast.makeText(this,
                "id: " + c.getString(0) + "\n" + "num deer " + c.getString(1) + "\n" + "distance  " + c.getString(2)+ "time :" + c.getString(3)+"\n points :" + c.getString(6)+"\n age: "+c.getString(7),
                Toast.LENGTH_SHORT).show();


    }

    public void showAll(View view){
        Log.d(TAG, "showAll");
        db.open();
        Cursor c = db.getAllDetails();
        if (c.moveToFirst())
        {
            do {
                Display(c);
            } while (c.moveToNext());
        }
        db.close();

    }
}
