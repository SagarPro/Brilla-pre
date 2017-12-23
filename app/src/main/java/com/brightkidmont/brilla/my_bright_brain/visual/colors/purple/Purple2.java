package com.brightkidmont.brilla.my_bright_brain.visual.colors.purple;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.purple.PurpleActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.purple.PurpleActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.purple.PurpleActivity.wrong;

public class Purple2 extends Fragment implements View.OnClickListener {

    private ImageView ivprb1, ivprb2, ivprb3, ivprb4, ivprb5, ivprb6, ivprb7;
    View view;

    public Purple2(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.purple2, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Identify the purple colored butterflies");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        ImageView prb1 = (ImageView) view.findViewById(R.id.prb1);
        ImageView prb2 = (ImageView) view.findViewById(R.id.prb2);
        ImageView prb3 = (ImageView) view.findViewById(R.id.prb3);
        ImageView prb4 = (ImageView) view.findViewById(R.id.prb4);
        ImageView prb5 = (ImageView) view.findViewById(R.id.prb5);
        ImageView prb6 = (ImageView) view.findViewById(R.id.prb6);
        ImageView prb7 = (ImageView) view.findViewById(R.id.prb7);

        String image1, image2, image3, image4, image5;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPurple%2Fpr2.png?alt=media&token=d3f81d94-522c-4f76-ace1-c476d7692257";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPurple%2Fpr3.png?alt=media&token=b887bae2-d64b-4492-961d-499c705dcbd7";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPurple%2Fpr4.png?alt=media&token=1546357a-05cd-4115-b370-1e6fba06c047";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlue%2Fbb5.png?alt=media&token=42243d49-b2dd-40e3-99e6-4392ecd44a0e";
        image5 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlue%2Fbb6.png?alt=media&token=fd601fd0-2a5e-466e-907a-0c78ada4d918";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(prb1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(prb2);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(prb3);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(prb4);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(prb5);
        Picasso.with(getContext()).load(image5).error(R.drawable.bright_kid_bg).into(prb6);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(prb7);

        ivprb1 = (ImageView) view.findViewById(R.id.ivprb1);
        ivprb2 = (ImageView) view.findViewById(R.id.ivprb2);
        ivprb3 = (ImageView) view.findViewById(R.id.ivprb3);
        ivprb4 = (ImageView) view.findViewById(R.id.ivprb4);
        ivprb5 = (ImageView) view.findViewById(R.id.ivprb5);
        ivprb6 = (ImageView) view.findViewById(R.id.ivprb6);
        ivprb7 = (ImageView) view.findViewById(R.id.ivprb7);

        ivprb1.setVisibility(View.INVISIBLE);
        ivprb2.setVisibility(View.INVISIBLE);
        ivprb3.setVisibility(View.INVISIBLE);
        ivprb4.setVisibility(View.INVISIBLE);
        ivprb5.setVisibility(View.INVISIBLE);
        ivprb6.setVisibility(View.INVISIBLE);
        ivprb7.setVisibility(View.INVISIBLE);

        prb1.setOnClickListener(this);
        prb2.setOnClickListener(this);
        prb3.setOnClickListener(this);
        prb4.setOnClickListener(this);
        prb5.setOnClickListener(this);
        prb6.setOnClickListener(this);
        prb7.setOnClickListener(this);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ivprb1.setVisibility(View.INVISIBLE);
                ivprb2.setVisibility(View.INVISIBLE);
                ivprb3.setVisibility(View.INVISIBLE);
                ivprb4.setVisibility(View.INVISIBLE);
                ivprb5.setVisibility(View.INVISIBLE);
                ivprb6.setVisibility(View.INVISIBLE);
                ivprb7.setVisibility(View.INVISIBLE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.prb1:
                    wrong.start();
                    ivprb1.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.prb2:
                    correct.start();
                    ivprb2.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.prb3:
                    wrong.start();
                    ivprb3.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.prb4:
                    correct.start();
                    ivprb4.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.prb5:
                    wrong.start();
                    ivprb5.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.prb6:
                    wrong.start();
                    ivprb6.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.prb7:
                    correct.start();
                    ivprb7.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
