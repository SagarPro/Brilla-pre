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

public class Pentagon1 extends Fragment implements View.OnClickListener{

    private ImageView ivCircleResult, ivPentagonResult, ivOvalResult, ivTriangleResult;

    public Pentagon1(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.pentagon1, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Identify Pentagon Shape");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        ImageView ivCircle = (ImageView) view.findViewById(R.id.ivCircle);
        ImageView ivPentagon = (ImageView) view.findViewById(R.id.ivPentagon);
        ImageView ivOval = (ImageView) view.findViewById(R.id.ivOval);
        ImageView ivTriangle = (ImageView) view.findViewById(R.id.ivTriangle);

        String image1, image2, image3, image4;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Fcircle.png?alt=media&token=23e0cf7e-fb40-4f89-b796-24b75c9d2a31";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Fpentagon.png?alt=media&token=32911db7-0479-481c-9072-84d9818a1fc8";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Foval.png?alt=media&token=f63dffcc-9cde-4d52-831d-006b8bc32489";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Ftriangle.png?alt=media&token=c61a50c0-c763-4ffe-9357-b043d6d8c46f";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(ivCircle);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(ivPentagon);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(ivOval);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(ivTriangle);

        ivCircleResult = (ImageView) view.findViewById(R.id.ivCircleResult);
        ivPentagonResult = (ImageView) view.findViewById(R.id.ivPentagonResult);
        ivOvalResult = (ImageView) view.findViewById(R.id.ivOvalResult);
        ivTriangleResult = (ImageView) view.findViewById(R.id.ivTriangleResult);

        ivCircleResult.setVisibility(View.GONE);
        ivPentagonResult.setVisibility(View.GONE);
        ivOvalResult.setVisibility(View.GONE);
        ivTriangleResult.setVisibility(View.GONE);

        ivCircle.setOnClickListener(this);
        ivPentagon.setOnClickListener(this);
        ivOval.setOnClickListener(this);
        ivTriangle.setOnClickListener(this);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ivCircleResult.setVisibility(View.GONE);
                ivPentagonResult.setVisibility(View.GONE);
                ivOvalResult.setVisibility(View.GONE);
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
                case R.id.ivPentagon:
                    ivPentagonResult.setVisibility(View.VISIBLE);
                    correct.start();
                    hideView();
                    break;
                case R.id.ivOval:
                    ivOvalResult.setVisibility(View.VISIBLE);
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
