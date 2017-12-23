package com.brightkidmont.brilla.my_bright_brain.visual.logical_thinking;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brightkidmont.brilla.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

public class Numbers extends Fragment implements View.OnClickListener, TextToSpeech.OnInitListener{

    private MediaPlayer correct, wrong;
    private ArrayList<ImageView> imageResults;
    private TextToSpeech tts;
    private ImageView imageResult1, imageResult2, imageResult3, imageResult4, imageResult5, imageResult6, imageResult7, imageResult8;

    public Numbers(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.numbers_fragment_layout, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Count the number of objects and click on correct number");
        flash_header.setTypeface(Typeface.DEFAULT_BOLD);

        tts = new TextToSpeech(getContext(), this);
        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        correct = MediaPlayer.create(getContext(), R.raw.correct);
        wrong = MediaPlayer.create(getContext(), R.raw.ur_wrong);

        //storing image urls in array
        ArrayList<String> imagesList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.numbers)));

        TextView tv1 = (TextView) view.findViewById(R.id.tv1);
        TextView tv2 = (TextView) view.findViewById(R.id.tv2);
        TextView tv3 = (TextView) view.findViewById(R.id.tv3);
        TextView tv4 = (TextView) view.findViewById(R.id.tv4);
        TextView tv5 = (TextView) view.findViewById(R.id.tv5);
        TextView tv6 = (TextView) view.findViewById(R.id.tv6);
        TextView tv7 = (TextView) view.findViewById(R.id.tv7);
        TextView tv8 = (TextView) view.findViewById(R.id.tv8);
        TextView tv9 = (TextView) view.findViewById(R.id.tv9);
        TextView tv10 = (TextView) view.findViewById(R.id.tv10);
        TextView tv11 = (TextView) view.findViewById(R.id.tv11);
        TextView tv12 = (TextView) view.findViewById(R.id.tv12);
        TextView tv13 = (TextView) view.findViewById(R.id.tv13);
        TextView tv14 = (TextView) view.findViewById(R.id.tv14);
        TextView tv15 = (TextView) view.findViewById(R.id.tv15);
        TextView tv16 = (TextView) view.findViewById(R.id.tv16);

        ImageView imageNumber1 = (ImageView) view.findViewById(R.id.imageNumber1);
        ImageView imageNumber2 = (ImageView) view.findViewById(R.id.imageNumber2);
        ImageView imageNumber3 = (ImageView) view.findViewById(R.id.imageNumber3);
        ImageView imageNumber4 = (ImageView) view.findViewById(R.id.imageNumber4);
        ImageView imageNumber5 = (ImageView) view.findViewById(R.id.imageNumber5);
        ImageView imageNumber6 = (ImageView) view.findViewById(R.id.imageNumber6);
        ImageView imageNumber7 = (ImageView) view.findViewById(R.id.imageNumber7);
        ImageView imageNumber8 = (ImageView) view.findViewById(R.id.imageNumber8);

        imageResult1 = (ImageView) view.findViewById(R.id.imageResult1);
        imageResult2 = (ImageView) view.findViewById(R.id.imageResult2);
        imageResult3 = (ImageView) view.findViewById(R.id.imageResult3);
        imageResult4 = (ImageView) view.findViewById(R.id.imageResult4);
        imageResult5 = (ImageView) view.findViewById(R.id.imageResult5);
        imageResult6 = (ImageView) view.findViewById(R.id.imageResult6);
        imageResult7 = (ImageView) view.findViewById(R.id.imageResult7);
        imageResult8 = (ImageView) view.findViewById(R.id.imageResult8);

        ArrayList<TextView> textNumbers = new ArrayList<>();
        textNumbers.add(tv1);
        textNumbers.add(tv2);
        textNumbers.add(tv3);
        textNumbers.add(tv4);
        textNumbers.add(tv5);
        textNumbers.add(tv6);
        textNumbers.add(tv7);
        textNumbers.add(tv8);
        textNumbers.add(tv9);
        textNumbers.add(tv10);
        textNumbers.add(tv11);
        textNumbers.add(tv12);
        textNumbers.add(tv13);
        textNumbers.add(tv14);
        textNumbers.add(tv15);
        textNumbers.add(tv16);

        ArrayList<ImageView> imageNumbers = new ArrayList<>();
        imageNumbers.add(imageNumber1);
        imageNumbers.add(imageNumber2);
        imageNumbers.add(imageNumber3);
        imageNumbers.add(imageNumber4);
        imageNumbers.add(imageNumber5);
        imageNumbers.add(imageNumber6);
        imageNumbers.add(imageNumber7);
        imageNumbers.add(imageNumber8);

        imageResults = new ArrayList<>();
        imageResults.add(imageResult1);
        imageResults.add(imageResult2);
        imageResults.add(imageResult3);
        imageResults.add(imageResult4);
        imageResults.add(imageResult5);
        imageResults.add(imageResult6);
        imageResults.add(imageResult7);
        imageResults.add(imageResult8);

        //setting image in imageView
        for(int i=0; i<8; i++){
            imageResults.get(i).setVisibility(View.GONE);
            Picasso.with(getContext()).load(imagesList.get(i)).error(R.drawable.bright_kid_bg).into(imageNumbers.get(i));
            imageNumbers.get(i).setOnClickListener(this);
        }

        for (int i=0; i<16; i++){
            textNumbers.get(i).setOnClickListener(this);
        }

        return view;
    }

    private void hideView(){
        for(int i=0; i<8; i++)
            imageResults.get(i).setVisibility(View.GONE);
    }

    //displaying result onClick
    @Override
    public void onClick(View v) {
        String message = null;
        switch (v.getId()){
            case R.id.imageNumber1:
                message = "Apple";
                break;
            case R.id.imageNumber2:
                message = "Boots";
                break;
            case R.id.imageNumber3:
                message = "Mangoes";
                break;
            case R.id.imageNumber4:
                message = "Hat";
                break;
            case R.id.imageNumber5:
                message = "Gloves";
                break;
            case R.id.imageNumber6:
                message = "Ball";
                break;
            case R.id.imageNumber7:
                message = "Cats";
                break;
            case R.id.imageNumber8:
                message = "Birds";
                break;
            case R.id.tv1:
                wrong.start();
                imageResult1.setVisibility(View.VISIBLE);
                imageResult1.setImageResource(R.drawable.wrong);
                break;
            case R.id.tv2:
                correct.start();
                imageResult1.setVisibility(View.VISIBLE);
                imageResult1.setImageResource(R.drawable.right);
                break;
            case R.id.tv3:
                correct.start();
                imageResult2.setVisibility(View.VISIBLE);
                imageResult2.setImageResource(R.drawable.right);
                break;
            case R.id.tv4:
                wrong.start();
                imageResult2.setVisibility(View.VISIBLE);
                imageResult2.setImageResource(R.drawable.wrong);
                break;
            case R.id.tv5:
                wrong.start();
                imageResult3.setVisibility(View.VISIBLE);
                imageResult3.setImageResource(R.drawable.wrong);
                break;
            case R.id.tv6:
                correct.start();
                imageResult3.setVisibility(View.VISIBLE);
                imageResult3.setImageResource(R.drawable.right);
                break;
            case R.id.tv7:
                correct.start();
                imageResult4.setVisibility(View.VISIBLE);
                imageResult4.setImageResource(R.drawable.right);
                break;
            case R.id.tv8:
                wrong.start();
                imageResult4.setVisibility(View.VISIBLE);
                imageResult4.setImageResource(R.drawable.wrong);
                break;
            case R.id.tv9:
                wrong.start();
                imageResult5.setVisibility(View.VISIBLE);
                imageResult5.setImageResource(R.drawable.wrong);
                break;
            case R.id.tv10:
                correct.start();
                imageResult5.setVisibility(View.VISIBLE);
                imageResult5.setImageResource(R.drawable.right);
                break;
            case R.id.tv11:
                wrong.start();
                imageResult6.setVisibility(View.VISIBLE);
                imageResult6.setImageResource(R.drawable.wrong);
                break;
            case R.id.tv12:
                correct.start();
                imageResult6.setVisibility(View.VISIBLE);
                imageResult6.setImageResource(R.drawable.right);
                break;
            case R.id.tv13:
                correct.start();
                imageResult7.setVisibility(View.VISIBLE);
                imageResult7.setImageResource(R.drawable.right);
                break;
            case R.id.tv14:
                wrong.start();
                imageResult7.setVisibility(View.VISIBLE);
                imageResult7.setImageResource(R.drawable.wrong);
                break;
            case R.id.tv15:
                wrong.start();
                imageResult8.setVisibility(View.VISIBLE);
                imageResult8.setImageResource(R.drawable.wrong);
                break;
            case R.id.tv16:
                correct.start();
                imageResult8.setVisibility(View.VISIBLE);
                imageResult8.setImageResource(R.drawable.right);
                break;
            case R.id.speaker:
                break;
        }
        tts.speak(message, TextToSpeech.QUEUE_FLUSH, null);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideView();
            }
        }, 1000);
    }

    @Override
    public void onPause() {
        super.onPause();
        if(tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }

    @Override
    public void onInit(int status) {
    }
}
