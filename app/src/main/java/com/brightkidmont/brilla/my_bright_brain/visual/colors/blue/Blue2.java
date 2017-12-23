package com.brightkidmont.brilla.my_bright_brain.visual.colors.blue;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.blue.BlueActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.blue.BlueActivity.wrong;

public class Blue2 extends Fragment implements View.OnClickListener {

    private ImageView balloon1;
    private ImageView balloon2;
    private ImageView balloon3;
    private int selectedId;
    View view;

    public Blue2(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.blue2, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Identify blue balloons");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_belize_hole));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);

        balloon1 = (ImageView) view.findViewById(R.id.balloon1);
        balloon2 = (ImageView) view.findViewById(R.id.balloon2);
        balloon3 = (ImageView) view.findViewById(R.id.balloon3);
        ImageView balloon4 = (ImageView) view.findViewById(R.id.balloon4);
        ImageView balloon5 = (ImageView) view.findViewById(R.id.balloon5);
        ImageView balloon6 = (ImageView) view.findViewById(R.id.balloon6);
        ImageView balloon7 = (ImageView) view.findViewById(R.id.balloon7);
        ImageView balloon8 = (ImageView) view.findViewById(R.id.balloon8);
        ImageView balloon9 = (ImageView) view.findViewById(R.id.balloon9);

        String image1, image2, image3, image4;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlue%2Fballoon1.png?alt=media&token=dd2bb5cb-d43d-47d7-9e44-ae8b003f2a8e";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlue%2Fballoon2.png?alt=media&token=2b3688bc-027a-464c-bcc3-eca4ecfe4ae1";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlue%2Fballoon3.png?alt=media&token=16170c5e-3efc-4925-bcf4-ca22407117e1";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlue%2Fballoon4.png?alt=media&token=b7d5cd70-4e80-454a-9f22-d84e436df08f";

        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(balloon1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(balloon2);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(balloon3);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(balloon4);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(balloon5);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(balloon6);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(balloon7);
        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(balloon8);
        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(balloon9);

        speaker.setOnClickListener(this);

        balloon1.setOnClickListener(this);
        balloon2.setOnClickListener(this);
        balloon3.setOnClickListener(this);

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
                case R.id.balloon1:
                    selectedId = R.id.balloon1;
                    transition_anim();
                    balloon1.setOnClickListener(null);
                    break;
                case R.id.balloon2:
                    selectedId = R.id.balloon2;
                    transition_anim();
                    balloon2.setOnClickListener(null);
                    break;
                case R.id.balloon3:
                    selectedId = R.id.balloon3;
                    transition_anim();
                    balloon3.setOnClickListener(null);
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
