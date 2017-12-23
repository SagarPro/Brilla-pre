package com.brightkidmont.brilla.my_bright_brain.visual.colors.red;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.red.RedActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.red.RedActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.red.RedActivity.wrong;

public class Red1 extends Fragment implements View.OnClickListener {

    private ImageView red_bird1;
    private ImageView red_bird2;
    private ImageView blue_wrong;
    private ImageView red_right;
    private ImageView green_wrong;

    public Red1(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.red1, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Color the bird with red color");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_alizarin));

        red_bird1 = (ImageView) view.findViewById(R.id.red_bird1);
        red_bird2 = (ImageView) view.findViewById(R.id.red_bird2);
        ImageView blue_c = (ImageView) view.findViewById(R.id.blue_c);
        ImageView red_c = (ImageView) view.findViewById(R.id.red_c);
        ImageView green_c = (ImageView) view.findViewById(R.id.green_c);
        blue_wrong = (ImageView) view.findViewById(R.id.blue_wrong);
        red_right = (ImageView) view.findViewById(R.id.red_right);
        green_wrong = (ImageView) view.findViewById(R.id.green_wrong);

        String image1, image2;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FRed%2Fred_bird1.png?alt=media&token=eb247657-9ca9-4686-9302-35a98f6ea370";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FRed%2Fred_bird2.png?alt=media&token=7505552d-7398-44b4-a554-fb89c1155a21";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(red_bird1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(red_bird2);

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q.start();
            }
        });

        red_bird2.setVisibility(View.GONE);

        blue_wrong.setVisibility(View.GONE);
        red_right.setVisibility(View.GONE);
        green_wrong.setVisibility(View.GONE);

        blue_c.setOnClickListener(this);
        red_c.setOnClickListener(this);
        green_c.setOnClickListener(this);

        return view;
    }

    public void transition_anim(){
        Animation fade_out = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        red_bird2.setVisibility(View.VISIBLE);
        red_bird2.startAnimation(fade_out);
        Animation fade_in = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        red_bird1.startAnimation(fade_in);
        red_bird1.setVisibility(View.INVISIBLE);
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                blue_wrong.setVisibility(View.GONE);
                red_right.setVisibility(View.GONE);
                green_wrong.setVisibility(View.GONE);
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
                    correct.start();
                    red_right.setVisibility(View.VISIBLE);
                    hideView();
                    transition_anim();
                    break;
                case R.id.green_c:
                    wrong.start();
                    green_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
            }
        }
    }

}
