package com.example.mc9509dw.dicefinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class gamePlayActivity extends AppCompatActivity {

    protected String curPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        curPlayer=getIntent().getStringExtra("current_player");

    }
}
