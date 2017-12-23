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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.circle.CircleActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.circle.CircleActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.circle.CircleActivity.wrong;


public class Circle2 extends Fragment implements View.OnClickListener{

    private ImageView tv_stand, wall_clock, mirror, cuboard, television, frame, ivLamp, chair, image1, image2, image3;

    public Circle2(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.circle2, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Identify all the objects which are in the shape of a circle in the living room");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        tv_stand = (ImageView) view.findViewById(R.id.tv_stand);
        wall_clock = (ImageView) view.findViewById(R.id.wall_clock);
        mirror = (ImageView) view.findViewById(R.id.mirror);
        cuboard = (ImageView) view.findViewById(R.id.cuboard);
        television = (ImageView) view.findViewById(R.id.television);
        frame = (ImageView) view.findViewById(R.id.frame);
        ivLamp = (ImageView) view.findViewById(R.id.ivLamp);
        chair = (ImageView) view.findViewById(R.id.chair);
        image1 = (ImageView) view.findViewById(R.id.image1);
        image2 = (ImageView) view.findViewById(R.id.image2);
        image3 = (ImageView) view.findViewById(R.id.image3);

        tv_stand.setOnClickListener(this);
        wall_clock.setOnClickListener(this);
        mirror.setOnClickListener(this);
        cuboard.setOnClickListener(this);
        television.setOnClickListener(this);
        frame.setOnClickListener(this);
        ivLamp.setOnClickListener(this);
        chair.setOnClickListener(this);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                chair.setImageResource(android.R.color.transparent);
                frame.setImageResource(android.R.color.transparent);
                television.setImageResource(android.R.color.transparent);
                cuboard.setImageResource(android.R.color.transparent);
                tv_stand.setImageResource(android.R.color.transparent);
            }
        },1000);
    }

    //onClick diplay appropriate image
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.tv_stand:
                    wrong.start();
                    tv_stand.setImageResource(R.drawable.wrong);
                    hideView();
                    break;
                case R.id.wall_clock:
                    correct.start();
                    wall_clock.setVisibility(View.INVISIBLE);
                    image1.setImageResource(R.drawable.wall_clock);
                    break;
                case R.id.mirror:
                    correct.start();
                    mirror.setVisibility(View.INVISIBLE);
                    image2.setImageResource(R.drawable.mirror);
                    break;
                case R.id.cuboard:
                    wrong.start();
                    cuboard.setImageResource(R.drawable.wrong);
                    hideView();
                    break;
                case R.id.television:
                    wrong.start();
                    television.setImageResource(R.drawable.wrong);
                    hideView();
                    break;
                case R.id.frame:
                    wrong.start();
                    frame.setImageResource(R.drawable.wrong);
                    hideView();
                    break;
                case R.id.ivLamp:
                    correct.start();
                    ivLamp.setVisibility(View.INVISIBLE);
                    image3.setImageResource(R.drawable.lamp);
                    break;
                case R.id.chair:
                    wrong.start();
                    chair.setImageResource(R.drawable.wrong);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }
}
