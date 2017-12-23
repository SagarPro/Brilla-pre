package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rectangle;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rectangle.RectangleActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rectangle.RectangleActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rectangle.RectangleActivity.wrong;

public class Rectangle6 extends Fragment implements View.OnClickListener{

    private ImageView tv_stand_right, wall_clock_wrong, mirror_wrong, cuboard_right, television_right, frame_right, ivLamp_wrong, chair_wrong;

    public Rectangle6(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.rectangle6, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Identify all the objects which are in the shape of a rectangle in the living room");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        ImageView tv_stand = (ImageView) view.findViewById(R.id.tv_stand);
        ImageView wall_clock = (ImageView) view.findViewById(R.id.wall_clock);
        ImageView mirror = (ImageView) view.findViewById(R.id.mirror);
        ImageView cuboard = (ImageView) view.findViewById(R.id.cuboard);
        ImageView television = (ImageView) view.findViewById(R.id.television);
        ImageView frame = (ImageView) view.findViewById(R.id.frame);
        ImageView ivLamp = (ImageView) view.findViewById(R.id.ivLamp);
        ImageView chair = (ImageView) view.findViewById(R.id.chair);

        tv_stand.setOnClickListener(this);
        wall_clock.setOnClickListener(this);
        mirror.setOnClickListener(this);
        cuboard.setOnClickListener(this);
        television.setOnClickListener(this);
        frame.setOnClickListener(this);
        ivLamp.setOnClickListener(this);
        chair.setOnClickListener(this);

        tv_stand_right = (ImageView) view.findViewById(R.id.tv_stand_right);
        wall_clock_wrong = (ImageView) view.findViewById(R.id.wall_clock_wrong);
        mirror_wrong = (ImageView) view.findViewById(R.id.mirror_wrong);
        cuboard_right = (ImageView) view.findViewById(R.id.cuboard_right);
        television_right = (ImageView) view.findViewById(R.id.television_right);
        frame_right = (ImageView) view.findViewById(R.id.frame_right);
        ivLamp_wrong = (ImageView) view.findViewById(R.id.ivLamp_wrong);
        chair_wrong = (ImageView) view.findViewById(R.id.chair_wrong);

        tv_stand_right.setVisibility(View.GONE);
        wall_clock_wrong.setVisibility(View.GONE);
        mirror_wrong.setVisibility(View.GONE);
        cuboard_right.setVisibility(View.GONE);
        television_right.setVisibility(View.GONE);
        frame_right.setVisibility(View.GONE);
        ivLamp_wrong.setVisibility(View.GONE);
        chair_wrong.setVisibility(View.GONE);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_stand_right.setVisibility(View.GONE);
                wall_clock_wrong.setVisibility(View.GONE);
                mirror_wrong.setVisibility(View.GONE);
                cuboard_right.setVisibility(View.GONE);
                television_right.setVisibility(View.GONE);
                frame_right.setVisibility(View.GONE);
                ivLamp_wrong.setVisibility(View.GONE);
                chair_wrong.setVisibility(View.GONE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.tv_stand:
                    correct.start();
                    tv_stand_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.wall_clock:
                    wrong.start();
                    wall_clock_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.mirror:
                    wrong.start();
                    mirror_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.cuboard:
                    correct.start();
                    cuboard_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.television:
                    correct.start();
                    television_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.frame:
                    correct.start();
                    frame_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.ivLamp:
                    wrong.start();
                    ivLamp_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.chair:
                    wrong.start();
                    chair_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
