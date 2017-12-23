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

public class Trapezium5 extends Fragment implements View.OnClickListener{

    private ImageView ivCircleWrong, ivTrapeziumRight, ivStarWrong, ivTriangleWrong;

    public Trapezium5(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.trapezium5, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Identify the trapezium shape");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        ImageView ivCircle = (ImageView) view.findViewById(R.id.ivCircle);
        ImageView ivTrapezium = (ImageView) view.findViewById(R.id.ivTrapezium);
        ImageView ivStar = (ImageView) view.findViewById(R.id.ivStar);
        ImageView ivTriangle = (ImageView) view.findViewById(R.id.ivTriangle);

        String image1, image2, image3, image4;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FCircle%2Fcircle_b.png?alt=media&token=8225c28b-341b-49c3-a160-3f1e9ae80b67";
        image2 ="https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FTrapezium%2Ftrapezium.png?alt=media&token=7c934369-9fc6-45e9-89d7-d0fe0cf3da3c";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FStar%2Fstar_g.png?alt=media&token=8e589017-9411-4bf8-bd6d-83cce4a51b99";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Ftriangle.png?alt=media&token=c61a50c0-c763-4ffe-9357-b043d6d8c46f";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(ivCircle);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(ivTrapezium);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(ivStar);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(ivTriangle);

        ivCircle.setOnClickListener(this);
        ivTrapezium.setOnClickListener(this);
        ivStar.setOnClickListener(this);
        ivTriangle.setOnClickListener(this);

        ivCircleWrong = (ImageView) view.findViewById(R.id.ivCircleWrong);
        ivTrapeziumRight = (ImageView) view.findViewById(R.id.ivTrapeziumRight);
        ivStarWrong = (ImageView) view.findViewById(R.id.ivStarWrong);
        ivTriangleWrong = (ImageView) view.findViewById(R.id.ivTriangleWrong);

        ivCircleWrong.setVisibility(View.GONE);
        ivTrapeziumRight.setVisibility(View.GONE);
        ivStarWrong.setVisibility(View.GONE);
        ivTriangleWrong.setVisibility(View.GONE);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ivCircleWrong.setVisibility(View.GONE);
                ivTrapeziumRight.setVisibility(View.GONE);
                ivStarWrong.setVisibility(View.GONE);
                ivTriangleWrong.setVisibility(View.GONE);
            }
        }, 1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.ivCircle:
                    wrong.start();
                    ivCircleWrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.ivTrapezium:
                    correct.start();
                    ivTrapeziumRight.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.ivStar:
                    wrong.start();
                    ivStarWrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.ivTriangle:
                    wrong.start();
                    ivTriangleWrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
