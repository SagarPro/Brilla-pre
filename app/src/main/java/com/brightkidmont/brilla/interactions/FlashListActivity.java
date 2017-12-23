package com.brightkidmont.brilla.interactions;

import android.content.Intent;
import android.graphics.Typeface;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.brightkidmont.brilla.R;
import com.brightkidmont.brilla.my_bright_brain.auditory.AuditoryActivity;
import com.brightkidmont.brilla.my_bright_brain.visual.DrawingPadActivity;
import com.brightkidmont.brilla.my_bright_brain.visual.jigsaw_puzzles.JigsawPuzzlesActivity;
import com.brightkidmont.brilla.my_bright_brain.visual.logical_thinking.LogicalThinkingActivity;
import com.brightkidmont.brilla.my_bright_brain.visual.riddles.RiddlesActivity;
import com.brightkidmont.brilla.my_bright_brain.visual.visual_discrimination.VisualDiscriminationActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

import static com.brightkidmont.brilla.HomePageActivity.bgm4;
import static com.brightkidmont.brilla.HomePageActivity.bgm4PausePosition;

public class FlashListActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flash_list_layout);

        Typeface font = Typeface.createFromAsset(this.getAssets(), "adikku_panadai_menari.ttf");
        Toolbar toolbar = (Toolbar) findViewById(R.id.tbCustom);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView tvCustom = (TextView) findViewById(R.id.tvCustom);
        tvCustom.setText("Brilla Pre");
        tvCustom.setTypeface(font);

        tts = new TextToSpeech(this, this);

        bgm4.seekTo(bgm4PausePosition);
        bgm4.start();

        ImageView ivShapes = (ImageView) findViewById(R.id.ivShapes);
        ImageView ivFruits = (ImageView) findViewById(R.id.ivFruits);
        ImageView ivColors = (ImageView) findViewById(R.id.ivColors);
        ImageView ivAuditory = (ImageView) findViewById(R.id.ivAuditory);
        ImageView ivLogicalThinking = (ImageView) findViewById(R.id.ivLogicalThinking);
        ImageView ivVisualDiscrimination = (ImageView) findViewById(R.id.ivVisualDiscrimination);
        ImageView ivJigsawPuzzles = (ImageView) findViewById(R.id.ivJigsawPuzzles);
        ImageView ivRiddles = (ImageView) findViewById(R.id.ivRiddles);
        ImageView ivDrawingPad = (ImageView) findViewById(R.id.ivDrawingPad);

        ArrayList<ImageView> imageflash = new ArrayList<>();
        imageflash.add(ivShapes);
        imageflash.add(ivColors);
        imageflash.add(ivAuditory);
        imageflash.add(ivLogicalThinking);
        imageflash.add(ivJigsawPuzzles);
        imageflash.add(ivRiddles);
        imageflash.add(ivDrawingPad);

        //array to store image urls
        ArrayList<String> imagesList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.flash_games)));

        for (int i=0; i<imageflash.size(); i++){
            Picasso.with(getBaseContext()).load(imagesList.get(i)).error(R.drawable.bright_kid_bg).into(imageflash.get(i));
        }

        ivShapes.setOnClickListener(this);
        ivFruits.setOnClickListener(this);
        ivColors.setOnClickListener(this);
        ivAuditory.setOnClickListener(this);
        ivLogicalThinking.setOnClickListener(this);
        ivVisualDiscrimination.setOnClickListener(this);
        ivJigsawPuzzles.setOnClickListener(this);
        ivRiddles.setOnClickListener(this);
        ivDrawingPad.setOnClickListener(this);

    }

    //directing to specific flash games
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.ivShapes:
                tts.speak("Shapes", TextToSpeech.QUEUE_FLUSH, null);
                startActivity(new Intent(FlashListActivity.this, ShapesActivity.class));
                break;
            case R.id.ivFruits:
                tts.speak("Fruits", TextToSpeech.QUEUE_FLUSH, null);
                Toast.makeText(getBaseContext(), "Currently In Development", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ivColors:
                tts.speak("Colors", TextToSpeech.QUEUE_FLUSH, null);
                startActivity(new Intent(FlashListActivity.this, ColorsActivity.class));
                break;
            case R.id.ivAuditory:
                tts.speak("Musical Instruments", TextToSpeech.QUEUE_FLUSH, null);
                Toast.makeText(this, "Please release your mobile from silent mode", Toast.LENGTH_LONG).show();
                startActivity(new Intent(FlashListActivity.this, AuditoryActivity.class));
                break;
            case R.id.ivLogicalThinking:
                tts.speak("Logical Thinking", TextToSpeech.QUEUE_FLUSH, null);
                startActivity(new Intent(FlashListActivity.this, LogicalThinkingActivity.class));
                break;
            case R.id.ivVisualDiscrimination:
                tts.speak("Visual Discrimination", TextToSpeech.QUEUE_FLUSH, null);
                startActivity(new Intent(FlashListActivity.this, VisualDiscriminationActivity.class));
                break;
            case R.id.ivJigsawPuzzles:
                tts.speak("Jigsaw Puzzles", TextToSpeech.QUEUE_FLUSH, null);
                startActivity(new Intent(FlashListActivity.this, JigsawPuzzlesActivity.class));
                break;
            case R.id.ivRiddles:
                tts.speak("Riddles", TextToSpeech.QUEUE_FLUSH, null);
                startActivity(new Intent(FlashListActivity.this, RiddlesActivity.class));
                break;
            case R.id.ivDrawingPad:
                tts.speak("Drawing Pad", TextToSpeech.QUEUE_FLUSH, null);
                startActivity(new Intent(FlashListActivity.this, DrawingPadActivity.class));
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        bgm4.pause();
        bgm4PausePosition = bgm4.getCurrentPosition();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bgm4.seekTo(bgm4PausePosition);
        bgm4.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        bgm4.pause();
        bgm4PausePosition = bgm4.getCurrentPosition();
        finish();
    }

    @Override
    public void onInit(int status) {
    }
}
