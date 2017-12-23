package com.brightkidmont.brilla.my_bright_brain.visual.visual_discrimination;

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

public class VisualDiscriminationActivity extends AppCompatActivity {

    public static MediaPlayer player;
    public static List<String> imageList, resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visual_discrimination_layout);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        imageList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.visual_discrimination)));
        resultList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.visual_discrimination_result)));

        player = new MediaPlayer();
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);

        //stopping audio when a new tab is selected
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

    //setting up of viewPager
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new VDFragment1(), "1");
        adapter.addFrag(new VDFragment2(), "2");
        adapter.addFrag(new VDFragment3(), "3");
        adapter.addFrag(new VDFragment4(), "4");
        adapter.addFrag(new VDFragment5(), "5");
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
