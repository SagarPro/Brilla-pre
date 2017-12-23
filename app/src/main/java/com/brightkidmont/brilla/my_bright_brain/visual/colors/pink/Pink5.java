package com.brightkidmont.brilla.my_bright_brain.visual.colors.pink;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.pink.PinkActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.pink.PinkActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.pink.PinkActivity.wrong;

public class Pink5 extends Fragment implements View.OnClickListener{

    private ImageView p1_right, p2_wrong, p3_right, p4_wrong, p5_right, p6_right;

    public Pink5(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.pink5, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Select pink dress children");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.f_pink));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);

        ImageView p1 = (ImageView) view.findViewById(R.id.p1);
        ImageView p2 = (ImageView) view.findViewById(R.id.p2);
        ImageView p3 = (ImageView) view.findViewById(R.id.p3);
        ImageView p4 = (ImageView) view.findViewById(R.id.p4);
        ImageView p5 = (ImageView) view.findViewById(R.id.p5);
        ImageView p6 = (ImageView) view.findViewById(R.id.p6);

        String image1, image2, image3, image4, image5, image6;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPink%2Fp10.png?alt=media&token=ffa9bb51-9d81-4d6c-bbb5-6fcdeca5dc32";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPink%2Fp12.png?alt=media&token=c5ac80f8-ef63-47f8-a49e-1a8e76b7af67";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPink%2Fp11.png?alt=media&token=69b4168d-73ab-496e-910c-2d4c79d29776";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPink%2Fp14.png?alt=media&token=320246ec-c6ea-48b5-bf0f-f726c45abeef";
        image5 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPink%2Fp13.png?alt=media&token=1b42d234-4d3f-4a11-acf4-95329f9bbdfb";
        image6 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPink%2Fp15.png?alt=media&token=62b3b586-ffc7-4401-8245-041e4a689587";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(p1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(p2);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(p3);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(p4);
        Picasso.with(getContext()).load(image5).error(R.drawable.bright_kid_bg).into(p5);
        Picasso.with(getContext()).load(image6).error(R.drawable.bright_kid_bg).into(p6);

        p1_right = (ImageView) view.findViewById(R.id.p1_right);
        p2_wrong = (ImageView) view.findViewById(R.id.p2_wrong);
        p3_right = (ImageView) view.findViewById(R.id.p3_right);
        p4_wrong = (ImageView) view.findViewById(R.id.p4_wrong);
        p5_right = (ImageView) view.findViewById(R.id.p5_right);
        p6_right = (ImageView) view.findViewById(R.id.p6_right);

        p1_right.setVisibility(View.INVISIBLE);
        p2_wrong.setVisibility(View.INVISIBLE);
        p3_right.setVisibility(View.INVISIBLE);
        p4_wrong.setVisibility(View.INVISIBLE);
        p5_right.setVisibility(View.INVISIBLE);
        p6_right.setVisibility(View.INVISIBLE);

        speaker.setOnClickListener(this);

        p1.setOnClickListener(this);
        p2.setOnClickListener(this);
        p3.setOnClickListener(this);
        p4.setOnClickListener(this);
        p5.setOnClickListener(this);
        p6.setOnClickListener(this);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                p1_right.setVisibility(View.INVISIBLE);
                p2_wrong.setVisibility(View.INVISIBLE);
                p3_right.setVisibility(View.INVISIBLE);
                p4_wrong.setVisibility(View.INVISIBLE);
                p5_right.setVisibility(View.INVISIBLE);
                p6_right.setVisibility(View.INVISIBLE);
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
                    break;
                case R.id.p2:
                    wrong.start();
                    p2_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.p3:
                    correct.start();
                    p3_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.p4:
                    wrong.start();
                    p4_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.p5:
                    correct.start();
                    p5_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.p6:
                    correct.start();
                    p6_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
