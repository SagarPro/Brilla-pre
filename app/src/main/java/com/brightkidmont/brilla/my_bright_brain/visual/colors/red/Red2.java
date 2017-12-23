package com.brightkidmont.brilla.my_bright_brain.visual.colors.red;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.red.RedActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.red.RedActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.red.RedActivity.wrong;

public class Red2 extends Fragment implements View.OnClickListener {

    private ImageView f1_wrong, f2_right, f3_wrong, f4_right, f5_wrong, f6_right;

    public Red2(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.red2, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Find red colored fruits");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_alizarin));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q.start();
            }
        });

        ImageView f1 = (ImageView) view.findViewById(R.id.f1);
        ImageView f2 = (ImageView) view.findViewById(R.id.f2);
        ImageView f3 = (ImageView) view.findViewById(R.id.f3);
        ImageView f4 = (ImageView) view.findViewById(R.id.f4);
        ImageView f5 = (ImageView) view.findViewById(R.id.f5);
        ImageView f6 = (ImageView) view.findViewById(R.id.f6);

        String image1, image2, image3, image4, image5, image6;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FRed%2Ff1.png?alt=media&token=63194b1f-554a-41bc-8eeb-aa333b8c7016";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FRed%2Ff2.png?alt=media&token=514969f3-ac3d-45ae-8ea3-dc9f196a61cf";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FRed%2Ff3.png?alt=media&token=d448cd06-7e86-4838-9a57-32b3a7bd8167";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FRed%2Ff4.png?alt=media&token=6b03739d-fa81-435a-bd10-3090f86d8f3a";
        image5 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FRed%2Ff5.png?alt=media&token=dd54da66-e874-4401-ba80-639a5410aa4b";
        image6 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FRed%2Fstrawberry.png?alt=media&token=ee930535-8868-4d6e-91ba-9cb10feb1fe0";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(f1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(f2);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(f3);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(f4);
        Picasso.with(getContext()).load(image5).error(R.drawable.bright_kid_bg).into(f5);
        Picasso.with(getContext()).load(image6).error(R.drawable.bright_kid_bg).into(f6);

        f1_wrong = (ImageView) view.findViewById(R.id.f1_wrong);
        f2_right = (ImageView) view.findViewById(R.id.f2_right);
        f3_wrong = (ImageView) view.findViewById(R.id.f3_wrong);
        f4_right = (ImageView) view.findViewById(R.id.f4_right);
        f5_wrong = (ImageView) view.findViewById(R.id.f5_right);
        f6_right = (ImageView) view.findViewById(R.id.f6_right);

        f1_wrong.setVisibility(View.INVISIBLE);
        f2_right.setVisibility(View.INVISIBLE);
        f3_wrong.setVisibility(View.INVISIBLE);
        f4_right.setVisibility(View.INVISIBLE);
        f5_wrong.setVisibility(View.INVISIBLE);
        f6_right.setVisibility(View.INVISIBLE);

        f1.setOnClickListener(this);
        f2.setOnClickListener(this);
        f3.setOnClickListener(this);
        f4.setOnClickListener(this);
        f5.setOnClickListener(this);
        f6.setOnClickListener(this);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                f1_wrong.setVisibility(View.INVISIBLE);
                f2_right.setVisibility(View.INVISIBLE);
                f3_wrong.setVisibility(View.INVISIBLE);
                f4_right.setVisibility(View.INVISIBLE);
                f5_wrong.setVisibility(View.INVISIBLE);
                f6_right.setVisibility(View.INVISIBLE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.f1:
                    wrong.start();
                    f1_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.f2:
                    correct.start();
                    f2_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.f3:
                    wrong.start();
                    f3_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.f4:
                    correct.start();
                    f4_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.f5:
                    wrong.start();
                    f5_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.f6:
                    correct.start();
                    f6_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
            }
        }
    }

}
