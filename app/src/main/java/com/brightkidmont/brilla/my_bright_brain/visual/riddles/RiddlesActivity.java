package com.brightkidmont.brilla.my_bright_brain.visual.riddles;

import android.media.MediaPlayer;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.brightkidmont.brilla.R;
import com.brightkidmont.brilla.adapter.ViewPagerAdapter;

public class RiddlesActivity extends AppCompatActivity{

    public static TabLayout tabLayout;
    public static MediaPlayer player;
    public static int points = 0, submitCount = 0;
    public static RiddlesActivity riddlesActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.riddles_layout);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        riddlesActivity = this;
        submitCount=0;

        player = MediaPlayer.create(getBaseContext(), R.raw.applause);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);

        //skipping trophyTab before answering all the questions
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //stopAudio();
                if (tab.getText()=="T"){
                    if(submitCount!=5) {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                tabLayout.getTabAt(0).select();
                            }
                        }, 100);
                    } else {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Trophy.trophyDistribution();
                            }
                        }, 500);
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getText()=="T")
                    stopAudio();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    //settingUp viewPager
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Riddle1(), "1");
        adapter.addFrag(new Riddle2(), "2");
        adapter.addFrag(new Riddle3(), "3");
        adapter.addFrag(new Riddle4(), "4");
        adapter.addFrag(new Riddle5(), "5");
        adapter.addFrag(new Trophy(), "T");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(player.isPlaying())
            player.stop();
        player.reset();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopAudio();
    }

    public static void stopAudio(){
        if(player.isPlaying())
            player.stop();
        player.reset();
    }
}
