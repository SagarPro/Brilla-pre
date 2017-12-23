package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.square;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.square.SquareActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.square.SquareActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.square.SquareActivity.wrong;

public class Square4 extends Fragment implements View.OnClickListener{

    private ImageView square1;
    private ImageView square2;
    private ImageView clock;
    private ImageView square3;
    private TextView tvCount;
    private int count = 0;

    public Square4(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.square4, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Count the square shaped objects");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        tvCount = (TextView) view.findViewById(R.id.tvCount);

        square1 = (ImageView) view.findViewById(R.id.square1);
        ImageView rect1 = (ImageView) view.findViewById(R.id.rect1);
        ImageView star2 = (ImageView) view.findViewById(R.id.star2);
        square2 = (ImageView) view.findViewById(R.id.square2);
        ImageView star3 = (ImageView) view.findViewById(R.id.star3);
        square3 = (ImageView) view.findViewById(R.id.square3);
        ImageView rect3 = (ImageView) view.findViewById(R.id.rect3);
        clock = (ImageView) view.findViewById(R.id.clock);

        String image1, image2, image3, image4, image5, image6, image7, image8;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FSquare%2Fsquare1.png?alt=media&token=58f742f0-5a68-4fd5-a2c5-f547848defdb";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FRectangle%2Ftv.png?alt=media&token=0fcda6d0-52cb-4d72-89f1-b5248c3f0419";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FStar%2Fstar2.png?alt=media&token=d2f40c33-9573-48d7-8380-20d6c6470a56";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FSquare%2Fsquare2.png?alt=media&token=d64bb67b-2d6b-4595-bff6-a7b66b7b5fb4";
        image5 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FStar%2Fstar3.png?alt=media&token=7f8d6294-c035-4ed4-8087-e2519faaeeae";
        image6 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FSquare%2Fsquare3.png?alt=media&token=79330c9c-6291-4020-9515-30867594be11";
        image7 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FRectangle%2Fpillow.png?alt=media&token=65d9a572-4ac8-4021-b9d7-68c0b7b470fd";
        image8 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FSquare%2Fclock.png?alt=media&token=58720b13-f865-482d-8435-e4e60f85e0b9";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(square1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(rect1);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(star2);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(square2);
        Picasso.with(getContext()).load(image5).error(R.drawable.bright_kid_bg).into(star3);
        Picasso.with(getContext()).load(image6).error(R.drawable.bright_kid_bg).into(square3);
        Picasso.with(getContext()).load(image7).error(R.drawable.bright_kid_bg).into(rect3);
        Picasso.with(getContext()).load(image8).error(R.drawable.bright_kid_bg).into(clock);

        square1.setOnClickListener(this);
        rect1.setOnClickListener(this);
        star2.setOnClickListener(this);
        square2.setOnClickListener(this);
        star3.setOnClickListener(this);
        square3.setOnClickListener(this);
        rect3.setOnClickListener(this);
        clock.setOnClickListener(this);

        return view;
    }

    public void countSquare(){
        count++;
        tvCount.setText("You Counted "+count+" Squares");
    }

    public void message(){
        if(count==4){
            tvCount.setText("You Counted all the Squares");
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.square1:
                    message();
                    countSquare();
                    correct.start();
                    square1.setAlpha((float) 0.2);
                    square1.setOnClickListener(null);
                    break;
                case R.id.rect1:
                    message();
                    wrong.start();
                    break;
                case R.id.star2:
                    message();
                    wrong.start();
                    break;
                case R.id.square2:
                    message();
                    countSquare();
                    correct.start();
                    square2.setAlpha((float) 0.2);
                    square2.setOnClickListener(null);
                    break;
                case R.id.star3:
                    message();
                    wrong.start();
                    break;
                case R.id.square3:
                    message();
                    countSquare();
                    correct.start();
                    square3.setAlpha((float) 0.2);
                    square3.setOnClickListener(null);
                    break;
                case R.id.rect3:
                    message();
                    wrong.start();
                    break;
                case R.id.clock:
                    message();
                    countSquare();
                    correct.start();
                    clock.setAlpha((float) 0.2);
                    clock.setOnClickListener(null);
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
