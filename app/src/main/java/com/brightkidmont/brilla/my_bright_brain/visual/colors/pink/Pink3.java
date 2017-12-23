package com.brightkidmont.brilla.my_bright_brain.visual.colors.pink;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.pink.PinkActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.pink.PinkActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.pink.PinkActivity.wrong;

public class Pink3 extends Fragment implements View.OnClickListener {

    private ImageView barbie1;
    private ImageView barbie2;
    private ImageView p1_right;
    private ImageView p2_wrong;
    private ImageView p3_wrong;

    public Pink3(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.pink3, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Select pink colored dress for Barbie");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.f_pink));

        barbie1 = (ImageView) view.findViewById(R.id.barbie1);
        barbie2 = (ImageView) view.findViewById(R.id.barbie2);
        ImageView p1 = (ImageView) view.findViewById(R.id.p1);
        ImageView p2 = (ImageView) view.findViewById(R.id.p2);
        ImageView p3 = (ImageView) view.findViewById(R.id.p3);
        p1_right = (ImageView) view.findViewById(R.id.p1_right);
        p2_wrong = (ImageView) view.findViewById(R.id.p2_wrong);
        p3_wrong = (ImageView) view.findViewById(R.id.p3_wrong);

        String image1, image2, image3, image4, image5;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPink%2Fp16.png?alt=media&token=eecd4e5e-fba6-4f41-ac42-1108f4bd8aae";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPink%2Fp20.png?alt=media&token=1b64a1c1-a8a2-44e4-8f65-b6ec20f70df0";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPink%2Fp17.png?alt=media&token=4e5361c1-3b3a-412c-b014-722e694593e5";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPink%2Fp18.png?alt=media&token=4d7abf66-1ae6-4eac-973b-a92c1405d6af";
        image5 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPink%2Fp19.png?alt=media&token=25cf2bea-ec8d-4705-b12f-08fc892ae516";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(barbie1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(barbie2);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(p1);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(p2);
        Picasso.with(getContext()).load(image5).error(R.drawable.bright_kid_bg).into(p3);

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);

        barbie2.setVisibility(View.GONE);

        p1_right.setVisibility(View.INVISIBLE);
        p2_wrong.setVisibility(View.INVISIBLE);
        p3_wrong.setVisibility(View.INVISIBLE);

        p1.setOnClickListener(this);
        p2.setOnClickListener(this);
        p3.setOnClickListener(this);

        speaker.setOnClickListener(this);

        return view;
    }

    public void transition_anim(){
        Animation fade_out = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        barbie2.setVisibility(View.VISIBLE);
        barbie2.startAnimation(fade_out);
        Animation fade_in = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        barbie1.startAnimation(fade_in);
        barbie1.setVisibility(View.INVISIBLE);
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                p1_right.setVisibility(View.INVISIBLE);
                p2_wrong.setVisibility(View.INVISIBLE);
                p3_wrong.setVisibility(View.INVISIBLE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.p1:
                    correct.start();
                    p1_right.setVisibility(View.VISIBLE);
                    hideView();
                    transition_anim();
                    break;
                case R.id.p2:
                    wrong.start();
                    p2_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.p3:
                    wrong.start();
                    p3_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
