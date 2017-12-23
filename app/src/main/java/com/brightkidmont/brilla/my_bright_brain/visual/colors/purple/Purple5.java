package com.brightkidmont.brilla.my_bright_brain.visual.colors.purple;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.purple.PurpleActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.purple.PurpleActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.purple.PurpleActivity.wrong;

public class Purple5 extends Fragment implements View.OnClickListener {

    private ImageView pr1;
    private ImageView pr2;
    private ImageView black_wrong;
    private ImageView red_wrong;
    private ImageView purple_right;

    public Purple5(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.purple5, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Color the Brinjal with purple color");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        pr1 = (ImageView) view.findViewById(R.id.pr1);
        pr2 = (ImageView) view.findViewById(R.id.pr2);
        ImageView blackc = (ImageView) view.findViewById(R.id.blackc);
        ImageView redc = (ImageView) view.findViewById(R.id.redc);
        ImageView purplec = (ImageView) view.findViewById(R.id.purplec);
        black_wrong = (ImageView) view.findViewById(R.id.black_wrong);
        red_wrong = (ImageView) view.findViewById(R.id.red_wrong);
        purple_right = (ImageView) view.findViewById(R.id.purple_right);

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);

        String image1, image2;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPurple%2Fpr12.png?alt=media&token=4d557508-3d4f-4ea3-9656-c4fd5733db94";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPurple%2Fpr13.png?alt=media&token=1461cd61-ab82-4c51-a1b7-817c40b31d69";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(pr1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(pr2);

        pr2.setVisibility(View.GONE);

        black_wrong.setVisibility(View.GONE);
        red_wrong.setVisibility(View.GONE);
        purple_right.setVisibility(View.GONE);

        blackc.setOnClickListener(this);
        redc.setOnClickListener(this);
        purplec.setOnClickListener(this);

        speaker.setOnClickListener(this);

        return view;
    }

    public void transition_anim(){
        Animation fade_out = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        pr2.setVisibility(View.VISIBLE);
        pr2.startAnimation(fade_out);
        Animation fade_in = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        pr1.startAnimation(fade_in);
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                black_wrong.setVisibility(View.GONE);
                red_wrong.setVisibility(View.GONE);
                purple_right.setVisibility(View.GONE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.blackc:
                    wrong.start();
                    black_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.redc:
                    wrong.start();
                    red_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.purplec:
                    correct.start();
                    purple_right.setVisibility(View.VISIBLE);
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
