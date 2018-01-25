package com.example.mc9509dw.finalapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class canvas_activity extends AppCompatActivity {


    private CanvasView customCanvas;

    public static final String MyPREFERENCES = "MyPrefs" ;
    private SharedPreferences sharedpreferences;
    private int fileName=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        customCanvas = (CanvasView) findViewById(R.id.canvas_view);

        //btn add with listener

    }

    public void clearCanvas(View v) {
        customCanvas.clearCanvas();
    }

    public void add(View v){
        Intent intent = new Intent(this, DeerSightings.class);
        startActivity(intent);

        //generate a filename
        fileName++;

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt("FILENAME",fileName);
        editor.apply();

        //does database stuff
        customCanvas.addCanvas(fileName);
    }

    @Override
    protected void onResume() {
        super.onResume();

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        fileName=sharedpreferences.getInt("FILENAME",-1);
    }
}
