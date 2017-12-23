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
import com.squareup.picasso.Picasso;

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.brown.BrownActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.brown.BrownActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.brown.BrownActivity.wrong;

public class Brown3 extends Fragment implements View.OnClickListener {

    private ImageView brown1;
    private ImageView green1;
    private ImageView red1;

    public Brown3(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.brown3, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Select the color of the owl");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.brown));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        ImageView owl = (ImageView) view.findViewById(R.id.owl);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBrown%2Fowl.png?alt=media&token=f470e338-02fe-4592-acab-3f8c63625f7f").error(R.drawable.bright_kid_bg).into(owl);

        ImageView brown = (ImageView) view.findViewById(R.id.brown);
        ImageView green = (ImageView) view.findViewById(R.id.green);
        ImageView red = (ImageView) view.findViewById(R.id.red);

        brown.setOnClickListener(this);
        green.setOnClickListener(this);
        red.setOnClickListener(this);

        brown1 = (ImageView) view.findViewById(R.id.brown1);
        green1 = (ImageView) view.findViewById(R.id.green1);
        red1 = (ImageView) view.findViewById(R.id.red1);

        brown1.setVisibility(View.INVISIBLE);
        green1.setVisibility(View.INVISIBLE);
        red1.setVisibility(View.INVISIBLE);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                brown1.setVisibility(View.INVISIBLE);
                green1.setVisibility(View.INVISIBLE);
                red1.setVisibility(View.INVISIBLE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.brown:
                    correct.start();
                    brown1.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.green:
                    wrong.start();
                    green1.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.red:
                    wrong.start();
                    red1.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }
}
