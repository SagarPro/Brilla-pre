package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.trapezium;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.trapezium.TrapeziumActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.trapezium.TrapeziumActivity.wrong;

public class Trapezium7 extends Fragment {

    private LinearLayout llt, llb;

    public Trapezium7(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.trapezium7, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Drag to complete the shape");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        llt = (LinearLayout) view.findViewById(R.id.llt);
        llb = (LinearLayout) view.findViewById(R.id.llb);

        llt.setOnDragListener(new MyDragListener());
        llb.setOnDragListener(new MyDragListener());

        ImageView ivt = (ImageView) view.findViewById(R.id.ivt);
        ImageView ivb = (ImageView) view.findViewById(R.id.ivb);

        String image1, image2;
        image1 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FTrapezium%2Ftrap_c2.png?alt=media&token=52368d21-99e8-4cc6-95e6-515d31082c2c";
        image2 = "https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2FTrapezium%2Ftrap_c1.png?alt=media&token=651e6a0b-d61c-48e6-b18c-c38cee8337f5";

        Picasso.with(getContext()).load(image1).error(R.drawable.bright_kid_bg).into(ivt);
        Picasso.with(getContext()).load(image2).error(R.drawable.bright_kid_bg).into(ivb);

        ivt.setOnTouchListener(new MyTouchListener());
        ivb.setOnTouchListener(new MyTouchListener());

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!q.isPlaying() && !wrong.isPlaying())
                    q.start();
            }
        });

        return view;
    }

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int id = view.getId();
            switch (id){
                case R.id.ivt:
                    llt.setOnDragListener(new MyDragListener());
                    llb.setOnDragListener(null);
                    break;
                case R.id.ivb:
                    llt.setOnDragListener(null);
                    llb.setOnDragListener(new MyDragListener());
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
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    LinearLayout container = (LinearLayout) v;
                    container.addView(view);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                default:
                    break;
            }
            return true;
        }

    }

}
