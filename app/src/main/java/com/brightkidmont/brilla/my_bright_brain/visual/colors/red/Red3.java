package com.brightkidmont.brilla.my_bright_brain.visual.colors.red;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.brightkidmont.brilla.R;
import com.squareup.picasso.Picasso;

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.red.RedActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.red.RedActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.red.RedActivity.wrong;

public class Red3 extends Fragment implements View.OnClickListener {

    private View view;
    private ImageView blue_wrong;
    private ImageView red_right;
    private ImageView green_wrong;
    private ImageView yellow_wrong;
    private int selectedId = R.id.rose2;

    public Red3(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.red3, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Color the fruits given below with red color");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_alizarin));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q.start();
            }
        });

        ImageView rose1 = (ImageView) view.findViewById(R.id.rose1);
        ImageView rose2 = (ImageView) view.findViewById(R.id.rose2);
        ImageView strawberry1 = (ImageView) view.findViewById(R.id.strawberry1);
        ImageView strawberry2 = (ImageView) view.findViewById(R.id.strawberry2);
        ImageView apple1 = (ImageView) view.findViewById(R.id.apple1);
        ImageView apple2 = (ImageView) view.findViewById(R.id.apple2);

        String image1, image2, image3, image4, image5, image6;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FRed%2Frose1.png?alt=media&token=075c8fbe-b7e1-4a98-ac9e-93bddc837095";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FRed%2Frose2.png?alt=media&token=fe36b431-bfe2-4a7b-a568-8b9f46cc4709";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FRed%2Fstrawberry1.png?alt=media&token=26d813b4-03b3-450b-9884-e8cf5036e4f2";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FRed%2Fstrawberry2.png?alt=media&token=a9415363-479a-4e86-8651-02e2333e3911";
        image5 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FRed%2Fapple1.png?alt=media&token=5afa631f-2704-4b80-8493-31dad70b2d81";
        image6 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FRed%2Fapple2.png?alt=media&token=437b05e5-9569-4da9-81ac-7e7baf00cf43";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(rose1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(rose2);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(strawberry1);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(strawberry2);
        Picasso.with(getContext()).load(image5).error(R.drawable.bright_kid_bg).into(apple1);
        Picasso.with(getContext()).load(image6).error(R.drawable.bright_kid_bg).into(apple2);

        rose2.setVisibility(View.GONE);
        strawberry2.setVisibility(View.GONE);
        apple2.setVisibility(View.GONE);

        ImageView blue = (ImageView) view.findViewById(R.id.blue);
        ImageView red = (ImageView) view.findViewById(R.id.red);
        ImageView green = (ImageView) view.findViewById(R.id.green);
        ImageView yellow = (ImageView) view.findViewById(R.id.yellow);

        blue_wrong = (ImageView) view.findViewById(R.id.blue_wrong);
        red_right = (ImageView) view.findViewById(R.id.red_right);
        green_wrong = (ImageView) view.findViewById(R.id.green_wrong);
        yellow_wrong = (ImageView) view.findViewById(R.id.yellow_wrong);

        blue_wrong.setVisibility(View.INVISIBLE);
        red_right.setVisibility(View.INVISIBLE);
        green_wrong.setVisibility(View.INVISIBLE);
        yellow_wrong.setVisibility(View.INVISIBLE);

        rose1.setOnClickListener(this);
        strawberry1.setOnClickListener(this);
        apple1.setOnClickListener(this);

        blue.setOnClickListener(this);
        red.setOnClickListener(this);
        green.setOnClickListener(this);
        yellow.setOnClickListener(this);

        return view;
    }

    public void transition_anim(){
        ImageView imageView = (ImageView) view.findViewById(selectedId);
        Animation fade_out = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        imageView.setVisibility(View.VISIBLE);
        imageView.startAnimation(fade_out);
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                blue_wrong.setVisibility(View.INVISIBLE);
                red_right.setVisibility(View.INVISIBLE);
                green_wrong.setVisibility(View.INVISIBLE);
                yellow_wrong.setVisibility(View.INVISIBLE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.rose1:
                    selectedId = R.id.rose2;
                    break;
                case R.id.strawberry1:
                    selectedId = R.id.strawberry2;
                    break;
                case R.id.apple1:
                    selectedId = R.id.apple2;
                    break;
                case R.id.blue:
                    wrong.start();
                    blue_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.red:
                    correct.start();
                    red_right.setVisibility(View.VISIBLE);
                    hideView();
                    transition_anim();
                    break;
                case R.id.green:
                    wrong.start();
                    green_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.yellow:
                    wrong.start();
                    yellow_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
            }
        }
    }

}
