package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.triangle;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.triangle.TriangleActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.triangle.TriangleActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.triangle.TriangleActivity.wrong;

public class Triangle5 extends Fragment implements View.OnClickListener{

    private ImageView wrong1, wrong2, right1, right2, right3;

    public Triangle5(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.triangle5, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Select all the objects in the shape of a triangle in the home");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        ImageView clock = (ImageView) view.findViewById(R.id.clock);
        ImageView window = (ImageView) view.findViewById(R.id.window);
        ImageView triangle1 = (ImageView) view.findViewById(R.id.triangle1);
        ImageView pillow1 = (ImageView) view.findViewById(R.id.pillow1);
        ImageView pillow2 = (ImageView) view.findViewById(R.id.pillow2);

        wrong1 = (ImageView) view.findViewById(R.id.wrong1);
        wrong2 = (ImageView) view.findViewById(R.id.wrong2);
        right1 = (ImageView) view.findViewById(R.id.right1);
        right2 = (ImageView) view.findViewById(R.id.right2);
        right3 = (ImageView) view.findViewById(R.id.right3);

        wrong1.setVisibility(View.GONE);
        wrong2.setVisibility(View.GONE);
        right1.setVisibility(View.GONE);
        right2.setVisibility(View.GONE);
        right3.setVisibility(View.GONE);

        clock.setOnClickListener(this);
        window.setOnClickListener(this);
        triangle1.setOnClickListener(this);
        pillow1.setOnClickListener(this);
        pillow2.setOnClickListener(this);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                wrong1.setVisibility(View.GONE);
                wrong2.setVisibility(View.GONE);
                right1.setVisibility(View.GONE);
                right2.setVisibility(View.GONE);
                right3.setVisibility(View.GONE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.clock:
                    wrong2.setVisibility(View.VISIBLE);
                    wrong.start();
                    hideView();
                    break;
                case R.id.window:
                    wrong1.setVisibility(View.VISIBLE);
                    wrong.start();
                    hideView();
                    break;
                case R.id.triangle1:
                    right3.setVisibility(View.VISIBLE);
                    correct.start();
                    hideView();
                    break;
                case R.id.pillow1:
                    right1.setVisibility(View.VISIBLE);
                    correct.start();
                    hideView();
                    break;
                case R.id.pillow2:
                    right2.setVisibility(View.VISIBLE);
                    correct.start();
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
