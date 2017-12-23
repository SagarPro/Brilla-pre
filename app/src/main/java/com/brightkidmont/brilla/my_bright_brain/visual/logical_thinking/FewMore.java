package com.brightkidmont.brilla.my_bright_brain.visual.logical_thinking;

import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
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

public class FewMore extends Fragment implements View.OnClickListener{

    private MediaPlayer correct, wrong;
    private TextView tvSign11, tvSign21, tvSign31, tvSign12, tvSign22, tvSign32, tvSign13, tvSign23, tvSign33, tvSign14, tvSign24, tvSign34;
    private TextView tvResult1, tvResult2, tvResult3, tvResult4;

    public FewMore(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.few_more_fragment_layout, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Count the objects and click the correct sign");
        flash_header.setTypeface(Typeface.DEFAULT_BOLD);

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        correct = MediaPlayer.create(getContext(), R.raw.correct);
        wrong = MediaPlayer.create(getContext(), R.raw.ur_wrong);

        //array to store image urls
        ArrayList<String> imagesList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.few_more)));

        tvSign11 = (TextView) view.findViewById(R.id.tvSign11);
        tvSign21 = (TextView) view.findViewById(R.id.tvSign21);
        tvSign31 = (TextView) view.findViewById(R.id.tvSign31);
        tvSign12 = (TextView) view.findViewById(R.id.tvSign12);
        tvSign22 = (TextView) view.findViewById(R.id.tvSign22);
        tvSign32 = (TextView) view.findViewById(R.id.tvSign32);
        tvSign13 = (TextView) view.findViewById(R.id.tvSign13);
        tvSign23 = (TextView) view.findViewById(R.id.tvSign23);
        tvSign33 = (TextView) view.findViewById(R.id.tvSign33);
        tvSign14 = (TextView) view.findViewById(R.id.tvSign14);
        tvSign24 = (TextView) view.findViewById(R.id.tvSign24);
        tvSign34 = (TextView) view.findViewById(R.id.tvSign34);

        ArrayList<TextView> textSigns = new ArrayList<>();
        textSigns.add(tvSign11);
        textSigns.add(tvSign21);
        textSigns.add(tvSign31);
        textSigns.add(tvSign12);
        textSigns.add(tvSign22);
        textSigns.add(tvSign32);
        textSigns.add(tvSign13);
        textSigns.add(tvSign23);
        textSigns.add(tvSign33);
        textSigns.add(tvSign14);
        textSigns.add(tvSign24);
        textSigns.add(tvSign34);

        for (int i=0; i<12; i++){
            textSigns.get(i).setOnClickListener(this);
        }

        tvResult1 = (TextView) view.findViewById(R.id.tvResult1);
        tvResult2 = (TextView) view.findViewById(R.id.tvResult2);
        tvResult3 = (TextView) view.findViewById(R.id.tvResult3);
        tvResult4 = (TextView) view.findViewById(R.id.tvResult4);

        ImageView fewMore1 = (ImageView) view.findViewById(R.id.fewMore1);
        ImageView fewMore2 = (ImageView) view.findViewById(R.id.fewMore2);
        ImageView fewMore3 = (ImageView) view.findViewById(R.id.fewMore3);
        ImageView fewMore4 = (ImageView) view.findViewById(R.id.fewMore4);
        ImageView fewMore5 = (ImageView) view.findViewById(R.id.fewMore5);
        ImageView fewMore6 = (ImageView) view.findViewById(R.id.fewMore6);
        ImageView fewMore7 = (ImageView) view.findViewById(R.id.fewMore7);
        ImageView fewMore8 = (ImageView) view.findViewById(R.id.fewMore8);

        ArrayList<ImageView> imagefewMore = new ArrayList<>();
        imagefewMore.add(fewMore1);
        imagefewMore.add(fewMore2);
        imagefewMore.add(fewMore3);
        imagefewMore.add(fewMore4);
        imagefewMore.add(fewMore5);
        imagefewMore.add(fewMore6);
        imagefewMore.add(fewMore7);
        imagefewMore.add(fewMore8);

        //setting image into imageView
        for(int i=0; i<8; i++){
            Picasso.with(getContext()).load(imagesList.get(i)).error(R.drawable.bright_kid_bg).into(imagefewMore.get(i));
        }

        return view;
    }

    //displaying results onClick
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvSign11:
                correct.start();
                tvResult1.setText(tvSign11.getText().toString());
                tvResult1.setTextColor(Color.GREEN);
                break;
            case R.id.tvSign21:
                wrong.start();
                tvResult1.setText(tvSign21.getText().toString());
                tvResult1.setTextColor(Color.RED);
                break;
            case R.id.tvSign31:
                wrong.start();
                tvResult1.setText(tvSign31.getText().toString());
                tvResult1.setTextColor(Color.RED);
                break;
            case R.id.tvSign12:
                correct.start();
                tvResult2.setText(tvSign12.getText().toString());
                tvResult2.setTextColor(Color.GREEN);
                break;
            case R.id.tvSign22:
                wrong.start();
                tvResult2.setText(tvSign22.getText().toString());
                tvResult2.setTextColor(Color.RED);
                break;
            case R.id.tvSign32:
                wrong.start();
                tvResult2.setText(tvSign32.getText().toString());
                tvResult2.setTextColor(Color.RED);
                break;
            case R.id.tvSign13:
                wrong.start();
                tvResult3.setText(tvSign13.getText().toString());
                tvResult3.setTextColor(Color.RED);
                break;
            case R.id.tvSign23:
                wrong.start();
                tvResult3.setText(tvSign23.getText().toString());
                tvResult3.setTextColor(Color.RED);
                break;
            case R.id.tvSign33:
                correct.start();
                tvResult3.setText(tvSign33.getText().toString());
                tvResult3.setTextColor(Color.GREEN);
                break;
            case R.id.tvSign14:
                wrong.start();
                tvResult4.setText(tvSign14.getText().toString());
                tvResult4.setTextColor(Color.RED);
                break;
            case R.id.tvSign24:
                correct.start();
                tvResult4.setText(tvSign24.getText().toString());
                tvResult4.setTextColor(Color.GREEN);
                break;
            case R.id.tvSign34:
                wrong.start();
                tvResult4.setText(tvSign34.getText().toString());
                tvResult4.setTextColor(Color.RED);
                break;
            case R.id.speaker:
                break;
        }
    }
}
