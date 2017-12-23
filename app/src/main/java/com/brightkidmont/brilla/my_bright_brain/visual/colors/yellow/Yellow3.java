package com.brightkidmont.brilla.my_bright_brain.visual.colors.yellow;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.brightkidmont.brilla.R;
import com.squareup.picasso.Picasso;

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.yellow.YellowActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.yellow.YellowActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.yellow.YellowActivity.wrong;

public class Yellow3 extends Fragment implements View.OnClickListener{

    private ImageView ivy1, ivy2, ivy3, ivy4, ivy5, ivy6;

    public Yellow3(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.yellow3, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Find yellow colored fruits");
        flash_header.setTextColor(getResources().getColor(R.color.f_black));

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_sun_flower));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setImageResource(R.drawable.speaker_b);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q.start();
            }
        });

        ImageView y1 = (ImageView) view.findViewById(R.id.y1);
        ImageView y2 = (ImageView) view.findViewById(R.id.y2);
        ImageView y3 = (ImageView) view.findViewById(R.id.y3);
        ImageView y4 = (ImageView) view.findViewById(R.id.y4);
        ImageView y5 = (ImageView) view.findViewById(R.id.y5);
        ImageView y6 = (ImageView) view.findViewById(R.id.y6);

        String image1, image2, image3, image4, image5, image6;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FRed%2Ff1.png?alt=media&token=63194b1f-554a-41bc-8eeb-aa333b8c7016";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FYellow%2Fy5.png?alt=media&token=f50b9e9d-8a55-453a-b6ac-d17d6ef36bd8";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FRed%2Ff3.png?alt=media&token=d448cd06-7e86-4838-9a57-32b3a7bd8167";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FYellow%2Fy6.png?alt=media&token=846e7242-18bf-4da3-9bb5-56bcd74a44f7";
        image5 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FRed%2Ff4.png?alt=media&token=6b03739d-fa81-435a-bd10-3090f86d8f3a";
        image6 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FYellow%2Fy7.png?alt=media&token=eef2862f-ab1a-4436-857d-672f39ad8506";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(y1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(y2);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(y3);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(y4);
        Picasso.with(getContext()).load(image5).error(R.drawable.bright_kid_bg).into(y5);
        Picasso.with(getContext()).load(image6).error(R.drawable.bright_kid_bg).into(y6);

        ivy1 = (ImageView) view.findViewById(R.id.ivy1);
        ivy2 = (ImageView) view.findViewById(R.id.ivy2);
        ivy3 = (ImageView) view.findViewById(R.id.ivy3);
        ivy4 = (ImageView) view.findViewById(R.id.ivy4);
        ivy5 = (ImageView) view.findViewById(R.id.ivy5);
        ivy6 = (ImageView) view.findViewById(R.id.ivy6);

        hide();

        y1.setOnClickListener(this);
        y2.setOnClickListener(this);
        y3.setOnClickListener(this);
        y4.setOnClickListener(this);
        y5.setOnClickListener(this);
        y6.setOnClickListener(this);

        return view;
    }

    public void hide(){
        ivy1.setVisibility(View.INVISIBLE);
        ivy2.setVisibility(View.INVISIBLE);
        ivy3.setVisibility(View.INVISIBLE);
        ivy4.setVisibility(View.INVISIBLE);
        ivy5.setVisibility(View.INVISIBLE);
        ivy6.setVisibility(View.INVISIBLE);
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hide();
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.y1:
                    wrong.start();
                    ivy1.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.y2:
                    correct.start();
                    ivy2.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.y3:
                    wrong.start();
                    ivy3.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.y4:
                    correct.start();
                    ivy4.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.y5:
                    wrong.start();
                    ivy5.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.y6:
                    correct.start();
                    ivy6.setVisibility(View.VISIBLE);
                    hideView();
                    break;
            }
        }
    }

}
