package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.arrow;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.arrow.ArrowActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.arrow.ArrowActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.arrow.ArrowActivity.wrong;

public class Arrow1 extends Fragment implements View.OnClickListener{

    private ImageView ivRectangle, ivCross, ivArrow, ivSquare;

    public Arrow1(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.arrow1, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("What is the name of this shape");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        TextView tvRectangle = (TextView) view.findViewById(R.id.tvRectangle);
        TextView tvCross = (TextView) view.findViewById(R.id.tvCross);
        TextView tvArrow = (TextView) view.findViewById(R.id.tvArrow);
        TextView tvSquare = (TextView) view.findViewById(R.id.tvSquare);

        ivRectangle = (ImageView) view.findViewById(R.id.ivRectangle);
        ivCross = (ImageView) view.findViewById(R.id.ivCross);
        ivArrow = (ImageView) view.findViewById(R.id.ivArrow);
        ivSquare = (ImageView) view.findViewById(R.id.ivSquare);

        ivRectangle.setVisibility(View.INVISIBLE);
        ivCross.setVisibility(View.INVISIBLE);
        ivArrow.setVisibility(View.INVISIBLE);
        ivSquare.setVisibility(View.INVISIBLE);

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        //Setting image by using path from database
        ImageView arrow = (ImageView) view.findViewById(R.id.arrow);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Farrow.png?alt=media&token=7b7ff666-8005-44ca-bb7c-bbd86f22e9c5").error(R.drawable.bright_kid_bg).into(arrow);

        tvRectangle.setOnClickListener(this);
        tvCross.setOnClickListener(this);
        tvArrow.setOnClickListener(this);
        tvSquare.setOnClickListener(this);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ivRectangle.setVisibility(View.INVISIBLE);
                ivCross.setVisibility(View.INVISIBLE);
                ivArrow.setVisibility(View.INVISIBLE);
                ivSquare.setVisibility(View.INVISIBLE);
            }
        },1000);
    }

    //Validation of onClick
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
                case R.id.tvCross:
                    wrong.start();
                    ivCross.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.tvArrow:
                    correct.start();
                    ivArrow.setVisibility(View.VISIBLE);
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
