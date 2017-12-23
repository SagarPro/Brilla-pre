package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.star;

import android.content.ClipData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.brightkidmont.brilla.R;
import com.squareup.picasso.Picasso;

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.star.StarActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.star.StarActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.star.StarActivity.wrong;

public class Star4 extends Fragment {

    private int starId;

    public Star4(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.star4, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Join the correct shape to make a star");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!q.isPlaying() && !wrong.isPlaying())
                    q.start();
            }
        });

        LinearLayout half_star = (LinearLayout) view.findViewById(R.id.half_star);
        ImageView half_star1 = (ImageView) view.findViewById(R.id.half_star1);
        ImageView half_star2 = (ImageView) view.findViewById(R.id.half_star2);
        ImageView half_rectangle = (ImageView) view.findViewById(R.id.half_rectangle);
        ImageView half_triangle = (ImageView) view.findViewById(R.id.half_triangle);

        String image1, image2, image3, image4;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FStar%2Fhalf_star1.png?alt=media&token=9d32190f-25a4-4bac-97e0-82d0639ec97a";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FStar%2Fhalf_star2.png?alt=media&token=a103937d-5bac-4467-917c-9b45af53c830";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FCircle%2Fhalf_rectangle.png?alt=media&token=a6e7c916-f86a-45c6-96ee-12447992d793";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FCircle%2Fhalf_triangle.png?alt=media&token=ed69d0bc-9a4b-4c51-973c-125eb2a35991";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(half_star1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(half_star2);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(half_rectangle);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(half_triangle);

        starId = half_star2.getId();

        half_star2.setOnTouchListener(new MyTouchListener());
        half_rectangle.setOnTouchListener(new MyTouchListener());
        half_triangle.setOnTouchListener(new MyTouchListener());

        half_star.setOnDragListener(new MyDragListener());

        return view;
    }

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                if(!q.isPlaying() && !wrong.isPlaying()) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                    view.startDrag(data, shadowBuilder, view, 0);
                }
                return true;
            } else {
                return false;
            }
        }
    }

    class MyDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    View view = (View) event.getLocalState();
                    int i = view.getId();
                    if(i == starId) {
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        correct.start();
                    } else {
                        wrong.start();
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                default:
                    break;
            }
            return true;
        }
    }

}
