package com.brightkidmont.brilla.my_bright_brain.visual.colors.white;

import android.media.MediaPlayer;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.brightkidmont.brilla.R;
import com.brightkidmont.brilla.adapter.ViewPagerAdapter;

import static com.brightkidmont.brilla.HomePageActivity.bgm6;
import static com.brightkidmont.brilla.HomePageActivity.bgm6PausePosition;

public class WhiteActivity extends AppCompatActivity {

    public static String whiteTab;
    private int i = 0, p = 0;
    private ImageView image;
    private Animation animation;
    private float width, x;
    private TextView tv1;
    public static MediaPlayer q, correct, wrong;
    public static int count ;
    public static String valid = "not";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.white_layout);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.fbutton_color_clouds));
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabTextColors(getResources().getColor(R.color.fbutton_color_asbestos), getResources().getColor(R.color.f_black));
        tabLayout.setupWithViewPager(viewPager);

        q = MediaPlayer.create(getBaseContext(), R.raw.white1);
        correct = MediaPlayer.create(getBaseContext(), R.raw.correct);
        wrong = MediaPlayer.create(getBaseContext(), R.raw.ur_wrong);
        q.start();

        image = (ImageView) findViewById(R.id.image);
        tv1 = (TextView) findViewById(R.id.tv1);
        ViewTreeObserver viewTreeObserver1 = tv1.getViewTreeObserver();
        viewTreeObserver1.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tv1.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                width  = tv1.getMeasuredWidth();
                animation = new TranslateAnimation(-width*6,-width*5,0,0);
                animation.setDuration(500);
                image.startAnimation(animation);
                animation.setFillAfter(true);
                x = -width*5;
            }
        });

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                whiteTab = tab.getText().toString();
                switch (whiteTab) {
                    case "1":
                        stopAllAudio();
                        q = MediaPlayer.create(getBaseContext(), R.raw.white1);
                        break;
                    case "2":
                        stopAllAudio();
                        q = MediaPlayer.create(getBaseContext(), R.raw.white2);
                        break;
                    case "3":
                        stopAllAudio();
                        q = MediaPlayer.create(getBaseContext(), R.raw.white3);
                        break;
                    case "4":
                        stopAllAudio();
                        q = MediaPlayer.create(getBaseContext(), R.raw.white4);
                        break;
                    case "5":
                        stopAllAudio();
                        q = MediaPlayer.create(getBaseContext(), R.raw.white5);
                        break;
                    case "6":
                        stopAllAudio();
                        q = MediaPlayer.create(getBaseContext(), R.raw.white6);
                        count=0;
                        valid="not";
                        break;
                }
                q.start();
                p = tab.getPosition()+1;
                if(i<p){
                    switch (p){
                        case 1:
                            animation = new TranslateAnimation(x,-width*5,0,0);
                            x = -width*5;
                            break;
                        case 2:
                            animation = new TranslateAnimation(x,-width*4,0,0);
                            x = -width*4;
                            break;
                        case 3:
                            animation = new TranslateAnimation(x,-width*3,0,0);
                            x = -width*3;
                            break;
                        case 4:
                            animation = new TranslateAnimation(x,-width*2,0,0);
                            x = -width*2;
                            break;
                        case 5:
                            animation = new TranslateAnimation(x,-width*1,0,0);
                            x = -width*1;
                            break;
                        case 6:
                            animation = new TranslateAnimation(x,-width*0,0,0);
                            x = -width*0;
                            break;
                    }
                } else {
                    switch (p){
                        case 1:
                            animation = new TranslateAnimation(x,-width*5,0,0);
                            x = -width*5;
                            break;
                        case 2:
                            animation = new TranslateAnimation(x,-width*4,0,0);
                            x = -width*4;
                            break;
                        case 3:
                            animation = new TranslateAnimation(x,-width*3,0,0);
                            x = -width*3;
                            break;
                        case 4:
                            animation = new TranslateAnimation(x,-width*2,0,0);
                            x = -width*2;
                            break;
                        case 5:
                            animation = new TranslateAnimation(x,-width*1,0,0);
                            x = -width*1;
                            break;
                    }
                }
                animation.setDuration(1000);
                image.startAnimation(animation);
                animation.setFillAfter(true);
                i=p;
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new White1(), "1");
        adapter.addFrag(new White2(), "2");
        adapter.addFrag(new White3(), "3");
        adapter.addFrag(new White4(), "4");
        adapter.addFrag(new White5(), "5");
        adapter.addFrag(new White6(), "6");
        viewPager.setAdapter(adapter);
    }

    public void stopAllAudio(){
        if(q.isPlaying())
            q.stop();
        q.reset();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopAllAudio();
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
        stopAllAudio();
        bgm6PausePosition = bgm6.getCurrentPosition();
        finish();
    }

}
