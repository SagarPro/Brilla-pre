package com.brightkidmont.brilla.my_bright_brain.visual.colors.black;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.black.BlackActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.black.BlackActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.black.BlackActivity.wrong;

public class Black7 extends Fragment {

    private int wigId;

    public Black7(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.black7, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Drag the black color wig on the boy's head");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.f_black));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q.start();
            }
        });

        ImageView wigbr = (ImageView) view.findViewById(R.id.wigbr);
        ImageView wigb = (ImageView) view.findViewById(R.id.wigb);
        ImageView wigy = (ImageView) view.findViewById(R.id.wigy);
        ImageView boyb = (ImageView) view.findViewById(R.id.boyb);

        String image1, image2, image3, image4;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlack%2Fboyb.png?alt=media&token=65d18560-bfc8-47a6-97e1-15c6fb21f63c";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlack%2Fwigbr.png?alt=media&token=c901e48d-1a9b-44e2-9f74-fe491e853fc5";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlack%2Fwigb.png?alt=media&token=a5f0e9cc-4aea-4660-9588-c3b87ebc4b7a";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBlack%2Fwigy.png?alt=media&token=c2a70cc1-58e3-470e-b23c-9720adf26221";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(boyb);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(wigbr);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(wigb);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(wigy);

        wigId = wigb.getId();

        wigbr.setOnTouchListener(new MyTouchListener());
        wigb.setOnTouchListener(new MyTouchListener());
        wigy.setOnTouchListener(new MyTouchListener());

        LinearLayout wig = (LinearLayout) view.findViewById(R.id.wig);
        wig.setOnDragListener(new MyDragListener());

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
                    if(i == wigId) {
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
