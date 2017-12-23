package com.brightkidmont.brilla.my_bright_brain.visual.colors.white;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.white.WhiteActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.white.WhiteActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.white.WhiteActivity.wrong;

public class White5 extends Fragment implements View.OnClickListener {

    private ImageView ivw1;
    private ImageView ivw2;
    private ImageView ivw3;

    public White5(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.white5, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Select the white colored vegetables");
        flash_header.setTextColor(getResources().getColor(R.color.f_black));

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_clouds));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setImageResource(R.drawable.speaker_b);
        speaker.setOnClickListener(this);

        ImageView w1 = (ImageView) view.findViewById(R.id.w1);
        ImageView w2 = (ImageView) view.findViewById(R.id.w2);
        ImageView w3 = (ImageView) view.findViewById(R.id.w3);

        String image1, image2, image3;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FWhite%2Fw16.png?alt=media&token=282fec0b-214b-4636-a6e0-dacf3063af13";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FWhite%2Fw18.png?alt=media&token=cb744ab3-cc78-4dad-80f9-48975a0d5b4f";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FWhite%2Fw17.png?alt=media&token=0fded98c-f533-493c-8350-4aa2b5c167da";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(w1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(w2);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(w3);

        ivw1 = (ImageView) view.findViewById(R.id.ivw1);
        ivw2 = (ImageView) view.findViewById(R.id.ivw2);
        ivw3 = (ImageView) view.findViewById(R.id.ivw3);

        ivw1.setVisibility(View.INVISIBLE);
        ivw2.setVisibility(View.INVISIBLE);
        ivw3.setVisibility(View.INVISIBLE);

        w1.setOnClickListener(this);
        w2.setOnClickListener(this);
        w3.setOnClickListener(this);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ivw1.setVisibility(View.INVISIBLE);
                ivw2.setVisibility(View.INVISIBLE);
                ivw3.setVisibility(View.INVISIBLE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.w1:
                    correct.start();
                    ivw1.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.w2:
                    correct.start();
                    ivw2.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.w3:
                    wrong.start();
                    ivw3.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
