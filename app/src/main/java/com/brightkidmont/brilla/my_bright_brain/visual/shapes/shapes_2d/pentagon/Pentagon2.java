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

public class Pentagon2 extends Fragment implements View.OnClickListener{

    private ImageView ivRectangle, ivOval, ivPentagon, ivSquare;

    public Pentagon2(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.pentagon2, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("What is the name of this shape");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        ImageView pentagon = (ImageView) view.findViewById(R.id.pentagon);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Fpentagon.png?alt=media&token=32911db7-0479-481c-9072-84d9818a1fc8").error(R.drawable.bright_kid_bg).into(pentagon);

        TextView tvRectangle = (TextView) view.findViewById(R.id.tvRectangle);
        TextView tvOval = (TextView) view.findViewById(R.id.tvOval);
        TextView tvPentagon = (TextView) view.findViewById(R.id.tvPentagon);
        TextView tvSquare = (TextView) view.findViewById(R.id.tvSquare);

        ivRectangle = (ImageView) view.findViewById(R.id.ivRectangle);
        ivOval = (ImageView) view.findViewById(R.id.ivOval);
        ivPentagon = (ImageView) view.findViewById(R.id.ivPentagon);
        ivSquare = (ImageView) view.findViewById(R.id.ivSquare);

        ivRectangle.setVisibility(View.INVISIBLE);
        ivOval.setVisibility(View.INVISIBLE);
        ivPentagon.setVisibility(View.INVISIBLE);
        ivSquare.setVisibility(View.INVISIBLE);

        tvRectangle.setOnClickListener(this);
        tvOval.setOnClickListener(this);
        tvPentagon.setOnClickListener(this);
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
                ivPentagon.setVisibility(View.INVISIBLE);
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
                    wrong.start();
                    hideView();
                    break;
                case R.id.tvPentagon:
                    correct.start();
                    ivPentagon.setVisibility(View.VISIBLE);
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
