package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.circle;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
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

public class Circle5 extends Fragment implements View.OnClickListener, TextToSpeech.OnInitListener{

    private ImageView yellow_circle;
    private ImageView top_left;
    private ImageView top_right;
    private ImageView bottom_left;
    private ImageView bottom_right;
    private ImageView center;
    private ImageView yc_right, tl_right, tr_right, bl_right, br_right, center_right;
    private TextView tvCount;
    private int count = 0;
    private TextToSpeech tts;

    public Circle5(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.circle5, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Count the number of circles");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        tts = new TextToSpeech(getContext(), this);

        tvCount = (TextView) view.findViewById(R.id.tvCount);

        ImageView pot = (ImageView) view.findViewById(R.id.pot);
        ImageView stem1 = (ImageView) view.findViewById(R.id.stem1);
        ImageView stem2 = (ImageView) view.findViewById(R.id.stem2);
        ImageView yellow_flower = (ImageView) view.findViewById(R.id.yellow_flower);
        yellow_circle = (ImageView) view.findViewById(R.id.yellow_circle);
        top_left = (ImageView) view.findViewById(R.id.top_left);
        top_right = (ImageView) view.findViewById(R.id.top_right);
        bottom_left = (ImageView) view.findViewById(R.id.bottom_left);
        bottom_right= (ImageView) view.findViewById(R.id.bottom_right);
        center = (ImageView) view.findViewById(R.id.center);

        //setting images from dataBase storage
        String image1, image2, image3, image4;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FCircle%2Fpot.png?alt=media&token=77cecd39-c43a-444c-980d-a6d641dac39c";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FCircle%2Fyellow_flower.png?alt=media&token=c8c417c9-69e9-4224-a2a2-881cc8b1f3cc";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FCircle%2Fyellow_circle.png?alt=media&token=58e1cb88-4f77-4dbc-92f6-1db49ca32243";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Fcircle.png?alt=media&token=23e0cf7e-fb40-4f89-b796-24b75c9d2a31";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(pot);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(yellow_flower);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(yellow_circle);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(top_left);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(top_right);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(bottom_left);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(bottom_right);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(center);

        yc_right = (ImageView) view.findViewById(R.id.yc_right);
        tl_right = (ImageView) view.findViewById(R.id.tl_right);
        tr_right = (ImageView) view.findViewById(R.id.tr_right);
        bl_right = (ImageView) view.findViewById(R.id.bl_right);
        br_right= (ImageView) view.findViewById(R.id.br_right);
        center_right = (ImageView) view.findViewById(R.id.center_right);

        yc_right.setVisibility(View.GONE);
        tl_right.setVisibility(View.GONE);
        tr_right.setVisibility(View.GONE);
        bl_right.setVisibility(View.GONE);
        br_right.setVisibility(View.GONE);
        center_right.setVisibility(View.GONE);

        pot.setOnClickListener(this);
        stem1.setOnClickListener(this);
        stem2.setOnClickListener(this);
        yellow_flower.setOnClickListener(this);
        yellow_circle.setOnClickListener(this);
        top_left.setOnClickListener(this);
        top_right.setOnClickListener(this);
        bottom_left.setOnClickListener(this);
        bottom_right.setOnClickListener(this);
        center.setOnClickListener(this);

        return view;
    }

    public void countCircle(){
        count++;
        tvCount.setText("You Counted "+count+" Circles");
    }

    public void message(){
        if(count>6){
            tts.speak("You Counted All The Circles", TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    //Displaying appropriate image
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.pot:
                    message();
                    wrong.start();
                    break;
                case R.id.stem1:
                    message();
                    wrong.start();
                    break;
                case R.id.stem2:
                    message();
                    wrong.start();
                    break;
                case R.id.yellow_flower:
                    message();
                    wrong.start();
                    break;
                case R.id.yellow_circle:
                    message();
                    countCircle();
                    correct.start();
                    yc_right.setVisibility(View.VISIBLE);
                    yellow_circle.setOnClickListener(null);
                    break;
                case R.id.top_left:
                    message();
                    countCircle();
                    correct.start();
                    tl_right.setVisibility(View.VISIBLE);
                    top_left.setOnClickListener(null);
                    break;
                case R.id.top_right:
                    message();
                    countCircle();
                    correct.start();
                    tr_right.setVisibility(View.VISIBLE);
                    top_right.setOnClickListener(null);
                    break;
                case R.id.bottom_left:
                    message();
                    countCircle();
                    correct.start();
                    bl_right.setVisibility(View.VISIBLE);
                    bottom_left.setOnClickListener(null);
                    break;
                case R.id.bottom_right:
                    message();
                    countCircle();
                    correct.start();
                    br_right.setVisibility(View.VISIBLE);
                    bottom_right.setOnClickListener(null);
                    break;
                case R.id.center:
                    message();
                    countCircle();
                    correct.start();
                    center_right.setVisibility(View.VISIBLE);
                    center.setOnClickListener(null);
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

    @Override
    public void onInit(int status) {

    }

}
