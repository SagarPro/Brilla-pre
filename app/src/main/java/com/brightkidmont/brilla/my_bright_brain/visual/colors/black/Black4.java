package com.brightkidmont.brilla.my_bright_brain.visual.colors.black;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.black.BlackActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.black.BlackActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.black.BlackActivity.wrong;

public class Black4 extends Fragment implements View.OnClickListener{

    private TextView tvCount;
    private int count = 0;
    private int selectedId;
    private View view;

    public Black4(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.black4, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Count the black colored crayons");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.f_black));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);

        speaker.setOnClickListener(this);

        tvCount = (TextView) view.findViewById(R.id.tvCount);

        ImageView cb1b = (ImageView) view.findViewById(R.id.cb1b);
        ImageView cb2b = (ImageView) view.findViewById(R.id.cb2b);
        ImageView cb3b = (ImageView) view.findViewById(R.id.cb3b);
        ImageView cb4b = (ImageView) view.findViewById(R.id.cb4b);
        ImageView cb2 = (ImageView) view.findViewById(R.id.cb2);
        ImageView cb3 = (ImageView) view.findViewById(R.id.cb3);
        ImageView cb4 = (ImageView) view.findViewById(R.id.cb4);
        ImageView cb5 = (ImageView) view.findViewById(R.id.cb5);

        String image1, image2, image3, image4, image5;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlack%2Fcb1.png?alt=media&token=d193e2ba-f3bb-45c8-a738-0e80ef57ee58";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlack%2Fcb2.png?alt=media&token=8bd97ee3-5c09-4402-b8ac-cc46664f34ad";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlack%2Fcb3.png?alt=media&token=360ab9b2-fb55-453d-ae00-ddc4bd4446b6";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlack%2Fcb4.png?alt=media&token=cb1460ba-ffb6-4f78-8e50-7369ca0df231";
        image5 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlack%2Fcb5.png?alt=media&token=c74d190b-5122-4411-bb6d-def3ed0b933f";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(cb1b);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(cb2);
        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(cb2b);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(cb4);
        Picasso.with(getContext()).load(image5).error(R.drawable.bright_kid_bg).into(cb5);
        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(cb3b);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(cb3);
        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(cb4b);

        cb1b.setOnClickListener(this);
        cb2b.setOnClickListener(this);
        cb3b.setOnClickListener(this);
        cb4b.setOnClickListener(this);
        cb2.setOnClickListener(this);
        cb3.setOnClickListener(this);
        cb4.setOnClickListener(this);
        cb5.setOnClickListener(this);

        return view;
    }

    public void setAlpha(){
        ImageView imageView = (ImageView) view.findViewById(selectedId);
        imageView.setAlpha((float) 0.2);
        imageView.setOnClickListener(null);
    }

    public void countBlack(){
        count++;
        tvCount.setText("You Counted "+count+" Black Crayons");
    }

    public void message(){
        if(count==4){
            tvCount.setText("You Counted All 4 Black Crayons");
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.cb1b:
                    message();
                    countBlack();
                    correct.start();
                    selectedId = R.id.cb1b;
                    setAlpha();
                    break;
                case R.id.cb2b:
                    message();
                    countBlack();
                    correct.start();
                    selectedId = R.id.cb2b;
                    setAlpha();
                    break;
                case R.id.cb3b:
                    message();
                    countBlack();
                    correct.start();
                    selectedId = R.id.cb3b;
                    setAlpha();
                    break;
                case R.id.cb4b:
                    message();
                    countBlack();
                    correct.start();
                    selectedId = R.id.cb4b;
                    setAlpha();
                    break;
                case R.id.cb2:
                    message();
                    wrong.start();
                    break;
                case R.id.cb3:
                    message();
                    wrong.start();
                    break;
                case R.id.cb4:
                    message();
                    wrong.start();
                    break;
                case R.id.cb5:
                    message();
                    wrong.start();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
