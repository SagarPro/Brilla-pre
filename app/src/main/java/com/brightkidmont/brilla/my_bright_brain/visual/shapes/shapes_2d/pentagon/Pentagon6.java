package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.pentagon;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.pentagon.PentagonActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.pentagon.PentagonActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.pentagon.PentagonActivity.wrong;

public class Pentagon6 extends Fragment implements View.OnClickListener{

    private ImageView iv3, iv7, iv5, iv9;

    public Pentagon6(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.pentagon6, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Count the number of sides for the shape given below");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        ImageView pentagon = (ImageView) view.findViewById(R.id.pentagon);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Fpentagon.png?alt=media&token=32911db7-0479-481c-9072-84d9818a1fc8").error(R.drawable.bright_kid_bg).into(pentagon);

        TextView tv3 = (TextView) view.findViewById(R.id.tv3);
        TextView tv7 = (TextView) view.findViewById(R.id.tv7);
        TextView tv5 = (TextView) view.findViewById(R.id.tv5);
        TextView tv9 = (TextView) view.findViewById(R.id.tv9);

        iv3 = (ImageView) view.findViewById(R.id.iv3);
        iv7 = (ImageView) view.findViewById(R.id.iv7);
        iv5 = (ImageView) view.findViewById(R.id.iv5);
        iv9 = (ImageView) view.findViewById(R.id.iv9);

        iv3.setVisibility(View.INVISIBLE);
        iv7.setVisibility(View.INVISIBLE);
        iv5.setVisibility(View.INVISIBLE);
        iv9.setVisibility(View.INVISIBLE);

        tv3.setOnClickListener(this);
        tv7.setOnClickListener(this);
        tv5.setOnClickListener(this);
        tv9.setOnClickListener(this);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iv3.setVisibility(View.INVISIBLE);
                iv7.setVisibility(View.INVISIBLE);
                iv5.setVisibility(View.INVISIBLE);
                iv9.setVisibility(View.INVISIBLE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.tv3:
                    wrong.start();
                    iv3.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.tv7:
                    iv7.setVisibility(View.VISIBLE);
                    wrong.start();
                    hideView();
                    break;
                case R.id.tv5:
                    correct.start();
                    iv5.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.tv9:
                    wrong.start();
                    iv9.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
