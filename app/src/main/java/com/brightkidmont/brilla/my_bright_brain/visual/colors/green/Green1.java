package com.brightkidmont.brilla.my_bright_brain.visual.colors.green;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.brightkidmont.brilla.R;
import com.squareup.picasso.Picasso;

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.green.GreenActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.green.GreenActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.green.GreenActivity.wrong;

public class Green1 extends Fragment implements View.OnClickListener {

    private ImageView g1;
    private ImageView g2;
    private ImageView blue_wrong;
    private ImageView red_wrong;
    private ImageView green_right;

    public Green1(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.green1, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Color the leaves of the sunflower plant with green color");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_emerald));

        g1 = (ImageView) view.findViewById(R.id.g1);
        g2 = (ImageView) view.findViewById(R.id.g2);
        ImageView blue_c = (ImageView) view.findViewById(R.id.blue_c);
        ImageView red_c = (ImageView) view.findViewById(R.id.red_c);
        ImageView green_c = (ImageView) view.findViewById(R.id.green_c);
        blue_wrong = (ImageView) view.findViewById(R.id.blue_wrong);
        red_wrong = (ImageView) view.findViewById(R.id.red_wrong);
        green_right = (ImageView) view.findViewById(R.id.green_right);

        String image1, image2;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FGreen%2Fg1.png?alt=media&token=6759b203-e77c-4db7-85d9-eb28bcfe5962";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FGreen%2Fg2.png?alt=media&token=78f66683-bcb9-4f14-b3c7-e414e669e89a";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(g1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(g2);

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);

        g2.setVisibility(View.GONE);

        blue_wrong.setVisibility(View.GONE);
        red_wrong.setVisibility(View.GONE);
        green_right.setVisibility(View.GONE);

        blue_c.setOnClickListener(this);
        red_c.setOnClickListener(this);
        green_c.setOnClickListener(this);

        speaker.setOnClickListener(this);

        return view;
    }

    public void transition_anim(){
        Animation fade_out = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        g2.setVisibility(View.VISIBLE);
        g2.startAnimation(fade_out);
        Animation fade_in = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        g1.startAnimation(fade_in);
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                blue_wrong.setVisibility(View.GONE);
                red_wrong.setVisibility(View.GONE);
                green_right.setVisibility(View.GONE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.blue_c:
                    wrong.start();
                    blue_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.red_c:
                    wrong.start();
                    red_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.green_c:
                    correct.start();
                    green_right.setVisibility(View.VISIBLE);
                    hideView();
                    transition_anim();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }
}
