package com.brightkidmont.brilla.my_bright_brain.visual.colors.orange;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.orange.OrangeActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.orange.OrangeActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.orange.OrangeActivity.wrong;

public class Orange3 extends Fragment implements View.OnClickListener {

    private ImageView rainbow2;
    private ImageView blue_wrong;
    private ImageView orange_right;
    private ImageView purple_wrong;
    private ImageView yellow_wrong;

    public Orange3(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.orange3, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Select the missing color in the rainbow");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_carrot));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);

        ImageView rainbow1 = (ImageView) view.findViewById(R.id.rainbow1);
        rainbow2 = (ImageView) view.findViewById(R.id.rainbow2);

        String image1, image2;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FOrange%2Frainbow_o1.png?alt=media&token=625043ff-2578-40c2-9192-7ea1025bbe99";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FOrange%2Frainbow_o2.png?alt=media&token=3e7fcd85-be04-4e8c-b4ac-5e498ab78bce";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(rainbow1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(rainbow2);

        rainbow2.setVisibility(View.GONE);

        ImageView blue = (ImageView) view.findViewById(R.id.blue);
        ImageView orange = (ImageView) view.findViewById(R.id.orange);
        ImageView purple = (ImageView) view.findViewById(R.id.purple);
        ImageView yellow = (ImageView) view.findViewById(R.id.yellow);

        blue_wrong = (ImageView) view.findViewById(R.id.blue_wrong);
        orange_right = (ImageView) view.findViewById(R.id.orange_right);
        purple_wrong = (ImageView) view.findViewById(R.id.purple_wrong);
        yellow_wrong = (ImageView) view.findViewById(R.id.yellow_wrong);

        blue_wrong.setVisibility(View.INVISIBLE);
        orange_right.setVisibility(View.INVISIBLE);
        purple_wrong.setVisibility(View.INVISIBLE);
        yellow_wrong.setVisibility(View.INVISIBLE);

        speaker.setOnClickListener(this);

        blue.setOnClickListener(this);
        orange.setOnClickListener(this);
        purple.setOnClickListener(this);
        yellow.setOnClickListener(this);

        return view;
    }

    public void transition_anim(){
        Animation fade_out = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        fade_out.setDuration(500);
        rainbow2.setVisibility(View.VISIBLE);
        rainbow2.startAnimation(fade_out);
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                blue_wrong.setVisibility(View.INVISIBLE);
                orange_right.setVisibility(View.INVISIBLE);
                purple_wrong.setVisibility(View.INVISIBLE);
                yellow_wrong.setVisibility(View.INVISIBLE);
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
                case R.id.orange:
                    correct.start();
                    orange_right.setVisibility(View.VISIBLE);
                    hideView();
                    transition_anim();
                    break;
                case R.id.purple:
                    wrong.start();
                    purple_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.yellow:
                    wrong.start();
                    yellow_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }
}
