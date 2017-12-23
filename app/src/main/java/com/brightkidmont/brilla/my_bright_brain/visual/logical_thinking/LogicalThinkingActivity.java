package com.brightkidmont.brilla.my_bright_brain.visual.logical_thinking;

import android.media.MediaPlayer;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.brightkidmont.brilla.R;
import com.brightkidmont.brilla.adapter.ViewPagerAdapter;

public class LogicalThinkingActivity extends AppCompatActivity {

    public static MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logical_thinking_layout);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        player = new MediaPlayer();
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);

        //for stopping all audios when changing tab
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                stopAudio();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    //setting up viewPager
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Sizes(), "1");
        adapter.addFrag(new TallShort(), "2");
        adapter.addFrag(new Numbers(), "3");
        adapter.addFrag(new FewMore(), "4");
        adapter.addFrag(new Opposites(), "5");
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
    public static void stopAudio(){
        if(player.isPlaying())
            player.stop();
        player.reset();
    }

}
