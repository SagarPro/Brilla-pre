package com.brightkidmont.brilla.interactions;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.brightkidmont.brilla.R;
import com.brightkidmont.brilla.my_bright_brain.visual.colors.black.BlackActivity;
import com.brightkidmont.brilla.my_bright_brain.visual.colors.blue.BlueActivity;
import com.brightkidmont.brilla.my_bright_brain.visual.colors.brown.BrownActivity;
import com.brightkidmont.brilla.my_bright_brain.visual.colors.green.GreenActivity;
import com.brightkidmont.brilla.my_bright_brain.visual.colors.grey.GreyActivity;
import com.brightkidmont.brilla.my_bright_brain.visual.colors.orange.OrangeActivity;
import com.brightkidmont.brilla.my_bright_brain.visual.colors.pink.PinkActivity;
import com.brightkidmont.brilla.my_bright_brain.visual.colors.purple.PurpleActivity;
import com.brightkidmont.brilla.my_bright_brain.visual.colors.red.RedActivity;
import com.brightkidmont.brilla.my_bright_brain.visual.colors.white.WhiteActivity;
import com.brightkidmont.brilla.my_bright_brain.visual.colors.yellow.YellowActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.brightkidmont.brilla.HomePageActivity.bgm6;
import static com.brightkidmont.brilla.HomePageActivity.bgm6PausePosition;

public class ColorsActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener{

    private ImageView ivBlack, ivBlue, ivBrown, ivGreen, ivGrey, ivOrange, ivPink, ivPurple, ivRed, ivWhite, ivYellow;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colors_layout);

        tts = new TextToSpeech(this, this);

        bgm6.seekTo(bgm6PausePosition);
        bgm6.start();

        ivBlack = (ImageView) findViewById(R.id.ivBlack);
        ivBlue = (ImageView) findViewById(R.id.ivBlue);
        ivBrown = (ImageView) findViewById(R.id.ivBrown);
        ivGreen = (ImageView) findViewById(R.id.ivGreen);
        ivGrey = (ImageView) findViewById(R.id.ivGrey);
        ivOrange = (ImageView) findViewById(R.id.ivOrange);
        ivPink = (ImageView) findViewById(R.id.ivPink);
        ivPurple = (ImageView) findViewById(R.id.ivPurple);
        ivRed = (ImageView) findViewById(R.id.ivRed);
        ivWhite = (ImageView) findViewById(R.id.ivWhite);
        ivYellow = (ImageView) findViewById(R.id.ivYellow);

        ivBlack.setOnClickListener(this);
        ivBlue.setOnClickListener(this);
        ivBrown.setOnClickListener(this);
        ivGreen.setOnClickListener(this);
        ivGrey.setOnClickListener(this);
        ivOrange.setOnClickListener(this);
        ivPink.setOnClickListener(this);
        ivPurple.setOnClickListener(this);
        ivRed.setOnClickListener(this);
        ivWhite.setOnClickListener(this);
        ivYellow.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Handler handler = new Handler();
        switch (id){
            case R.id.ivBlack:
                tts.speak("Black", TextToSpeech.QUEUE_FLUSH, null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(ColorsActivity.this, BlackActivity.class));
                    }
                },1000);
                break;
            case R.id.ivBlue:
                tts.speak("Blue", TextToSpeech.QUEUE_FLUSH, null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(ColorsActivity.this, BlueActivity.class));
                    }
                },1000);
                break;
            case R.id.ivBrown:
                tts.speak("Brown", TextToSpeech.QUEUE_FLUSH, null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(ColorsActivity.this, BrownActivity.class));
                    }
                },1000);
                break;
            case R.id.ivGreen:
                tts.speak("Green", TextToSpeech.QUEUE_FLUSH, null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(ColorsActivity.this, GreenActivity.class));
                    }
                },1000);
                break;
            case R.id.ivGrey:
                tts.speak("Grey", TextToSpeech.QUEUE_FLUSH, null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(ColorsActivity.this, GreyActivity.class));
                    }
                },1000);
                break;
            case R.id.ivOrange:
                tts.speak("Orange", TextToSpeech.QUEUE_FLUSH, null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(ColorsActivity.this, OrangeActivity.class));
                    }
                },1000);
                break;
            case R.id.ivPink:
                tts.speak("Pink", TextToSpeech.QUEUE_FLUSH, null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(ColorsActivity.this, PinkActivity.class));
                    }
                },1000);
                break;
            case R.id.ivPurple:
                tts.speak("Purple", TextToSpeech.QUEUE_FLUSH, null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(ColorsActivity.this, PurpleActivity.class));
                    }
                },1000);
                break;
            case R.id.ivRed:
                tts.speak("Red", TextToSpeech.QUEUE_FLUSH, null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(ColorsActivity.this, RedActivity.class));
                    }
                },1000);
                break;
            case R.id.ivWhite:
                tts.speak("White", TextToSpeech.QUEUE_FLUSH, null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(ColorsActivity.this, WhiteActivity.class));
                    }
                },1000);
                break;
            case R.id.ivYellow:
                tts.speak("Yellow", TextToSpeech.QUEUE_FLUSH, null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(ColorsActivity.this, YellowActivity.class));
                    }
                },1000);
                break;
        }
    }

    @Override
    public void onInit(int status) {
    }

    @Override
    protected void onPause() {
        super.onPause();
        bgm6.pause();
        bgm6PausePosition = bgm6.getCurrentPosition();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bgm6.seekTo(bgm6PausePosition);
        bgm6.start();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        bgm6.pause();
        bgm6PausePosition = bgm6.getCurrentPosition();
        finish();
    }

}
