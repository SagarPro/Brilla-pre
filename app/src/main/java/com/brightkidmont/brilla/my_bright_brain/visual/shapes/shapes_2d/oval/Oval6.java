package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.oval;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.oval.OvalActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.oval.OvalActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.oval.OvalActivity.wrong;

public class Oval6 extends Fragment {

    private int ovalId;

    public Oval6(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.oval6, container, false);

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

        LinearLayout half_oval = (LinearLayout) view.findViewById(R.id.half_oval);
        ImageView half_oval1 = (ImageView) view.findViewById(R.id.half_oval1);
        ImageView half_oval2 = (ImageView) view.findViewById(R.id.half_oval2);
        ImageView half_rectangle = (ImageView) view.findViewById(R.id.half_rectangle);
        ImageView half_triangle = (ImageView) view.findViewById(R.id.half_triangle);

        String image1, image2, image3, image4;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FOval%2Fhalf_oval1.png?alt=media&token=7f3bd453-5259-4362-8ee1-142b997060fd";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FOval%2Fhalf_oval2.png?alt=media&token=08595b3d-7154-4c2e-8ded-22fb963607ea";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FRectangle%2Fhalf_rectangle.png?alt=media&token=a6d959c0-c059-4d81-a902-ba506928ce4e";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FTriangle%2Fhalf_triangle.png?alt=media&token=aecda77c-d7b1-472d-a028-5dfd58571ee7";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(half_oval1);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(half_oval2);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(half_rectangle);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(half_triangle);

        ovalId = half_oval2.getId();

        half_oval2.setOnTouchListener(new MyTouchListener());
        half_rectangle.setOnTouchListener(new MyTouchListener());
        half_triangle.setOnTouchListener(new MyTouchListener());

        half_oval.setOnDragListener(new MyDragListener());

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
                    if(i == ovalId) {
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
