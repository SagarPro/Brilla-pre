package com.brightkidmont.brilla.my_bright_brain.visual.colors.purple;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.purple.PurpleActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.purple.PurpleActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.purple.PurpleActivity.wrong;

public class Purple6 extends Fragment implements View.OnClickListener {

    private ImageView ivpr1;
    private ImageView ivpr2;
    private ImageView ivpr3;
    View view;

    public Purple6(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.purple6, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Identify purple colored vegetable");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        ImageView pr1 = (ImageView) view.findViewById(R.id.pr1);
        ImageView pr2 = (ImageView) view.findViewById(R.id.pr2);
        ImageView pr3 = (ImageView) view.findViewById(R.id.pr3);

        String image1, image2, image3;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPurple%2Fpr10.png?alt=media&token=e04e53a6-fd88-49f1-98aa-eda8f7dc4151";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPurple%2Fpr11.png?alt=media&token=22ae211f-a1d3-46cc-9a02-400e240ddb5b";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPurple%2Fpr9.png?alt=media&token=0cbc8122-534d-4ec9-9489-a60f3228ebae";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(pr1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(pr2);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(pr3);

        ivpr1 = (ImageView) view.findViewById(R.id.ivpr1);
        ivpr2 = (ImageView) view.findViewById(R.id.ivpr2);
        ivpr3 = (ImageView) view.findViewById(R.id.ivpr3);

        ivpr1.setVisibility(View.INVISIBLE);
        ivpr2.setVisibility(View.INVISIBLE);
        ivpr3.setVisibility(View.INVISIBLE);

        pr1.setOnClickListener(this);
        pr2.setOnClickListener(this);
        pr3.setOnClickListener(this);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ivpr1.setVisibility(View.INVISIBLE);
                ivpr2.setVisibility(View.INVISIBLE);
                ivpr3.setVisibility(View.INVISIBLE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.pr1:
                    wrong.start();
                    ivpr1.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.pr2:
                    wrong.start();
                    ivpr2.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.pr3:
                    correct.start();
                    ivpr3.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }
}
