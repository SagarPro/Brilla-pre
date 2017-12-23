package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.pentagon;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.pentagon.PentagonActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.pentagon.PentagonActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.pentagon.PentagonActivity.wrong;

public class Pentagon4 extends Fragment implements View.OnClickListener{

    private ImageView pentagon1;
    private ImageView pentagon2;
    private ImageView pentagon3;
    private TextView tvCount;
    private int count = 0;

    public Pentagon4(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.pentagon4, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Count the pentagon shaped objects");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        tvCount = (TextView) view.findViewById(R.id.tvCount);

        ImageView square1 = (ImageView) view.findViewById(R.id.square1);
        ImageView oval1 = (ImageView) view.findViewById(R.id.oval1);
        ImageView star2 = (ImageView) view.findViewById(R.id.star2);
        pentagon1 = (ImageView) view.findViewById(R.id.pentagon1);
        pentagon2 = (ImageView) view.findViewById(R.id.pentagon2);
        pentagon3 = (ImageView) view.findViewById(R.id.pentagon3);
        ImageView clock = (ImageView) view.findViewById(R.id.clock);
        ImageView oval4 = (ImageView) view.findViewById(R.id.oval4);

        String image1, image2, image3, image4, image5, image6, image7, image8;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FSquare%2Fsquare1.png?alt=media&token=58f742f0-5a68-4fd5-a2c5-f547848defdb";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FOval%2Foval1.png?alt=media&token=2544d940-655d-4292-a07f-fa2320865542";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FStar%2Fstar2.png?alt=media&token=d2f40c33-9573-48d7-8380-20d6c6470a56";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FPentagon%2Fpentagon1.png?alt=media&token=8f656c01-ec14-4e8a-952f-84f99c5efcd3";
        image5 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FPentagon%2Fpentagon2.jpg?alt=media&token=b2053c68-4d50-40c7-8f86-5b764db58235";
        image6 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FRectangle%2Fpillow.png?alt=media&token=65d9a572-4ac8-4021-b9d7-68c0b7b470fd";
        image7 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FPentagon%2Fpentagon3.png?alt=media&token=b1eaa0e1-61d4-4ac2-9d7b-d827b13a07be";
        image8 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FOval%2Foval4.png?alt=media&token=ebe66bd4-4055-4ca6-bc0a-64aefa0d4570";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(square1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(oval1);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(star2);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(pentagon1);
        Picasso.with(getContext()).load(image5).error(R.drawable.bright_kid_bg).into(pentagon2);
        Picasso.with(getContext()).load(image6).error(R.drawable.bright_kid_bg).into(clock);
        Picasso.with(getContext()).load(image7).error(R.drawable.bright_kid_bg).into(pentagon3);
        Picasso.with(getContext()).load(image8).error(R.drawable.bright_kid_bg).into(oval4);

        square1.setOnClickListener(this);
        oval1.setOnClickListener(this);
        star2.setOnClickListener(this);
        pentagon1.setOnClickListener(this);
        pentagon2.setOnClickListener(this);
        pentagon3.setOnClickListener(this);
        clock.setOnClickListener(this);
        oval4.setOnClickListener(this);

        return view;
    }

    public void countSquare(){
        count++;
        tvCount.setText("You Counted "+count+" Pentagons");
    }

    public void message(){
        if(count==3){
            tvCount.setText("You Counted all the Pentagons");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.square1:
                    message();
                    wrong.start();
                    break;
                case R.id.oval1:
                    message();
                    wrong.start();
                    break;
                case R.id.star2:
                    message();
                    wrong.start();
                    break;
                case R.id.pentagon1:
                    message();
                    countSquare();
                    correct.start();
                    pentagon1.setAlpha((float) 0.2);
                    pentagon1.setOnClickListener(null);
                    break;
                case R.id.pentagon2:
                    message();
                    countSquare();
                    correct.start();
                    pentagon2.setAlpha((float) 0.2);
                    pentagon2.setOnClickListener(null);
                    break;
                case R.id.clock:
                    message();
                    wrong.start();
                    break;
                case R.id.pentagon3:
                    message();
                    countSquare();
                    correct.start();
                    pentagon3.setAlpha((float) 0.2);
                    pentagon3.setOnClickListener(null);
                    break;
                case R.id.oval4:
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
