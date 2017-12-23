package com.brightkidmont.brilla.my_bright_brain.visual.colors.brown;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.brown.BrownActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.brown.BrownActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.brown.BrownActivity.wrong;

public class Brown1 extends Fragment implements View.OnClickListener{

    private ImageView ivPurple, ivPink, ivBrown, ivBlack;

    public Brown1(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.brown1, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Identify the color given below");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.brown));

        TextView tvPurple = (TextView) view.findViewById(R.id.tvPurple);
        TextView tvPink = (TextView) view.findViewById(R.id.tvPink);
        TextView tvBrown = (TextView) view.findViewById(R.id.tvBrown);
        TextView tvBlack = (TextView) view.findViewById(R.id.tvBlack);

        ivPurple = (ImageView) view.findViewById(R.id.ivPurple);
        ivPink = (ImageView) view.findViewById(R.id.ivPink);
        ivBrown = (ImageView) view.findViewById(R.id.ivBrown);
        ivBlack = (ImageView) view.findViewById(R.id.ivBlack);

        ivPurple.setVisibility(View.INVISIBLE);
        ivPink.setVisibility(View.INVISIBLE);
        ivBrown.setVisibility(View.INVISIBLE);
        ivBlack.setVisibility(View.INVISIBLE);

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        tvPurple.setOnClickListener(this);
        tvPink.setOnClickListener(this);
        tvBrown.setOnClickListener(this);
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
                ivBrown.setVisibility(View.INVISIBLE);
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
                case R.id.tvBrown:
                    correct.start();
                    ivBrown.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.tvBlack:
                    wrong.start();
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
