package com.brightkidmont.brilla.interactions;

import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.brightkidmont.brilla.R;
import com.brightkidmont.brilla.adapter.DressAdapter;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

import static com.brightkidmont.brilla.HomePageActivity.bgm2;
import static com.brightkidmont.brilla.HomePageActivity.bgm2PausePosition;
import static com.brightkidmont.brilla.HomePageActivity.genderDress;

public class DressActivity extends AppCompatActivity{

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    ArrayList<String> alName;
    ArrayList<String> alImage1;
    public static ImageView image;
    public static DressActivity activity;
    private KonfettiView viewKonfetti;
    private MediaPlayer player;
    public static MediaPlayer[] mpGirls = new MediaPlayer[10];
    public static MediaPlayer[] mpBoys = new MediaPlayer[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dress_layout);

        Typeface font = Typeface.createFromAsset(this.getAssets(), "adikku_panadai_menari.ttf");
        Toolbar toolbar = (Toolbar) findViewById(R.id.tbCustom);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView tvCustom = (TextView) findViewById(R.id.tvCustom);
        tvCustom.setText("Brilla Pre");
        tvCustom.setTypeface(font);

        boysAudio();
        girlsAudio();

        activity = this;
        image = (ImageView) findViewById(R.id.image);

        if(genderDress.equals("BOY")){
            alName = new ArrayList<>(Arrays.asList("Jack", "Smith", "Jim", "Bruce", "Andy", "Max", "Peter", "Tom", "John"));
            alImage1 = new ArrayList<>();
            alImage1.add("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Boys%20Dress%2Fdress_b1.png?alt=media&token=b44b6f1d-417e-4b0f-857e-dd78896b1e34");
            alImage1.add("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Boys%20Dress%2Fdress_b2.png?alt=media&token=3fa998bd-1467-466b-a7d9-d87326482704");
            alImage1.add("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Boys%20Dress%2Fdress_b3.png?alt=media&token=9d4328c9-e13f-43c5-9cbd-2231ab5bbb89");
            alImage1.add("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Boys%20Dress%2Fdress_b4.png?alt=media&token=cf55e1ac-a9e6-4c3b-981c-5b14a32aba9d");
            alImage1.add("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Boys%20Dress%2Fdress_b5.png?alt=media&token=fbd66895-9122-4d3a-864e-f0761b043264");
            alImage1.add("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Boys%20Dress%2Fdress_b6.png?alt=media&token=a0c65dd2-a170-47b3-9588-bfdcd0519693");
            alImage1.add("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Boys%20Dress%2Fdress_b7.png?alt=media&token=a3cbad57-1a76-4110-b915-e9b287c0666b");
            alImage1.add("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Boys%20Dress%2Fdress_b8.png?alt=media&token=e9496a16-b413-4cd0-8c9d-e444e951e448");
            alImage1.add("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Boys%20Dress%2Fdress_b10.png?alt=media&token=88b154b1-2847-4231-bad4-77212794a44b");

            Picasso.with(this).load(alImage1.get(0)).placeholder(R.drawable.bright_kid_bg).error(R.drawable.bright_kid_bg).into(image);

        } else {
            alName = new ArrayList<>(Arrays.asList("Ria", "Tina", "Sophie", "Lisa", "Flora", "Rosy", "Anna", "Emily", "Sana", "Daisy"));
            alImage1 = new ArrayList<>();
            alImage1.add("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Girls%20Dress%2Fdress_g1.png?alt=media&token=620366a0-f7a7-4354-ab6d-580660245583");
            alImage1.add("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Girls%20Dress%2Fdress_g2.png?alt=media&token=5eb3467d-9b4c-4d5a-a8a2-50c88d848d59");
            alImage1.add("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Girls%20Dress%2Fdress_g3.png?alt=media&token=883b65d3-6491-4432-a7fa-d38f7ea4e34b");
            alImage1.add("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Girls%20Dress%2Fdress_g4.png?alt=media&token=346e2e99-d215-45d2-8d4e-a3e1b25c9717");
            alImage1.add("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Girls%20Dress%2Fdress_g5.png?alt=media&token=1eb1b03c-e7ef-46c9-b644-8104b9f56de8");
            alImage1.add("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Girls%20Dress%2Fdress_g6.png?alt=media&token=14c74a07-1991-412d-8399-f95a2e944efd");
            alImage1.add("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Girls%20Dress%2Fdress_g7.png?alt=media&token=d6932190-d2b3-4de4-ba07-3370eeeef416");
            alImage1.add("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Girls%20Dress%2Fdress_g8.png?alt=media&token=d5225b22-d837-4a95-8d61-230a3554dd04");
            alImage1.add("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Girls%20Dress%2Fdress_g9.png?alt=media&token=69566703-debf-4c47-97ab-1a8469b97a43");
            alImage1.add("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Girls%20Dress%2Fdress_g10.png?alt=media&token=5ecff640-09be-4955-af1c-982b49f5529a");

            Picasso.with(this).load(alImage1.get(0)).placeholder(R.drawable.bright_kid_bg).error(R.drawable.bright_kid_bg).into(image);

        }

        // Calling the RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // The number of Columns
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new DressAdapter(DressActivity.this, alName, alImage1);
        mRecyclerView.setAdapter(mAdapter);

        player = MediaPlayer.create(getBaseContext(), R.raw.applause);

        //applying splatters of color papers effect
        viewKonfetti = (KonfettiView) findViewById(R.id.viewKonfetti);
        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.start();
                mRecyclerView.setVisibility(View.GONE);
                viewKonfetti.build()
                        .addColors(Color.BLUE, Color.YELLOW, Color.RED)
                        .setDirection(0.0, 359.0)
                        .setSpeed(1f, 5f)
                        .setFadeOutEnabled(true)
                        .setTimeToLive(2000L)
                        .addShapes(Shape.RECT, Shape.CIRCLE)
                        .addSizes(new Size(12, 1.0f))
                        .setPosition(-50f, viewKonfetti.getWidth() + 50f, -50f, -50f)
                        .stream(300, 10000L);
            }
        });

    }

    //Preparing all audios for boys dress
    public void boysAudio(){
        List<String> boys;
        boys = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.boys_dress_audio)));
        for(int i=0; i<9; i++){
            mpBoys[i] = new MediaPlayer();
            mpBoys[i].setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mpBoys[i].setDataSource(boys.get(i));
                mpBoys[i].prepareAsync();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //Preparing all audios for girls dress
    public void girlsAudio(){
        List<String> girls;
        girls = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.girls_dress_audio)));
        for(int i=0; i<10; i++){
            mpGirls[i] = new MediaPlayer();
            mpGirls[i].setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mpGirls[i].setDataSource(girls.get(i));
                mpGirls[i].prepareAsync();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        bgm2.pause();
        bgm2PausePosition = bgm2.getCurrentPosition();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bgm2.seekTo(bgm2PausePosition);
        bgm2.start();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        bgm2.pause();
        bgm2PausePosition = bgm2.getCurrentPosition();
        finish();
    }

}
