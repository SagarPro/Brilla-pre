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

public class White2 extends Fragment implements View.OnClickListener {

    private ImageView ivw1;
    private ImageView ivw2;

    public White2(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.white2, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Select the girl with white colored dress");
        flash_header.setTextColor(getResources().getColor(R.color.f_black));

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_clouds));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setImageResource(R.drawable.speaker_b);
        speaker.setOnClickListener(this);

        ImageView w1 = (ImageView) view.findViewById(R.id.w1);
        ImageView w2 = (ImageView) view.findViewById(R.id.w2);

        String image1, image2;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FWhite%2Fw2.png?alt=media&token=2491e435-ae9b-4829-a5f0-896178dad741";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FWhite%2Fw3.png?alt=media&token=287a9a5c-fdb1-4f3f-bc93-51b19d622ef5";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(w1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(w2);

        w1.setOnClickListener(this);
        w2.setOnClickListener(this);

        ivw1 = (ImageView) view.findViewById(R.id.ivw1);
        ivw2 = (ImageView) view.findViewById(R.id.ivw2);

        ivw1.setVisibility(View.INVISIBLE);
        ivw2.setVisibility(View.INVISIBLE);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ivw1.setVisibility(View.INVISIBLE);
                ivw2.setVisibility(View.INVISIBLE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.w1:
                    correct.start();
                    ivw1.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.w2:
                    wrong.start();
                    ivw2.setVisibility(View.VISIBLE);
                    hideView();
                    break;
            }
        }
    }
}
