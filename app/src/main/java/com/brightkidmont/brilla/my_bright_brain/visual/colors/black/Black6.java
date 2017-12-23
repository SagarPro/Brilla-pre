package com.brightkidmont.brilla.my_bright_brain.visual.colors.black;

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

import static com.brightkidmont.brilla.my_bright_brain.visual.colors.black.BlackActivity.correct;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.black.BlackActivity.count;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.black.BlackActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.black.BlackActivity.valid;
import static com.brightkidmont.brilla.my_bright_brain.visual.colors.black.BlackActivity.wrong;

public class Black6 extends Fragment{

    private View view;
    private LinearLayout llB, llL, llA, llC, llK;
    private LinearLayout llB1, llL1, llA1, llC1, llK1;
    private TextView tvB, tvL, tvA, tvC, tvK;

    public Black6(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.black6, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Arrange the spellings for black color");

        RelativeLayout flash_question = (RelativeLayout) view.findViewById(R.id.flash_question);
        flash_question.setBackgroundColor(getResources().getColor(R.color.f_black));

        ImageView speaker = (ImageView) view.findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q.start();
            }
        });

        llB = (LinearLayout) view.findViewById(R.id.llB);
        llL = (LinearLayout) view.findViewById(R.id.llL);
        llA = (LinearLayout) view.findViewById(R.id.llA);
        llC = (LinearLayout) view.findViewById(R.id.llC);
        llK = (LinearLayout) view.findViewById(R.id.llK);

        llB1 = (LinearLayout) view.findViewById(R.id.llB1);
        llL1 = (LinearLayout) view.findViewById(R.id.llL1);
        llA1 = (LinearLayout) view.findViewById(R.id.llA1);
        llC1 = (LinearLayout) view.findViewById(R.id.llC1);
        llK1 = (LinearLayout) view.findViewById(R.id.llK1);

        llB.setOnDragListener(new MyDragListener());
        llL.setOnDragListener(new MyDragListener());
        llA.setOnDragListener(new MyDragListener());
        llC.setOnDragListener(new MyDragListener());
        llK.setOnDragListener(new MyDragListener());

        tvB = (TextView) view.findViewById(R.id.tvB);
        tvL = (TextView) view.findViewById(R.id.tvL);
        tvA = (TextView) view.findViewById(R.id.tvA);
        tvC = (TextView) view.findViewById(R.id.tvC);
        tvK = (TextView) view.findViewById(R.id.tvK);

        tvB.setOnTouchListener(new MyTouchListener());
        tvL.setOnTouchListener(new MyTouchListener());
        tvA.setOnTouchListener(new MyTouchListener());
        tvC.setOnTouchListener(new MyTouchListener());
        tvK.setOnTouchListener(new MyTouchListener());

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
        llL.removeAllViews();
        llA.removeAllViews();
        llC.removeAllViews();
        llK.removeAllViews();

        llB1.removeAllViews();
        llL1.removeAllViews();
        llA1.removeAllViews();
        llC1.removeAllViews();
        llK1.removeAllViews();
    }

    public void refreshView(){
        llB1.addView(tvB);
        llL1.addView(tvL);
        llA1.addView(tvA);
        llC1.addView(tvC);
        llK1.addView(tvK);

        tvB.setOnTouchListener(new MyTouchListener());
        tvL.setOnTouchListener(new MyTouchListener());
        tvA.setOnTouchListener(new MyTouchListener());
        tvC.setOnTouchListener(new MyTouchListener());
        tvK.setOnTouchListener(new MyTouchListener());

        llB.setOnDragListener(new MyDragListener());
        llL.setOnDragListener(new MyDragListener());
        llA.setOnDragListener(new MyDragListener());
        llC.setOnDragListener(new MyDragListener());
        llK.setOnDragListener(new MyDragListener());
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
                    int i = view.getId();
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
            if(((ViewGroup)view.findViewById(tvL.getId()).getParent()).getId() == llL.getId())
                if(((ViewGroup)view.findViewById(tvA.getId()).getParent()).getId() == llA.getId())
                    if(((ViewGroup)view.findViewById(tvC.getId()).getParent()).getId() == llC.getId())
                        if(((ViewGroup)view.findViewById(tvK.getId()).getParent()).getId() == llK.getId())
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
