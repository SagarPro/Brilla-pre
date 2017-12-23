package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.oval;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.oval.OvalActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.oval.OvalActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.oval.OvalActivity.wrong;

public class Oval2 extends Fragment implements View.OnClickListener{

    private ImageView ivRectangle, ivOval, ivCircle, ivSquare;

    public Oval2(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.oval2, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("What is the name of this shape");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        ImageView oval = (ImageView) view.findViewById(R.id.oval);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Foval.png?alt=media&token=f63dffcc-9cde-4d52-831d-006b8bc32489").error(R.drawable.bright_kid_bg).into(oval);

        TextView tvRectangle = (TextView) view.findViewById(R.id.tvRectangle);
        TextView tvOval = (TextView) view.findViewById(R.id.tvOval);
        TextView tvCircle = (TextView) view.findViewById(R.id.tvCircle);
        TextView tvSquare = (TextView) view.findViewById(R.id.tvSquare);

        ivRectangle = (ImageView) view.findViewById(R.id.ivRectangle);
        ivOval = (ImageView) view.findViewById(R.id.ivOval);
        ivCircle = (ImageView) view.findViewById(R.id.ivCircle);
        ivSquare = (ImageView) view.findViewById(R.id.ivSquare);

        ivRectangle.setVisibility(View.INVISIBLE);
        ivOval.setVisibility(View.INVISIBLE);
        ivCircle.setVisibility(View.INVISIBLE);
        ivSquare.setVisibility(View.INVISIBLE);

        tvRectangle.setOnClickListener(this);
        tvOval.setOnClickListener(this);
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
                ivOval.setVisibility(View.INVISIBLE);
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
                case R.id.tvOval:
                    ivOval.setVisibility(View.VISIBLE);
                    correct.start();
                    hideView();
                    break;
                case R.id.tvCircle:
                    wrong.start();
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
