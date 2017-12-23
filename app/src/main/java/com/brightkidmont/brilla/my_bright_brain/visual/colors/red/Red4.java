package com.brightkidmont.brilla.my_bright_brain.visual.colors.red;

import android.os.Bundle;
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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.red.RedActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.red.RedActivity.wrong;

public class Red4 extends Fragment implements View.OnClickListener {

    private View view;
    private ImageView bal7;
    private ImageView bal8;
    private ImageView bal9;
    private int selectedId;

    public Red4(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.red4, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Identify red balloons");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_alizarin));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q.start();
            }
        });

        ImageView bal1 = (ImageView) view.findViewById(R.id.bal1);
        ImageView bal2 = (ImageView) view.findViewById(R.id.bal2);
        ImageView bal3 = (ImageView) view.findViewById(R.id.bal3);
        ImageView bal4 = (ImageView) view.findViewById(R.id.bal4);
        ImageView bal5 = (ImageView) view.findViewById(R.id.bal5);
        ImageView bal6 = (ImageView) view.findViewById(R.id.bal6);
        bal7 = (ImageView) view.findViewById(R.id.bal7);
        bal8 = (ImageView) view.findViewById(R.id.bal8);
        bal9 = (ImageView) view.findViewById(R.id.bal9);

        String image1, image2, image3, image4;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlue%2Fballoon2.png?alt=media&token=2b3688bc-027a-464c-bcc3-eca4ecfe4ae1";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlue%2Fballoon3.png?alt=media&token=16170c5e-3efc-4925-bcf4-ca22407117e1";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlue%2Fballoon4.png?alt=media&token=b7d5cd70-4e80-454a-9f22-d84e436df08f";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlue%2Fballoon1.png?alt=media&token=dd2bb5cb-d43d-47d7-9e44-ae8b003f2a8e";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(bal1);
        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(bal2);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(bal3);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(bal4);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(bal5);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(bal6);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(bal7);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(bal8);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(bal9);

        speaker.setOnClickListener(this);

        bal7.setOnClickListener(this);
        bal8.setOnClickListener(this);
        bal9.setOnClickListener(this);

        return view;
    }

    public void transition_anim(){
        ImageView imageView = (ImageView) view.findViewById(selectedId);
        Animation translate_up = AnimationUtils.loadAnimation(getContext(), R.anim.translate_up);
        imageView.startAnimation(translate_up);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.bal7:
                    selectedId = R.id.bal7;
                    transition_anim();
                    bal7.setOnClickListener(null);
                    break;
                case R.id.bal8:
                    selectedId = R.id.bal8;
                    transition_anim();
                    bal8.setOnClickListener(null);
                    break;
                case R.id.bal9:
                    selectedId = R.id.bal9;
                    transition_anim();
                    bal9.setOnClickListener(null);
                    break;
            }
        }
    }

}
