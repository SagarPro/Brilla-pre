package com.brightkidmont.brilla.my_bright_brain.visual.colors.blue;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.blue.BlueActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.blue.BlueActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.blue.BlueActivity.wrong;

public class Blue5 extends Fragment implements View.OnClickListener {

    private ImageView ivPurple, ivPink, ivYellow, ivBlue;
    View view;

    public Blue5(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.blue5, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("What is the color of the fish given below");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_belize_hole));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        ImageView dory = (ImageView) view.findViewById(R.id.dory);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlue%2Fdory.png?alt=media&token=c2e20bde-1b3f-4157-b159-164148acb15b").error(R.drawable.bright_kid_bg).into(dory);

        TextView tvPurple = (TextView) view.findViewById(R.id.tvPurple);
        TextView tvPink = (TextView) view.findViewById(R.id.tvPink);
        TextView tvYellow = (TextView) view.findViewById(R.id.tvYellow);
        TextView tvBlue = (TextView) view.findViewById(R.id.tvBlue);

        ivPurple = (ImageView) view.findViewById(R.id.ivPurple);
        ivPink = (ImageView) view.findViewById(R.id.ivPink);
        ivYellow = (ImageView) view.findViewById(R.id.ivYellow);
        ivBlue = (ImageView) view.findViewById(R.id.ivBlue);

        ivPurple.setVisibility(View.INVISIBLE);
        ivPink.setVisibility(View.INVISIBLE);
        ivYellow.setVisibility(View.INVISIBLE);
        ivBlue.setVisibility(View.INVISIBLE);

        tvPurple.setOnClickListener(this);
        tvPink.setOnClickListener(this);
        tvYellow.setOnClickListener(this);
        tvBlue.setOnClickListener(this);

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
                ivBlue.setVisibility(View.INVISIBLE);
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
                case R.id.tvBlue:
                    correct.start();
                    ivBlue.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
