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

public class Grey1 extends Fragment implements View.OnClickListener{

    private ImageView grey_right;
    private ImageView red_wrong;
    private ImageView green_wrong;

    public Grey1(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.grey1, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Select the color of the rabbit");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.f_grey));

        ImageView gr1 = (ImageView) view.findViewById(R.id.gr1);
        ImageView grey_c = (ImageView) view.findViewById(R.id.grey_c);
        ImageView red_c = (ImageView) view.findViewById(R.id.red_c);
        ImageView green_c = (ImageView) view.findViewById(R.id.green_c);
        grey_right = (ImageView) view.findViewById(R.id.grey_right);
        red_wrong = (ImageView) view.findViewById(R.id.red_wrong);
        green_wrong = (ImageView) view.findViewById(R.id.green_wrong);

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);

        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FGrey%2Fgr1.png?alt=media&token=5562b813-eafe-4718-afdb-1da7e1d9cc03").error(R.drawable.bright_kid_bg).into(gr1);

        grey_right.setVisibility(View.GONE);
        red_wrong.setVisibility(View.GONE);
        green_wrong.setVisibility(View.GONE);

        grey_c.setOnClickListener(this);
        red_c.setOnClickListener(this);
        green_c.setOnClickListener(this);

        speaker.setOnClickListener(this);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                grey_right.setVisibility(View.GONE);
                red_wrong.setVisibility(View.GONE);
                green_wrong.setVisibility(View.GONE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.grey_c:
                    correct.start();
                    grey_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.red_c:
                    wrong.start();
                    red_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.green_c:
                    wrong.start();
                    green_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
