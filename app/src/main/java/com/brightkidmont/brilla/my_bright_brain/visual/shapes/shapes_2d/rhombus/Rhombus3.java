package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rhombus;

import android.content.ClipData;
import android.media.MediaPlayer;
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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rhombus.RhombusActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rhombus.RhombusActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.rhombus.RhombusActivity.wrong;

public class Rhombus3 extends Fragment {

    private int rhombusId;

    public Rhombus3(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.rhombus3, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Identify the matching shape");

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

        ImageView circle = (ImageView) view.findViewById(R.id.circle);
        ImageView rhombus = (ImageView) view.findViewById(R.id.rhombus);
        ImageView square = (ImageView) view.findViewById(R.id.square);
        ImageView star = (ImageView) view.findViewById(R.id.star);

        String image1, image2, image3, image4;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FCircle%2Fcircle_b.png?alt=media&token=8225c28b-341b-49c3-a160-3f1e9ae80b67";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FRhombus%2Frhombus.png?alt=media&token=17e5825c-062b-480a-aa49-2400d1935809";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Fsquare.png?alt=media&token=6924460b-2faf-4e93-9fef-6d44cf90463c";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FStar%2Fstar_g.png?alt=media&token=8e589017-9411-4bf8-bd6d-83cce4a51b99";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(circle);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(rhombus);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(square);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(star);

        rhombusId = rhombus.getId();

        circle.setOnTouchListener(new MyTouchListener());
        rhombus.setOnTouchListener(new MyTouchListener());
        square.setOnTouchListener(new MyTouchListener());
        star.setOnTouchListener(new MyTouchListener());

        LinearLayout empty_rhombus = (LinearLayout) view.findViewById(R.id.empty_rhombus);
        empty_rhombus.setOnDragListener(new MyDragListener());

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
            int action = event.getAction();
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
                    if(i == rhombusId) {
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
