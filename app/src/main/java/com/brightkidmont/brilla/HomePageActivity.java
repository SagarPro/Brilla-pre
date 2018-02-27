package com.brightkidmont.brilla;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.brightkidmont.brilla.adapter.MyAdapter;
import com.brightkidmont.brilla.interactions.DressActivity;
import com.brightkidmont.brilla.interactions.FlashListActivity;
import com.brightkidmont.brilla.interactions.PickFoodActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener{

    private SharedPreferences loginPreferences;
    private LinearLayout llHomePage;
    private ProgressBar pbHP;
    public static String genderDress = "gender";
    public static MediaPlayer bgm1, bgm2, bgm3, bgm4, bgm5, bgm6;
    public static int bgm1PausePosition, bgm2PausePosition, bgm3PausePosition, bgm4PausePosition, bgm5PausePosition, bgm6PausePosition;
    private static ViewPager mPager;
    private static int currentPage = 0;
    static List<Bitmap> bitmapList = new ArrayList<>();
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_layout);

        Typeface font = Typeface.createFromAsset(this.getAssets(), "adikku_panadai_menari.ttf");
        Toolbar toolbar = (Toolbar) findViewById(R.id.tbCustom);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView tvCustom = (TextView) findViewById(R.id.tvCustom);
        tvCustom.setText("Brilla Pre");
        tvCustom.setTypeface(font);

        context = this;

        llHomePage = (LinearLayout) findViewById(R.id.llHomePage);
        llHomePage.setVisibility(View.GONE);

        pbHP = (ProgressBar) findViewById(R.id.pbHP);
        pbHP.setVisibility(View.VISIBLE);

        bgm();

        loginPreferences = getSharedPreferences("LOGINTYPE", MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPreferences.edit();
        editor.putString("LOGIN", "login");
        editor.apply();

        init();

        ImageView ivDress = (ImageView) findViewById(R.id.ivDress);
        ImageView ivFood = (ImageView) findViewById(R.id.ivFood);
        ImageView ivZappar = (ImageView) findViewById(R.id.ivZappar);
        ImageView ivFlash = (ImageView) findViewById(R.id.ivFlash);

        ivDress.setOnClickListener(this);
        ivFood.setOnClickListener(this);
        ivZappar.setOnClickListener(this);
        ivFlash.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        loginPreferences = getSharedPreferences("LOGINTYPE", MODE_PRIVATE);
        if(loginPreferences.getString("GUESTLOGIN", "not").equals("guest")) {
            MenuItem item = menu.findItem(R.id.profile);
            item.setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.logout:
                loginPreferences = getSharedPreferences("LOGINTYPE", MODE_PRIVATE);
                SharedPreferences.Editor editor = loginPreferences.edit();
                editor.putString("LOGIN", "logout");
                editor.apply();
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                finish();
                break;
            case R.id.profile:
                showUserDetails();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Display user details in dialog
    public void showUserDetails(){

        loginPreferences = getSharedPreferences("LOGINTYPE", MODE_PRIVATE);
        String userEmail = loginPreferences.getString("USEREMAIL", null);

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.ps_user_details_dialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        final ProgressBar pbPSUserDetails = (ProgressBar) dialog.findViewById(R.id.pbPSUserDetails);
        pbPSUserDetails.setVisibility(View.VISIBLE);

        final TextView udChildName = (TextView) dialog.findViewById(R.id.udChildName);
        final TextView udDOB = (TextView) dialog.findViewById(R.id.udDOB);
        final TextView udPreSchool = (TextView) dialog.findViewById(R.id.udPreSchool);
        final TextView udLevel = (TextView) dialog.findViewById(R.id.udLevel);
        final TextView udLocation = (TextView) dialog.findViewById(R.id.udLocation);
        final TextView udFatherName = (TextView) dialog.findViewById(R.id.udFatherName);
        final TextView udMotherName = (TextView) dialog.findViewById(R.id.udMotherName);
        final TextView udPhone = (TextView) dialog.findViewById(R.id.udPhone);
        final TextView udEmail = (TextView) dialog.findViewById(R.id.udEmail);

        FirebaseDatabase.getInstance().getReference().child("users")
                .orderByChild("email")
                .equalTo(userEmail)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                            udChildName.setText(String.valueOf(childSnapshot.child("childName").getValue()));
                            udDOB.setText(String.valueOf(childSnapshot.child("dob").getValue()));
                            udPreSchool.setText(String.valueOf(childSnapshot.child("preSchool").getValue()));
                            udLevel.setText(String.valueOf(childSnapshot.child("level").getValue()));
                            udLocation.setText(String.valueOf(childSnapshot.child("location").getValue()));
                            udFatherName.setText(String.valueOf(childSnapshot.child("fatherName").getValue()));
                            udMotherName.setText(String.valueOf(childSnapshot.child("motherName").getValue()));
                            udPhone.setText(String.valueOf(childSnapshot.child("phone").getValue()));
                            udEmail.setText(String.valueOf(childSnapshot.child("email").getValue()));
                            pbPSUserDetails.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
        dialog.show();
    }

    //displaying viewPager
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

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id){

            //Display dialog to select boy or girl & start DressActivity
            case R.id.ivDress:
                genderDress = "gender";
                final CharSequence[] gender = {"Boy","Girl"};
                AlertDialog.Builder alert = new AlertDialog.Builder(HomePageActivity.this);
                alert.setTitle("Select Gender");
                alert.setSingleChoiceItems(gender,-1, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        if(gender[which]=="Boy")
                        {
                            genderDress = "BOY";
                        }
                        else if (gender[which]=="Girl")
                        {
                            genderDress = "GIRL";
                        }
                    }
                });
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(genderDress.equals("gender")){
                            Toast.makeText(getBaseContext(), "Please select gender", Toast.LENGTH_SHORT).show();
                        } else {
                            bgm1.pause();
                            bgm1PausePosition = bgm1.getCurrentPosition();
                            bgm2.seekTo(bgm2PausePosition);
                            bgm2.start();
                            startActivity(new Intent(getBaseContext(), DressActivity.class));
                            dialog.dismiss();
                        }
                    }
                });
                alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.show();
                break;

            //start Food activity
            case R.id.ivFood:
                bgm1.pause();
                bgm1PausePosition = bgm1.getCurrentPosition();
                startActivity(new Intent(getBaseContext(), PickFoodActivity.class));
                break;

            //Launch zappar app if already installed else direct to play store
            case R.id.ivZappar:
                bgm1.pause();
                bgm1PausePosition = bgm1.getCurrentPosition();
                    PackageManager pm = getBaseContext().getPackageManager();
                    Intent appStartIntent = pm.getLaunchIntentForPackage("com.zappar.Zappar");
                    if (null != appStartIntent) {
                        getBaseContext().startActivity(appStartIntent);
                    } else {
                        Toast.makeText(getBaseContext(), "Please Install Zappar App To Get More Features", Toast.LENGTH_SHORT).show();
                        Intent goToMarket = new Intent(Intent.ACTION_VIEW)
                                .setData(Uri.parse("https://play.google.com/store/apps/details?id=com.zappar.Zappar&hl=en"));
                        startActivity(goToMarket);
                    }
                break;

            //direct to flash games
            case R.id.ivFlash:
                bgm1.pause();
                bgm1PausePosition = bgm1.getCurrentPosition();
                startActivity(new Intent(getBaseContext(), FlashListActivity.class));
                break;
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

    public static class loadImages extends AsyncTask<URL, Void, Bitmap>{

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
            mPager.setAdapter(new MyAdapter(context, bitmapList));
            //setCircleIndicator();
        }

    }

    @Override
    public void onDestroy() {
        bgm1.stop();
        bgm1.reset();
        super.onDestroy();
    }

    //Preparing bgm for all activities & starting bgm1
    public void bgm(){
        List<String> bgm;
        bgm = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.bgm)));
        int MAX_VOLUME = 100;
        final float volume = (float) (1 - (Math.log(MAX_VOLUME - 50) / Math.log(MAX_VOLUME)));
        bgm1 = new MediaPlayer();
        bgm2 = new MediaPlayer();
        bgm3 = new MediaPlayer();
        bgm4 = new MediaPlayer();
        bgm5 = new MediaPlayer();
        bgm6 = new MediaPlayer();
        bgm1.setAudioStreamType(AudioManager.STREAM_MUSIC);
        bgm2.setAudioStreamType(AudioManager.STREAM_MUSIC);
        bgm2.setVolume(volume, volume);
        bgm3.setAudioStreamType(AudioManager.STREAM_MUSIC);
        bgm3.setVolume(volume, volume);
        bgm4.setAudioStreamType(AudioManager.STREAM_MUSIC);
        bgm5.setAudioStreamType(AudioManager.STREAM_MUSIC);
        bgm5.setVolume(volume, volume);
        bgm6.setAudioStreamType(AudioManager.STREAM_MUSIC);
        bgm6.setVolume(volume, volume);
        try {
            bgm1.setDataSource(bgm.get(0));
            bgm1.prepareAsync();
            bgm1.setLooping(true);
            bgm2.setDataSource(bgm.get(3));
            bgm2.prepareAsync();
            bgm2.setLooping(true);
            bgm3.setDataSource(bgm.get(2));
            bgm3.prepareAsync();
            bgm3.setLooping(true);
            bgm4.setDataSource(bgm.get(3));
            bgm4.prepareAsync();
            bgm4.setLooping(true);
            bgm5.setDataSource(bgm.get(4));
            bgm5.prepareAsync();
            bgm5.setLooping(true);
            bgm6.setDataSource(bgm.get(5));
            bgm6.prepareAsync();
            bgm6.setLooping(true);
        } catch (Exception e) {
        }
        bgm1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                bgm1.start();
                pbHP.setVisibility(View.GONE);
                llHomePage.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        bgm1.pause();
        bgm1PausePosition = bgm1.getCurrentPosition();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bgm1.seekTo(bgm1PausePosition);
        bgm1.start();
    }

    @Override
    public void onBackPressed() {
        bgm1.stop();
        bgm1.reset();
        this.moveTaskToBack(true);
    }

}
