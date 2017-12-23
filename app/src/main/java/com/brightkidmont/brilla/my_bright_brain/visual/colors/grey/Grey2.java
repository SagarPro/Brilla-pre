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

public class Grey2 extends Fragment implements View.OnClickListener{

    private ImageView gr1_right, gr2_right, gr3_wrong, gr4_right, gr5_wrong, gr6_wrong;

    public Grey2(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.grey2, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Find the grey colored Animals");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.f_grey));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);

        ImageView gr1 = (ImageView) view.findViewById(R.id.gr1);
        ImageView gr2 = (ImageView) view.findViewById(R.id.gr2);
        ImageView gr3 = (ImageView) view.findViewById(R.id.gr3);
        ImageView gr4 = (ImageView) view.findViewById(R.id.gr4);
        ImageView gr5 = (ImageView) view.findViewById(R.id.gr5);
        ImageView gr6 = (ImageView) view.findViewById(R.id.gr6);

        String image1, image2, image3, image4, image5, image6;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FGrey%2Fgr5.png?alt=media&token=a04ecaf0-06bd-461f-9c60-a44467fe25d3";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FGrey%2Fgr3.png?alt=media&token=0f0085a9-ef8d-4ea5-81fe-025e5a616323";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FGrey%2Fgr4.png?alt=media&token=244aeeeb-eaf1-4de8-b912-6147bf46316d";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FGrey%2Fgr7.png?alt=media&token=8876d9aa-9b76-4950-a6ab-59421f410711";
        image5 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FGrey%2Fgr8.png?alt=media&token=157e5f07-33a9-4597-a3b8-5ee52cf7d9d7";
        image6 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FGrey%2Fgr6.png?alt=media&token=96629e58-ab30-48a9-9292-a0c3e7646110";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(gr1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(gr2);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(gr3);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(gr4);
        Picasso.with(getContext()).load(image5).error(R.drawable.bright_kid_bg).into(gr5);
        Picasso.with(getContext()).load(image6).error(R.drawable.bright_kid_bg).into(gr6);

        gr1_right = (ImageView) view.findViewById(R.id.gr1_right);
        gr2_right = (ImageView) view.findViewById(R.id.gr2_right);
        gr3_wrong = (ImageView) view.findViewById(R.id.gr3_wrong);
        gr4_right = (ImageView) view.findViewById(R.id.gr4_right);
        gr5_wrong = (ImageView) view.findViewById(R.id.gr5_wrong);
        gr6_wrong = (ImageView) view.findViewById(R.id.gr6_wrong);

        gr1_right.setVisibility(View.INVISIBLE);
        gr2_right.setVisibility(View.INVISIBLE);
        gr3_wrong.setVisibility(View.INVISIBLE);
        gr4_right.setVisibility(View.INVISIBLE);
        gr5_wrong.setVisibility(View.INVISIBLE);
        gr6_wrong.setVisibility(View.INVISIBLE);

        speaker.setOnClickListener(this);

        gr1.setOnClickListener(this);
        gr2.setOnClickListener(this);
        gr3.setOnClickListener(this);
        gr4.setOnClickListener(this);
        gr5.setOnClickListener(this);
        gr6.setOnClickListener(this);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gr1_right.setVisibility(View.INVISIBLE);
                gr2_right.setVisibility(View.INVISIBLE);
                gr3_wrong.setVisibility(View.INVISIBLE);
                gr4_right.setVisibility(View.INVISIBLE);
                gr5_wrong.setVisibility(View.INVISIBLE);
                gr6_wrong.setVisibility(View.INVISIBLE);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.gr1:
                    correct.start();
                    gr1_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.gr2:
                    correct.start();
                    gr2_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.gr3:
                    wrong.start();
                    gr3_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.gr4:
                    correct.start();
                    gr4_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.gr5:
                    wrong.start();
                    gr5_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.gr6:
                    wrong.start();
                    gr6_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
