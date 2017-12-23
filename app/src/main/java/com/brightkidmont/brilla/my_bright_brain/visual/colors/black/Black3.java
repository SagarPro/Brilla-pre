package com.brightkidmont.brilla.my_bright_brain.visual.colors.black;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.black.BlackActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.black.BlackActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.black.BlackActivity.wrong;

public class Black3 extends Fragment implements View.OnClickListener {

    private ImageView crow1;
    private ImageView crow2;
    private ImageView black_right;
    private ImageView red_wrong;
    private ImageView blue_wrong;

    public Black3(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.black3, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Color the crow with black color");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.f_black));

        crow1 = (ImageView) view.findViewById(R.id.crow1);
        crow2 = (ImageView) view.findViewById(R.id.crow2);
        ImageView blackc = (ImageView) view.findViewById(R.id.blackc);
        ImageView redc = (ImageView) view.findViewById(R.id.redc);
        ImageView bluec = (ImageView) view.findViewById(R.id.bluec);
        black_right = (ImageView) view.findViewById(R.id.black_right);
        red_wrong = (ImageView) view.findViewById(R.id.red_wrong);
        blue_wrong = (ImageView) view.findViewById(R.id.blue_wrong);

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);

        String image1, image2;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlack%2Fcrow1.png?alt=media&token=b6005247-cecc-48d6-ba6b-be7b503e41d9";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlack%2Fcrow2.png?alt=media&token=4bb2a16f-7172-45c4-9ab5-52b25d19f972";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(crow1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(crow2);

        crow2.setVisibility(View.GONE);

        black_right.setVisibility(View.GONE);
        red_wrong.setVisibility(View.GONE);
        blue_wrong.setVisibility(View.GONE);

        blackc.setOnClickListener(this);
        redc.setOnClickListener(this);
        bluec.setOnClickListener(this);

        speaker.setOnClickListener(this);

        return view;
    }

    public void transition_anim(){
        Animation fade_out = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        crow2.setVisibility(View.VISIBLE);
        crow2.startAnimation(fade_out);
        Animation fade_in = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        crow1.startAnimation(fade_in);
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                black_right.setVisibility(View.GONE);
                red_wrong.setVisibility(View.GONE);
                blue_wrong.setVisibility(View.GONE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.blackc:
                    correct.start();
                    black_right.setVisibility(View.VISIBLE);
                    hideView();
                    transition_anim();
                    break;
                case R.id.redc:
                    wrong.start();
                    red_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.bluec:
                    wrong.start();
                    blue_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
