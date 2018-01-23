package com.example.mc9509dw.dicefinal;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;


public class gamePlay_fragment extends Fragment {

    private static final Random oneDie = new Random();
    protected int rollVal;
    protected int rollVals[];
    protected int diceHoldCounter;
    protected int rollsRemain;
    protected int rollsCount=0;

    protected gamePlayActivity ob;
    protected String currentPlayer;
    protected ImageButton [] btnArr= new ImageButton[5];
    protected int score=0;

    protected String screen_orientation;



    public gamePlay_fragment() {
        oneDie.setSeed(908374);
        diceHoldCounter=0;
        rollsRemain=3;
    }

    public int getRollVal(){
        rollVal = oneDie.nextInt(6) + 1;
        return rollVal;
    }

    public int [] rollAllDice(){

        int val1= getRollVal();
        int val2= getRollVal();
        int val3= getRollVal();
        int val4= getRollVal();
        int val5= getRollVal();

        int valArr []= new int[]{val1,val2,val3,val4,val5};
        rollsRemain--;
        return valArr;
    }

    public int select_dice_img(int rval){
        int img_src = 0;
        switch (rval){
            case 1:
                img_src= R.drawable.die1;
                break;
            case 2:
                img_src= R.drawable.die2;
                break;
            case 3:
                img_src= R.drawable.die3;
                break;
            case 4:
                img_src= R.drawable.die4;
                break;
            case 5:
                img_src= R.drawable.die5;
                break;
            case 6:
                img_src= R.drawable.die6;
                break;
        }
        return img_src;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //for dice_ hold labels of TextView
        final ImageButton btn1= (ImageButton)getView().findViewById(R.id.btn_1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sending pos of button to perform_dice_hold_check
                int temp = diceHoldCounter;
                perform_dice_hold_check(0);
                if (diceHoldCounter>temp){
                    btn1.setVisibility(View.GONE);
                }
            }
        });
        final ImageButton btn2= (ImageButton)getView().findViewById(R.id.btn_2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = diceHoldCounter;
                perform_dice_hold_check(1);
                if (diceHoldCounter>temp){
                    btn2.setVisibility(View.GONE);
                }
            }
        });
        final ImageButton btn3= (ImageButton)getView().findViewById(R.id.btn_3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = diceHoldCounter;
                perform_dice_hold_check(2);
                if (diceHoldCounter>temp){
                    btn3.setVisibility(View.GONE);
                }
            }
        });
        final ImageButton btn4= (ImageButton)getView().findViewById(R.id.btn_4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = diceHoldCounter;
                perform_dice_hold_check(3);
                if (diceHoldCounter>temp){
                    btn4.setVisibility(View.GONE);
                }
            }
        });
        final ImageButton btn5= (ImageButton)getView().findViewById(R.id.btn_5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = diceHoldCounter;
                perform_dice_hold_check(4);
                if (diceHoldCounter>temp){
                    btn5.setVisibility(View.GONE);
                }
            }
        });

        ImageButton newButtonArr []= new ImageButton[]{btn1,btn2,btn3,btn4,btn5};
        btnArr = newButtonArr;

        //lbl_roll_left
        final TextView roll_left= (TextView)getView().findViewById(R.id.lbl_rollsLeft);
        roll_left.setText("Rolls left: 3");

        // for btn_roll event
        final Button test= (Button)getView().findViewById(R.id.btn_roll);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // rolling the dice
                if (rollsRemain>0){
                    rollVals = rollAllDice();
                    roll_left.setText("Rolls left: " + rollsRemain);
                    //save img resource values
                    //setting image on buttons
                    btn1.setImageResource(select_dice_img(rollVals[0]));
                    btn2.setImageResource(select_dice_img(rollVals[1]));
                    btn3.setImageResource(select_dice_img(rollVals[2]));
                    btn4.setImageResource(select_dice_img(rollVals[3]));
                    btn5.setImageResource(select_dice_img(rollVals[4]));

                    // reset the counter variables and make visible all the buttons
                }else{
                    Toast.makeText(getActivity(),"No more rolls left." , Toast.LENGTH_SHORT).show();
                }

            }
        });

        // for get_score button
        final TextView lbl_score= (TextView)getView().findViewById(R.id.textView2);
        Button getScore = (Button)getView().findViewById(R.id.btn_getScore);
        getScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.setClickable(false);
                if(diceHoldCounter==3){
                    score = calc_score();
                    lbl_score.setText("Your turn is over. \nScore: " + score);
                }else{
                    lbl_score.setText("Your turn is over. \nScore: 0");
                }

            }
        });


        if (screen_orientation.equals("Potrait")){
            // for label Player name
            ob=(gamePlayActivity) getActivity();
            currentPlayer=ob.curPlayer;
            TextView lbl_curplayer= (TextView)getView().findViewById(R.id.textView);
            lbl_curplayer.setText("Player : " + currentPlayer);
        }

        if (screen_orientation.equals("Landscape")){
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                currentPlayer = bundle.getString("current_player");
            }
            TextView lbl_curplayer= (TextView)getView().findViewById(R.id.textView);
            lbl_curplayer.setText("Player : " + currentPlayer);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_play, container, false);
    }

    // when dice click for hold
    public void perform_dice_hold_check(int pos) {
        if(diceHoldCounter<3) {
            switch (diceHoldCounter) {
                // first dice hold shouild be six
                case 0:
                    if (rollVals[pos] == 6) {
                        ImageView lbl_dice_6 = (ImageView) getActivity().findViewById(R.id.lbl_img_6);
                        lbl_dice_6.setVisibility(View.VISIBLE);
                        diceHoldCounter++;
                    } else {
                        Toast.makeText(getActivity(), "Unable to hold this dice. Try another one.", Toast.LENGTH_LONG).show();
                    }
                    break;
                // second dice hold should be five
                case 1:
                    if (rollVals[pos] == 5) {
                        ImageView lbl_dice_5 = (ImageView) getActivity().findViewById(R.id.lbl_img_5);
                        lbl_dice_5.setVisibility(View.VISIBLE);
                        diceHoldCounter++;
                    } else {
                        Toast.makeText(getActivity(), "Unable to hold this dice. Try another one.", Toast.LENGTH_LONG).show();
                    }
                    break;
                //third dice hold
                case 2:
                    if (rollVals[pos] == 4) {
                        ImageView lbl_dice_4 = (ImageView) getActivity().findViewById(R.id.lbl_img_4);
                        lbl_dice_4.setVisibility(View.VISIBLE);
                        diceHoldCounter++;
                    } else {
                        Toast.makeText(getActivity(), "Unable to hold this dice. Try another one.", Toast.LENGTH_LONG).show();
                    }
                    break;
            }

        }else{
            Toast.makeText(getActivity(),"Max dice hold reached.",Toast.LENGTH_LONG).show();
        }
    }

    public int calc_score(){
        int score=0;

        for(int i=0;i < btnArr.length;i++){
            if(btnArr[i].getVisibility()==View.VISIBLE){
                score=score + rollVals[i];
            }
        }
    return score;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            screen_orientation="Landscape";
        }
        else {
            screen_orientation="Potrait";
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        /*
        *
        *
    private static final Random oneDie = new Random();
    protected int rollVal;
    protected int rollVals[];
    protected int diceHoldCounter;
    protected int rollsRemain;
    protected int rollsCount=0;

    protected gamePlayActivity ob;
    protected String currentPlayer;
    protected ImageButton [] btnArr= new ImageButton[5];
    protected int score=0;

    protected String screen_orientation;
        *
        * */
        outState.putInt("rollVal",rollVal);
        outState.putIntArray("rollVals",rollVals);
        outState.putInt("diceHoldCounter",diceHoldCounter);
        outState.putInt("rollsRemain",rollsRemain);
        outState.putInt("rollsCount",rollsCount);
    }
}
