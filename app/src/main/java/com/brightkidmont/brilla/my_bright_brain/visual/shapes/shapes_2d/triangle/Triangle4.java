package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.triangle;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.triangle.TriangleActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.triangle.TriangleActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.triangle.TriangleActivity.wrong;

public class Triangle4 extends Fragment implements View.OnClickListener{

    private ImageView tri1;
    private ImageView tri2;
    private ImageView tri3;
    private TextView tvCount;
    private int count = 0;

    public Triangle4(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.triangle4, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Identify the triangle shaped objects");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        tvCount = (TextView) view.findViewById(R.id.tvCount);

        tri1 = (ImageView) view.findViewById(R.id.tri1);
        ImageView star1 = (ImageView) view.findViewById(R.id.star1);
        ImageView star2 = (ImageView) view.findViewById(R.id.star2);
        tri2 = (ImageView) view.findViewById(R.id.tri2);
        ImageView star3 = (ImageView) view.findViewById(R.id.star3);
        ImageView pillow = (ImageView) view.findViewById(R.id.pillow);
        tri3 = (ImageView) view.findViewById(R.id.tri3);
        ImageView clock = (ImageView) view.findViewById(R.id.clock);

        String image1, image2, image3, image4, image5, image6, image7, image8;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FTriangle%2Ftri1.png?alt=media&token=90ce4cfc-f645-40ed-9d85-37387ec0ae56";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FStar%2Fstar1.png?alt=media&token=a51e004c-d7be-4af6-bd59-547c9af59b7e";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FStar%2Fstar2.png?alt=media&token=d2f40c33-9573-48d7-8380-20d6c6470a56";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FTriangle%2Ftri2.png?alt=media&token=2c1094b7-ee78-4e1a-b478-1c9d7ef93a3c";
        image5 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FStar%2Fstar3.png?alt=media&token=7f8d6294-c035-4ed4-8087-e2519faaeeae";
        image6 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FRectangle%2Fpillow.png?alt=media&token=65d9a572-4ac8-4021-b9d7-68c0b7b470fd";
        image7 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FTriangle%2Ftri3.png?alt=media&token=d8e75e3b-7311-46fa-a4a5-3095bcebc5fe";
        image8 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FSquare%2Fclock.png?alt=media&token=58720b13-f865-482d-8435-e4e60f85e0b9";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(tri1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(star1);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(star2);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(tri2);
        Picasso.with(getContext()).load(image5).error(R.drawable.bright_kid_bg).into(star3);
        Picasso.with(getContext()).load(image6).error(R.drawable.bright_kid_bg).into(pillow);
        Picasso.with(getContext()).load(image7).error(R.drawable.bright_kid_bg).into(tri3);
        Picasso.with(getContext()).load(image8).error(R.drawable.bright_kid_bg).into(clock);


        tri1.setOnClickListener(this);
        star1.setOnClickListener(this);
        star2.setOnClickListener(this);
        tri2.setOnClickListener(this);
        star3.setOnClickListener(this);
        pillow.setOnClickListener(this);
        tri3.setOnClickListener(this);
        clock.setOnClickListener(this);

        return view;
    }

    public void countTriangle(){
        count++;
        tvCount.setText("You Counted "+count+" Triangles");
    }

    public void message(){
        if(count==3){
            tvCount.setText("You Counted "+count+" Triangles");
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.tri1:
                    message();
                    countTriangle();
                    correct.start();
                    tri1.setAlpha((float) 0.2);
                    tri1.setOnClickListener(null);
                    break;
                case R.id.star1:
                    message();
                    wrong.start();
                    break;
                case R.id.star2:
                    message();
                    wrong.start();
                    break;
                case R.id.tri2:
                    message();
                    countTriangle();
                    correct.start();
                    tri2.setAlpha((float) 0.2);
                    tri2.setOnClickListener(null);
                    break;
                case R.id.star3:
                    message();
                    wrong.start();
                    break;
                case R.id.pillow:
                    message();
                    wrong.start();
                    break;
                case R.id.tri3:
                    message();
                    countTriangle();
                    correct.start();
                    tri3.setAlpha((float) 0.2);
                    tri3.setOnClickListener(null);
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
