package com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.arrow;

import android.content.ClipData;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.arrow.ArrowActivity.count;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.arrow.ArrowActivity.q;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.arrow.ArrowActivity.valid;
import static com.brightkidmont.brilla.my_bright_brain.visual.shapes.shapes_2d.arrow.ArrowActivity.wrong;

public class Arrow3 extends Fragment {

    private View view;
    private LinearLayout llA, llR, llR1, llO, llW;
    private LinearLayout llA1, llR2, llR12, llO1, llW1;
    private TextView tvA, tvR, tvR1, tvO, tvW;
    private SwipeRefreshLayout swipeContainer;

    public Arrow3(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.arrow3, container, false);

        TextView flash_header = (TextView) view.findViewById(R.id.flash_header);
        flash_header.setText("Can you arrange the letters to spell the word for the shape given below");

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

        ImageView arrow = (ImageView) view.findViewById(R.id.arrow);
        Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/brilla-47f1f.appspot.com/o/Shapes%2Farrow.png?alt=media&token=7b7ff666-8005-44ca-bb7c-bbd86f22e9c5").error(R.drawable.bright_kid_bg).into(arrow);

        llA = (LinearLayout) view.findViewById(R.id.llA);
        llR = (LinearLayout) view.findViewById(R.id.llR);
        llR1 = (LinearLayout) view.findViewById(R.id.llR1);
        llO = (LinearLayout) view.findViewById(R.id.llO);
        llW = (LinearLayout) view.findViewById(R.id.llW);

        llA.setOnDragListener(new MyDragListener());
        llR.setOnDragListener(new MyDragListener());
        llR1.setOnDragListener(new MyDragListener());
        llO.setOnDragListener(new MyDragListener());
        llW.setOnDragListener(new MyDragListener());

        llA1 = (LinearLayout) view.findViewById(R.id.llA1);
        llR2 = (LinearLayout) view.findViewById(R.id.llR2);
        llR12 = (LinearLayout) view.findViewById(R.id.llR12);
        llO1 = (LinearLayout) view.findViewById(R.id.llO1);
        llW1 = (LinearLayout) view.findViewById(R.id.llW1);

        tvA = (TextView) view.findViewById(R.id.tvA);
        tvR = (TextView) view.findViewById(R.id.tvR);
        tvR1 = (TextView) view.findViewById(R.id.tvR1);
        tvO = (TextView) view.findViewById(R.id.tvO);
        tvW = (TextView) view.findViewById(R.id.tvW);

        tvA.setOnTouchListener(new MyTouchListener());
        tvR.setOnTouchListener(new MyTouchListener());
        tvR1.setOnTouchListener(new MyTouchListener());
        tvO.setOnTouchListener(new MyTouchListener());
        tvW.setOnTouchListener(new MyTouchListener());

        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeContainer.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        count = 0;
                        valid = "not";
                        removeView();
                        refreshView();
                        swipeContainer.setRefreshing(false);
                    }
                },800);
            }
        });

        return view;
    }

    //Removing all assigned views when refreshed
    public void removeView(){
        llA.removeAllViews();
        llR.removeAllViews();
        llR1.removeAllViews();
        llO.removeAllViews();
        llW.removeAllViews();

        llA1.removeAllViews();
        llR2.removeAllViews();
        llR12.removeAllViews();
        llO1.removeAllViews();
        llW1.removeAllViews();
    }

    public void refreshView(){
        llA1.addView(tvA);
        llR2.addView(tvR);
        llR12.addView(tvR1);
        llO1.addView(tvO);
        llW1.addView(tvW);

        tvA.setOnTouchListener(new MyTouchListener());
        tvR.setOnTouchListener(new MyTouchListener());
        tvR1.setOnTouchListener(new MyTouchListener());
        tvO.setOnTouchListener(new MyTouchListener());
        tvW.setOnTouchListener(new MyTouchListener());

        llA.setOnDragListener(new MyDragListener());
        llR.setOnDragListener(new MyDragListener());
        llR1.setOnDragListener(new MyDragListener());
        llO.setOnDragListener(new MyDragListener());
        llW.setOnDragListener(new MyDragListener());
    }

    //invoking OnTouch listener
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

    //validation to check for spelling correctness
    public void validateValues(){
        if(((ViewGroup)view.findViewById(tvA.getId()).getParent()).getId() == llA.getId())
            if(((ViewGroup)view.findViewById(tvR.getId()).getParent()).getId() == llR.getId() || ((ViewGroup)view.findViewById(tvR.getId()).getParent()).getId() == llR1.getId())
                if(((ViewGroup)view.findViewById(tvR1.getId()).getParent()).getId() == llR.getId() || ((ViewGroup)view.findViewById(tvR1.getId()).getParent()).getId() == llR1.getId())
                    if(((ViewGroup)view.findViewById(tvO.getId()).getParent()).getId() == llO.getId())
                        if(((ViewGroup)view.findViewById(tvW.getId()).getParent()).getId() == llW.getId())
                            valid = "valid";
        validate();
    }

    //displaying validation result
    public void validate(){
        if(valid.equals("valid")) {
            correct.start();
        }
        else
            wrong.start();
    }

}
