package com.brightkidmont.brilla.my_bright_brain.visual.colors.orange;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.orange.OrangeActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.orange.OrangeActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.orange.OrangeActivity.wrong;

public class Orange2 extends Fragment implements View.OnClickListener {

    private ImageView o2_wrong, o1_right, o4_wrong, o3_right, o8_wrong, o6_right;

    public Orange2(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.orange2, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Select orange colored objects");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_carrot));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);

        ImageView o2 = (ImageView) view.findViewById(R.id.o2);
        ImageView o1 = (ImageView) view.findViewById(R.id.o1);
        ImageView o4 = (ImageView) view.findViewById(R.id.o4);
        ImageView o3 = (ImageView) view.findViewById(R.id.o3);
        ImageView o8 = (ImageView) view.findViewById(R.id.o8);
        ImageView o6 = (ImageView) view.findViewById(R.id.o6);

        String image1, image2, image3, image4, image5, image6;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FOrange%2Fo2.png?alt=media&token=3d8ab4ae-3971-44c4-bce8-82fe611556cf";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FOrange%2Fo1.png?alt=media&token=ee5d8bc9-477b-41ad-aa10-9bd14c2a8083";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FOrange%2Fo4.png?alt=media&token=e15608ac-18d2-41ea-b64e-f84b8551ac3e";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FOrange%2Fo3.png?alt=media&token=92535f96-ce2e-4e5b-8b92-3e34d88d6e89";
        image5 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FOrange%2Fo8.png?alt=media&token=cc6edf83-2f23-42aa-a362-c58c03bf3636";
        image6 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FOrange%2Fo6.png?alt=media&token=d02e337f-42de-4675-afe2-744884eb5085";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(o2);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(o1);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(o4);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(o3);
        Picasso.with(getContext()).load(image5).error(R.drawable.bright_kid_bg).into(o8);
        Picasso.with(getContext()).load(image6).error(R.drawable.bright_kid_bg).into(o6);

        o2_wrong = (ImageView) view.findViewById(R.id.o2_wrong);
        o1_right = (ImageView) view.findViewById(R.id.o1_right);
        o4_wrong = (ImageView) view.findViewById(R.id.o4_wrong);
        o3_right = (ImageView) view.findViewById(R.id.o3_right);
        o8_wrong = (ImageView) view.findViewById(R.id.o8_right);
        o6_right = (ImageView) view.findViewById(R.id.o6_right);

        o2_wrong.setVisibility(View.INVISIBLE);
        o1_right.setVisibility(View.INVISIBLE);
        o4_wrong.setVisibility(View.INVISIBLE);
        o3_right.setVisibility(View.INVISIBLE);
        o8_wrong.setVisibility(View.INVISIBLE);
        o6_right.setVisibility(View.INVISIBLE);

        speaker.setOnClickListener(this);

        o2.setOnClickListener(this);
        o1.setOnClickListener(this);
        o4.setOnClickListener(this);
        o3.setOnClickListener(this);
        o8.setOnClickListener(this);
        o6.setOnClickListener(this);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                o2_wrong.setVisibility(View.INVISIBLE);
                o1_right.setVisibility(View.INVISIBLE);
                o4_wrong.setVisibility(View.INVISIBLE);
                o3_right.setVisibility(View.INVISIBLE);
                o8_wrong.setVisibility(View.INVISIBLE);
                o6_right.setVisibility(View.INVISIBLE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.o2:
                    wrong.start();
                    o2_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.o1:
                    correct.start();
                    o1_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.o4:
                    wrong.start();
                    o4_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.o3:
                    correct.start();
                    o3_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.o8:
                    wrong.start();
                    o8_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.o6:
                    correct.start();
                    o6_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }
}
