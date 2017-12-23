package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.arrow;

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

import static com.brightkidmont.brilla.HomePageActivity.bgm5;
import static com.brightkidmont.brilla.HomePageActivity.bgm5PausePosition;

public class ArrowActivity extends AppCompatActivity {

    public static String arrowTab;
    private int i = 0, p = 0;
    private ImageView image;
    private Animation animation;
    private float width, x;
    private TextView tv1;
    public static MediaPlayer q, correct, wrong;
    public static int count = 0;
    public static String valid = "not";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arrow_layout);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);

        //audio for question
        q = MediaPlayer.create(getBaseContext(), R.raw.circle6);
        correct = MediaPlayer.create(getBaseContext(), R.raw.correct);
        wrong = MediaPlayer.create(getBaseContext(), R.raw.ur_wrong);
        q.start();

        //Setting position for imageView in bottom
        image = (ImageView) findViewById(R.id.image);
        tv1 = (TextView) findViewById(R.id.tv1);
        ViewTreeObserver viewTreeObserver1 = tv1.getViewTreeObserver();
        viewTreeObserver1.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tv1.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                width  = tv1.getMeasuredWidth();
                animation = new TranslateAnimation(-width*3,-width*2,0,0);
                animation.setDuration(500);
                image.startAnimation(animation);
                animation.setFillAfter(true);
                x = -width*2;
            }
        });

        //onSelecting different tab, play respective audio file & move bottom image to respective position
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                arrowTab = tab.getText().toString();
                switch (arrowTab){
                    case "1":
                        stopAllAudio();
                        q = MediaPlayer.create(getBaseContext(), R.raw.circle6);
                        break;
                    case "2":
                        stopAllAudio();
                        q = MediaPlayer.create(getBaseContext(), R.raw.star5);
                        break;
                    case "3":
                        stopAllAudio();
                        q = MediaPlayer.create(getBaseContext(), R.raw.circle8);
                        count=0;
                        valid="not";
                        break;
                }
                q.start();
                p = tab.getPosition()+1;
                if(i<p){
                    switch (p){
                        case 1:
                            animation = new TranslateAnimation(x,-width*2,0,0);
                            x = -width*2;
                            break;
                        case 2:
                            animation = new TranslateAnimation(x,-width*1,0,0);
                            x = -width*1;
                            break;
                        case 3:
                            animation = new TranslateAnimation(x,-width*0,0,0);
                            x = -width*0;
                            break;
                    }
                } else {
                    switch (p){
                        case 1:
                            animation = new TranslateAnimation(x,-width*2,0,0);
                            x = -width*2;
                            break;
                        case 2:
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

    //Setting up viewPager
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Arrow1(), "1");
        adapter.addFrag(new Arrow2(), "2");
        adapter.addFrag(new Arrow3(), "3");
        viewPager.setAdapter(adapter);
    }

    //Stop all audios
    public void stopAllAudio(){
        if(q.isPlaying())
            q.stop();
        q.reset();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopAllAudio();
        bgm5.pause();
        bgm5PausePosition = bgm5.getCurrentPosition();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bgm5.seekTo(bgm5PausePosition);
        bgm5.start();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        stopAllAudio();
        bgm5.pause();
        bgm5PausePosition = bgm5.getCurrentPosition();
        finish();
    }
}
