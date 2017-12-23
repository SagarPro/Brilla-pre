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

public class White1 extends Fragment implements View.OnClickListener {

    private ImageView ivw1, ivw2, ivw3, ivw4, ivw5, ivw6, ivw7, ivw8, ivw9, ivw10, ivw11;

    public White1(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.white1, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Identify all the white colored stars");
        flash_header.setTextColor(getResources().getColor(R.color.f_black));

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_clouds));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setImageResource(R.drawable.speaker_b);
        speaker.setOnClickListener(this);

        ImageView w1 = (ImageView) view.findViewById(R.id.w1);
        ImageView w2 = (ImageView) view.findViewById(R.id.w2);
        ImageView w3 = (ImageView) view.findViewById(R.id.w3);
        ImageView w4 = (ImageView) view.findViewById(R.id.w4);
        ImageView w5 = (ImageView) view.findViewById(R.id.w5);
        ImageView w6 = (ImageView) view.findViewById(R.id.w6);
        ImageView w7 = (ImageView) view.findViewById(R.id.w7);
        ImageView w8 = (ImageView) view.findViewById(R.id.w8);
        ImageView w9 = (ImageView) view.findViewById(R.id.w9);
        ImageView w10 = (ImageView) view.findViewById(R.id.w10);
        ImageView w11 = (ImageView) view.findViewById(R.id.w11);

        String image1, image2, image3, image4;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FWhite%2Fmoon.png?alt=media&token=5a3f007c-e25a-4242-b597-31bd8c46ce70";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FWhite%2Fw1.png?alt=media&token=58bf9a4d-d313-4b4b-ba6b-626c59f0630c";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FWhite%2Fwhite_star.png?alt=media&token=648033fb-a453-4049-86ef-06ebb2f0d314";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FWhite%2Fcloud.png?alt=media&token=617676a8-df0c-4049-9ea3-ef9dc42d5ad0";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(w1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(w2);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(w3);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(w4);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(w5);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(w6);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(w7);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(w8);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(w9);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(w10);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(w11);

        ivw1 = (ImageView) view.findViewById(R.id.ivw1);
        ivw2 = (ImageView) view.findViewById(R.id.ivw2);
        ivw3 = (ImageView) view.findViewById(R.id.ivw3);
        ivw4 = (ImageView) view.findViewById(R.id.ivw4);
        ivw5 = (ImageView) view.findViewById(R.id.ivw5);
        ivw6 = (ImageView) view.findViewById(R.id.ivw6);
        ivw7 = (ImageView) view.findViewById(R.id.ivw7);
        ivw8 = (ImageView) view.findViewById(R.id.ivw8);
        ivw9 = (ImageView) view.findViewById(R.id.ivw9);
        ivw10 = (ImageView) view.findViewById(R.id.ivw10);
        ivw11 = (ImageView) view.findViewById(R.id.ivw11);

        hide();

        w1.setOnClickListener(this);
        w2.setOnClickListener(this);
        w3.setOnClickListener(this);
        w4.setOnClickListener(this);
        w5.setOnClickListener(this);
        w6.setOnClickListener(this);
        w7.setOnClickListener(this);
        w8.setOnClickListener(this);
        w9.setOnClickListener(this);
        w10.setOnClickListener(this);
        w11.setOnClickListener(this);

        return view;
    }

    public void hide(){
        ivw1.setVisibility(View.INVISIBLE);
        ivw2.setVisibility(View.INVISIBLE);
        ivw3.setVisibility(View.INVISIBLE);
        ivw4.setVisibility(View.INVISIBLE);
        ivw5.setVisibility(View.INVISIBLE);
        ivw6.setVisibility(View.INVISIBLE);
        ivw7.setVisibility(View.INVISIBLE);
        ivw8.setVisibility(View.INVISIBLE);
        ivw9.setVisibility(View.INVISIBLE);
        ivw10.setVisibility(View.INVISIBLE);
        ivw11.setVisibility(View.INVISIBLE);
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hide();
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.w1:
                    wrong.start();
                    ivw1.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.w2:
                    wrong.start();
                    ivw2.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.w3:
                    wrong.start();
                    ivw3.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.w4:
                    wrong.start();
                    ivw4.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.w5:
                    correct.start();
                    ivw5.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.w6:
                    correct.start();
                    ivw6.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.w7:
                    correct.start();
                    ivw7.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.w8:
                    correct.start();
                    ivw8.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.w9:
                    correct.start();
                    ivw9.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.w10:
                    wrong.start();
                    ivw10.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.w11:
                    wrong.start();
                    ivw11.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }
}
