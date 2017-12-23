package com.brightkidmont.brilla.my_bright_brain.visual.jigsaw_puzzles;

import android.media.MediaPlayer;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.brightkidmont.brilla.R;
import com.brightkidmont.brilla.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JigsawPuzzlesActivity extends AppCompatActivity {

    public static TabLayout tabLayout;
    public static MediaPlayer player;
    public static ArrayList<String> jpImages, jpBG, jpFull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jigsaw_puzzles_layout);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        player = new MediaPlayer();
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);

        jpImages = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.jigsaw_puzzles)));
        jpBG = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.jigsaw_puzzle_bg)));
        jpFull = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.jigsaw_puzzle_full)));

        //stopping audio on changing tabs
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
        adapter.addFrag(new Animals(), "1");
        adapter.addFrag(new Birds(), "2");
        adapter.addFrag(new Flowers(), "3");
        adapter.addFrag(new Fruits(), "4");
        adapter.addFrag(new SeaAnimals(), "5");
        adapter.addFrag(new Vegetables(), "6");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
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
