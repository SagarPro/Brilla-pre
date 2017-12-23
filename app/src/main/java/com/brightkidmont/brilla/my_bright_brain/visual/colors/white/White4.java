package com.brightkidmont.brilla.my_bright_brain.visual.colors.white;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.white.WhiteActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.white.WhiteActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.white.WhiteActivity.whiteTab;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.white.WhiteActivity.wrong;

public class White4 extends Fragment implements View.OnClickListener {

    private TextView tvCount;
    private int count = 0;

    private int selectedId;
    private View view;

    public White4(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.white4, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Count the white colored pencils");
        flash_header.setTextColor(getResources().getColor(R.color.f_black));

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_clouds));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setImageResource(R.drawable.speaker_b);
        speaker.setOnClickListener(this);

        tvCount = (TextView) view.findViewById(R.id.tvCount);

        ImageView w1 = (ImageView) view.findViewById(R.id.w1);
        ImageView w2 = (ImageView) view.findViewById(R.id.w2);
        ImageView w3 = (ImageView) view.findViewById(R.id.w3);
        ImageView w4 = (ImageView) view.findViewById(R.id.w4);
        ImageView w5 = (ImageView) view.findViewById(R.id.w5);
        ImageView w6 = (ImageView) view.findViewById(R.id.w6);
        ImageView w7 = (ImageView) view.findViewById(R.id.w7);
        ImageView w8 = (ImageView) view.findViewById(R.id.w8);

        String image1, image2, image3, image4;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FWhite%2Fw12.png?alt=media&token=ed10630d-980c-48f7-954b-921b7468921f";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FWhite%2Fw13.png?alt=media&token=e96aecc9-efc6-4405-ba25-9536fdbaf56d";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FWhite%2Fw14.png?alt=media&token=e565afbc-f789-4df1-b0c8-21028b188384";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FWhite%2Fw15.png?alt=media&token=b4fe94e1-c845-4c04-99ed-071567264385";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(w1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(w2);
        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(w3);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(w4);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(w5);
        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(w6);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(w7);
        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(w8);

        w1.setOnClickListener(this);
        w2.setOnClickListener(this);
        w3.setOnClickListener(this);
        w4.setOnClickListener(this);
        w5.setOnClickListener(this);
        w6.setOnClickListener(this);
        w7.setOnClickListener(this);
        w8.setOnClickListener(this);

        if(whiteTab.equals("4")){
            count = 0;
        }

        return view;
    }

    public void setAlpha(){
        ImageView imageView = (ImageView) view.findViewById(selectedId);
        imageView.setAlpha((float) 0.2);
        imageView.setOnClickListener(null);
    }

    public void countBlack(){
        count++;
        tvCount.setText("You Counted "+count+" White Pencils");
    }

    public void message(){
        if(count==4){
            tvCount.setText("You Counted All 4 White Pencils");
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.w1:
                    message();
                    countBlack();
                    correct.start();
                    selectedId = R.id.w1;
                    setAlpha();
                    break;
                case R.id.w2:
                    message();
                    wrong.start();
                    break;
                case R.id.w3:
                    message();
                    countBlack();
                    correct.start();
                    selectedId = R.id.w3;
                    setAlpha();
                    break;
                case R.id.w4:
                    message();
                    wrong.start();
                    break;
                case R.id.w5:
                    message();
                    wrong.start();
                    break;
                case R.id.w6:
                    message();
                    countBlack();
                    correct.start();
                    selectedId = R.id.w6;
                    setAlpha();
                    break;
                case R.id.w7:
                    message();
                    wrong.start();
                    break;
                case R.id.w8:
                    message();
                    countBlack();
                    correct.start();
                    selectedId = R.id.w8;
                    setAlpha();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
