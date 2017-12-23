package com.brightkidmont.brilla.my_bright_brain.visual.colors.green;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.green.GreenActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.green.GreenActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.green.GreenActivity.wrong;

public class Green3 extends Fragment implements View.OnClickListener {

    private ImageView ivPurple, ivGreen, ivYellow, ivBlack;

    public Green3(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.green3, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Select the name of the color of Peacock tail feathers");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_emerald));

        ImageView peacock = (ImageView) view.findViewById(R.id.peacock);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FGreen%2Fg4.png?alt=media&token=593873c8-eb72-40d6-bc33-378a5cf1b2e5").error(R.drawable.bright_kid_bg).into(peacock);

        TextView tvPurple = (TextView) view.findViewById(R.id.tvPurple);
        TextView tvGreen = (TextView) view.findViewById(R.id.tvGreen);
        TextView tvYellow = (TextView) view.findViewById(R.id.tvYellow);
        TextView tvBlack = (TextView) view.findViewById(R.id.tvBlack);

        ivPurple = (ImageView) view.findViewById(R.id.ivPurple);
        ivGreen = (ImageView) view.findViewById(R.id.ivGreen);
        ivYellow = (ImageView) view.findViewById(R.id.ivYellow);
        ivBlack = (ImageView) view.findViewById(R.id.ivBlack);

        ivPurple.setVisibility(View.INVISIBLE);
        ivGreen.setVisibility(View.INVISIBLE);
        ivYellow.setVisibility(View.INVISIBLE);
        ivBlack.setVisibility(View.INVISIBLE);

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);

        speaker.setOnClickListener(this);

        tvPurple.setOnClickListener(this);
        tvGreen.setOnClickListener(this);
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
                ivGreen.setVisibility(View.INVISIBLE);
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
                case R.id.tvGreen:
                    ivGreen.setVisibility(View.VISIBLE);
                    correct.start();
                    hideView();
                    break;
                case R.id.tvYellow:
                    wrong.start();
                    ivYellow.setVisibility(View.VISIBLE);
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
