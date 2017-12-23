package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.oval;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.brightkidmont.brilla.R;
import com.squareup.picasso.Picasso;

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.oval.OvalActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.oval.OvalActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.oval.OvalActivity.wrong;

public class Oval4 extends Fragment implements View.OnClickListener {

    private ImageView oval1;
    private ImageView oval2;
    private ImageView oval3;
    private ImageView oval4;
    private TextView tvCount;
    private int count = 0;

    public Oval4(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.oval4, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Count the oval shaped objects");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        tvCount = (TextView) view.findViewById(R.id.tvCount);

        ImageView square1 = (ImageView) view.findViewById(R.id.square1);
        oval1 = (ImageView) view.findViewById(R.id.oval1);
        ImageView star2 = (ImageView) view.findViewById(R.id.star2);
        oval2 = (ImageView) view.findViewById(R.id.oval2);
        oval3 = (ImageView) view.findViewById(R.id.oval3);
        ImageView rect3 = (ImageView) view.findViewById(R.id.rect3);
        ImageView clock = (ImageView) view.findViewById(R.id.clock);
        oval4 = (ImageView) view.findViewById(R.id.oval4);

        String image1, image2, image3, image4, image5, image6, image7, image8;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FSquare%2Fsquare1.png?alt=media&token=58f742f0-5a68-4fd5-a2c5-f547848defdb";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FOval%2Foval1.png?alt=media&token=2544d940-655d-4292-a07f-fa2320865542";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FStar%2Fstar2.png?alt=media&token=d2f40c33-9573-48d7-8380-20d6c6470a56";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FOval%2Foval2.png?alt=media&token=28310699-6785-4c09-b75a-50867fd263b2";
        image5 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FOval%2Foval3.png?alt=media&token=f8fffe15-3295-45d4-a281-e513d96fa2cc";
        image6 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FSquare%2Fclock.png?alt=media&token=58720b13-f865-482d-8435-e4e60f85e0b9";
        image7 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FRectangle%2Fpillow.png?alt=media&token=65d9a572-4ac8-4021-b9d7-68c0b7b470fd";
        image8 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FOval%2Foval4.png?alt=media&token=ebe66bd4-4055-4ca6-bc0a-64aefa0d4570";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(square1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(oval1);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(star2);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(oval2);
        Picasso.with(getContext()).load(image5).error(R.drawable.bright_kid_bg).into(oval3);
        Picasso.with(getContext()).load(image6).error(R.drawable.bright_kid_bg).into(rect3);
        Picasso.with(getContext()).load(image7).error(R.drawable.bright_kid_bg).into(clock);
        Picasso.with(getContext()).load(image8).error(R.drawable.bright_kid_bg).into(oval4);

        square1.setOnClickListener(this);
        oval1.setOnClickListener(this);
        star2.setOnClickListener(this);
        oval2.setOnClickListener(this);
        oval3.setOnClickListener(this);
        rect3.setOnClickListener(this);
        clock.setOnClickListener(this);
        oval4.setOnClickListener(this);

        return view;
    }

    public void countOval(){
        count++;
        tvCount.setText("You Counted "+count+" Ovals");
    }

    public void message(){
        if(count==4){
            tvCount.setText("You Counted all Ovals");
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.square1:
                    message();
                    wrong.start();
                    break;
                case R.id.oval1:
                    message();
                    countOval();
                    correct.start();
                    oval1.setAlpha((float) 0.2);
                    oval1.setOnClickListener(null);
                    break;
                case R.id.star2:
                    message();
                    wrong.start();
                    break;
                case R.id.oval2:
                    message();
                    countOval();
                    correct.start();
                    oval2.setAlpha((float) 0.2);
                    oval2.setOnClickListener(null);
                    break;
                case R.id.oval3:
                    message();
                    countOval();
                    correct.start();
                    oval3.setAlpha((float) 0.2);
                    oval3.setOnClickListener(null);
                    break;
                case R.id.clock:
                    message();
                    wrong.start();
                    break;
                case R.id.rect3:
                    message();
                    wrong.start();
                    break;
                case R.id.oval4:
                    message();
                    countOval();
                    correct.start();
                    oval4.setAlpha((float) 0.2);
                    oval4.setOnClickListener(null);
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
