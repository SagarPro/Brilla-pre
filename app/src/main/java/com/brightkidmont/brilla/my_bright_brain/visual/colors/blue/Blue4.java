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

public class Blue4 extends Fragment implements View.OnClickListener {

    private ImageView ivbb1, ivbb2, ivbb3, ivbb4, ivbb5, ivbb6, ivbb7;
    View view;

    public Blue4(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.blue4, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Identify the blue butterflies");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_belize_hole));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        ImageView bb1 = (ImageView) view.findViewById(R.id.bb1);
        ImageView bb2 = (ImageView) view.findViewById(R.id.bb2);
        ImageView bb3 = (ImageView) view.findViewById(R.id.bb3);
        ImageView bb4 = (ImageView) view.findViewById(R.id.bb4);
        ImageView bb5 = (ImageView) view.findViewById(R.id.bb5);
        ImageView bb6 = (ImageView) view.findViewById(R.id.bb6);
        ImageView bb7 = (ImageView) view.findViewById(R.id.bb7);

        String image1, image2, image3, image4, image5, image6;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlue%2Fbb1.png?alt=media&token=fa6aeb94-1b3c-4285-b1b4-17a039b0d511";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlue%2Fbb2.png?alt=media&token=3e8b40d9-3353-4372-8358-bdaecb5a7e85";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlue%2Fbb3.png?alt=media&token=0101a213-23e8-497a-9ead-e111a77aa82f";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlue%2Fbb4.png?alt=media&token=12eae3e6-31cf-4fa3-8df2-6505057b3c18";
        image5 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlue%2Fbb5.png?alt=media&token=42243d49-b2dd-40e3-99e6-4392ecd44a0e";
        image6 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlue%2Fbb6.png?alt=media&token=fd601fd0-2a5e-466e-907a-0c78ada4d918";

        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(bb1);
        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(bb2);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(bb3);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(bb4);
        Picasso.with(getContext()).load(image5).error(R.drawable.bright_kid_bg).into(bb5);
        Picasso.with(getContext()).load(image6).error(R.drawable.bright_kid_bg).into(bb6);
        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(bb7);

        ivbb1 = (ImageView) view.findViewById(R.id.ivbb1);
        ivbb2 = (ImageView) view.findViewById(R.id.ivbb2);
        ivbb3 = (ImageView) view.findViewById(R.id.ivbb3);
        ivbb4 = (ImageView) view.findViewById(R.id.ivbb4);
        ivbb5 = (ImageView) view.findViewById(R.id.ivbb5);
        ivbb6 = (ImageView) view.findViewById(R.id.ivbb6);
        ivbb7 = (ImageView) view.findViewById(R.id.ivbb7);

        ivbb1.setVisibility(View.INVISIBLE);
        ivbb2.setVisibility(View.INVISIBLE);
        ivbb3.setVisibility(View.INVISIBLE);
        ivbb4.setVisibility(View.INVISIBLE);
        ivbb5.setVisibility(View.INVISIBLE);
        ivbb6.setVisibility(View.INVISIBLE);
        ivbb7.setVisibility(View.INVISIBLE);

        bb1.setOnClickListener(this);
        bb2.setOnClickListener(this);
        bb3.setOnClickListener(this);
        bb4.setOnClickListener(this);
        bb5.setOnClickListener(this);
        bb6.setOnClickListener(this);
        bb7.setOnClickListener(this);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ivbb1.setVisibility(View.INVISIBLE);
                ivbb2.setVisibility(View.INVISIBLE);
                ivbb3.setVisibility(View.INVISIBLE);
                ivbb4.setVisibility(View.INVISIBLE);
                ivbb5.setVisibility(View.INVISIBLE);
                ivbb6.setVisibility(View.INVISIBLE);
                ivbb7.setVisibility(View.INVISIBLE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.bb1:
                    wrong.start();
                    ivbb1.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.bb2:
                    correct.start();
                    ivbb2.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.bb3:
                    wrong.start();
                    ivbb3.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.bb4:
                    correct.start();
                    ivbb4.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.bb5:
                    wrong.start();
                    ivbb5.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.bb6:
                    wrong.start();
                    ivbb6.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.bb7:
                    correct.start();
                    ivbb7.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }
}
