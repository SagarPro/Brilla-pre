package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.star;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.brightkidmont.brilla.R;
import com.squareup.picasso.Picasso;

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.star.StarActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.star.StarActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.star.StarActivity.wrong;

public class Star7 extends Fragment implements View.OnClickListener{

    private ImageView star1;
    private ImageView star2;
    private ImageView star3;
    private ImageView star4;
    private TextView tvCount;
    private int count = 0;

    public Star7(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.star7, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Count the star shaped objects");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        tvCount = (TextView) view.findViewById(R.id.tvCount);

        ImageView clock = (ImageView) view.findViewById(R.id.clock);
        star1 = (ImageView) view.findViewById(R.id.star1);
        star2 = (ImageView) view.findViewById(R.id.star2);
        ImageView vlc = (ImageView) view.findViewById(R.id.vlc);
        star3 = (ImageView) view.findViewById(R.id.star3);
        ImageView choco = (ImageView) view.findViewById(R.id.choco);
        star4 = (ImageView) view.findViewById(R.id.star4);
        ImageView pillow = (ImageView) view.findViewById(R.id.pillow);

        String image1, image2, image3, image4, image5, image6, image7, image8;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FSquare%2Fclock.png?alt=media&token=58720b13-f865-482d-8435-e4e60f85e0b9";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FStar%2Fstar1.png?alt=media&token=a51e004c-d7be-4af6-bd59-547c9af59b7e";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FStar%2Fstar2.png?alt=media&token=d2f40c33-9573-48d7-8380-20d6c6470a56";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FStar%2Fvlc.png?alt=media&token=dbf31a41-dfa1-445e-bbbf-e508830305dc";
        image5 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FStar%2Fstar3.png?alt=media&token=7f8d6294-c035-4ed4-8087-e2519faaeeae";
        image6 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FStar%2Fchoco.png?alt=media&token=8fc9b94e-1ce1-4871-bc0e-2a1ec5d0b8b1";
        image7 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FStar%2Fstar4.png?alt=media&token=71dd5dc5-73f3-4cbd-aa6b-2d8eb9ff740c";
        image8 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FRectangle%2Fpillow.png?alt=media&token=65d9a572-4ac8-4021-b9d7-68c0b7b470fd";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(clock);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(star1);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(star2);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(vlc);
        Picasso.with(getContext()).load(image5).error(R.drawable.bright_kid_bg).into(star3);
        Picasso.with(getContext()).load(image6).error(R.drawable.bright_kid_bg).into(choco);
        Picasso.with(getContext()).load(image7).error(R.drawable.bright_kid_bg).into(star4);
        Picasso.with(getContext()).load(image8).error(R.drawable.bright_kid_bg).into(pillow);

        clock.setOnClickListener(this);
        star1.setOnClickListener(this);
        star2.setOnClickListener(this);
        vlc.setOnClickListener(this);
        star3.setOnClickListener(this);
        choco.setOnClickListener(this);
        star4.setOnClickListener(this);
        pillow.setOnClickListener(this);

        return view;
    }

    public void countStar(){
        count++;
        tvCount.setText("You Counted "+count+" Stars");
    }

    public void message(){
        if(count>4){
            tvCount.setText("You Counted all the Stars");
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.clock:
                    message();
                    wrong.start();
                    break;
                case R.id.star1:
                    message();
                    countStar();
                    correct.start();
                    star1.setAlpha((float) 0.2);
                    star1.setOnClickListener(null);
                    break;
                case R.id.star2:
                    message();
                    countStar();
                    correct.start();
                    star2.setAlpha((float) 0.2);
                    star2.setOnClickListener(null);
                    break;
                case R.id.vlc:
                    message();
                    wrong.start();
                    break;
                case R.id.star3:
                    message();
                    countStar();
                    correct.start();
                    star3.setAlpha((float) 0.2);
                    star3.setOnClickListener(null);
                    break;
                case R.id.choco:
                    message();
                    wrong.start();
                    break;
                case R.id.star4:
                    message();
                    countStar();
                    correct.start();
                    star4.setAlpha((float) 0.2);
                    star4.setOnClickListener(null);
                    break;
                case R.id.pillow:
                    message();
                    wrong.start();
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
