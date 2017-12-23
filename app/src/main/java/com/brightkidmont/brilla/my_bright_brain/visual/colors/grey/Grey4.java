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

public class Grey4 extends Fragment implements View.OnClickListener {

    private ImageView gr1_right, gr2_wrong, gr3_right, gr4_right;

    public Grey4(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.grey4, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Find the grey colored animals");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.f_grey));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);

        ImageView gr1 = (ImageView) view.findViewById(R.id.gr1);
        ImageView gr2 = (ImageView) view.findViewById(R.id.gr2);
        ImageView gr3 = (ImageView) view.findViewById(R.id.gr3);
        ImageView gr4 = (ImageView) view.findViewById(R.id.gr4);

        String image1, image2, image3, image4;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FGrey%2Fgr1.png?alt=media&token=5562b813-eafe-4718-afdb-1da7e1d9cc03";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FGrey%2Fgr11.png?alt=media&token=cc88286b-392c-46c7-afd1-3d1657c77d80";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FGrey%2Fgr9.png?alt=media&token=dab1ac0d-8946-40f3-97b3-da65591009f2";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FGrey%2Fgr10.png?alt=media&token=c4c03b96-4b96-4595-93cd-0e04469fda44";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(gr1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(gr2);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(gr3);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(gr4);

        gr1_right = (ImageView) view.findViewById(R.id.gr1_right);
        gr2_wrong = (ImageView) view.findViewById(R.id.gr2_wrong);
        gr3_right = (ImageView) view.findViewById(R.id.gr3_right);
        gr4_right = (ImageView) view.findViewById(R.id.gr4_right);

        gr1_right.setVisibility(View.INVISIBLE);
        gr2_wrong.setVisibility(View.INVISIBLE);
        gr3_right.setVisibility(View.INVISIBLE);
        gr4_right.setVisibility(View.INVISIBLE);

        speaker.setOnClickListener(this);

        gr1.setOnClickListener(this);
        gr2.setOnClickListener(this);
        gr3.setOnClickListener(this);
        gr4.setOnClickListener(this);

        return view;
    }

    public void hideView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gr1_right.setVisibility(View.INVISIBLE);
                gr2_wrong.setVisibility(View.INVISIBLE);
                gr3_right.setVisibility(View.INVISIBLE);
                gr4_right.setVisibility(View.INVISIBLE);
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
                    wrong.start();
                    gr2_wrong.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.gr3:
                    correct.start();
                    gr3_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.gr4:
                    correct.start();
                    gr4_right.setVisibility(View.VISIBLE);
                    hideView();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
