package com.brightkidmont.brilla.my_bright_brain.visual.colors.yellow;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.yellow.YellowActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.yellow.YellowActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.yellow.YellowActivity.wrong;

public class Yellow1 extends Fragment implements View.OnClickListener{

    private ImageView y1;
    private ImageView y2;
    private ImageView blue_wrong;
    private ImageView red_wrong;
    private ImageView yellow_right;

    public Yellow1(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.yellow1, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Color the sunflower with yellow color");
        flash_header.setTextColor(getResources().getColor(R.color.f_black));

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_sun_flower));

        y1 = (ImageView) view.findViewById(R.id.y1);
        y2 = (ImageView) view.findViewById(R.id.y2);
        ImageView blue = (ImageView) view.findViewById(R.id.blue);
        ImageView red = (ImageView) view.findViewById(R.id.red);
        ImageView yellow = (ImageView) view.findViewById(R.id.yellow);
        blue_wrong = (ImageView) view.findViewById(R.id.blue_wrong);
        red_wrong = (ImageView) view.findViewById(R.id.red_wrong);
        yellow_right = (ImageView) view.findViewById(R.id.yellow_right);

        String image1, image2;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FYellow%2Fy1.png?alt=media&token=ab03cec5-d111-4ab7-a728-a46841d6dd68";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FYellow%2Fy2.png?alt=media&token=dd0cd7f4-2439-4c1d-834a-05aff2332a17";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(y1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(y2);

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setImageResource(R.drawable.speaker_b);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q.start();
            }
        });

        y2.setVisibility(View.GONE);

        blue_wrong.setVisibility(View.GONE);
        red_wrong.setVisibility(View.GONE);
        yellow_right.setVisibility(View.GONE);

        blue.setOnClickListener(this);
        red.setOnClickListener(this);
        yellow.setOnClickListener(this);

        return view;
    }

    public void transition_anim(){
        Animation fade_out = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        y2.setVisibility(View.VISIBLE);
        y2.startAnimation(fade_out);
        Animation fade_in = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        y1.startAnimation(fade_in);
        y1.setVisibility(View.INVISIBLE);
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                blue_wrong.setVisibility(View.GONE);
                red_wrong.setVisibility(View.GONE);
                yellow_right.setVisibility(View.GONE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.blue:
                    wrong.start();
                    blue_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.red:
                    wrong.start();
                    red_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.yellow:
                    correct.start();
                    yellow_right.setVisibility(View.VISIBLE);
                    hideView();
                    transition_anim();
                    break;
            }
        }
    }

}
