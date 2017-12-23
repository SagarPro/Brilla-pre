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

public class Orange1 extends Fragment implements View.OnClickListener {

    private ImageView bheem1;
    private ImageView bheem2;
    private ImageView green_wrong;
    private ImageView orange_right;
    private ImageView yellow_wrong;

    public Orange1(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.orange1, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Color the bheem with orange");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_carrot));

        bheem1 = (ImageView) view.findViewById(R.id.bheem1);
        bheem2 = (ImageView) view.findViewById(R.id.bheem2);
        ImageView green_o = (ImageView) view.findViewById(R.id.green_o);
        ImageView orange_o = (ImageView) view.findViewById(R.id.orange_o);
        ImageView yellow_o = (ImageView) view.findViewById(R.id.yellow_o);
        green_wrong = (ImageView) view.findViewById(R.id.green_wrong);
        orange_right = (ImageView) view.findViewById(R.id.orange_right);
        yellow_wrong = (ImageView) view.findViewById(R.id.yellow_wrong);

        String image1, image2;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FOrange%2Fbheem1.png?alt=media&token=2d567a75-7700-47f5-8975-995d59215492";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FOrange%2Fbheem2.png?alt=media&token=fb93cf99-53a5-4a01-9082-0a1b171b4d70";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(bheem1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(bheem2);

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);

        bheem2.setVisibility(View.GONE);

        green_wrong.setVisibility(View.INVISIBLE);
        orange_right.setVisibility(View.INVISIBLE);
        yellow_wrong.setVisibility(View.INVISIBLE);

        green_o.setOnClickListener(this);
        orange_o.setOnClickListener(this);
        yellow_o.setOnClickListener(this);

        speaker.setOnClickListener(this);

        return view;
    }

    public void transition_anim(){
        Animation fade_out = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        bheem2.setVisibility(View.VISIBLE);
        bheem2.startAnimation(fade_out);
        Animation fade_in = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        bheem1.startAnimation(fade_in);
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                green_wrong.setVisibility(View.INVISIBLE);
                orange_right.setVisibility(View.INVISIBLE);
                yellow_wrong.setVisibility(View.INVISIBLE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.green_o:
                    wrong.start();
                    green_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.orange_o:
                    correct.start();
                    orange_right.setVisibility(View.VISIBLE);
                    hideView();
                    transition_anim();
                    break;
                case R.id.yellow_o:
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
