package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rhombus;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rhombus.RhombusActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rhombus.RhombusActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rhombus.RhombusActivity.wrong;

public class Rhombus1 extends Fragment implements View.OnClickListener{

    private ImageView ivCircleResult, ivRectangleResult, ivRhombusResult, ivTriangleResult;

    public Rhombus1(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.rhombus1, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Identify the rhombus");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        ImageView ivCircle = (ImageView) view.findViewById(R.id.ivCircle);
        ImageView ivRectangle = (ImageView) view.findViewById(R.id.ivRectangle);
        ImageView ivRhombus = (ImageView) view.findViewById(R.id.ivRhombus);
        ImageView ivTriangle = (ImageView) view.findViewById(R.id.ivTriangle);

        String image1, image2, image3, image4;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FCircle%2Fcircle_b.png?alt=media&token=8225c28b-341b-49c3-a160-3f1e9ae80b67";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Frectangle.png?alt=media&token=dd5b0c32-109f-46f1-866d-ee0eea4a31d6";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FRhombus%2Frhombus.png?alt=media&token=17e5825c-062b-480a-aa49-2400d1935809";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FTriangle%2Ftriangle_g.png?alt=media&token=740e4511-f90b-4c29-ad67-9c75449c4e4d";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(ivCircle);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(ivRectangle);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(ivRhombus);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(ivTriangle);

        ivCircleResult = (ImageView) view.findViewById(R.id.ivCircleResult);
        ivRectangleResult = (ImageView) view.findViewById(R.id.ivRectangleResult);
        ivRhombusResult = (ImageView) view.findViewById(R.id.ivRhombusResult);
        ivTriangleResult = (ImageView) view.findViewById(R.id.ivTriangleResult);

        ivCircleResult.setVisibility(View.GONE);
        ivRectangleResult.setVisibility(View.GONE);
        ivRhombusResult.setVisibility(View.GONE);
        ivTriangleResult.setVisibility(View.GONE);

        ivCircle.setOnClickListener(this);
        ivRectangle.setOnClickListener(this);
        ivRhombus.setOnClickListener(this);
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
                ivRhombusResult.setVisibility(View.GONE);
                ivTriangleResult.setVisibility(View.GONE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.ivCircle:
                    ivCircleResult.setVisibility(View.VISIBLE);
                    wrong.start();
                    hideView();
                    break;
                case R.id.ivRectangle:
                    ivRectangleResult.setVisibility(View.VISIBLE);
                    wrong.start();
                    hideView();
                    break;
                case R.id.ivRhombus:
                    ivRhombusResult.setVisibility(View.VISIBLE);
                    correct.start();
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
