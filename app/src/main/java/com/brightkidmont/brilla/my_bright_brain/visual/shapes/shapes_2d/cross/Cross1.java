package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.cross;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.cross.CrossActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.cross.CrossActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.cross.CrossActivity.wrong;

public class Cross1 extends Fragment implements View.OnClickListener{

    private ImageView ivDivResult, ivSubResult, ivCrossResult, ivAddResult;

    public Cross1(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.cross1, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Select the cross shape");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        ImageView ivDiv = (ImageView) view.findViewById(R.id.ivDiv);
        ImageView ivSub = (ImageView) view.findViewById(R.id.ivSub);
        ImageView ivCross = (ImageView) view.findViewById(R.id.ivCross);
        ImageView ivAdd = (ImageView) view.findViewById(R.id.ivAdd);

        String image1, image2, image3, image4;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FCross%2Fdiv.png?alt=media&token=0ebbb127-b11e-41fe-9b3c-abee3da679d1";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FCross%2Fsub.png?alt=media&token=2e0fe405-07ef-4fad-b689-18f6f80e4534";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FCross%2Fcross.png?alt=media&token=967ce5f3-91d8-4e28-88c9-92772a359d2f";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FCross%2Fadd.png?alt=media&token=30c25b09-b907-4cce-a2a7-2bc393409f8f";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(ivDiv);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(ivSub);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(ivCross);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(ivAdd);

        ivDivResult = (ImageView) view.findViewById(R.id.ivDivResult);
        ivSubResult = (ImageView) view.findViewById(R.id.ivSubResult);
        ivCrossResult = (ImageView) view.findViewById(R.id.ivCrossResult);
        ivAddResult = (ImageView) view.findViewById(R.id.ivAddResult);

        ivDivResult.setVisibility(View.INVISIBLE);
        ivSubResult.setVisibility(View.INVISIBLE);
        ivCrossResult.setVisibility(View.INVISIBLE);
        ivAddResult.setVisibility(View.INVISIBLE);

        ivDiv.setOnClickListener(this);
        ivSub.setOnClickListener(this);
        ivCross.setOnClickListener(this);
        ivAdd.setOnClickListener(this);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ivDivResult.setVisibility(View.INVISIBLE);
                ivSubResult.setVisibility(View.INVISIBLE);
                ivCrossResult.setVisibility(View.INVISIBLE);
                ivAddResult.setVisibility(View.INVISIBLE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.ivDiv:
                    ivDivResult.setVisibility(View.VISIBLE);
                    wrong.start();
                    hideView();
                    break;
                case R.id.ivSub:
                    ivSubResult.setVisibility(View.VISIBLE);
                    wrong.start();
                    hideView();
                    break;
                case R.id.ivCross:
                    ivCrossResult.setVisibility(View.VISIBLE);
                    correct.start();
                    hideView();
                    break;
                case R.id.ivAdd:
                    ivAddResult.setVisibility(View.VISIBLE);
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
