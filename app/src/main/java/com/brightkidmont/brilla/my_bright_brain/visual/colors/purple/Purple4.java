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

public class Purple4 extends Fragment implements View.OnClickListener {

    private ImageView ivpr1, ivpr2, ivpr3, ivpr4, ivpr5, ivpr6;

    public Purple4(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.purple4, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Identify purple colored objects");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);

        ImageView pr1 = (ImageView) view.findViewById(R.id.pr1);
        ImageView pr2 = (ImageView) view.findViewById(R.id.pr2);
        ImageView pr3 = (ImageView) view.findViewById(R.id.pr3);
        ImageView pr4 = (ImageView) view.findViewById(R.id.pr4);
        ImageView pr5 = (ImageView) view.findViewById(R.id.pr5);
        ImageView pr6 = (ImageView) view.findViewById(R.id.pr6);

        String image1, image2, image3, image4, image5, image6;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPink%2Fp6.png?alt=media&token=294d116f-dc5a-454a-8107-7c48bb225a50";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPink%2Fp7.png?alt=media&token=b7fc7754-a80a-4365-a678-664e93a68aea";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPurple%2Fpr6.png?alt=media&token=7826051d-e50f-48b5-a6c7-fba67a20ded0";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPurple%2Fpr7.png?alt=media&token=dfe729bc-3a0d-4627-bca8-3630f5f7fc28";
        image5 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPink%2Fp9.png?alt=media&token=1f8ac261-9a51-41d8-9379-77900a7f19f9";
        image6 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FPurple%2Fpr8.png?alt=media&token=a3521145-77e1-4bce-afe6-9cf2ae726396";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(pr1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(pr2);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(pr3);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(pr4);
        Picasso.with(getContext()).load(image5).error(R.drawable.bright_kid_bg).into(pr5);
        Picasso.with(getContext()).load(image6).error(R.drawable.bright_kid_bg).into(pr6);

        ivpr1 = (ImageView) view.findViewById(R.id.ivpr1);
        ivpr2 = (ImageView) view.findViewById(R.id.ivpr2);
        ivpr3 = (ImageView) view.findViewById(R.id.ivpr3);
        ivpr4 = (ImageView) view.findViewById(R.id.ivpr4);
        ivpr5 = (ImageView) view.findViewById(R.id.ivpr5);
        ivpr6 = (ImageView) view.findViewById(R.id.ivpr6);

        ivpr1.setVisibility(View.INVISIBLE);
        ivpr2.setVisibility(View.INVISIBLE);
        ivpr3.setVisibility(View.INVISIBLE);
        ivpr4.setVisibility(View.INVISIBLE);
        ivpr5.setVisibility(View.INVISIBLE);
        ivpr6.setVisibility(View.INVISIBLE);

        speaker.setOnClickListener(this);

        pr1.setOnClickListener(this);
        pr2.setOnClickListener(this);
        pr3.setOnClickListener(this);
        pr4.setOnClickListener(this);
        pr5.setOnClickListener(this);
        pr6.setOnClickListener(this);

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
                ivpr4.setVisibility(View.INVISIBLE);
                ivpr5.setVisibility(View.INVISIBLE);
                ivpr6.setVisibility(View.INVISIBLE);
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
                case R.id.pr4:
                    correct.start();
                    ivpr4.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.pr5:
                    wrong.start();
                    ivpr5.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.pr6:
                    correct.start();
                    ivpr6.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
