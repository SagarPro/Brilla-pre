package com.brightkidmont.brilla.interactions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.brightkidmont.brilla.HomePageActivity;
import com.brightkidmont.brilla.R;
import com.brightkidmont.brilla.adapter.MyAdapter;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

import static com.brightkidmont.brilla.HomePageActivity.bgm3;
import static com.brightkidmont.brilla.HomePageActivity.bgm3PausePosition;

public class PickFoodActivity extends AppCompatActivity implements View.OnClickListener{

    Context context;
    public static MediaPlayer[] mpFood = new MediaPlayer[4];
    public static MediaPlayer[] mpBreakfast = new MediaPlayer[20];
    public static MediaPlayer[] mpLunch = new MediaPlayer[20];
    public static MediaPlayer[] mpSnacks = new MediaPlayer[11];
    public static MediaPlayer[] mpDinner = new MediaPlayer[18];
    private ProgressBar pbFood;
    SharedPreferences preferences;
    private static ViewPager mPager;
    private static int currentPage = 0;
    List<Bitmap> bitmapList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_food_layout);

        Typeface font = Typeface.createFromAsset(this.getAssets(), "adikku_panadai_menari.ttf");
        Toolbar toolbar = (Toolbar) findViewById(R.id.tbCustom);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView tvCustom = (TextView) findViewById(R.id.tvCustom);
        tvCustom.setText("Brilla Pre");
        tvCustom.setTypeface(font);

        init();
        context = this;
        pbFood = (ProgressBar) findViewById(R.id.pbFood);
        pbFood.setVisibility(View.VISIBLE);

        //Preparing all audios for food types
        foodAudio();
        breakfastAudio();
        lunchAudio();
        snacksAudio();
        dinnerAudio();

        bgm3.seekTo(bgm3PausePosition);
        bgm3.start();

        LinearLayout llBreakFast = (LinearLayout) findViewById(R.id.llBreakFast);
        LinearLayout llLunch = (LinearLayout) findViewById(R.id.llLunch);
        LinearLayout llSnacks = (LinearLayout) findViewById(R.id.llSnacks);
        LinearLayout llDinner = (LinearLayout) findViewById(R.id.llDinner);

        llBreakFast.setOnClickListener(this);
        llLunch.setOnClickListener(this);
        llSnacks.setOnClickListener(this);
        llDinner.setOnClickListener(this);

    }

    //view pager for food type
    private void init() {
        List<String> imagesList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.food_viewPager)));
        mPager = (ViewPager) findViewById(R.id.pager);

        for (int i=0;i<imagesList.size();i++) {
            try {
                URL myURL = new URL(imagesList.get(i));
                new loadImages().execute(myURL);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        setCircleIndicator();

    }

    //Stating pickingFood activity based on selected food type
    @Override
    public void onClick(View v) {

        int id = v.getId();

        preferences = getSharedPreferences("FOODTYPE", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Boolean playing = false;
        for(int i=0; i<4; i++) {
            if (mpFood[i].isPlaying()) {
                playing = true;
                break;
            }
        }

        if(playing){
            Toast.makeText(context, "Please wait a sec", Toast.LENGTH_SHORT).show();
        } else {

            switch (id) {

                case R.id.llBreakFast:
                    editor.putString("TYPE", "BreakFast");
                    editor.apply();
                    mpFood[0].start();
                    break;
                case R.id.llLunch:
                    editor.putString("TYPE", "Lunch");
                    editor.apply();
                    mpFood[1].start();
                    break;
                case R.id.llSnacks:
                    editor.putString("TYPE", "Snacks");
                    editor.apply();
                    mpFood[2].start();
                    break;
                case R.id.llDinner:
                    editor.putString("TYPE", "Dinner");
                    editor.apply();
                    mpFood[3].start();
                    break;

            }
            startActivity(new Intent(getBaseContext(), PickingFoodActivity.class));
        }

    }

    //preparing all audios for food type
    private void foodAudio(){
        List<String> food;
        food = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.food_audio)));
        for(int i=0; i<4; i++){
            mpFood[i] = new MediaPlayer();
            mpFood[i].setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mpFood[i].setDataSource(food.get(i));
                mpFood[i].prepareAsync();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mpFood[3].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                pbFood.setVisibility(View.GONE);
            }
        });
    }

    //preparing breakfast audio
    private void breakfastAudio(){
        List<String> breakfast;
        breakfast = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.breakfast_audio)));
        for(int i=0; i<20; i++){
            mpBreakfast[i] = new MediaPlayer();
            mpBreakfast[i].setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mpBreakfast[i].setDataSource(breakfast.get(i));
                mpBreakfast[i].prepareAsync();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //preparing audio file for lunch
    private void lunchAudio(){
        List<String> lunch;
        lunch = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.lunch_audio)));
        for(int i=0; i<20; i++){
            mpLunch[i] = new MediaPlayer();
            mpLunch[i].setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mpLunch[i].setDataSource(lunch.get(i));
                mpLunch[i].prepareAsync();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //preparing snacks audio
    private void snacksAudio(){
        List<String> snacks;
        snacks = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.snacks_audio)));
        for(int i=0; i<11; i++){
            mpSnacks[i] = new MediaPlayer();
            mpSnacks[i].setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mpSnacks[i].setDataSource(snacks.get(i));
                mpSnacks[i].prepareAsync();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //preparing dinner audio files
    private void dinnerAudio(){
        List<String> dinner;
        dinner = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.dinner_audio)));
        for(int i=0; i<18; i++){
            mpDinner[i] = new MediaPlayer();
            mpDinner[i].setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mpDinner[i].setDataSource(dinner.get(i));
                mpDinner[i].prepareAsync();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setCircleIndicator(){
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                int i = mPager.getCurrentItem();
                if (currentPage == bitmapList.size()) {
                    currentPage = 0;
                } else if(i != currentPage){
                    currentPage = i;
                } else {
                    currentPage =currentPage+1;
                }
                mPager.setCurrentItem(currentPage, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);
    }

    public class loadImages extends AsyncTask<URL, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(URL... params) {
            URL imageURL = params[0];
            Bitmap downloadedBitmap = null;
            try {
                InputStream inputStream = imageURL.openStream();
                downloadedBitmap = BitmapFactory.decodeStream(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
            bitmapList.add(downloadedBitmap);
            return downloadedBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            mPager.setAdapter(new MyAdapter(PickFoodActivity.this, bitmapList));
            //setCircleIndicator();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        bgm3.pause();
        bgm3PausePosition = bgm3.getCurrentPosition();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bgm3.seekTo(bgm3PausePosition);
        bgm3.start();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        bgm3.pause();
        bgm3PausePosition = bgm3.getCurrentPosition();
        finish();
    }

}
