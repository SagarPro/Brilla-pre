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

public class Pink4 extends Fragment implements View.OnClickListener{

    private ImageView brown1;
    private ImageView pink1;
    private ImageView red1;

    public Pink4(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.pink4, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Select the color of the Teddy");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.f_pink));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        ImageView teddy = (ImageView) view.findViewById(R.id.teddy);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPink%2Fp6.png?alt=media&token=294d116f-dc5a-454a-8107-7c48bb225a50").error(R.drawable.bright_kid_bg).into(teddy);

        ImageView brown = (ImageView) view.findViewById(R.id.brown);
        ImageView pink = (ImageView) view.findViewById(R.id.pink);
        ImageView red = (ImageView) view.findViewById(R.id.red);

        brown.setOnClickListener(this);
        pink.setOnClickListener(this);
        red.setOnClickListener(this);

        brown1 = (ImageView) view.findViewById(R.id.brown1);
        pink1 = (ImageView) view.findViewById(R.id.pink1);
        red1 = (ImageView) view.findViewById(R.id.red1);

        brown1.setVisibility(View.INVISIBLE);
        pink1.setVisibility(View.INVISIBLE);
        red1.setVisibility(View.INVISIBLE);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                brown1.setVisibility(View.INVISIBLE);
                pink1.setVisibility(View.INVISIBLE);
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
                case R.id.pink:
                    correct.start();
                    pink1.setVisibility(View.VISIBLE);
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
