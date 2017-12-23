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

public class Circle1 extends Fragment implements View.OnClickListener{

    private ImageView ivCircleResult, ivRectangleResult, ivStarResult, ivTriangleResult;

    public Circle1(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.circle1, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Identify the circle");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        ImageView ivCircle = (ImageView) view.findViewById(R.id.ivCircle);
        ImageView ivRectangle = (ImageView) view.findViewById(R.id.ivRectangle);
        ImageView ivStar = (ImageView) view.findViewById(R.id.ivStar);
        ImageView ivTriangle = (ImageView) view.findViewById(R.id.ivTriangle);

        //setting images from database storage
        String image1, image2, image3, image4;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Fcircle.png?alt=media&token=23e0cf7e-fb40-4f89-b796-24b75c9d2a31";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Frectangle.png?alt=media&token=dd5b0c32-109f-46f1-866d-ee0eea4a31d6";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Fstar.png?alt=media&token=400c5703-b974-4ebf-abb3-a469fa275924";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Ftriangle.png?alt=media&token=c61a50c0-c763-4ffe-9357-b043d6d8c46f";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(ivCircle);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(ivRectangle);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(ivStar);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(ivTriangle);

        ivCircleResult = (ImageView) view.findViewById(R.id.ivCircleResult);
        ivRectangleResult = (ImageView) view.findViewById(R.id.ivRectangleResult);
        ivStarResult = (ImageView) view.findViewById(R.id.ivStarResult);
        ivTriangleResult = (ImageView) view.findViewById(R.id.ivTriangleResult);

        ivCircleResult.setVisibility(View.GONE);
        ivRectangleResult.setVisibility(View.GONE);
        ivStarResult.setVisibility(View.GONE);
        ivTriangleResult.setVisibility(View.GONE);

        ivCircle.setOnClickListener(this);
        ivRectangle.setOnClickListener(this);
        ivStar.setOnClickListener(this);
        ivTriangle.setOnClickListener(this);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ivCircleResult.setVisibility(View.GONE);
                ivRectangleResult.setVisibility(View.GONE);
                ivStarResult.setVisibility(View.GONE);
                ivTriangleResult.setVisibility(View.GONE);
            }
        },1000);
    }

    //validating and setting visibility
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.ivCircle:
                    correct.start();
                    ivCircleResult.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.ivRectangle:
                    ivRectangleResult.setVisibility(View.VISIBLE);
                    wrong.start();
                    hideView();
                    break;
                case R.id.ivStar:
                    ivStarResult.setVisibility(View.VISIBLE);
                    wrong.start();
                    hideView();
                    break;
                case R.id.ivTriangle:
                    ivTriangleResult.setVisibility(View.VISIBLE);
                    wrong.start();
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }
}
