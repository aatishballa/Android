package com.example.mc9509dw.finalapp;


import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import android.graphics.Region;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class review_activity extends AppCompatActivity {

    private DBAdapter db;
    private Cursor c;
    private MyPaths cur_path= new MyPaths();
    private int filename=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_review);

        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            db= new DBAdapter(this);
            db.open();
            c = db.getAllDetails();
            c.moveToFirst();
            displayDetails();
            db.close();

            displayCanvas();

            //for next button

            Button next= (Button) findViewById(R.id.btn_next);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // if(c.isAfterLast()==false){
                        //Toast.makeText(getApplicationContext(), "No next items!", Toast.LENGTH_LONG).show();
                   // }else{
                        c.moveToNext();
                        displayDetails();

                        //increment filename
                        filename++;
                        displayCanvas();

                    //}

                }
            });

            //for pre button
            Button pre= (Button)findViewById(R.id.btn_pre);
            pre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // if(c.isBeforeFirst()==false){
                     //   Toast.makeText(getApplicationContext(), "No previous items!", Toast.LENGTH_LONG).show();
                 //   }else{
                        c.moveToPrevious();
                        displayDetails();

                        filename--;
                        displayCanvas();
                  //  }
                }
            });

        }



    }

    public void displayCanvas(){

        // read the file first
        //deserialize
        try {
            FileInputStream fo;
            fo= openFileInput(Integer.toString(filename)+".txt");
            ObjectInputStream oi = new ObjectInputStream(fo);
            cur_path=(MyPaths) oi.readObject();
            oi.close();
            fo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //image view canvas
        ImageViewCanvas v = new ImageViewCanvas(getApplicationContext());
        v.setPathOb(cur_path);
        Bitmap bitmap = Bitmap.createBitmap(500/*width*/, 500/*height*/, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);

        //Toast.makeText(getApplicationContext(), cur_path.getxPath().get(0).toString(), Toast.LENGTH_LONG).show();
        ImageView iv = (ImageView) findViewById(R.id.imgView_path);
        iv.setImageBitmap(bitmap);


    }

    public void displayDetails(){
        //setting textviews
        TextView lbl_num=(TextView) findViewById(R.id.txt_num);
        TextView lbl_distance=(TextView) findViewById(R.id.txt_distance);
        TextView lbl_time= (TextView) findViewById(R.id.txt_time);
        TextView lbl_direction=(TextView) findViewById(R.id.txt_direction);
        TextView lbl_type=(TextView) findViewById(R.id.txt_type);
        TextView lbl_points=(TextView)findViewById(R.id.txt_points);
        TextView lbl_age= (TextView) findViewById(R.id.txt_age);
        TextView lbl_sex=(TextView)findViewById(R.id.txt_sex);

        //needs work
        lbl_num.setText("No. of Deers: " + c.getString(1));
        lbl_distance.setText("Distance: "+ c.getString(2));
        lbl_time.setText("Time: " + c.getString(3));
        lbl_direction.setText("Direction: " + c.getString(4));
        lbl_type.setText("Type: " + c.getString(5));
        lbl_points.setText("Points: " + c.getString(6));
        lbl_age.setText("Age: "+ c.getString(7));
        lbl_sex.setText("Sex: "+ c.getString(8));
    }


}
