package com.brightkidmont.brilla.my_bright_brain.visual.colors.white;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.white.WhiteActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.white.WhiteActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.white.WhiteActivity.wrong;

public class White3 extends Fragment implements View.OnClickListener {

    private ImageView ivw1, ivw2, ivw3, ivw4, ivw5, ivw6;

    public White3(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.white3, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Select the white colored Animals");
        flash_header.setTextColor(getResources().getColor(R.color.f_black));

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_clouds));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setImageResource(R.drawable.speaker_b);
        speaker.setOnClickListener(this);

        ImageView w1 = (ImageView) view.findViewById(R.id.w1);
        ImageView w2 = (ImageView) view.findViewById(R.id.w2);
        ImageView w3 = (ImageView) view.findViewById(R.id.w3);
        ImageView w4 = (ImageView) view.findViewById(R.id.w4);
        ImageView w5 = (ImageView) view.findViewById(R.id.w5);
        ImageView w6 = (ImageView) view.findViewById(R.id.w6);

        String image1, image2, image3, image4, image5, image6;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FWhite%2Fw6.png?alt=media&token=1693f031-cec5-455d-aed9-6bc396c32bb1";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FWhite%2Fw7.png?alt=media&token=333901aa-77a9-4270-87a8-b4ad8d8bc1f4";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FWhite%2Fw8.png?alt=media&token=68cf3923-e9c4-4369-8788-13545cd38833";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FWhite%2Fw9.png?alt=media&token=c3308784-d9a6-4299-babc-c59689cc310d";
        image5 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FWhite%2Fw10.png?alt=media&token=9e296fa9-514a-47de-b7ec-b314d7dfe015";
        image6 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FWhite%2Fw11.png?alt=media&token=6d2e2042-cbb2-4da0-83de-b232459159ee";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(w1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(w2);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(w3);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(w4);
        Picasso.with(getContext()).load(image5).error(R.drawable.bright_kid_bg).into(w5);
        Picasso.with(getContext()).load(image6).error(R.drawable.bright_kid_bg).into(w6);

        ivw1 = (ImageView) view.findViewById(R.id.ivw1);
        ivw2 = (ImageView) view.findViewById(R.id.ivw2);
        ivw3 = (ImageView) view.findViewById(R.id.ivw3);
        ivw4 = (ImageView) view.findViewById(R.id.ivw4);
        ivw5 = (ImageView) view.findViewById(R.id.ivw5);
        ivw6 = (ImageView) view.findViewById(R.id.ivw6);

        hide();

        w1.setOnClickListener(this);
        w2.setOnClickListener(this);
        w3.setOnClickListener(this);
        w4.setOnClickListener(this);
        w5.setOnClickListener(this);
        w6.setOnClickListener(this);

        return view;
    }

    public void hide(){
        ivw1.setVisibility(View.INVISIBLE);
        ivw2.setVisibility(View.INVISIBLE);
        ivw3.setVisibility(View.INVISIBLE);
        ivw4.setVisibility(View.INVISIBLE);
        ivw5.setVisibility(View.INVISIBLE);
        ivw6.setVisibility(View.INVISIBLE);
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hide();
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.w1:
                    wrong.start();
                    ivw1.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.w2:
                    correct.start();
                    ivw2.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.w3:
                    wrong.start();
                    ivw3.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.w4:
                    correct.start();
                    ivw4.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.w5:
                    wrong.start();
                    ivw5.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.w6:
                    correct.start();
                    ivw6.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
