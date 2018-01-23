package com.example.mc9509dw.dicefinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class firstActivity extends AppCompatActivity {

    public ArrayList names= new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        final EditText player_name= (EditText) findViewById(R.id.input_names);
        final Button submit= (Button) findViewById(R.id.button2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                names.add(player_name.getText().toString());
            }
        });

        final Button startGame =  (Button) findViewById(R.id.btn_startGame);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // swtich to next activity here.
                //Toast.makeText(getApplicationContext(),"CLICK",Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(firstActivity.this, secondActivity.class);
                myIntent.putStringArrayListExtra("name_list", names);
                startActivity(myIntent);
            }
        });

        if(savedInstanceState != null){
            names=savedInstanceState.getStringArrayList("names");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("names",names);
    }
}
