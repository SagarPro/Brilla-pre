package com.brightkidmont.brilla.my_bright_brain.visual.colors.blue;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.blue.BlueActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.blue.BlueActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.blue.BlueActivity.wrong;

public class Blue1 extends Fragment implements View.OnClickListener {

    private ImageView bo1_right, bo2_wrong, bo3_right, bo4_wrong, bo5_right, bo6_wrong;

    public Blue1(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.blue1, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Select blue colored objects");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_belize_hole));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);

        ImageView bo1 = (ImageView) view.findViewById(R.id.bo1);
        ImageView bo2 = (ImageView) view.findViewById(R.id.bo2);
        ImageView bo3 = (ImageView) view.findViewById(R.id.bo3);
        ImageView bo4 = (ImageView) view.findViewById(R.id.bo4);
        ImageView bo5 = (ImageView) view.findViewById(R.id.bo5);
        ImageView bo6 = (ImageView) view.findViewById(R.id.bo6);

        String image1, image2, image3, image4, image5, image6;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlue%2Fbo1.png?alt=media&token=ddd85e7d-106b-4c08-8680-f9d313ac58a8";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlue%2Fbo2.png?alt=media&token=eea9d87b-8319-49be-9933-8c97446d2ea2";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlue%2Fbo3.png?alt=media&token=c7d8afae-1126-4ed2-9ce8-4f092a4d4034";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlue%2Fbo4.png?alt=media&token=9aec63bf-4cc4-405b-b6c0-0e10cf127970";
        image5 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlue%2Fbo5.png?alt=media&token=891becd2-4237-4cb8-a440-1fa8184928cd";
        image6 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlue%2Fbo6.png?alt=media&token=fc896ee5-705c-4e2f-98ca-90b37cd90824";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(bo1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(bo2);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(bo3);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(bo4);
        Picasso.with(getContext()).load(image5).error(R.drawable.bright_kid_bg).into(bo5);
        Picasso.with(getContext()).load(image6).error(R.drawable.bright_kid_bg).into(bo6);

        bo1_right = (ImageView) view.findViewById(R.id.bo1_right);
        bo2_wrong = (ImageView) view.findViewById(R.id.bo2_wrong);
        bo3_right = (ImageView) view.findViewById(R.id.bo3_right);
        bo4_wrong = (ImageView) view.findViewById(R.id.bo4_wrong);
        bo5_right = (ImageView) view.findViewById(R.id.bo5_right);
        bo6_wrong = (ImageView) view.findViewById(R.id.bo6_wrong);

        bo1_right.setVisibility(View.INVISIBLE);
        bo2_wrong.setVisibility(View.INVISIBLE);
        bo3_right.setVisibility(View.INVISIBLE);
        bo4_wrong.setVisibility(View.INVISIBLE);
        bo5_right.setVisibility(View.INVISIBLE);
        bo6_wrong.setVisibility(View.INVISIBLE);

        speaker.setOnClickListener(this);

        bo1.setOnClickListener(this);
        bo2.setOnClickListener(this);
        bo3.setOnClickListener(this);
        bo4.setOnClickListener(this);
        bo5.setOnClickListener(this);
        bo6.setOnClickListener(this);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                bo1_right.setVisibility(View.INVISIBLE);
                bo2_wrong.setVisibility(View.INVISIBLE);
                bo3_right.setVisibility(View.INVISIBLE);
                bo4_wrong.setVisibility(View.INVISIBLE);
                bo5_right.setVisibility(View.INVISIBLE);
                bo6_wrong.setVisibility(View.INVISIBLE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.bo1:
                    correct.start();
                    bo1_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.bo2:
                    wrong.start();
                    bo2_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.bo3:
                    correct.start();
                    bo3_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.bo4:
                    wrong.start();
                    bo4_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.bo5:
                    correct.start();
                    bo5_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.bo6:
                    wrong.start();
                    bo6_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
