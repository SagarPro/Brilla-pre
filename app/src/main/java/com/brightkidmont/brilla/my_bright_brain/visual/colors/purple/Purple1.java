package com.brightkidmont.brilla.my_bright_brain.visual.colors.purple;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.purple.PurpleActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.purple.PurpleActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.purple.PurpleActivity.wrong;

public class Purple1 extends Fragment implements View.OnClickListener{

    private ImageView pr1_right, pr2_wrong, pr3_right, pr4_wrong, pr5_right, pr6_wrong;

    public Purple1(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.purple1, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Select the purple colored fruits");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);

        ImageView pr1 = (ImageView) view.findViewById(R.id.pr1);
        ImageView pr2 = (ImageView) view.findViewById(R.id.pr2);
        ImageView pr3 = (ImageView) view.findViewById(R.id.pr3);
        ImageView pr4 = (ImageView) view.findViewById(R.id.pr4);
        ImageView pr5 = (ImageView) view.findViewById(R.id.pr5);
        ImageView pr6 = (ImageView) view.findViewById(R.id.pr6);

        String image1, image2, image3, image4, image5, image6;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPurple%2Fpr1.png?alt=media&token=5d23db7f-583b-4d61-aa8d-15890b6bcabb";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FRed%2Ff1.png?alt=media&token=63194b1f-554a-41bc-8eeb-aa333b8c7016";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPurple%2Fpr5.png?alt=media&token=ea63418e-baa3-4aa6-84cb-2c5046527197";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FRed%2Ff2.png?alt=media&token=514969f3-ac3d-45ae-8ea3-dc9f196a61cf";
        image5 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FRed%2Ff3.png?alt=media&token=d448cd06-7e86-4838-9a57-32b3a7bd8167";
        image6 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FRed%2Ff4.png?alt=media&token=6b03739d-fa81-435a-bd10-3090f86d8f3a";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(pr1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(pr2);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(pr3);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(pr4);
        Picasso.with(getContext()).load(image5).error(R.drawable.bright_kid_bg).into(pr5);
        Picasso.with(getContext()).load(image6).error(R.drawable.bright_kid_bg).into(pr6);

        pr1_right = (ImageView) view.findViewById(R.id.pr1_right);
        pr2_wrong = (ImageView) view.findViewById(R.id.pr2_wrong);
        pr3_right = (ImageView) view.findViewById(R.id.pr3_right);
        pr4_wrong = (ImageView) view.findViewById(R.id.pr4_wrong);
        pr5_right = (ImageView) view.findViewById(R.id.pr5_right);
        pr6_wrong = (ImageView) view.findViewById(R.id.pr6_wrong);

        pr1_right.setVisibility(View.INVISIBLE);
        pr2_wrong.setVisibility(View.INVISIBLE);
        pr3_right.setVisibility(View.INVISIBLE);
        pr4_wrong.setVisibility(View.INVISIBLE);
        pr5_right.setVisibility(View.INVISIBLE);
        pr6_wrong.setVisibility(View.INVISIBLE);

        speaker.setOnClickListener(this);

        pr1.setOnClickListener(this);
        pr2.setOnClickListener(this);
        pr3.setOnClickListener(this);
        pr4.setOnClickListener(this);
        pr5.setOnClickListener(this);
        pr6.setOnClickListener(this);

        return view;

    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pr1_right.setVisibility(View.INVISIBLE);
                pr2_wrong.setVisibility(View.INVISIBLE);
                pr3_right.setVisibility(View.INVISIBLE);
                pr4_wrong.setVisibility(View.INVISIBLE);
                pr5_right.setVisibility(View.INVISIBLE);
                pr6_wrong.setVisibility(View.INVISIBLE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.pr1:
                    correct.start();
                    pr1_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.pr2:
                    wrong.start();
                    pr2_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.pr3:
                    correct.start();
                    pr3_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.pr4:
                    wrong.start();
                    pr4_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.pr5:
                    correct.start();
                    pr5_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.pr6:
                    wrong.start();
                    pr6_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
