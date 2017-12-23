package com.brightkidmont.brilla.my_bright_brain.visual.colors.brown;

import android.content.ClipData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.brightkidmont.brilla.R;
import com.squareup.picasso.Picasso;

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.brown.BrownActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.brown.BrownActivity.count;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.brown.BrownActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.brown.BrownActivity.valid;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.brown.BrownActivity.wrong;

public class Brown4 extends Fragment {

    private LinearLayout llB, llR, llO, llW, llN;
    private LinearLayout llB1, llR1, llO1, llW1, llN1;
    private TextView tvB, tvR, tvO, tvW, tvN;
    View view;

    public Brown4(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.brown4, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Arrange the spelling for an Owl color");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.brown));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q.start();
            }
        });

        ImageView owl = (ImageView) view.findViewById(R.id.owl);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Colors%2FBrown%2Fowl.png?alt=media&token=f470e338-02fe-4592-acab-3f8c63625f7f").error(R.drawable.bright_kid_bg).into(owl);

        llB = (LinearLayout) view.findViewById(R.id.llB);
        llR = (LinearLayout) view.findViewById(R.id.llR);
        llO = (LinearLayout) view.findViewById(R.id.llO);
        llW = (LinearLayout) view.findViewById(R.id.llW);
        llN = (LinearLayout) view.findViewById(R.id.llN);

        llB.setOnDragListener(new MyDragListener());
        llR.setOnDragListener(new MyDragListener());
        llO.setOnDragListener(new MyDragListener());
        llW.setOnDragListener(new MyDragListener());
        llN.setOnDragListener(new MyDragListener());

        llB1 = (LinearLayout) view.findViewById(R.id.llB1);
        llR1 = (LinearLayout) view.findViewById(R.id.llR1);
        llO1 = (LinearLayout) view.findViewById(R.id.llO1);
        llW1 = (LinearLayout) view.findViewById(R.id.llW1);
        llN1 = (LinearLayout) view.findViewById(R.id.llN1);

        tvB = (TextView) view.findViewById(R.id.tvB);
        tvR = (TextView) view.findViewById(R.id.tvR);
        tvO = (TextView) view.findViewById(R.id.tvO);
        tvW = (TextView) view.findViewById(R.id.tvW);
        tvN = (TextView) view.findViewById(R.id.tvN);

        tvB.setOnTouchListener(new MyTouchListener());
        tvR.setOnTouchListener(new MyTouchListener());
        tvO.setOnTouchListener(new MyTouchListener());
        tvW.setOnTouchListener(new MyTouchListener());
        tvN.setOnTouchListener(new MyTouchListener());

        Button refresh = (Button) view.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                valid = "not";
                removeView();
                refreshView();
            }
        });

        return view;
    }

    public void removeView(){
        llB.removeAllViews();
        llR.removeAllViews();
        llO.removeAllViews();
        llW.removeAllViews();
        llN.removeAllViews();

        llB1.removeAllViews();
        llR1.removeAllViews();
        llO1.removeAllViews();
        llW1.removeAllViews();
        llN1.removeAllViews();
    }

    public void refreshView(){
        llB1.addView(tvB);
        llR1.addView(tvR);
        llO1.addView(tvO);
        llW1.addView(tvW);
        llN1.addView(tvN);

        tvB.setOnTouchListener(new MyTouchListener());
        tvR.setOnTouchListener(new MyTouchListener());
        tvO.setOnTouchListener(new MyTouchListener());
        tvW.setOnTouchListener(new MyTouchListener());
        tvN.setOnTouchListener(new MyTouchListener());

        llB.setOnDragListener(new MyDragListener());
        llR.setOnDragListener(new MyDragListener());
        llO.setOnDragListener(new MyDragListener());
        llW.setOnDragListener(new MyDragListener());
        llN.setOnDragListener(new MyDragListener());
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
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    LinearLayout container = (LinearLayout) v;
                    container.addView(view);

                    view.setOnTouchListener(null);
                    v.setOnDragListener(null);
                    count++;
                    if(count == 5){
                        validateValues();
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                default:
                    break;
            }
            return true;
        }
    }

    public void validateValues(){
        if(((ViewGroup)view.findViewById(tvB.getId()).getParent()).getId() == llB.getId())
            if(((ViewGroup)view.findViewById(tvR.getId()).getParent()).getId() == llR.getId())
                if(((ViewGroup)view.findViewById(tvO.getId()).getParent()).getId() == llO.getId())
                    if(((ViewGroup)view.findViewById(tvW.getId()).getParent()).getId() == llW.getId())
                        if(((ViewGroup)view.findViewById(tvN.getId()).getParent()).getId() == llN.getId())
                            valid = "valid";
        validate();
    }

    public void validate(){
        if(valid.equals("valid")) {
            correct.start();
            Toast.makeText(getContext(), "Correct", Toast.LENGTH_SHORT).show();
        }
        else
            wrong.start();
    }

}
