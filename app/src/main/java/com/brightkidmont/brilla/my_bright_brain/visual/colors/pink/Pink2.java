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

public class Pink2 extends Fragment implements View.OnClickListener {

    private ImageView p1_right, p2_wrong, p3_right, p4_wrong, p5_right, p6_wrong;

    public Pink2(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.pink2, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Identify pink colored objects");

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
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPink%2Fp6.png?alt=media&token=294d116f-dc5a-454a-8107-7c48bb225a50";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPink%2Fp7.png?alt=media&token=b7fc7754-a80a-4365-a678-664e93a68aea";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPink%2Fp8.png?alt=media&token=65f0b2fe-94bc-419c-9aef-db1ffb3d239b";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlack%2Fb4.png?alt=media&token=448cb83d-93b6-439e-b960-cb6010efa59b";
        image5 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPink%2Fp9.png?alt=media&token=1f8ac261-9a51-41d8-9379-77900a7f19f9";
        image6 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlack%2Fb6.png?alt=media&token=cfbc4a9a-b82c-4ebb-ac5e-9f5f5205821b";

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
        p6_wrong = (ImageView) view.findViewById(R.id.p6_wrong);

        p1_right.setVisibility(View.INVISIBLE);
        p2_wrong.setVisibility(View.INVISIBLE);
        p3_right.setVisibility(View.INVISIBLE);
        p4_wrong.setVisibility(View.INVISIBLE);
        p5_right.setVisibility(View.INVISIBLE);
        p6_wrong.setVisibility(View.INVISIBLE);

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
                p6_wrong.setVisibility(View.INVISIBLE);
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
                    wrong.start();
                    p6_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
