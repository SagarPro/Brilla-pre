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

public class Black1 extends Fragment implements View.OnClickListener {

    private ImageView b1_right, b2_wrong, b3_right, b4_wrong, b5_right, b6_right;

    public Black1(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.black1, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Identify black colored objects");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.f_black));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);

        ImageView b1 = (ImageView) view.findViewById(R.id.b1);
        ImageView b2 = (ImageView) view.findViewById(R.id.b2);
        ImageView b3 = (ImageView) view.findViewById(R.id.b3);
        ImageView b4 = (ImageView) view.findViewById(R.id.b4);
        ImageView b5 = (ImageView) view.findViewById(R.id.b5);
        ImageView b6 = (ImageView) view.findViewById(R.id.b6);

        String image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlack%2Fb1.png?alt=media&token=67bef59b-597e-4f4d-bc74-80318332ed15";
        String image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlack%2Fb2.png?alt=media&token=17fdaf53-dd10-40ff-8233-0edaa8a7846f";
        String image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlack%2Fb3.png?alt=media&token=78541d14-3bad-4333-8b6b-d500716ea2f6";
        String image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlack%2Fb4.png?alt=media&token=448cb83d-93b6-439e-b960-cb6010efa59b";
        String image5 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlack%2Fb5.png?alt=media&token=08415e98-6265-4708-b5ef-e57b02b00b58";
        String image6 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlack%2Fb6.png?alt=media&token=cfbc4a9a-b82c-4ebb-ac5e-9f5f5205821b";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(b1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(b2);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(b3);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(b4);
        Picasso.with(getContext()).load(image5).error(R.drawable.bright_kid_bg).into(b5);
        Picasso.with(getContext()).load(image6).error(R.drawable.bright_kid_bg).into(b6);

        b1_right = (ImageView) view.findViewById(R.id.b1_right);
        b2_wrong = (ImageView) view.findViewById(R.id.b2_wrong);
        b3_right = (ImageView) view.findViewById(R.id.b3_right);
        b4_wrong = (ImageView) view.findViewById(R.id.b4_wrong);
        b5_right = (ImageView) view.findViewById(R.id.b5_right);
        b6_right = (ImageView) view.findViewById(R.id.b6_right);

        b1_right.setVisibility(View.INVISIBLE);
        b2_wrong.setVisibility(View.INVISIBLE);
        b3_right.setVisibility(View.INVISIBLE);
        b4_wrong.setVisibility(View.INVISIBLE);
        b5_right.setVisibility(View.INVISIBLE);
        b6_right.setVisibility(View.INVISIBLE);

        speaker.setOnClickListener(this);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                b1_right.setVisibility(View.INVISIBLE);
                b2_wrong.setVisibility(View.INVISIBLE);
                b3_right.setVisibility(View.INVISIBLE);
                b4_wrong.setVisibility(View.INVISIBLE);
                b5_right.setVisibility(View.INVISIBLE);
                b6_right.setVisibility(View.INVISIBLE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.b1:
                    correct.start();
                    b1_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.b2:
                    wrong.start();
                    b2_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.b3:
                    correct.start();
                    b3_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.b4:
                    wrong.start();
                    b4_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.b5:
                    correct.start();
                    b5_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.b6:
                    correct.start();
                    b6_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
