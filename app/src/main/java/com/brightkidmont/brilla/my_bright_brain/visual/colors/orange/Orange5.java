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

public class Orange5 extends Fragment implements View.OnClickListener {

    private ImageView ivPurple, ivPink, ivYellow, ivOrange;

    public Orange5(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.orange5, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Select the color of the carrot");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_carrot));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        ImageView carrot = (ImageView) view.findViewById(R.id.carrot);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FOrange%2Fcarrot.png?alt=media&token=1c2070fb-3782-4296-9ff7-43bb460015d4").error(R.drawable.bright_kid_bg).into(carrot);

        TextView tvPurple = (TextView) view.findViewById(R.id.tvPurple);
        TextView tvPink = (TextView) view.findViewById(R.id.tvPink);
        TextView tvYellow = (TextView) view.findViewById(R.id.tvYellow);
        TextView tvOrange = (TextView) view.findViewById(R.id.tvOrange);

        ivPurple = (ImageView) view.findViewById(R.id.ivPurple);
        ivPink = (ImageView) view.findViewById(R.id.ivPink);
        ivYellow = (ImageView) view.findViewById(R.id.ivYellow);
        ivOrange = (ImageView) view.findViewById(R.id.ivOrange);

        ivPurple.setVisibility(View.INVISIBLE);
        ivPink.setVisibility(View.INVISIBLE);
        ivYellow.setVisibility(View.INVISIBLE);
        ivOrange.setVisibility(View.INVISIBLE);

        tvPurple.setOnClickListener(this);
        tvPink.setOnClickListener(this);
        tvYellow.setOnClickListener(this);
        tvOrange.setOnClickListener(this);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ivPurple.setVisibility(View.INVISIBLE);
                ivPink.setVisibility(View.INVISIBLE);
                ivYellow.setVisibility(View.INVISIBLE);
                ivOrange.setVisibility(View.INVISIBLE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.tvPurple:
                    wrong.start();
                    ivPurple.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.tvPink:
                    ivPink.setVisibility(View.VISIBLE);
                    wrong.start();
                    hideView();
                    break;
                case R.id.tvYellow:
                    wrong.start();
                    ivYellow.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.tvOrange:
                    correct.start();
                    ivOrange.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
