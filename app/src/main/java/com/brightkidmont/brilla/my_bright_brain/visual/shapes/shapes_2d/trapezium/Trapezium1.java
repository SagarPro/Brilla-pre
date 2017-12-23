package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.trapezium;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.trapezium.TrapeziumActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.trapezium.TrapeziumActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.trapezium.TrapeziumActivity.wrong;

public class Trapezium1 extends Fragment implements View.OnClickListener{

    private ImageView trap1;
    private ImageView trap2;
    private ImageView trap3;
    private ImageView trap4;
    private ImageView trapezium;
    private TextView tvCount;
    private int count = 0;

    public Trapezium1(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.trapezium1, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Count the trapezium shaped objects");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(this);

        tvCount = (TextView) view.findViewById(R.id.tvCount);

        trap1 = (ImageView) view.findViewById(R.id.trap1);
        ImageView star1 = (ImageView) view.findViewById(R.id.star1);
        trap2 = (ImageView) view.findViewById(R.id.trap2);
        ImageView pizza = (ImageView) view.findViewById(R.id.pizza);
        trap3 = (ImageView) view.findViewById(R.id.trap3);
        trap4 = (ImageView) view.findViewById(R.id.trap4);
        ImageView star4 = (ImageView) view.findViewById(R.id.star4);
        trapezium = (ImageView) view.findViewById(R.id.trapezium);

        String image1, image2, image3, image4, image5, image6, image7, image8;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FTrapezium%2Ftrap1.png?alt=media&token=3a26e079-5767-4472-83ca-71cc2d400f0e";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FStar%2Fstar1.png?alt=media&token=a51e004c-d7be-4af6-bd59-547c9af59b7e";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FTrapezium%2Ftrap2.png?alt=media&token=af7b4139-183f-494f-a5bd-d91fb99cece2";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FTrapezium%2Fpizza.png?alt=media&token=c9907548-ca63-4e3e-9fb9-e24cd86ff983";
        image5 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FTrapezium%2Ftrap3.png?alt=media&token=9fdc80ac-7133-4dc1-9f6e-f916dd615021";
        image6 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FTrapezium%2Ftrap4.png?alt=media&token=8449f921-af97-44f6-93f4-6133874e638d";
        image7 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FStar%2Fstar4.png?alt=media&token=71dd5dc5-73f3-4cbd-aa6b-2d8eb9ff740c";
        image8 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FTrapezium%2Ftrapezium.png?alt=media&token=7c934369-9fc6-45e9-89d7-d0fe0cf3da3c";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(trap1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(star1);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(trap2);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(pizza);
        Picasso.with(getContext()).load(image5).error(R.drawable.bright_kid_bg).into(trap3);
        Picasso.with(getContext()).load(image6).error(R.drawable.bright_kid_bg).into(trap4);
        Picasso.with(getContext()).load(image7).error(R.drawable.bright_kid_bg).into(star4);
        Picasso.with(getContext()).load(image8).error(R.drawable.bright_kid_bg).into(trapezium);


        trap1.setOnClickListener(this);
        star1.setOnClickListener(this);
        trap2.setOnClickListener(this);
        pizza.setOnClickListener(this);
        trap3.setOnClickListener(this);
        trap4.setOnClickListener(this);
        star4.setOnClickListener(this);
        trapezium.setOnClickListener(this);

        return view;
    }

    public void countStar(){
        count++;
        tvCount.setText("You Counted "+count+" Trapezium");
    }

    public void message(){
        if(count>5){
            tvCount.setText("You Counted all the Trapezium");
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(!q.isPlaying() && !wrong.isPlaying()) {
            switch (id) {
                case R.id.trap1:
                    message();
                    countStar();
                    correct.start();
                    trap1.setAlpha((float) 0.2);
                    trap1.setOnClickListener(null);
                    break;
                case R.id.star1:
                    message();
                    wrong.start();
                    break;
                case R.id.trap2:
                    message();
                    countStar();
                    correct.start();
                    trap2.setAlpha((float) 0.2);
                    trap2.setOnClickListener(null);
                    break;
                case R.id.pizza:
                    message();
                    wrong.start();
                    break;
                case R.id.trap3:
                    message();
                    countStar();
                    correct.start();
                    trap3.setAlpha((float) 0.2);
                    trap3.setOnClickListener(null);
                    break;
                case R.id.trap4:
                    message();
                    countStar();
                    correct.start();
                    trap4.setAlpha((float) 0.2);
                    trap4.setOnClickListener(null);
                    break;
                case R.id.star4:
                    message();
                    wrong.start();
                    break;
                case R.id.trapezium:
                    message();
                    countStar();
                    correct.start();
                    trapezium.setAlpha((float) 0.2);
                    trapezium.setOnClickListener(null);
                    break;
                case R.id.speaker:
                    q.start();
                    break;
            }
        }
    }

}
