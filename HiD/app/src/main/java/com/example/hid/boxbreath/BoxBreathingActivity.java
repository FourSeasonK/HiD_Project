package com.example.hid.boxbreath;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hid.R;
import com.example.hid.activities.NavigationActivity;
import com.example.hid.databinding.ActivityBoxBreathingBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoxBreathingActivity extends NavigationActivity {

    private static final String TAG = BoxBreathingActivity.class.getSimpleName();
    ActivityBoxBreathingBinding activityBoxBreathingBinding;

    ImageView firstStart, secondStart, thirdStart, fourthStart, arrowRight,
                arrowDown, arrowLeft, arrowUp, imgPlay, imgReset;
    TextView txtProcess, breathCount;
    EditText countUserNum;
    int shortAnimationDuration;
    MediaPlayer mediaPlayer;
    boolean countCheck;
    boolean playcheck, killRunable;
    int index, listIndex, txtTime;
    int round = 1;
    Thread thread;
    int totalCount;
    int totalCountSound, totalCountText;
    List<String> processTxts = Arrays.asList("Inhale", "Hold", "Exhale", "Hold", "Inhale", "hold", "Exhale", "hold");
    Handler handlerSound, handlerText;
    Runnable runnableSound, runnableText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_box_breathing);

        activityBoxBreathingBinding = ActivityBoxBreathingBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_box_breathing, frameLayout);

        txtProcess = rootView.findViewById(R.id.txtProcess);
        breathCount = rootView.findViewById(R.id.txtCount);
        countUserNum = rootView.findViewById(R.id.edittxtUserNumInput);
        firstStart = rootView.findViewById(R.id.imgBoxBFirstStep);
        secondStart = rootView.findViewById(R.id.imgBoxBSecondStep);
        thirdStart = rootView.findViewById(R.id.imgBoxBThirdStep);
        fourthStart = rootView.findViewById(R.id.imgBoxBFourthStep);
        arrowRight = rootView.findViewById(R.id.imgArrowRight);
        arrowDown = rootView.findViewById(R.id.imgArrowDown);
        arrowLeft = rootView.findViewById(R.id.imgArrowLeft);
        arrowUp = rootView.findViewById(R.id.imgArrowUp);
        imgPlay = rootView.findViewById(R.id.imgbtnBoxBPlay);
        imgReset = rootView.findViewById(R.id.imgbtnBoxBRest);

        firstStart.setVisibility(View.GONE);
        arrowRight.setVisibility(View.GONE);
        secondStart.setVisibility(View.GONE);
        arrowDown.setVisibility(View.GONE);
        thirdStart.setVisibility(View.GONE);
        arrowLeft.setVisibility(View.GONE);
        fourthStart.setVisibility(View.GONE);
        arrowUp.setVisibility(View.GONE);

//        shortAnimationDuration = getResources()
//                .getInteger(android.R.integer.config_longAnimTime);

        playcheck = true;

        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String countStr = countUserNum.getText().toString();

                if(TextUtils.isEmpty(countStr) || countStr.equals("")){
                    Toast.makeText(BoxBreathingActivity.this, "Please enter the rounds", Toast.LENGTH_SHORT).show();

                } else {
                    int count = Integer.parseInt(countStr);
                    Log.d(TAG, "UerInputStr" + countStr);
                    Log.d(TAG, "UerInputInt" + count);

                    if(playcheck == true) {
                        imgPlay.setImageResource(R.drawable.btnstopcircle);
                        txtProcess.setVisibility(View.VISIBLE);

                        for(int i = 0; i < count; i++){

                            controlImgView();
                        }
                        playSoundEffect(count);
                        displayTextViews(count);
//                    controlImgView();
//                    playSoundEffect();
                        //displayTextViews();

                        playcheck = false;

                    } else if(playcheck == false){
                        imgPlay.setImageResource(R.drawable.btnplaycircle);

                        txtProcess.setVisibility(View.INVISIBLE);

                        firstStart.setVisibility(View.GONE);
                        arrowRight.setVisibility(View.GONE);
                        secondStart.setVisibility(View.GONE);
                        arrowDown.setVisibility(View.GONE);
                        thirdStart.setVisibility(View.GONE);
                        arrowLeft.setVisibility(View.GONE);
                        fourthStart.setVisibility(View.GONE);
                        arrowUp.setVisibility(View.GONE);

                        mediaPlayer.stop();
                        handlerSound = new Handler();
                        handlerText = new Handler();

                        playcheck = true;
                    }
                }

            }
        });

        imgReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                txtProcess.setText("");
                txtProcess.setVisibility(View.INVISIBLE);
                breathCount.setText("");
                countUserNum.setText("");
                imgPlay.setImageResource(R.drawable.btnplaycircle);

                firstStart.setVisibility(View.GONE);
                arrowRight.setVisibility(View.GONE);
                secondStart.setVisibility(View.GONE);
                arrowDown.setVisibility(View.GONE);
                thirdStart.setVisibility(View.GONE);
                arrowLeft.setVisibility(View.GONE);
                fourthStart.setVisibility(View.GONE);
                arrowUp.setVisibility(View.GONE);

                mediaPlayer.stop();
                handlerSound.removeCallbacks(runnableSound );
                handlerText = new Handler();
            }
        });
    }

    public  void playSoundEffect(int count){
//    public  void playSoundEffect(){
        mediaPlayer = MediaPlayer.create(this, R.raw.successsound);
        mediaPlayer.setLooping(false);
        mediaPlayer.start();

        int soundTime = 4000;
        totalCountSound = (count * 7);
        Log.d(TAG, "Total count sound: " + totalCountSound);

        for(int i = 0; i < totalCountSound; i ++){

            handlerSound = new Handler();
            runnableSound = new Runnable() {
                @Override
                public void run() {
                    mediaPlayer.start();
                }
            };
            handlerSound.postDelayed(runnableSound,soundTime);
//            handlerSound.postDelayed(new Runnable() {
////             new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    mediaPlayer.start();
//                }
//            }, soundTime);

            soundTime += 4000;
        }
    }
    public void displayTextViews(int count){

        listIndex = 0;
        txtTime = 0;
        totalCountText = (count * 7);

//        while (listIndex < processTxts.size()) {
        for(int i = 0; i < totalCountText; i ++){

           handlerText = new Handler();
           runnableText = new Runnable() {
               @Override
               public void run() {
                   txtProcess.setText(processTxts.get(listIndex));

                    if (listIndex == 7){
                        listIndex = 0;
                    } else {
                        listIndex++;
                    }
               }
           };
           handlerText.postDelayed(runnableText, txtTime);
//            handlerText.postDelayed(new Runnable() {
////             new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    txtProcess.setText(processTxts.get(listIndex));
//
//                    if (listIndex == 7){
//                        listIndex = 0;
//                    } else {
//                        listIndex++;
//                    }
//                }
//            }, txtTime);



            txtTime += 4000;
        }
    }

    public void controlImgView(){
//    public void controlImgView(){

        int i = 0;
//        round = 1;
        while (i < 8){
//        while (countCheck){

                if(i == 0){
                    firstStart.setAlpha(1f);
                    firstStart.setVisibility(View.VISIBLE);
//                    firstStart.setAlpha(0f);
//                    firstStart.setVisibility(View.VISIBLE);

                    i++;
                }
                if(i == 1){
                    arrowRight.setAlpha(0f);
                    arrowRight.setVisibility(View.VISIBLE);

                    arrowRight.animate()
                            .alpha(1f)
                            .setDuration(4000)
                            .setListener(null);

                    i++;
                }

                if(i == 2){
                    secondStart.setAlpha(0f);
                    secondStart.setVisibility(View.VISIBLE);

                    secondStart.animate()
                            .alpha(1f)
                            .setDuration(8000)
                            .setListener(null);
                    i++;
                }

                if(i == 3){
                    arrowDown.setAlpha(0f);
                    arrowDown.setVisibility(View.VISIBLE);

                    arrowDown.animate()
                            .alpha(1f)
                            .setDuration(12000)
                            .setListener(null);
                    i++;
                }

                if(i == 4){
                    thirdStart.setAlpha(0f);
                    thirdStart.setVisibility(View.VISIBLE);

                    thirdStart.animate()
                            .alpha(1f)
                            .setDuration(16000)
                            .setListener(null);
                    i++;
                }

                if(i == 5){
                    arrowLeft.setAlpha(0f);
                    arrowLeft.setVisibility(View.VISIBLE);

                    arrowLeft.animate()
                            .alpha(1f)
                            .setDuration(20000)
                            .setListener(null);
                    i++;
                }

                if(i == 6){
                    fourthStart.setAlpha(0f);
                    fourthStart.setVisibility(View.VISIBLE);

                    fourthStart.animate()
                            .alpha(1f)
                            .setDuration(24000)
                            .setListener(null);
                    i++;
                }

                if(i == 7){
                    arrowUp.setAlpha(0f);
                    arrowUp.setVisibility(View.VISIBLE);

                    arrowUp.animate()
                            .alpha(1f)
                            .setDuration(28000)
                            .setListener(null);
                    i++;
                }

            }
    }

    public void timeDelayVisibleView(float time, final ImageView view){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                firstStart.setVisibility(View.VISIBLE);
            }
        }, (long) time);
    }
}