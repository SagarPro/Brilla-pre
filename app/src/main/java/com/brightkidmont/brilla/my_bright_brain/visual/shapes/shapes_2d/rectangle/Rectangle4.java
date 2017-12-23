package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rectangle;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rectangle.RectangleActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rectangle.RectangleActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rectangle.RectangleActivity.wrong;

public class Rectangle4 extends Fragment implements View.OnClickListener{

    private ImageView rect1;
    private ImageView rect2;
    private ImageView rect3;
    private ImageView rect4;
    private TextView tvCount;
    private int count = 0;

    public Rectangle4(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.rectangle4, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Count the rectangle shaped objects");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        tvCount = (TextView) view.findViewById(R.id.tvCount);

        ImageView tri1 = (ImageView) view.findViewById(R.id.tri1);
        rect1 = (ImageView) view.findViewById(R.id.rect1);
        ImageView star2 = (ImageView) view.findViewById(R.id.star2);
        rect2 = (ImageView) view.findViewById(R.id.rect2);
        ImageView star3 = (ImageView) view.findViewById(R.id.star3);
        rect3 = (ImageView) view.findViewById(R.id.rect3);
        rect4 = (ImageView) view.findViewById(R.id.rect4);
        ImageView clock = (ImageView) view.findViewById(R.id.clock);

        String image1, image2, image3, image4, image5, image6, image7, image8;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FTriangle%2Ftri1.png?alt=media&token=90ce4cfc-f645-40ed-9d85-37387ec0ae56";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FRectangle%2Ftv.png?alt=media&token=0fcda6d0-52cb-4d72-89f1-b5248c3f0419";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FStar%2Fstar2.png?alt=media&token=d2f40c33-9573-48d7-8380-20d6c6470a56";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FRectangle%2Fbrick.jpg?alt=media&token=329b1877-56e2-4354-973f-3e2446fe01d5";
        image5 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FStar%2Fstar3.png?alt=media&token=7f8d6294-c035-4ed4-8087-e2519faaeeae";
        image6 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FRectangle%2Fpillow.png?alt=media&token=65d9a572-4ac8-4021-b9d7-68c0b7b470fd";
        image7 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FRectangle%2Feraser.jpg?alt=media&token=02962e77-ce8e-464b-88cb-0b38de131e9c";
        image8 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FSquare%2Fclock.png?alt=media&token=58720b13-f865-482d-8435-e4e60f85e0b9";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(tri1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(rect1);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(star2);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(rect2);
        Picasso.with(getContext()).load(image5).error(R.drawable.bright_kid_bg).into(star3);
        Picasso.with(getContext()).load(image6).error(R.drawable.bright_kid_bg).into(rect3);
        Picasso.with(getContext()).load(image7).error(R.drawable.bright_kid_bg).into(rect4);
        Picasso.with(getContext()).load(image8).error(R.drawable.bright_kid_bg).into(clock);

        tri1.setOnClickListener(this);
        rect1.setOnClickListener(this);
        star2.setOnClickListener(this);
        rect2.setOnClickListener(this);
        star3.setOnClickListener(this);
        rect3.setOnClickListener(this);
        rect4.setOnClickListener(this);
        clock.setOnClickListener(this);

        return view;
    }

    public void countRectangle(){
        count++;
        tvCount.setText("You Counted "+count+" Rectangles");
    }

    public void message(){
        if(count==4){
            tvCount.setText("You Counted all the Rectangles");
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.tri1:
                    message();
                    wrong.start();
                    break;
                case R.id.rect1:
                    message();
                    countRectangle();
                    correct.start();
                    rect1.setAlpha((float) 0.2);
                    rect1.setOnClickListener(null);
                    break;
                case R.id.star2:
                    message();
                    wrong.start();
                    break;
                case R.id.rect2:
                    message();
                    countRectangle();
                    correct.start();
                    rect2.setAlpha((float) 0.2);
                    rect2.setOnClickListener(null);
                    break;
                case R.id.star3:
                    message();
                    wrong.start();
                    break;
                case R.id.rect3:
                    message();
                    countRectangle();
                    correct.start();
                    rect3.setAlpha((float) 0.2);
                    rect3.setOnClickListener(null);
                    break;
                case R.id.rect4:
                    message();
                    countRectangle();
                    correct.start();
                    rect4.setAlpha((float) 0.2);
                    rect4.setOnClickListener(null);
                    break;
                case R.id.clock:
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
