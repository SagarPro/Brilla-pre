package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.square;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.square.SquareActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.square.SquareActivity.wrong;

public class Square6 extends Fragment {

    private LinearLayout lltl, lltr, llbl, llbr;

    public Square6(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.square6, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Drag and complete the image");

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

        lltl = (LinearLayout) view.findViewById(R.id.lltl);
        lltr = (LinearLayout) view.findViewById(R.id.lltr);
        llbl = (LinearLayout) view.findViewById(R.id.llbl);
        llbr = (LinearLayout) view.findViewById(R.id.llbr);

        lltl.setOnDragListener(new MyDragListener());
        lltr.setOnDragListener(new MyDragListener());
        llbl.setOnDragListener(new MyDragListener());
        llbr.setOnDragListener(new MyDragListener());

        ImageView ivtl = (ImageView) view.findViewById(R.id.ivtl);
        ImageView ivtr = (ImageView) view.findViewById(R.id.ivtr);
        ImageView ivbl = (ImageView) view.findViewById(R.id.ivbl);
        ImageView ivbr = (ImageView) view.findViewById(R.id.ivbr);

        String image1, image2, image3, image4;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FSquare%2Fsquare6_tl.png?alt=media&token=117216d3-24ce-405c-a5b3-d046d9506846";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FSquare%2Fsquare6_tr.png?alt=media&token=a8d0258d-d5bc-479c-a2da-af14dcd48fd3";
        image3 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FSquare%2Fsquare6_bl.png?alt=media&token=1960715d-ec4e-4ea2-89ec-a37e75422565";
        image4 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FSquare%2Fsquare6_br.png?alt=media&token=8d13a4d9-e777-4500-8f14-e1b0ed60e328";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(ivtl);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(ivtr);
        Picasso.with(getContext()).load(image3).error(R.drawable.bright_kid_bg).into(ivbl);
        Picasso.with(getContext()).load(image4).error(R.drawable.bright_kid_bg).into(ivbr);

        ivtl.setOnTouchListener(new MyTouchListener());
        ivtr.setOnTouchListener(new MyTouchListener());
        ivbl.setOnTouchListener(new MyTouchListener());
        ivbr.setOnTouchListener(new MyTouchListener());

        return view;
    }

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int id = view.getId();
            switch (id){
                case R.id.ivtl:
                    lltl.setOnDragListener(new MyDragListener());
                    lltr.setOnDragListener(null);
                    llbl.setOnDragListener(null);
                    llbr.setOnDragListener(null);
                    break;
                case R.id.ivtr:
                    lltl.setOnDragListener(null);
                    lltr.setOnDragListener(new MyDragListener());
                    llbl.setOnDragListener(null);
                    llbr.setOnDragListener(null);
                    break;
                case R.id.ivbl:
                    lltl.setOnDragListener(null);
                    lltr.setOnDragListener(null);
                    llbl.setOnDragListener(new MyDragListener());
                    llbr.setOnDragListener(null);
                    break;
                case R.id.ivbr:
                    lltl.setOnDragListener(null);
                    lltr.setOnDragListener(null);
                    llbl.setOnDragListener(null);
                    llbr.setOnDragListener(new MyDragListener());
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
                    //correct.start();
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                default:
                    break;
            }
            return true;
        }

    }

}
