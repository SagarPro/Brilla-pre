package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rhombus;

import android.os.Bundle;
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

public class Rhombus4 extends Fragment implements View.OnClickListener{

    private ImageView rhombus1;
    private ImageView rhombus3;
    private ImageView rhombus5;
    private TextView tvCount;
    private int count = 0;

    public Rhombus4(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.rhombus4, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Count the rhombus shaped objects");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        tvCount = (TextView) view.findViewById(R.id.tvCount);

        rhombus1 = (ImageView) view.findViewById(R.id.rhombus1);
        ImageView rhombus2 = (ImageView) view.findViewById(R.id.rhombus2);
        rhombus3 = (ImageView) view.findViewById(R.id.rhombus3);
        ImageView rhombus4 = (ImageView) view.findViewById(R.id.rhombus4);
        rhombus5 = (ImageView) view.findViewById(R.id.rhombus5);

        String image1, image2, image3, image4, image5;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FRhombus%2Frhombus1.png?alt=media&token=4bd6cf91-b59d-440a-9f82-8902fd9ec96f";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FRhombus%2Frhombus2.png?alt=media&token=22ccd532-e23b-4955-9b19-59a255270cfb";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FRhombus%2Frhombus3.png?alt=media&token=5c733757-32f5-4d11-8733-ae085b8c0ad3";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FRhombus%2Frhombus4.png?alt=media&token=5f2f5aa6-8ab0-4d69-b18c-6527c625a5d2";
        image5 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FRhombus%2Frhombus5.png?alt=media&token=309c917d-7285-4727-ac91-5a0423cfbf5d";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(rhombus1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(rhombus2);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(rhombus3);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(rhombus4);
        Picasso.with(getContext()).load(image5).error(R.drawable.bright_kid_bg).into(rhombus5);

        rhombus1.setOnClickListener(this);
        rhombus2.setOnClickListener(this);
        rhombus3.setOnClickListener(this);
        rhombus4.setOnClickListener(this);
        rhombus5.setOnClickListener(this);

        return view;
    }

    public void countSquare(){
        count++;
        tvCount.setText("You Counted "+count+" Rhombus");
    }

    public void message(){
        if(count==3){
            //tts.speak("You Counted All The Rhombus", TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.rhombus1:
                    message();
                    countSquare();
                    correct.start();
                    rhombus1.setAlpha((float) 0.2);
                    rhombus1.setOnClickListener(null);
                    break;
                case R.id.rhombus2:
                    message();
                    wrong.start();
                    break;
                case R.id.rhombus3:
                    message();
                    countSquare();
                    correct.start();
                    rhombus3.setAlpha((float) 0.2);
                    rhombus3.setOnClickListener(null);
                    break;
                case R.id.rhombus4:
                    message();
                    wrong.start();
                    break;
                case R.id.rhombus5:
                    message();
                    countSquare();
                    correct.start();
                    rhombus5.setAlpha((float) 0.2);
                    rhombus5.setOnClickListener(null);
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
