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

public class Green4 extends Fragment implements View.OnClickListener {

    private ImageView brown1;
    private ImageView green1;
    private ImageView red1;

    public Green4(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.green4, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Select the color of the Crocodile");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_emerald));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        ImageView crocodile = (ImageView) view.findViewById(R.id.crocodile);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FGreen%2Fcrocodile.png?alt=media&token=484bad34-3a0e-4c3f-ba1d-4d42e38cf041").error(R.drawable.bright_kid_bg).into(crocodile);

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
                    wrong.start();
                    brown1.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.green:
                    correct.start();
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
