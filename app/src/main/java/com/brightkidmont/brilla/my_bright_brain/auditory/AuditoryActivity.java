package com.brightkidmont.brilla.my_bright_brain.auditory;

import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.brightkidmont.brilla.R;
import com.brightkidmont.brilla.adapter.ViewPagerAdapter;

public class AuditoryActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    public static MediaPlayer player;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auditory_layout);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        player = new MediaPlayer();
        tts = new TextToSpeech(this, this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);

        //tts on selecting a tab
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                stopAudio();
                switch (tab.getPosition()){
                    case 0:tts.speak("Musical Instruments", TextToSpeech.QUEUE_FLUSH, null);
                        break;
                    case 1:tts.speak("Vehicles", TextToSpeech.QUEUE_FLUSH, null);
                        break;
                    case 2:tts.speak("Animals", TextToSpeech.QUEUE_FLUSH, null);
                        break;
                    case 3:tts.speak("Birds", TextToSpeech.QUEUE_FLUSH, null);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    //setting up of viewPager
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new MusicalInstruments(), "1");
        adapter.addFrag(new Vehicles(), "2");
        adapter.addFrag(new Animals(), "3");
        adapter.addFrag(new Birds(), "4");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(player.isPlaying())
            player.stop();
        player.reset();
    }
    public static void stopAudio(){
        if(player.isPlaying())
            player.stop();
        player.reset();
    }

    @Override
    public void onInit(int status) {
    }
}
