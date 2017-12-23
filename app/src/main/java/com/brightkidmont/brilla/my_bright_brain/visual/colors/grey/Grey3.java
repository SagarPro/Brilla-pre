package com.brightkidmont.brilla.my_bright_brain.visual.colors.grey;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.grey.GreyActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.grey.GreyActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.grey.GreyActivity.wrong;

public class Grey3 extends Fragment implements View.OnClickListener {

    private ImageView ivGrey, ivGreen, ivYellow, ivPink;

    public Grey3(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.grey3, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Select the color name of the baby Elephant");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.f_grey));

        ImageView elephant = (ImageView) view.findViewById(R.id.elephant);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FGrey%2Fgr10.png?alt=media&token=c4c03b96-4b96-4595-93cd-0e04469fda44").error(R.drawable.bright_kid_bg).into(elephant);

        TextView tvGrey = (TextView) view.findViewById(R.id.tvGrey);
        TextView tvGreen = (TextView) view.findViewById(R.id.tvGreen);
        TextView tvYellow = (TextView) view.findViewById(R.id.tvYellow);
        TextView tvPink = (TextView) view.findViewById(R.id.tvPink);

        ivGrey = (ImageView) view.findViewById(R.id.ivGrey);
        ivGreen = (ImageView) view.findViewById(R.id.ivGreen);
        ivYellow = (ImageView) view.findViewById(R.id.ivYellow);
        ivPink = (ImageView) view.findViewById(R.id.ivPink);

        ivGrey.setVisibility(View.INVISIBLE);
        ivGreen.setVisibility(View.INVISIBLE);
        ivYellow.setVisibility(View.INVISIBLE);
        ivPink.setVisibility(View.INVISIBLE);

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);

        speaker.setOnClickListener(this);

        tvGrey.setOnClickListener(this);
        tvGreen.setOnClickListener(this);
        tvYellow.setOnClickListener(this);
        tvPink.setOnClickListener(this);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ivGrey.setVisibility(View.INVISIBLE);
                ivGreen.setVisibility(View.INVISIBLE);
                ivYellow.setVisibility(View.INVISIBLE);
                ivPink.setVisibility(View.INVISIBLE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.tvGrey:
                    correct.start();
                    ivGrey.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.tvGreen:
                    ivGreen.setVisibility(View.VISIBLE);
                    wrong.start();
                    hideView();
                    break;
                case R.id.tvYellow:
                    wrong.start();
                    ivYellow.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.tvPink:
                    wrong.start();
                    ivPink.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
