package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.arrow;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.arrow.ArrowActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.arrow.ArrowActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.arrow.ArrowActivity.wrong;

public class Arrow2 extends Fragment {

    private int arrowId;

    public Arrow2(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.arrow2, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Identify the matching shape");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!q.isPlaying() && !wrong.isPlaying()) {
                    q.start();
                }
            }
        });

        ImageView circle = (ImageView) view.findViewById(R.id.circle);
        ImageView arrow = (ImageView) view.findViewById(R.id.arrow);
        ImageView square = (ImageView) view.findViewById(R.id.square);
        ImageView star = (ImageView) view.findViewById(R.id.star);

        //setting images from fireBase storage
        String image1, image2, image3, image4;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Fcircle.png?alt=media&token=23e0cf7e-fb40-4f89-b796-24b75c9d2a31";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Farrow.png?alt=media&token=7b7ff666-8005-44ca-bb7c-bbd86f22e9c5";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Fsquare.png?alt=media&token=6924460b-2faf-4e93-9fef-6d44cf90463c";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Fstar.png?alt=media&token=400c5703-b974-4ebf-abb3-a469fa275924";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(circle);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(arrow);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(square);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(star);

        arrowId = arrow.getId();

        circle.setOnTouchListener(new MyTouchListener());
        arrow.setOnTouchListener(new MyTouchListener());
        square.setOnTouchListener(new MyTouchListener());
        star.setOnTouchListener(new MyTouchListener());

        LinearLayout empty_arrow = (LinearLayout) view.findViewById(R.id.empty_arrow);
        empty_arrow.setOnDragListener(new MyDragListener());

        return view;
    }

    //invoking on touch listener
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

    //invoking onDrag listener
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
                    if(i == arrowId) {
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
