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

public class Circle7 extends Fragment {

    private LinearLayout empty_circle, empty_square, empty_rectangle;

    public Circle7(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.circle7, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Match the shapes");

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

        empty_circle = (LinearLayout) view.findViewById(R.id.empty_circle);
        empty_square = (LinearLayout) view.findViewById(R.id.empty_square);
        empty_rectangle = (LinearLayout) view.findViewById(R.id.empty_rectangle);

        empty_circle.setOnDragListener(new MyDragListener());
        empty_rectangle.setOnDragListener(new MyDragListener());
        empty_square.setOnDragListener(new MyDragListener());

        ImageView circle = (ImageView) view.findViewById(R.id.circle);
        ImageView rectangle = (ImageView) view.findViewById(R.id.oval);
        ImageView square = (ImageView) view.findViewById(R.id.square);

        //setting images from dataBase storage
        String image1, image2, image3;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Fcircle.png?alt=media&token=23e0cf7e-fb40-4f89-b796-24b75c9d2a31";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Frectangle.png?alt=media&token=dd5b0c32-109f-46f1-866d-ee0eea4a31d6";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Fsquare.png?alt=media&token=6924460b-2faf-4e93-9fef-6d44cf90463c";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(circle);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(rectangle);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(square);

        circle.setOnTouchListener(new MyTouchListener());
        rectangle.setOnTouchListener(new MyTouchListener());
        square.setOnTouchListener(new MyTouchListener());

        return view;
    }

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int id = view.getId();
            switch (id){
                case R.id.circle:
                    empty_circle.setOnDragListener(new MyDragListener());
                    empty_rectangle.setOnDragListener(null);
                    empty_square.setOnDragListener(null);
                    break;
                case R.id.oval:
                    empty_circle.setOnDragListener(null);
                    empty_rectangle.setOnDragListener(new MyDragListener());
                    empty_square.setOnDragListener(null);
                    break;
                case R.id.square:
                    empty_circle.setOnDragListener(null);
                    empty_rectangle.setOnDragListener(null);
                    empty_square.setOnDragListener(new MyDragListener());
                    break;
            }
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
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    LinearLayout container = (LinearLayout) v;
                    container.addView(view);
                    correct.start();
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                default:
                    break;
            }
            return true;
        }

    }

}
