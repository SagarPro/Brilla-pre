package com.brightkidmont.brilla.my_bright_brain.visual.colors.orange;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.orange.OrangeActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.orange.OrangeActivity.count;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.orange.OrangeActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.orange.OrangeActivity.valid;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.orange.OrangeActivity.wrong;

public class Orange6 extends Fragment {

    private LinearLayout llO, llR, llA, llN, llG, llE;
    private LinearLayout llO1, llR1, llA1, llN1, llG1, llE1;
    private TextView tvO, tvR, tvA, tvN, tvG, tvE;
    View view;

    public Orange6(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.orange6, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Arrange the spellings for the color given below");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.fbutton_color_carrot));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q.start();
            }
        });

        llO = (LinearLayout) view.findViewById(R.id.llO);
        llR = (LinearLayout) view.findViewById(R.id.llR);
        llA = (LinearLayout) view.findViewById(R.id.llA);
        llN = (LinearLayout) view.findViewById(R.id.llN);
        llG = (LinearLayout) view.findViewById(R.id.llG);
        llE = (LinearLayout) view.findViewById(R.id.llE);

        llO.setOnDragListener(new MyDragListener());
        llR.setOnDragListener(new MyDragListener());
        llA.setOnDragListener(new MyDragListener());
        llN.setOnDragListener(new MyDragListener());
        llG.setOnDragListener(new MyDragListener());
        llE.setOnDragListener(new MyDragListener());

        llO1 = (LinearLayout) view.findViewById(R.id.llO1);
        llR1 = (LinearLayout) view.findViewById(R.id.llR1);
        llA1 = (LinearLayout) view.findViewById(R.id.llA1);
        llN1 = (LinearLayout) view.findViewById(R.id.llN1);
        llG1 = (LinearLayout) view.findViewById(R.id.llG1);
        llE1 = (LinearLayout) view.findViewById(R.id.llE1);

        tvO = (TextView) view.findViewById(R.id.tvO);
        tvR = (TextView) view.findViewById(R.id.tvR);
        tvA = (TextView) view.findViewById(R.id.tvA);
        tvN = (TextView) view.findViewById(R.id.tvN);
        tvG = (TextView) view.findViewById(R.id.tvG);
        tvE = (TextView) view.findViewById(R.id.tvE);

        tvO.setOnTouchListener(new MyTouchListener());
        tvR.setOnTouchListener(new MyTouchListener());
        tvA.setOnTouchListener(new MyTouchListener());
        tvN.setOnTouchListener(new MyTouchListener());
        tvG.setOnTouchListener(new MyTouchListener());
        tvE.setOnTouchListener(new MyTouchListener());

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
        llO.removeAllViews();
        llR.removeAllViews();
        llA.removeAllViews();
        llN.removeAllViews();
        llG.removeAllViews();
        llE.removeAllViews();

        llO1.removeAllViews();
        llR1.removeAllViews();
        llA1.removeAllViews();
        llN1.removeAllViews();
        llG1.removeAllViews();
        llE1.removeAllViews();
    }

    public void refreshView(){
        llO1.addView(tvO);
        llR1.addView(tvR);
        llA1.addView(tvA);
        llN1.addView(tvN);
        llG1.addView(tvG);
        llE1.addView(tvE);

        tvO.setOnTouchListener(new MyTouchListener());
        tvR.setOnTouchListener(new MyTouchListener());
        tvA.setOnTouchListener(new MyTouchListener());
        tvN.setOnTouchListener(new MyTouchListener());
        tvG.setOnTouchListener(new MyTouchListener());
        tvE.setOnTouchListener(new MyTouchListener());

        llO.setOnDragListener(new MyDragListener());
        llR.setOnDragListener(new MyDragListener());
        llA.setOnDragListener(new MyDragListener());
        llN.setOnDragListener(new MyDragListener());
        llG.setOnDragListener(new MyDragListener());
        llE.setOnDragListener(new MyDragListener());
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
                    if(count == 6){
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
            if(((ViewGroup)view.findViewById(tvO.getId()).getParent()).getId() == llO.getId())
                if(((ViewGroup)view.findViewById(tvR.getId()).getParent()).getId() == llR.getId())
                    if(((ViewGroup)view.findViewById(tvA.getId()).getParent()).getId() == llA.getId())
                        if(((ViewGroup)view.findViewById(tvN.getId()).getParent()).getId() == llN.getId())
                            if(((ViewGroup)view.findViewById(tvG.getId()).getParent()).getId() == llG.getId())
                                if(((ViewGroup)view.findViewById(tvE.getId()).getParent()).getId() == llE.getId())
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
