package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.circle;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.circle.CircleActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.circle.CircleActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.circle.CircleActivity.wrong;

public class Circle4 extends Fragment {

    private int circleId;

    public Circle4(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.circle4, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Drag the matching shape");

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

        LinearLayout half_circle = (LinearLayout) view.findViewById(R.id.half_circle);
        ImageView half_circle1 = (ImageView) view.findViewById(R.id.half_circle1);
        ImageView half_circle2 = (ImageView) view.findViewById(R.id.half_circle2);
        ImageView half_rectangle = (ImageView) view.findViewById(R.id.half_rectangle);
        ImageView half_triangle = (ImageView) view.findViewById(R.id.half_triangle);

        //fetching and setting image from datBase storage
        String image1, image2, image3, image4;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FCircle%2Fhalf_circle1.png?alt=media&token=b84dbb95-b836-485f-b234-9c15b85017d6";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FCircle%2Fhalf_circle2.png?alt=media&token=7671cb74-8b6f-4ca4-83f8-44df9b79174f";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FCircle%2Fhalf_rectangle.png?alt=media&token=a6e7c916-f86a-45c6-96ee-12447992d793";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FCircle%2Fhalf_triangle.png?alt=media&token=ed69d0bc-9a4b-4c51-973c-125eb2a35991";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(half_circle1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(half_circle2);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(half_rectangle);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(half_triangle);

        circleId = half_circle2.getId();

        half_circle2.setOnTouchListener(new MyTouchListener());
        half_rectangle.setOnTouchListener(new MyTouchListener());
        half_triangle.setOnTouchListener(new MyTouchListener());

        half_circle.setOnDragListener(new MyDragListener());

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
                    if(i == circleId) {
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
