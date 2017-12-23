package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rhombus;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rhombus.RhombusActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rhombus.RhombusActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rhombus.RhombusActivity.wrong;

public class Rhombus2 extends Fragment implements View.OnClickListener{

    private ImageView ivRectangle, ivTriangle, ivCircle, ivRhombus;

    public Rhombus2(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.rhombus2, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("What is the name of this shape");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        ImageView rhombus = (ImageView) view.findViewById(R.id.rhombus);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FRhombus%2Frhombus.png?alt=media&token=17e5825c-062b-480a-aa49-2400d1935809").error(R.drawable.bright_kid_bg).into(rhombus);

        TextView tvRectangle = (TextView) view.findViewById(R.id.tvRectangle);
        TextView tvTriangle = (TextView) view.findViewById(R.id.tvTriangle);
        TextView tvCircle = (TextView) view.findViewById(R.id.tvCircle);
        TextView tvRhombus = (TextView) view.findViewById(R.id.tvRhombus);

        ivRectangle = (ImageView) view.findViewById(R.id.ivRectangle);
        ivTriangle = (ImageView) view.findViewById(R.id.ivTriangle);
        ivCircle = (ImageView) view.findViewById(R.id.ivCircle);
        ivRhombus = (ImageView) view.findViewById(R.id.ivRhombus);

        ivRectangle.setVisibility(View.INVISIBLE);
        ivTriangle.setVisibility(View.INVISIBLE);
        ivCircle.setVisibility(View.INVISIBLE);
        ivRhombus.setVisibility(View.INVISIBLE);

        tvRectangle.setOnClickListener(this);
        tvTriangle.setOnClickListener(this);
        tvCircle.setOnClickListener(this);
        tvRhombus.setOnClickListener(this);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ivRectangle.setVisibility(View.INVISIBLE);
                ivTriangle.setVisibility(View.INVISIBLE);
                ivCircle.setVisibility(View.INVISIBLE);
                ivRhombus.setVisibility(View.INVISIBLE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.tvRectangle:
                    wrong.start();
                    ivRectangle.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.tvTriangle:
                    ivTriangle.setVisibility(View.VISIBLE);
                    wrong.start();
                    hideView();
                    break;
                case R.id.tvCircle:
                    wrong.start();
                    ivCircle.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.tvRhombus:
                    correct.start();
                    ivRhombus.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
