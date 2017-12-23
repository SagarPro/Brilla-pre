package com.brightkidmont.brilla.my_bright_brain.visual.colors.black;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.black.BlackActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.black.BlackActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.black.BlackActivity.wrong;

public class Black5 extends Fragment implements View.OnClickListener {

    private ImageView dress1_wrong;
    private ImageView dress2_right;
    private ImageView dress3_wrong;

    public Black5(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.black5, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Select black colored dress for the party");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.f_black));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        ImageView dress1 = (ImageView) view.findViewById(R.id.dress1);
        ImageView dress2 = (ImageView) view.findViewById(R.id.dress2);
        ImageView dress3 = (ImageView) view.findViewById(R.id.dress3);

        String image1, image2, image3;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlack%2Fdress1.png?alt=media&token=c0d0d928-0d1b-4297-9082-dab4c09355a4";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlack%2Fdress2.png?alt=media&token=06534f19-959e-4143-b931-c3edd1611573";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlack%2Fdress3.png?alt=media&token=de894e2d-9de1-4bec-9c49-b91d48b8f11f";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(dress1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(dress2);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(dress3);

        dress1.setOnClickListener(this);
        dress2.setOnClickListener(this);
        dress3.setOnClickListener(this);

        dress1_wrong = (ImageView) view.findViewById(R.id.dress1_wrong);
        dress2_right = (ImageView) view.findViewById(R.id.dress2_right);
        dress3_wrong = (ImageView) view.findViewById(R.id.dress3_wrong);

        dress1_wrong.setVisibility(View.GONE);
        dress2_right.setVisibility(View.GONE);
        dress3_wrong.setVisibility(View.GONE);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dress1_wrong.setVisibility(View.GONE);
                dress2_right.setVisibility(View.GONE);
                dress3_wrong.setVisibility(View.GONE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.dress1:
                    wrong.start();
                    dress1_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.dress2:
                    correct.start();
                    dress2_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.dress3:
                    wrong.start();
                    dress3_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
