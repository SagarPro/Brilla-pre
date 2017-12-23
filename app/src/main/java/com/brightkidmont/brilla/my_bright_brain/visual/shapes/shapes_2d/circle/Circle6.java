package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.circle;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.circle.CircleActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.circle.CircleActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.circle.CircleActivity.wrong;

public class Circle6 extends Fragment implements View.OnClickListener{
    private ImageView ivRectangle, ivTriangle, ivCircle, ivSquare;

    public Circle6(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.circle6, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("What is the name of this shape");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        ImageView circle = (ImageView) view.findViewById(R.id.circle);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Fcircle.png?alt=media&token=23e0cf7e-fb40-4f89-b796-24b75c9d2a31").error(R.drawable.bright_kid_bg).into(circle);

        TextView tvRectangle = (TextView) view.findViewById(R.id.tvRectangle);
        TextView tvTriangle = (TextView) view.findViewById(R.id.tvTriangle);
        TextView tvCircle = (TextView) view.findViewById(R.id.tvCircle);
        TextView tvSquare = (TextView) view.findViewById(R.id.tvSquare);

        ivRectangle = (ImageView) view.findViewById(R.id.ivRectangle);
        ivTriangle = (ImageView) view.findViewById(R.id.ivTriangle);
        ivCircle = (ImageView) view.findViewById(R.id.ivCircle);
        ivSquare = (ImageView) view.findViewById(R.id.ivSquare);

        ivRectangle.setVisibility(View.INVISIBLE);
        ivTriangle.setVisibility(View.INVISIBLE);
        ivCircle.setVisibility(View.INVISIBLE);
        ivSquare.setVisibility(View.INVISIBLE);

        tvRectangle.setOnClickListener(this);
        tvTriangle.setOnClickListener(this);
        tvCircle.setOnClickListener(this);
        tvSquare.setOnClickListener(this);

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
                ivSquare.setVisibility(View.INVISIBLE);
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
                    correct.start();
                    ivCircle.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.tvSquare:
                    wrong.start();
                    ivSquare.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }
}
