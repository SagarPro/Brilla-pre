package com.brightkidmont.brilla.my_bright_brain.visual.colors.green;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.green.GreenActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.green.GreenActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.green.GreenActivity.wrong;

public class Green2 extends Fragment implements View.OnClickListener{

    private ImageView g1_right, g2_right, g3_wrong, g4_right, g5_wrong, g6_right;

    public Green2(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.green2, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Find green colored fruits");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_emerald));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);

        ImageView g1 = (ImageView) view.findViewById(R.id.g1);
        ImageView g2 = (ImageView) view.findViewById(R.id.g2);
        ImageView g3 = (ImageView) view.findViewById(R.id.g3);
        ImageView g4 = (ImageView) view.findViewById(R.id.g4);
        ImageView g5 = (ImageView) view.findViewById(R.id.g5);
        ImageView g6 = (ImageView) view.findViewById(R.id.g6);

        String image1, image2, image3, image4, image5, image6;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FGreen%2Fg5.png?alt=media&token=29c80f88-e8a7-41d6-9828-02a22cce6a1d";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FGreen%2Fg6.png?alt=media&token=6664a61c-4ac9-4180-bc43-b22d72e4f18e";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FRed%2Ff3.png?alt=media&token=d448cd06-7e86-4838-9a57-32b3a7bd8167";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FGreen%2Fg7.png?alt=media&token=ee533d17-06aa-4432-b397-0f9de171a45f";
        image5 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FGreen%2Fg8.png?alt=media&token=7b0cf697-a6f2-4513-87f2-d517e592cd2e";
        image6 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FGreen%2Fg9.png?alt=media&token=b0902a21-fddb-42a5-851e-ac460319b037";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(g1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(g2);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(g3);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(g4);
        Picasso.with(getContext()).load(image6).error(R.drawable.bright_kid_bg).into(g5);
        Picasso.with(getContext()).load(image5).error(R.drawable.bright_kid_bg).into(g6);

        g1_right = (ImageView) view.findViewById(R.id.g1_right);
        g2_right = (ImageView) view.findViewById(R.id.g2_right);
        g3_wrong = (ImageView) view.findViewById(R.id.g3_wrong);
        g4_right = (ImageView) view.findViewById(R.id.g4_right);
        g5_wrong = (ImageView) view.findViewById(R.id.g5_wrong);
        g6_right = (ImageView) view.findViewById(R.id.g6_right);

        g1_right.setVisibility(View.INVISIBLE);
        g2_right.setVisibility(View.INVISIBLE);
        g3_wrong.setVisibility(View.INVISIBLE);
        g4_right.setVisibility(View.INVISIBLE);
        g5_wrong.setVisibility(View.INVISIBLE);
        g6_right.setVisibility(View.INVISIBLE);

        speaker.setOnClickListener(this);

        g1.setOnClickListener(this);
        g2.setOnClickListener(this);
        g3.setOnClickListener(this);
        g4.setOnClickListener(this);
        g5.setOnClickListener(this);
        g6.setOnClickListener(this);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                g1_right.setVisibility(View.INVISIBLE);
                g2_right.setVisibility(View.INVISIBLE);
                g3_wrong.setVisibility(View.INVISIBLE);
                g4_right.setVisibility(View.INVISIBLE);
                g5_wrong.setVisibility(View.INVISIBLE);
                g6_right.setVisibility(View.INVISIBLE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.g1:
                    correct.start();
                    g1_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.g2:
                    correct.start();
                    g2_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.g3:
                    wrong.start();
                    g3_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.g4:
                    correct.start();
                    g4_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.g5:
                    wrong.start();
                    g5_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.g6:
                    correct.start();
                    g6_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }
}