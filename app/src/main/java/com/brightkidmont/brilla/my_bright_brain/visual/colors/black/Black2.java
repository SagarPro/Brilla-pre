package com.brightkidmont.brilla.my_bright_brain.visual.colors.black;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.black.BlackActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.black.BlackActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.black.BlackActivity.wrong;

public class Black2 extends Fragment implements View.OnClickListener {

    private ImageView ivPurple;
    private ImageView ivPink;
    private ImageView ivYellow;
    private ImageView ivBlack;

    public Black2(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.black2, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Select the color of the car");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.f_black));

        TextView tvPurple = (TextView) view.findViewById(R.id.tvPurple);
        TextView tvPink = (TextView) view.findViewById(R.id.tvPink);
        TextView tvYellow = (TextView) view.findViewById(R.id.tvYellow);
        TextView tvBlack = (TextView) view.findViewById(R.id.tvBlack);

        ivPurple = (ImageView) view.findViewById(R.id.ivPurple);
        ivPink = (ImageView) view.findViewById(R.id.ivPink);
        ivYellow = (ImageView) view.findViewById(R.id.ivYellow);
        ivBlack = (ImageView) view.findViewById(R.id.ivBlack);

        ImageView ivCar = (ImageView) view.findViewById(R.id.ivCar);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlack%2Fcar.png?alt=media&token=0e891001-2fcd-419c-90f4-ffd7c0c72147").error(R.drawable.bright_kid_bg).into(ivCar);

        ivPurple.setVisibility(View.INVISIBLE);
        ivPink.setVisibility(View.INVISIBLE);
        ivYellow.setVisibility(View.INVISIBLE);
        ivBlack.setVisibility(View.INVISIBLE);

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);

        speaker.setOnClickListener(this);

        tvPurple.setOnClickListener(this);
        tvPink.setOnClickListener(this);
        tvYellow.setOnClickListener(this);
        tvBlack.setOnClickListener(this);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ivPurple.setVisibility(View.INVISIBLE);
                ivPink.setVisibility(View.INVISIBLE);
                ivYellow.setVisibility(View.INVISIBLE);
                ivBlack.setVisibility(View.INVISIBLE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.tvPurple:
                    wrong.start();
                    ivPurple.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.tvPink:
                    ivPink.setVisibility(View.VISIBLE);
                    wrong.start();
                    hideView();
                    break;
                case R.id.tvYellow:
                    wrong.start();
                    ivYellow.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.tvBlack:
                    correct.start();
                    ivBlack.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
