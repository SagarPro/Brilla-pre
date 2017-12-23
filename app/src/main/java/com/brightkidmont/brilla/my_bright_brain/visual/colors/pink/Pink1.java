package com.brightkidmont.brilla.my_bright_brain.visual.colors.pink;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.pink.PinkActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.pink.PinkActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.pink.PinkActivity.wrong;

public class Pink1 extends Fragment implements View.OnClickListener {

    private TextView tvCount;
    private int count = 0;
    private int selectedId;
    private View view;

    public Pink1(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.pink1, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Count the pink colored flowers");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.f_pink));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);

        speaker.setOnClickListener(this);

        tvCount = (TextView) view.findViewById(R.id.tvCount);

        ImageView p1 = (ImageView) view.findViewById(R.id.p1);
        ImageView p2 = (ImageView) view.findViewById(R.id.p2);
        ImageView p3 = (ImageView) view.findViewById(R.id.p3);
        ImageView p4 = (ImageView) view.findViewById(R.id.p4);
        ImageView p5 = (ImageView) view.findViewById(R.id.p5);
        ImageView p6 = (ImageView) view.findViewById(R.id.p6);

        String image1, image2, image3, image4;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPink%2Fp2.png?alt=media&token=093ab0d0-08bf-4d8f-a693-bac92cfabaed";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPink%2Fp3.png?alt=media&token=e91fb66a-5d10-42aa-8ae4-cd2122ec386b";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPink%2Fp4.png?alt=media&token=ae9fd576-b5a6-4459-bac2-7b091965d1e5";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPink%2Fp5.png?alt=media&token=dff01e8e-36c6-4e47-a7b4-b36eaff699b6";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(p1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(p2);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(p3);
        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(p4);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(p5);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(p6);

        p1.setOnClickListener(this);
        p2.setOnClickListener(this);
        p3.setOnClickListener(this);
        p4.setOnClickListener(this);
        p5.setOnClickListener(this);
        p6.setOnClickListener(this);

        return view;
    }

    public void setAlpha(){
        ImageView imageView = (ImageView) view.findViewById(selectedId);
        imageView.setAlpha((float) 0.2);
        imageView.setOnClickListener(null);
    }

    public void countPink(){
        count++;
        tvCount.setText("You Counted "+count+" Pink Flowers");
    }

    public void message(){
        if(count==2){
            tvCount.setText("You Counted All 2 Pink Flowers");
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.p1:
                    message();
                    countPink();
                    correct.start();
                    selectedId = R.id.p1;
                    setAlpha();
                    break;
                case R.id.p2:
                    message();
                    wrong.start();
                    break;
                case R.id.p3:
                    message();
                    wrong.start();
                    break;
                case R.id.p4:
                    message();
                    countPink();
                    correct.start();
                    selectedId = R.id.p4;
                    setAlpha();
                    break;
                case R.id.p5:
                    message();
                    wrong.start();
                    break;
                case R.id.p6:
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
