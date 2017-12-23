package com.brightkidmont.brilla.my_bright_brain.visual.logical_thinking;

import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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

public class TallShort extends Fragment implements View.OnClickListener {

    private MediaPlayer correct, wrong;
    private ArrayList<ImageView> imageSizes;
    private ImageView imageSize1, imageSize2, imageSize3, imageSize4, imageSize5, imageSize6, imageSize7, imageSize8, imageSize9, imageSize10, imageSize11, imageSize12;

    public TallShort(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.sizes_fragment_layout, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Click all the shortest objects");
        flash_header.setTypeface(Typeface.DEFAULT_BOLD);

        correct = MediaPlayer.create(getContext(), R.raw.correct);
        wrong = MediaPlayer.create(getContext(), R.raw.ur_wrong);

        //storing image url in array
        ArrayList<String> imagesList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.tall_short)));

        imageSize1 = (ImageView) view.findViewById(R.id.imageSize1);
        imageSize2 = (ImageView) view.findViewById(R.id.imageSize2);
        imageSize3 = (ImageView) view.findViewById(R.id.imageSize3);
        imageSize4 = (ImageView) view.findViewById(R.id.imageSize4);
        imageSize5 = (ImageView) view.findViewById(R.id.imageSize5);
        imageSize6 = (ImageView) view.findViewById(R.id.imageSize6);
        imageSize7 = (ImageView) view.findViewById(R.id.imageSize7);
        imageSize8 = (ImageView) view.findViewById(R.id.imageSize8);
        imageSize9 = (ImageView) view.findViewById(R.id.imageSize9);
        imageSize10 = (ImageView) view.findViewById(R.id.imageSize10);
        imageSize11 = (ImageView) view.findViewById(R.id.imageSize11);
        imageSize12 = (ImageView) view.findViewById(R.id.imageSize12);

        imageSizes = new ArrayList<>();
        imageSizes.add(imageSize1);
        imageSizes.add(imageSize2);
        imageSizes.add(imageSize3);
        imageSizes.add(imageSize4);
        imageSizes.add(imageSize5);
        imageSizes.add(imageSize6);
        imageSizes.add(imageSize7);
        imageSizes.add(imageSize8);
        imageSizes.add(imageSize9);
        imageSizes.add(imageSize10);
        imageSizes.add(imageSize11);
        imageSizes.add(imageSize12);

        //setting image into imageView
        for (int i=0; i<12; i++){
            Picasso.with(getContext()).load(imagesList.get(i)).error(R.drawable.bright_kid_bg).into(imageSizes.get(i));
            imageSizes.get(i).setOnClickListener(this);
        }

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        return view;
    }

    private void hideView(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            for(int i=0; i<12; i++){
                imageSizes.get(i).setBackgroundColor(Color.TRANSPARENT);
            }
        }
    }

    //displaying result
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageSize1:
                wrong.start();
                imageSize1.setBackground(getResources().getDrawable(R.drawable.wrong));
                break;
            case R.id.imageSize2:
                correct.start();
                imageSize2.setBackground(getResources().getDrawable(R.drawable.right));
                break;
            case R.id.imageSize3:
                correct.start();
                imageSize3.setBackground(getResources().getDrawable(R.drawable.right));
                break;
            case R.id.imageSize4:
                wrong.start();
                imageSize4.setBackground(getResources().getDrawable(R.drawable.wrong));
                break;
            case R.id.imageSize5:
                correct.start();
                imageSize5.setBackground(getResources().getDrawable(R.drawable.right));
                break;
            case R.id.imageSize6:
                wrong.start();
                imageSize6.setBackground(getResources().getDrawable(R.drawable.wrong));
                break;
            case R.id.imageSize7:
                wrong.start();
                imageSize7.setBackground(getResources().getDrawable(R.drawable.wrong));
                break;
            case R.id.imageSize8:
                correct.start();
                imageSize8.setBackground(getResources().getDrawable(R.drawable.right));
                break;
            case R.id.imageSize9:
                wrong.start();
                imageSize9.setBackground(getResources().getDrawable(R.drawable.wrong));
                break;
            case R.id.imageSize10:
                correct.start();
                imageSize10.setBackground(getResources().getDrawable(R.drawable.right));
                break;
            case R.id.imageSize11:
                correct.start();
                imageSize11.setBackground(getResources().getDrawable(R.drawable.right));
                break;
            case R.id.imageSize12:
                wrong.start();
                imageSize12.setBackground(getResources().getDrawable(R.drawable.wrong));
                break;
            case R.id.speaker:
                break;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               hideView();
            }
        }, 1000);
    }
}
