package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.trapezium;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.trapezium.TrapeziumActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.trapezium.TrapeziumActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.trapezium.TrapeziumActivity.wrong;

public class Trapezium2 extends Fragment implements View.OnClickListener{

    private ImageView ivTrapezium, ivTriangle, ivStar, ivSquare;

    public Trapezium2(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.trapezium2, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("What is the name of this shape");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        ImageView trapezium = (ImageView) view.findViewById(R.id.trapezium);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FTrapezium%2Ftrapezium.png?alt=media&token=7c934369-9fc6-45e9-89d7-d0fe0cf3da3c").error(R.drawable.bright_kid_bg).into(trapezium);

        TextView tvTrapezium = (TextView) view.findViewById(R.id.tvTrapezium);
        TextView tvTriangle = (TextView) view.findViewById(R.id.tvTriangle);
        TextView tvStar = (TextView) view.findViewById(R.id.tvStar);
        TextView tvSquare = (TextView) view.findViewById(R.id.tvSquare);

        ivTrapezium = (ImageView) view.findViewById(R.id.ivTrapezium);
        ivTriangle = (ImageView) view.findViewById(R.id.ivTriangle);
        ivStar = (ImageView) view.findViewById(R.id.ivStar);
        ivSquare = (ImageView) view.findViewById(R.id.ivSquare);

        ivTrapezium.setVisibility(View.INVISIBLE);
        ivTriangle.setVisibility(View.INVISIBLE);
        ivStar.setVisibility(View.INVISIBLE);
        ivSquare.setVisibility(View.INVISIBLE);

        tvTrapezium.setOnClickListener(this);
        tvTriangle.setOnClickListener(this);
        tvStar.setOnClickListener(this);
        tvSquare.setOnClickListener(this);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ivTrapezium.setVisibility(View.INVISIBLE);
                ivTriangle.setVisibility(View.INVISIBLE);
                ivStar.setVisibility(View.INVISIBLE);
                ivSquare.setVisibility(View.INVISIBLE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.tvTrapezium:
                    correct.start();
                    ivTrapezium.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.tvTriangle:
                    ivTriangle.setVisibility(View.VISIBLE);
                    wrong.start();
                    hideView();
                    break;
                case R.id.tvStar:
                    wrong.start();
                    ivStar.setVisibility(View.VISIBLE);
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
