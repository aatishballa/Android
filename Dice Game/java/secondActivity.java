package com.example.mc9509dw.dicefinal;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class secondActivity extends FragmentActivity {

    protected ArrayList namelist=new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        namelist = getIntent().getStringArrayListExtra("name_list");


        if(savedInstanceState != null){
            namelist=savedInstanceState.getStringArrayList("names");
        }

        // testing if we get values from forst activity
        //String test =namelist.get(2).toString();
       // Toast.makeText(getApplicationContext(),test,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("names",namelist);
    }
}
